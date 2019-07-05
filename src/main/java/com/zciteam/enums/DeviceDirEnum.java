package com.zciteam.enums;

/**
 * 远程手机端目录枚举
 */
public enum DeviceDirEnum {

    VIDEO(0,"/sdcard/DCIM/Video/"),
    IMAGE(1,"/sdcard/DCIM/"),
    JAR_UI(2,"/data/local/tmp/local/tmp/"),
    JAR_HELPER(3,"/data/local/tmp/"),
    ZIP(4,"/sdcard/");

    private int start;
    private String startInfo;

    public int getStart() {
        return start;
    }

    public String getStartInfo() {
        return startInfo;
    }

    DeviceDirEnum(int start, String startInfo) {
        this.start = start;
        this.startInfo = startInfo;
    }

    public static DeviceDirEnum startOf(int index){

        for(DeviceDirEnum codeEnum: values()){
            if (codeEnum.getStart() == index){
                return codeEnum;
            }
        }
        return null;
    }
}
