package com.zciteam.bean;

public class ScriptType {

    private int id;
    private String scriptName;
    private String type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "ScriptType{" +
                "id=" + id +
                ", scriptName='" + scriptName + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
