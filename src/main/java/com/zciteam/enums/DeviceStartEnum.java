package com.zciteam.enums;

public enum DeviceStartEnum {
    OFFLINE(0,"设备离线"),
    ONLINE(1,"设备在线"),
    UNKNOWN(-1,"设备未知状态");

    private int state;
    private String stateInfo;

    DeviceStartEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static DeviceStartEnum stateOf(int index){
        for (DeviceStartEnum startEnum: values()) {
            if (startEnum.getState() == index){
                return startEnum;
            }
        }
        return null;
    }
}
