package org.liko.generator;

import java.util.List;

public class ActionParameter {
    /**
     * id
     */
    private int id;

    /**
     * param name
     */
    private String name;

    /**
     * identifier
     */
    private String identifier;

    /**
     * data type
     */
    private String dataType;

    /**
     * remark
     */
    private String remark;

    /**
     * sub param list
     */
    private List<ActionParameter> parameterList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<ActionParameter> getParameterList() {
        return parameterList;
    }

    public void setParameterList(List<ActionParameter> parameterList) {
        this.parameterList = parameterList;
    }
}
