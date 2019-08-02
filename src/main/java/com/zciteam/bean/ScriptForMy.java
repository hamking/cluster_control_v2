package com.zciteam.bean;

public class ScriptForMy {

    private int suid;
    private String scriptName;
    private String code;
    private String workxml;

    public int getSuid() {
        return suid;
    }

    public void setSuid(int suid) {
        this.suid = suid;
    }

    public String getWorkxml() {
        return workxml;
    }

    public void setWorkxml(String workxml) {
        this.workxml = workxml;
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
                "suid=" + suid +
                ", scriptName='" + scriptName + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
