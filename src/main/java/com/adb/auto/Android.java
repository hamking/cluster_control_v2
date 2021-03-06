package com.adb.auto;

import com.adb.autoComponent.Execute;
import com.adb.util.ScriptUtils;

import java.io.IOException;
import java.util.Map;

public class Android {

    private int x;
    private int y;
    private int width;
    private int highly;

    private int dx;
    private int dy;

    private String deviceUUID;
    private Map<String, String> noteMap;

    /**
     * 获取节点的所有信息
     * @param bound bound
     * @param noteMap 节点的map
     * @param deviceUUID deviceUUID
     */
    public Android(String bound, Map noteMap, String deviceUUID) {

        String s1  = bound.replace("][", ",");
        String s2  = s1.replace("[", "");
        String s3  = s2.replace("]","");
        String s[] = s3.split(",");
        x          = Integer.parseInt(s[0]);
        y          = Integer.parseInt(s[1]);
        width      = Integer.parseInt(s[2]);
        highly     = Integer.parseInt(s[3]);

        dx = x + 30;
        dy = y;
        this.deviceUUID = deviceUUID;
        this.noteMap = noteMap;
    }

    public Map<String, String> getNoteMap() {
        return noteMap;
    }

    /**
     * 点击
     */
    public void click()  {

        new Execute().shell(deviceUUID, "input tap" + " " + dx + " " + dy);
    }

    /**
     * 点击
     * @param offsetX x轴偏移量
     * @param offsetY y轴偏移量
     */
    public void click(int offsetX, int offsetY){

        new Execute().shell(deviceUUID, "input tap" + " " + (dx + offsetX) + " " + (dy + offsetY));
    }

    /**
     * 点击
     * @param offsetX x轴偏移量
     */
    public void clickOffsetX(int offsetX){

        new Execute().shell(deviceUUID, "input tap" + " " + (dx + offsetX) + " " + dy);
    }

    /**
     * 点击
     * @param offsetY y轴偏移量
     */
    public void clickOffsetY(int offsetY)  {

        new Execute().shell(deviceUUID, "input tap" + " " + dx + " " + (dy + offsetY));
    }

    /**
     * 发送文字
     * @param str str
     */
    public void sendKeys(String str) {
        click();
        new Execute().shell(deviceUUID, "am broadcast -a ADB_INPUT_TEXT --es msg " + str);
    }

    /**
     * 随机发送文字
     * @param str str
     */
    public void randomSendKeys(int num, String str) {
        if (str.contains ("$")) {
            String[] strings = str.split ("$");
            //点击文本框并输入文字
            int s = new ScriptUtils ().random ("随机私信", 0, strings.length);
            for (int i = 0; i < num; i++) {
                sendKeys(strings[s]);
            }
        }
    }
}
