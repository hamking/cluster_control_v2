package com.zciteam.dto;

public class ScriptResult<T> {

    private T scriptList;

    public ScriptResult(T scriptList) {
        this.scriptList = scriptList;
    }

    public T getScriptList() {
        return scriptList;
    }

    public void setScriptList(T scriptList) {
        this.scriptList = scriptList;
    }
}