package com.scriptEditor.control;

import com.adb.auto.Auto;
import com.adb.auto.Android;
import com.zciteam.enums.KeyboardEnum;
import com.zciteam.web.WebSocketDeviceLog;
import org.dom4j.DocumentException;

import java.util.List;

public class AdbControl {

    private Auto auto = null;
    private WebSocketDeviceLog log = new WebSocketDeviceLog();


    public void start(String uuid,String packageName){
        auto = new Auto(uuid,packageName);
    }

    public void back(){
        auto.back();
    }

    public void home(){
        auto.home();
    }

    public void switchKey(int index){
        auto.switchKeyboardforSystem(KeyboardEnum.stateOf(index));
    }

    public void swipeDown(){
        auto.swipeDown ();
    }

    public void swipeUp(){
        auto.swipeUp();
    }

    public void lock(){
        auto.lock();
    }

    public void unLock(){
        auto.unLock();
    }

    /**
     * 根据xpatch查找控件数组
     * @param xpath xpath
     */
    public List<Android> findByXpatchs(String xpath){

        try {
            return auto.findsByXpatch(xpath,true);
        } catch (DocumentException e) {
            e.printStackTrace ();
        }
        return null;
    }

    /**
     * 根据xpatch查找控件
     * @param xpath xpath
     */
    public Android findByXpatch(String xpath){

        try {
            return auto.findByXpatch(xpath,true);
        } catch (DocumentException e) {
            e.printStackTrace ();
        }
        return null;
    }

    /**
     * 根据text查找控件
     * @param text text
     */
    public Android findByText(String text){

        try {
            return auto.findByText(text,true);
        } catch (DocumentException e) {
            e.printStackTrace ();
        }
        return null;
    }

    /**
     * 根据text查找控件数组
     * @param text text
     */
    public List<Android> findByTexts(String text){
        try {
            return auto.findByTexts(text,true);
        } catch (DocumentException e) {
            e.printStackTrace ();
        }
        return null;
    }

    /**
     * 根据xpatch查找控件的父级
     * @param xpath xpath
     */
    public Android findByXpatchParent(String xpath){
        try {
            return auto.findByXpatchParent(xpath, true);
        } catch (DocumentException e) {
            e.printStackTrace ();
        }
        return null;
    }

    /**
     * 根据text查找控件是否存在
     */
    public boolean isFindNoteForText(String xpath, String findText){

        boolean isFind = false;
        try {
            isFind = auto.isFindNoteForText(xpath, findText, true);
        } catch (DocumentException e) {
            e.printStackTrace ();
        }
        return isFind;
    }

    /**
     * 输出日志相关
     * @param msg msg
     */
    public void push(String uuid,String msg){
        log.push(uuid,msg);
    }
}
