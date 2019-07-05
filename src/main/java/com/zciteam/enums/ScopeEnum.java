package com.zciteam.enums;

public enum ScopeEnum {
    ALL_DEVICE(-2,"全部设备"),
    CURRENT_DEVICE(-1,"当前设备"),
    GROUP_DEVICE(null,"组设备");

    private Integer state;
    private String stateInfo;

    ScopeEnum(Integer state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public Integer getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static ScopeEnum stateOf(Integer index){
        for(ScopeEnum scopeEnum: values ()){
            if (scopeEnum.getState() == index){
                return scopeEnum;
            }
        }
        return GROUP_DEVICE;
    }
}
