package org.liko.generator;

import javax.swing.*;
import java.util.List;

public class InparamGeneratorInfo {

    /**
     * interface
     */
    List<Action> actions;

    /**
     * basePackage
     */
    String basePackage;

    /**
     * interface version
     */
    String version;

    /**
     * user-defined annotation
     */
    List<String> annotations;

    /**
     * over write
     */
    boolean overWrite;

    /**
     * web out param
     */
    boolean webOutParam;

    /**
     * valid annotation
     */
    boolean addValidAnnotation;

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<String> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<String> annotations) {
        this.annotations = annotations;
    }

    public boolean isOverWrite() {
        return overWrite;
    }

    public void setOverWrite(boolean overWrite) {
        this.overWrite = overWrite;
    }

    public boolean isWebOutParam() {
        return webOutParam;
    }

    public void setWebOutParam(boolean webOutParam) {
        this.webOutParam = webOutParam;
    }

    public boolean isAddValidAnnotation() {
        return addValidAnnotation;
    }

    public void setAddValidAnnotation(boolean addValidAnnotation) {
        this.addValidAnnotation = addValidAnnotation;
    }
}
