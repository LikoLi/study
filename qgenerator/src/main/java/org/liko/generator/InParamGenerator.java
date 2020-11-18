package org.liko.generator;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class InParamGenerator {
    private static final Logger logger = LoggerFactory.getLogger(InParamGenerator.class);

    /**
     * Cache import param
     */
    private static final Map<String, String> typeImportMapping = new HashMap<>();
    private static final Map<String, InParamEntity> IN_PARAM_EMTITY_MAP = new HashMap<>();

    static {
        typeImportMapping.put("List", "java.util.List");
        typeImportMapping.put("Date", "java.util.Date");
    }

    private static final String IN = "In";
    private static final String OUT = "Out";
    private static final String PARAM_SUFFIX = "Param";
    private static final String PROPERTY_SUFFIX = "Prop";

    public static void generate(InparamGeneratorInfo info) {
        //generate InParamEntity
        List<InParamEntity> entities = parseEntity(info);
        //generate file
        generateFromFtl(entities, info.isOverWrite());
    }

    private static void generateFromFtl(List<InParamEntity> entities, boolean overWrite) {

    }

    private static List<InParamEntity> parseEntity(InparamGeneratorInfo info) {
        List<Action> actions = info.getActions();
        List<InParamEntity> entities = new ArrayList<>();
        for (Action action : actions) {
            if (0 == action.getGenInParam() && 0 == action.getGenOutParam()) {
                continue;
            }
            parseEntity(action, info, entities);
        }
        return entities;
    }

    private static void parseEntity(Action action, InparamGeneratorInfo info, List<InParamEntity> entities) {
        //app&service&method
        String description = action.getDescription();

        if (null != description && description.split("&").length == 3) {
            //get controller name and method name for desc, and set package name and class name
            String[] temps = description.split("&");
            String app = temps[0];
            String service = temps[1];
            String method = temps[2];

            if (StringUtils.isBlank(service) || StringUtils.isBlank(method)) {
                logger.error("generator inparam failed<actionId={}>, please check description!", action.getId());
                return;
            }

            String targetPackage = info.getBasePackage() + "." + getPackageName(service).toLowerCase();

            String className = changeBeanCamelCase(method);

            Set<String> annotationList = new HashSet<>();
            Set<String> importList = new HashSet<>();
            if (CollectionUtils.isNotEmpty(info.getAnnotations())) {
                for (String annotation : info.getAnnotations()) {
                    String annotationClass = annotation.substring(annotation.lastIndexOf(".") + 1);
                    String annotationStr = AnnotationGeneratorFactory.getGenerator(annotationClass).generate(app, service, method, info.getVersion());
                    importList.add(annotation);
                    annotationList.add(annotationStr);
                }
            }

            // 入参Bean解析
            if (1 == action.getGenInParam()) {
                Set<String> tempImportList = new HashSet<>();
                tempImportList.addAll(importList);
                parseEntity(targetPackage, className, action.getName(), action.getRequestParameterList(), IN, false, tempImportList, annotationList, entities, info.isAddValidAnnotation());
            }

            // 出参Bean解析
            if (1 == action.getGenOutParam()) {
                //如果是web出参生成的话, 取第一层结构中data进行出参生成
                if (info.isWebOutParam()) {
                    List<ActionParameter> actionParameters = action.getResponseParameterList();
                    for (ActionParameter actionParameter : actionParameters) {
                        if ("data".equals(actionParameter.getIdentifier())) {
                            parseEntity(targetPackage, className, action.getName(), actionParameter.getParameterList(), OUT, false, importList, annotationList, entities, info.isAddValidAnnotation());
                        }
                    }
                } else {
                    parseEntity(targetPackage, className, action.getName(), action.getResponseParameterList(), OUT, false, importList, annotationList, entities, info.isAddValidAnnotation());
                }
            }
            return;
        }
        logger.error("generator inparam failed<actionId={}>, 接口说明未按标准[AppName + ControllerName + MethodName] 配置", action.getId());
        return;
    }

    /**
     * convert param to class attr
     */
    private static String parseEntity(String targetPackage, String className, String classComment, List<ActionParameter> params, String requestTypeSuffix, boolean isProperty, Set<String> importList, Set<String> annotationList, List<InParamEntity> entities, boolean addValidAnnotation) {
        if (CollectionUtils.isEmpty(params)) {
            return null;
        }

        List<Constant> constants = new ArrayList<>();
        List<Parameter> parameters = new ArrayList<>();

        for (ActionParameter param : params) {
            Parameter parameter = new Parameter();
            //field name
            String name = param.getIdentifier();
            parameter.setName(name);

            //set method need to upper case or not
            parameter.setMethodUpperCase(isMethodUpperCase(name));

            // filed comment
            String comment = param.getName();

            // rap data type
            String rapDataType = param.getDataType();
            if (StringUtils.isBlank(rapDataType)) {
                throw new RuntimeException(param.getIdentifier() + " data type can't be null.");
            }

            Type type = transType(param.getDataType());
            if (type.getName().startsWith("ARRAY")) {
                importList.add(getImportType("List"));
            }
            if (type.getName().contains("DATE")) {
                importList.add(getImportType("Date"));
            }

            if (type == Type.OBJECT || type == Type.ARRAY_OBJECT) {
                List<ActionParameter> childParams = param.getParameterList();
                if (CollectionUtils.isNotEmpty(childParams)) {
                    String childClassName = className + changeBeanCamelCase(param.getIdentifier());
                    int index = comment.indexOf("$");
                    if (index > -1) {
                        childClassName = comment.substring(index + 1);
                        comment = comment.substring(0, index);
                    }
                    String childClassComment = param.getName();
                    String packageName = parseEntity(targetPackage, childClassName, childClassComment, childParams, requestTypeSuffix, true, new HashSet<>(), null, entities, addValidAnnotation);
                    //如果packageName 不为空的话, 说明新生成的类已存在, 且和当前类不一致, 则要import 进来
                    if (null != packageName) {
                        importList.add(packageName);
                    }

                    setObjectJavaType(type, childClassName + requestTypeSuffix + PROPERTY_SUFFIX, parameter);
                } else {
                    setObjectJavaType(type, Type.OBJECT.getJavaType(), parameter);
                }
            } else {
                constants.addAll(parseComment(comment, type, name));
                parameter.setType(type.getJavaType());
            }

            parameter.setComment(comment);
            // 增加校验注解
            if (addValidAnnotation) {
                ValidAnnotationGenerator.addValidAnnotation(parameter, type, importList, param.getRemark());
            }
            parameters.add(parameter);
        }

        InParamEntity entity = new InParamEntity();
        String paramTypeSuffix = isProperty ? PROPERTY_SUFFIX : PARAM_SUFFIX;
        String clsName = className + requestTypeSuffix + paramTypeSuffix;
        entity.setTargetPackage(targetPackage);
        entity.setClassName(clsName);
        entity.setClassComment(classComment);
        entity.setImportList(getSortList(importList));
        entity.setParams(parameters);
        entity.setAnnotationList(getSortList(annotationList));
        entity.setConstants(constants);

        //判断是否存在相同的入参, 存在则使用缓存中的入参
        InParamEntity cachaEmtity = IN_PARAM_EMTITY_MAP.get(clsName);
        if (null != cachaEmtity && entity.equals(cachaEmtity)) {
            String packageName = cachaEmtity.getTargetPackage();
            if (!packageName.equals(targetPackage)) {
                return packageName + "." + clsName;
            }
            return null;
        }

        IN_PARAM_EMTITY_MAP.put(clsName, entity);
        entities.add(entity);

        return null;
    }

    private static List<String> getSortList(Set<String> set) {
        if (CollectionUtils.isEmpty(set)) {
            if (CollectionUtils.isEmpty(set)) {
                return Collections.emptyList();
            }
        }
        return set.stream().sorted().collect(Collectors.toList());
    }

    /**
     * 获取备注中的常量
     */
    private static List<Constant> parseComment(String comment, Type type, String fieldName) {
        if (StringUtils.isBlank(comment)) {
            return Collections.emptyList();
        }

        List<Constant> constantList = new ArrayList<>();
        int pos = comment.indexOf("<>");
        if (pos != -1) {
            String[] constants = comment.substring(pos + 2).split("&");

            for (String c : constants) {
                String[] f = c.split("=");
                Constant constant = new Constant();
                constant.setName(f[0].trim());
                constant.setValue(f[1].trim());
                if (Type.STRING.equals(type) && !constant.getValue().startsWith("\"")) {
                    // 如果是字符串, 常量值前后要有双引号
                    constant.setValue("\"" + constant.getValue() + "\"");
                } else if (Type.LONG.equals(type)) {
                    constant.setValue(constant.getValue() + "L");
                }

                constant.setComment(f[2]);

                constant.setType(type.getJavaType());
                constant.setFieldName(fieldName);
                constantList.add(constant);
            }
        }
        return constantList;
    }

    private static void setObjectJavaType(Type type, String javaType, Parameter parameter) {
        if (type == Type.OBJECT) {
            parameter.setType(javaType);
        } else {
            parameter.setType(String.format(type.getJavaType(), javaType));
        }
    }

    private static String getImportType(String type) {
        return typeImportMapping.get(type);
    }

    private static Type transType(String rapDataType) {
        Type type = Type.getType(rapDataType);
        if (null == type) {
            throw new RuntimeException("rap data type not exists : " + rapDataType);
        }
        return type;
    }

    private static boolean isMethodUpperCase(String name) {
        boolean toUpperCase = true;
        // 只有当参数名长度大于2, 且第二个字符为大写才不需要转换
        if (name.length() > 1 && Character.isUpperCase(name.charAt(1))) {
            toUpperCase = false;
        }
        return toUpperCase;
    }

    private static String getPackageName(String service) {
        if (service.endsWith("Controller")) {
            return service.substring(0, service.indexOf("Controller"));
        } else if (service.endsWith("Service")) {
            return service.substring(0, service.indexOf("Service"));
        }
        return service;
    }

    private static String changeBeanCamelCase(String name) {
        if (StringUtils.isNotBlank(name)) {
            return name.substring(0, 1).toUpperCase() + name.substring(1);
        }
        return null;
    }

    /**
     * data type enum
     */
    public enum Type {
        SHORT("short", "Short"),
        INTEGER("integer", "Integer"),
        LONG("long", "Long"),
        NUMBER("number", "Double"),
        STRING("string", "String"),
        BOOLEAN("boolean", "Boolean"),
        DATE("date", "Date"),
        OBJECT("object", "Object"),
        ARRAY_INTEGER("array<integer>", "List<Integer>"),
        ARRAY_LONG("array<long>", "List<Long>"),
        ARRAY_NUMBER("array<number>", "List<Double>"),
        ARRAY_STRING("array<string>", "List<String>"),
        ARRAY_DATE("array<date>", "List<Date>"),
        ARRAY_OBJECT("array<object>", "List<%s>"),
        ARRAY("array", "List");

        private String rapType;
        private String javaType;

        Type(String rapType, String javaType) {
            this.rapType = rapType;
            this.javaType = javaType;
        }

        public static Type getType(String rapType) {
            for (Type type : Type.values()) {
                if (type.getRapType().equals(rapType)) {
                    return type;
                }
            }
            return null;
        }

        public String getName() {
            return this.name();
        }

        public String getRapType() {
            return rapType;
        }

        public String getJavaType() {
            return javaType;
        }
    }
}
