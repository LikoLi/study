package org.liko.generator;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.security.util.Length;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Valid Annotation
 */
public class ValidAnnotationGenerator {
    private static final Logger logger = LoggerFactory.getLogger(ValidAnnotationGenerator.class);

    /**
     * 增加校验注解
     */
    public static void addValidAnnotation(Parameter parameter, InParamGenerator.Type type, Set<String> importList, String remark) {
        if (StringUtils.isBlank(remark)) {
            return;
        }

        ValidRule validRule = null;

        String name = parameter.getName();

        JSONObject jsonObject = JSONObject.parseObject(remark);
        String validRuleStr = jsonObject.getString("rule");
        if (StringUtils.isBlank(validRuleStr)) {
            return;
        }
        String tempName = jsonObject.getString("name");
        if (StringUtils.isNotBlank(tempName)) {
            name = tempName + "(" + name + ")";
        }
        validRule = JSONObject.parseObject(validRuleStr, ValidRule.class);

        List<String> annotations = new ArrayList<>();
        parameter.setAnnotations(annotations);

        if (Objects.equals(Boolean.TRUE, validRule.getRequired())) {
            addRequiredAnnotation(name, type, importList, annotations);
        }

        if (null != validRule.getMinimum()) {
            addNumberAnnotation(name, validRule, type, importList, annotations, true);
        }

        if (null != validRule.getMaximum()) {
            addNumberAnnotation(name, validRule, type, importList, annotations, false);
        }

        addStringLengthAnnotation(name, validRule, type, importList, annotations);

        if (null != validRule.getPattern()) {
            addPatternAnnotation(name, validRule, type, importList, annotations);
        }
    }

    /**
     * 增加正则表达式校验
     *
     * @param name
     * @param validRule
     * @param type
     * @param importList
     * @param annotations
     */
    private static void addPatternAnnotation(String name, ValidRule validRule, InParamGenerator.Type type, Set<String> importList, List<String> annotations) {
        if (!Objects.equals(InParamGenerator.Type.STRING, type)) {
            throw new RuntimeException(name + "类型不是字符串，不能设置正则格式校验");
        }
        Class annotation = Pattern.class;
        String pattern = validRule.getPattern().replaceAll("\\\\", "\\\\\\\\");
        importList.add(getImportString(annotation));
        StringBuilder annotationStr = new StringBuilder("@");
        annotationStr.append(getAnnotationName(annotation));
        annotationStr.append("(regexp = \"");
        annotationStr.append(pattern);
        annotationStr.append("\", message = \"【");
        annotationStr.append(name);
        annotationStr.append("】格式错误，格式为【");
        annotationStr.append(pattern);
        annotationStr.append("】\")");

        annotations.add(annotationStr.toString());
    }

    /**
     * 增加字符串长度注解
     *
     * @param name
     * @param validRule
     * @param type
     * @param importList
     * @param annotations
     */
    private static void addStringLengthAnnotation(String name, ValidRule validRule, InParamGenerator.Type type, Set<String> importList, List<String> annotations) {
        if (null == validRule.getMinLength() && null == validRule.getMaxLength()) {
            return;
        }
        if (!Objects.equals(InParamGenerator.Type.STRING, type)) {
            throw new RuntimeException(name + "类型不是字符串，不能设置最大最小长度");
        }
        boolean min = null != validRule.getMinLength();
        boolean max = null != validRule.getMaxLength();
        Class annotation = Length.class;
        importList.add(getImportString(annotation));
        StringBuilder annotationStr = new StringBuilder("@");
        annotationStr.append(getAnnotationName(annotation));
        annotationStr.append("(");
        if (min) {
            annotationStr.append("min = ");
            annotationStr.append(validRule.getMinLength());
            if (max) {
                annotationStr.append(", ");
            }
        }
        if (max) {
            annotationStr.append("max = ");
            annotationStr.append(validRule.getMaxLength());
        }
        annotationStr.append(", message = \"【");
        annotationStr.append(name);
        annotationStr.append("】");
        if (min) {
            annotationStr.append("最小长度为【");
            annotationStr.append(validRule.getMinLength());
            annotationStr.append("】");
            if (max) {
                annotationStr.append(", ");
            }
        }
        if (max) {
            annotationStr.append("最大长度为【");
            annotationStr.append(validRule.getMaxLength());
            annotationStr.append("】");
        }
        annotationStr.append("\")");

        annotations.add(annotationStr.toString());
    }

