package com.zciteam.bean;

public class Device {
    private int id;
    private int groupId;
    private String nickname;
    private String uuid;
    private String additional;
    private int additionalType;
    private int isRun;
    private int state;
    private int additionalVariable;

    //用于设备的个性化操作
    private String individuationString;
    private int individuationInt;
    private int individuationVar1;
    private int individuationVar2;

    //功能附加字段
    private String function1;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getAdditional() {
        return additional;
    }

    public void setAdditional(String additional) {
        this.additional = additional;
    }

    public int getAdditionalType() {
        return additionalType;
    }

    public void setAdditionalType(int additionalType) {
        this.additionalType = additionalType;
    }

    public int getIsRun() {
        return isRun;
    }

    public void setIsRun(int isRun) {
        this.isRun = isRun;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getAdditionalVariable() {
        return additionalVariable;
    }

    public void setAdditionalVariable(int additionalVariable) {
        this.additionalVariable = additionalVariable;
    }

    public String getIndividuationString() {
        return individuationString;
    }

    public void setIndividuationString(String individuationString) {
        this.individuationString = individuationString;
    }

    public int getIndividuationInt() {
        return individuationInt;
    }

    public void setIndividuationInt(int individuationInt) {
        this.individuationInt = individuationInt;
    }

    public int getIndividuationVar1() {
        return individuationVar1;
    }

    public void setIndividuationVar1(int individuationVar1) {
        this.individuationVar1 = individuationVar1;
    }

    public int getIndividuationVar2() {
        return individuationVar2;
    }

    public void setIndividuationVar2(int individuationVar2) {
        this.individuationVar2 = individuationVar2;
    }

    public String getFunction1() {
        return function1;
    }

    public void setFunction1(String function1) {
        this.function1 = function1;
    }

    @Override
    public String toString() {
        return "Device{" +
                "id:" + id +
                ", groupId:" + groupId +
                ", nickname:'" + nickname + '\'' +
                ", uuid:'" + uuid + '\'' +
                ", additional:'" + additional + '\'' +
                ", additionalType:" + additionalType +
                ", isRun:" + isRun +
                ", state:" + state +
                ", additionalVariable:" + additionalVariable +
                ", individuationString:'" + individuationString + '\'' +
                ", individuationInt:" + individuationInt +
                ", individuationVar1:" + individuationVar1 +
                ", individuationVar2:" + individuationVar2 +
                ", function1:'" + function1 + '\'' +
                '}';
    }
}
