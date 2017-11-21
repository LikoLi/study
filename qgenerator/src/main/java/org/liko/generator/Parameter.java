package org.liko.generator;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Objects;

public class Parameter {
    /**
     * attr name
     */
    private String name;

    /**
     * attr type
     */
    private String type;

    /**
     * comment
     */
    private String comment;

    /**
     * required
     */
    private boolean required;

    /**
     * index
     */
    private int index;

    /**
     * Capitalize the first letter
     */
    private boolean methodUpperCase;

    /**
     * annotation
     */
    private List<String> annotations;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isMethodUpperCase() {
        return methodUpperCase;
    }

    public void setMethodUpperCase(boolean methodUpperCase) {
        this.methodUpperCase = methodUpperCase;
    }

    public List<String> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<String> annotations) {
        this.annotations = annotations;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Parameter) {
            Parameter parameter = (Parameter) o;
            return StringUtils.equals(this.name, parameter.getName()) && StringUtils.equals(this.type, parameter.getType());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.name + this.type);
    }
}