    /**
     * 增加最小最大值注解
     *
     * @param name
     * @param validRule
     * @param type
     * @param importList
     * @param annotations
     * @param min
     */
    private static void addNumberAnnotation(String name, ValidRule validRule, InParamGenerator.Type type, Set<String> importList, List<String> annotations, boolean min) {
        if (notIn(type, InParamGenerator.Type.SHORT, InParamGenerator.Type.INTEGER, InParamGenerator.Type.LONG, InParamGenerator.Type.NUMBER)) {
            throw new RuntimeException(name + "类型不是数值型，不能设置最大最小值");
        }
        boolean isDouble = Objects.equals(type, InParamGenerator.Type.NUMBER);
        Object number;
        Class annotation;
        // 如果是String，则增加@NotBalank注解，如果是数组，则增加NotEmpty注解，否则增加@NotNull注解
        if (min) {
            if (isDouble) {
                annotation = DecimalMin.class;
                number = validRule.getMinimum();
            } else {
                annotation = Min.class;
                number = validRule.getMinimum().intValue();
            }
        } else {
            if (isDouble) {
                annotation = DecimalMax.class;
                number = validRule.getMaximum();
            } else {
                annotation = Max.class;
                number = validRule.getMaximum().intValue();
            }
        }
        importList.add(getImportString(annotation));
        StringBuilder annotationStr = new StringBuilder("@");
        annotationStr.append(getAnnotationName(annotation));
        annotationStr.append("(value = ");
        annotationStr.append(isDouble ? "\"" : "");
        annotationStr.append(number);
        annotationStr.append(isDouble ? "\"" : "");
        annotationStr.append(", message = \"【");
        annotationStr.append(name);
        annotationStr.append("】");
        annotationStr.append(min ? "最小" : "最大");
        annotationStr.append("为【");
        annotationStr.append(number);
        annotationStr.append("】\")");

        annotations.add(annotationStr.toString());
    }

    private static boolean notIn(InParamGenerator.Type val, InParamGenerator.Type... vals) {
        return !in(val, vals);
    }

    private static boolean in(InParamGenerator.Type val, InParamGenerator.Type... vals) {
        for (Object valTemp : vals) {
            if (Objects.equals(val, valTemp)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 增加是否为空的注解
     *
     * @param name
     * @param type
     * @param importList
     * @param annotations
     */
    private static void addRequiredAnnotation(String name, InParamGenerator.Type type, Set<String> importList, List<String> annotations) {
        Class annotation;
        // 如果是String，则增加@NotBalank注解，如果是数组，则增加NotEmpty注解，否则增加@NotNull注解
        if (Objects.equals(InParamGenerator.Type.STRING, type)) {
            annotation = NotBlank.class;
        } else if (type.getRapType().startsWith("array")) {
            annotation = NotEmpty.class;
        } else {
            annotation = NotNull.class;
        }
        importList.add(getImportString(annotation));
        StringBuilder annotationStr = new StringBuilder("@");
        annotationStr.append(getAnnotationName(annotation));
        annotationStr.append("(message = \"【");
        annotationStr.append(name);
        annotationStr.append("】不能为空\")");
        annotations.add(annotationStr.toString());
    }

    private static String getImportString(Class annotation) {
        return annotation.getName();
    }

    private static String getAnnotationName(Class annotation) {
        return annotation.getSimpleName();
    }
}
