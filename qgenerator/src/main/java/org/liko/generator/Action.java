package org.liko.generator;

import java.util.List;
import java.util.Objects;

/**
 * Action ==> Class
 */
public class Action {

    /**
     * id
     */
    private int id;

    /**
     * interface name
     */
    private String name;

    /**
     * interface desc
     */
    private String description;

    /**
     * request type
     */
    private String requestType;

    /**
     * request url
     */
    private String requestUrl;

    /**
     * request param list
     */
    private List<ActionParameter> requestParameterList;

    /**
     * response param list
     */
    private List<ActionParameter> responseParameterList;

    /**
     * remark
     */
    private String remarks;

    /**
     * is disable
     */
    private int disableAction;

    /**
     * is generate in param
     */
    private int genInParam;

    /**
     * is generate out param
     */
    private int genOutParam;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public List<ActionParameter> getRequestParameterList() {
        return requestParameterList;
    }

    public void setRequestParameterList(List<ActionParameter> requestParameterList) {
        this.requestParameterList = requestParameterList;
    }

    public List<ActionParameter> getResponseParameterList() {
        return responseParameterList;
    }

    public void setResponseParameterList(List<ActionParameter> responseParameterList) {
        this.responseParameterList = responseParameterList;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getDisableAction() {
        return disableAction;
    }

    public void setDisableAction(int disableAction) {
        this.disableAction = disableAction;
    }

    public int getGenInParam() {
        return genInParam;
    }

    public void setGenInParam(int genInParam) {
        this.genInParam = genInParam;
    }

    public int getGenOutParam() {
        return genOutParam;
    }

    public void setGenOutParam(int genOutParam) {
        this.genOutParam = genOutParam;
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof Action) && (this.id == ((Action)o).getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id);
    }
}
