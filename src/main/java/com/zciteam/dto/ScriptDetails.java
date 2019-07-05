package com.zciteam.dto;

import com.zciteam.bean.Script;

import java.util.List;

public class ScriptDetails{

    private String scriptName;
    private String type;
    private List<Script> scriptDetails;

    public ScriptDetails(String scriptName, String type, List<Script> scriptDetails) {
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

    public List<Script> getscriptDetails() {
        return scriptDetails;
    }

    public void setscriptDetails(List<Script> scriptDetails) {
        this.scriptDetails = scriptDetails;
    }
}
