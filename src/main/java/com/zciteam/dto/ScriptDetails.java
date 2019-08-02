package com.zciteam.dto;

import com.zciteam.bean.Script;

import java.util.List;

public class ScriptDetails<T>{

    private String scriptName;
    private String type;
    private List<T> scriptDetails;

    public ScriptDetails(String scriptName, String type, List<T> scriptDetails) {
        this.scriptName = scriptName;
        this.type = type;
        this.scriptDetails = scriptDetails;
    }

    public String getScriptName() {
        return scriptName;
    }

    public void setScriptName(String scriptName) {
        this.scriptName = scriptName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<T> getscriptDetails() {
        return scriptDetails;
    }

    public void setscriptDetails(List<T> scriptDetails) {
        this.scriptDetails = scriptDetails;
    }
}
