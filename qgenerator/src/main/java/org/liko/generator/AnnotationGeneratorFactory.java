package org.liko.generator;

public class AnnotationGeneratorFactory {
    public static final String DUBBO_API_HEADER = "DubboApiHeader";

    public static AnnotationGenerator getGenerator(String annotation) {
        switch (annotation) {
            case DUBBO_API_HEADER:
                return new DubboApiHeaderGenerator();
            default:
                throw new RuntimeException("Get annotation generator failed : " + annotation);
        }
    }
}
