package com.zciteam.enums;

public enum ScopeEnum {
    ALL_DEVICE("-2","全部设备"),
    CURRENT_DEVICE("-1","当前设备"),
    GROUP_DEVICE(null,"组设备");

    private String state;
    private String stateInfo;

    ScopeEnum(String state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public String getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static ScopeEnum stateOf(String index){
        try{
            for(ScopeEnum scopeEnum: values ()){
                if (scopeEnum.getState().equals(index)){
                    return scopeEnum;
                }
            }
        }catch (Exception e){
            return GROUP_DEVICE;
        }
        return GROUP_DEVICE;
    }
}
