package org.liko.generator;

import java.util.List;

/**
 * Project
 */
public class Project {
    private String version;
    private List<Action> actions;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }
}
