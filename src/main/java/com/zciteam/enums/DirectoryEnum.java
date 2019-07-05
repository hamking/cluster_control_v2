package com.zciteam.enums;

/**
 * 服务器本地路径枚举
 */
public enum DirectoryEnum {
    APPLICATION(0,"/tmp/yunsheng/application/"),
    IMAGE(1,"/tmp/yunsheng/image/"),
    VIDEO(2,"/tmp/yunsheng/video/"),
    ADDRESS_BOOK(3,"/tmp/yunsheng/addressBook/"),
    BOOK(4,"/tmp/yunsheng/book/"),
    OYHER(5,"/tmp/yunsheng/other/");

    private int start;
    private String startInfo;

    public int getStart() {
        return start;
    }

    public String getStartInfo() {
        return startInfo;
    }

    DirectoryEnum(int start, String startInfo) {
        this.start = start;
        this.startInfo = startInfo;
    }

    public static DirectoryEnum startOf(int index){
        for (DirectoryEnum fileType: values ()) {
            if (fileType.getStart() == index){
                return fileType;
            }
        }
        return null;
    }
}
