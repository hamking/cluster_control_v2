package com.zciteam.enums;

public enum KeyboardEnum {
    ADB_KEYBOARD(0,"切换为ADB键盘"),
    SYSTEM_KEYBOARD(1,"切换为系统键盘");

    private int state;
    private String stateInfo;

    KeyboardEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static KeyboardEnum stateOf(int index){
        for(KeyboardEnum keyboardEnum: values ()){
            if(keyboardEnum.getState() == index){
                return keyboardEnum;
            }
        }
        return null;
    }
}
