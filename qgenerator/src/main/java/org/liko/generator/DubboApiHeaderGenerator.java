package org.liko.generator;

public class DubboApiHeaderGenerator implements AnnotationGenerator {
    @Override
    public String generate(String... params) {
        if (null == params || params.length != 4) {
            throw new RuntimeException(String.format("Get annotation %s failed.", AnnotationGeneratorFactory.DUBBO_API_HEADER));
        }
        return String.format("@%s(app = \"%s\", service = \"%s\", method = \"%s\", version = \"%s\")", AnnotationGeneratorFactory.DUBBO_API_HEADER, params[0], params[1], params[2], params[3]);
    }
}
