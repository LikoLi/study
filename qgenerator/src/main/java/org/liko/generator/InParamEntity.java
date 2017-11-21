package org.liko.generator;

import java.util.List;

public class InParamEntity {
    /**
     * package name
     */
    private String targetPackage;

    /**
     * class name
     */
    private String className;

    /**
     * class comment
     */
    private String classComment;

    /**
     * import
     */
    private List<String> importList;

    /**
     * user-defined annotation
     */
    private List<String> annotationList;

    /**
     * param list
     */
    private List<Parameter> params;

    /**
     * constant list
     */
    private List<Constant> constants;

    /**
     * content
     */
    private String content;

    public String getTargetPackage() {
        return targetPackage;
    }

    public void setTargetPackage(String targetPackage) {
        this.targetPackage = targetPackage;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassComment() {
        return classComment;
    }

    public void setClassComment(String classComment) {
        this.classComment = classComment;
    }

    public List<String> getImportList() {
        return importList;
    }

    public void setImportList(List<String> importList) {
        this.importList = importList;
    }

    public List<String> getAnnotationList() {
        return annotationList;
    }

    public void setAnnotationList(List<String> annotationList) {
        this.annotationList = annotationList;
    }

    public List<Parameter> getParams() {
        return params;
    }

    public void setParams(List<Parameter> params) {
        this.params = params;
    }

    public List<Constant> getConstants() {
        return constants;
    }

    public void setConstants(List<Constant> constants) {
        this.constants = constants;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

