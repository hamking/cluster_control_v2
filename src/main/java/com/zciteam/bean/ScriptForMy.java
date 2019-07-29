package com.zciteam.bean;

public class ScriptForMy {

    private int id;
    private String scriptName;
    private String code;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "ScriptForMy{" +
                "id=" + id +
                ", scriptName='" + scriptName + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
