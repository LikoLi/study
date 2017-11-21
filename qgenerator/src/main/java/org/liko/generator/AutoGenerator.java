package org.liko.generator;

import com.alibaba.fastjson.JSON;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class AutoGenerator {

    private static final Logger logger = LoggerFactory.getLogger(AutoGenerator.class);

    //TODO: move to properties in next version
    private static final String RAP_MODEL_URI = "/workspace/getActionParam.do?id=";

    public static void generate() {
        // load properties
        InputStream inputStream = ClassLoader.getSystemResourceAsStream("inparam.properties");

        if (null == inputStream) {
            throw new RuntimeException("failed, inparam.properties not found!");
        } else {
            Properties properties = new Properties();

            try {
                properties.load(inputStream);

                String rapUrl = properties.getProperty("rap.url");
                if (StringUtils.isBlank(rapUrl)) {
                    throw new RuntimeException("failed, rap.url not in inparam.properties!");
                }

                String projectIdStr = properties.getProperty("rap.projectId");
                String[] projectIds = null;
                if (StringUtils.isBlank(projectIdStr)) {
                    throw new RuntimeException("failed, rap.projectId not in inparam.properties!");
                } else {
                    projectIds = projectIdStr.split(",");
                }

                String actionIdStr = properties.getProperty("rap.actionId");
                String[] actionIds = null;
                if (StringUtils.isNotBlank(actionIdStr)) {
                    actionIds = actionIdStr.split(",");
                }

                String basePackage = properties.getProperty("basePackage");
                if (StringUtils.isBlank(basePackage)) {
                    throw new RuntimeException("failed, basePackage not in inparam.properties!");
                }
                InparamGeneratorInfo info = new InparamGeneratorInfo();

                info.setBasePackage(basePackage);

                boolean overWrite = !"false".equalsIgnoreCase(properties.getProperty("overWrite"));
                info.setOverWrite(overWrite);

                boolean webOutParam = "true".equalsIgnoreCase(properties.getProperty("webOutParam"));
                info.setWebOutParam(webOutParam);

                boolean addValidAnnotation = "true".equalsIgnoreCase(properties.getProperty("addValidAnnotation"));
                info.setAddValidAnnotation(addValidAnnotation);

                boolean addAnnotation = "true".equalsIgnoreCase(properties.getProperty("addAnnotation"));
                if (addAnnotation) {
                    String annotationStr = properties.getProperty("annotations");
                    if (StringUtils.isNotBlank(annotationStr)) {
                        String[] annotations = annotationStr.split(",");
                        for(String annotation : annotations) {
                            if (!annotation.matches("(\\w+.)+\\w+")) {
                                throw new RuntimeException(String.format("user-defiend annotation %s error, format should be: xxx.xxx.....Annotation", annotation));
                            }
                        }
                        info.setAnnotations(Arrays.asList(annotations));
                    }
                }

                for (String projectId : projectIds) {
                    logger.debug("Auto generator start, project id : {}", projectId);
                    String requestUrl = rapUrl + RAP_MODEL_URI + projectId.trim();
                    String rapData = HttpRequestUtil.request(requestUrl);

                    if (StringUtils.isNotBlank(rapData)) {
                        Project project = JSON.parseObject(rapData, Project.class);
                        List<Action> actions = project.getActions();
                        if (CollectionUtils.isEmpty(actions)) {
                            throw new RuntimeException("failed, no action found.");
                        } else {
                            //filter action id
                            if (null != actionIds) {
                                final List<String> actionIdList = Arrays.asList(actionIds);
                                actions = actions.stream().filter(action -> actionIdList.contains(String.valueOf(action.getId()))).distinct().collect(Collectors.toList());
                                if (CollectionUtils.isEmpty(actions)) {
                                    continue;
                                } else {
                                    info.setActions(actions);
                                }
                            }
                        }

                        String version = project.getVersion();
                        if (StringUtils.isBlank(version)) {
                            throw new RuntimeException("failed, version not found.");
                        } else {
                            info.setVersion(version);
                        }

                        InParamGenerator.generate(info);
                    }

                }








            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
