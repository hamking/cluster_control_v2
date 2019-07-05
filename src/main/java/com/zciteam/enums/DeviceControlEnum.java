package com.zciteam.enums;

public enum DeviceControlEnum {
    UNKNOWN(-1,"设备未知状态"),
    LOCK(0,"锁定屏幕"),
    UNLOCK(1,"解锁屏幕"),
    VOLUMEUP(2,"设备音量加"),
    VOLUMEDOWN(3,"设备音量减"),
    MUTE(4,"静音");

    private int state;
    private String StateInfo;

    DeviceControlEnum(int state, String stateInfo) {
        this.state = state;
        StateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return StateInfo;
    }

    public static DeviceControlEnum stateOf(int index){
        for(DeviceControlEnum controlEnum: values()){
            if (controlEnum.getState() == index){
                return controlEnum;
            }
        }
        return null;
    }
}
