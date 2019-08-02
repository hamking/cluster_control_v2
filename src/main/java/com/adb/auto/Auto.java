package com.adb.auto;

import com.adb.autoComponent.ChangeNodes;
import com.adb.autoComponent.Execute;
import com.adb.autoComponent.LayoutXml;
import com.adb.enums.Keyevent;
import com.zciteam.enums.KeyboardEnum;
import org.dom4j.*;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 自动化工具
 */
public class Auto {

    private String xmlFile;
    private String deviceUUID;

    /**
     * 只做初始化
     * @param deviceUUID deviceUUID
     */
    public Auto(String deviceUUID){

        this.deviceUUID = deviceUUID;
    }

    /**
     * 启动应用
     * @param deviceUUID deviceUUID
     * @param packageName packageName
     */
    public Auto(String deviceUUID, String packageName){

        this.deviceUUID = deviceUUID;
        new Execute().startApp(deviceUUID, packageName);
    }

    /**
     * 根据xpatch查找控件数组
     * @param xpath xpath
     * @param isSearch 是否解析界面
     */
    public List<Android> findsByXpatch(String xpath, boolean isSearch) throws DocumentException {

        SAXReader reader = new SAXReader();
        if (isSearch){
            xmlFile = new LayoutXml ().getLayout(deviceUUID);
        }
        Document doc = reader.read(new File(xmlFile));
        new ChangeNodes ().getNodes(doc.getRootElement());
        List<Node> list = doc.selectNodes(xpath);

        List<Android> bounds = new ArrayList<> ();
        list.forEach(node -> {

            final Android[] bound = new Android[1];
            Element element = (Element) node;
            if (element == null){
                try {
                    throw new DocumentException("xpath不存在");
                } catch (DocumentException ignored) {
                }
                return;
            }
            List<Attribute> listAttr=element.attributes();
            //节点信息
            Map<String, String>noteMap = new HashMap<>();

            listAttr.forEach(attribute -> {
                String name=attribute.getName();
                String value=attribute.getValue();
                noteMap.put(name,value);
                if (name.equals("bounds")){
                    bound[0] = new Android(value.toString(),noteMap,deviceUUID);
                }
            });
            bounds.add(bound[0]);
        });
        return bounds;
    }

    /**
     * 根据xpatch查找控件
     * @param xpath xpath
     * @param isSearch 是否解析界面
     */
    public Android findByXpatch(String xpath, boolean isSearch) throws DocumentException {

        //节点信息
        Map<String, String>noteMap = new HashMap<>();
        final String[] bound = new String[1];
        SAXReader reader = new SAXReader();
        if (isSearch){
            xmlFile = new LayoutXml().getLayout(deviceUUID);
        }
        Document doc = reader.read(new File(xmlFile));
        new ChangeNodes ().getNodes(doc.getRootElement());
        Element element = (Element)doc.selectSingleNode(xpath);
        if (element == null){
            throw new DocumentException("xpath不存在: -> " + xpath);
        }
        List<Attribute> listAttr = element.attributes();
        listAttr.forEach(attribute -> {
            String name = attribute.getName();
            String value = attribute.getValue();
            if (name.equals("bounds")){
                bound[0] = value;
            }
            noteMap.put(name,value);
        });
        return new Android(bound[0],noteMap,deviceUUID);
    }

    /**
     * 根据text查找控件
     * @param text text
     * @param isSearch 是否解析界面
     */
    public Android findByText(String text, boolean isSearch) throws DocumentException {

        //节点信息
        Map<String, String>noteMap = new HashMap<>();
        final String[] bound = new String[1];
        SAXReader reader = new SAXReader();
        if (isSearch){
            xmlFile = new LayoutXml().getLayout(deviceUUID);
        }
        Document doc = reader.read(new File(xmlFile));
        new ChangeNodes().getNodes(doc.getRootElement());
        Element element = (Element) doc.selectSingleNode(String.format("//*[@text='%s']", text));
        if (element == null){
            throw new DocumentException("text不存在: -> " + text);
        }
        List<Attribute> listAttr = element.attributes();
        listAttr.forEach(attribute -> {
            String name = attribute.getName();
            String value = attribute.getValue();
            if (name.equals("bounds")){
                bound[0] = value;
            }
            noteMap.put(name,value);
        });
        return new Android(bound[0],noteMap,deviceUUID);
    }


    /**
     * 根据text查找控件数组
     * @param text text
     * @param isSearch 是否解析界面
     */
    public List<Android> findByTexts(String text, boolean isSearch) throws DocumentException {

        SAXReader reader = new SAXReader();
        if (isSearch){
            xmlFile = new LayoutXml().getLayout(deviceUUID);
        }
        Document doc = reader.read(new File(xmlFile));
        new ChangeNodes().getNodes(doc.getRootElement());
        List<Node> list = doc.selectNodes(String.format("//*[@text='%s']", text));

        List<Android> bounds = new ArrayList<> ();
        list.forEach(node -> {

            final Android[] bound = new Android[1];
            Element element = (Element) node;
            if (element == null){
                try {
                    throw new DocumentException("xpath不存在");
                } catch (DocumentException ignored) {
                }
                return;
            }
            List<Attribute> listAttr=element.attributes();
            //节点信息
            Map<String, String>noteMap = new HashMap<>();

            listAttr.forEach(attribute -> {
                String name=attribute.getName();
                String value=attribute.getValue();
                noteMap.put(name,value);
                if (name.equals("bounds")){
                    bound[0] = new Android(value.toString(),noteMap,deviceUUID);
                }
            });
            bounds.add(bound[0]);
        });
        return bounds;
    }

    /**
     * 根据xpatch查找控件的父级
     * @param xpath xpath
     * @param isSearch 是否解析界面
     */
    public Android findByXpatchParent(String xpath, boolean isSearch) throws DocumentException {

        //节点信息
        Map<String, String>noteMap = new HashMap<>();
        final String[] bound = new String[1];
        SAXReader reader = new SAXReader();
        if (isSearch){
            xmlFile = new LayoutXml().getLayout(deviceUUID);
        }
        Document doc = reader.read(new File(xmlFile));
        new ChangeNodes().getNodes(doc.getRootElement());
        Element element = doc.selectSingleNode(xpath).getParent();
        if (element == null){
            throw new DocumentException("xpath不存在: -> " + xpath);
        }
        List<Attribute> listAttr = element.attributes();
        listAttr.forEach(attribute -> {
            String name = attribute.getName();
            String value = attribute.getValue();
            if (name.equals("bounds")){
                bound[0] = value;
            }
            noteMap.put(name,value);
        });
        return new Android(bound[0],noteMap,deviceUUID);
    }


    /**
     * 根据text查找控件是否存在
     * @param xpath xpath
     * @param isSearch 是否解析界面
     */
    private boolean notTrue = true;
    private boolean isTrue = false;
    public boolean isFindNoteForText(String xpath, String findText, boolean isSearch) throws DocumentException {

        notTrue = true;
        isTrue = false;
        SAXReader reader = new SAXReader();
        if (isSearch){
            xmlFile = new LayoutXml().getLayout(deviceUUID);
        }
        Document doc = reader.read(new File(xmlFile));
        new ChangeNodes().getNodes(doc.getRootElement());
        Element element = (Element) doc.selectSingleNode(xpath);
        if (element == null){
            return false;
        }
        return eacheNoteForText(element, findText);
    }
    private boolean eacheNoteForText(Element element, String text){

        for (int i = 0; i < element.attributes().size() && notTrue; i++) {
            Attribute attribute = element.attributes().get(i);
            String name = attribute.getName();
            String value = attribute.getValue();
            if (name.equals("text")){
                if (value.contains(text)){
                    notTrue = false;
                    isTrue = true;
                }
            }
        }

        for (Element e : element.elements()) {
            eacheNoteForText(e, text);
        }
        return isTrue;
    }

    /**
     * 返回
     */
    public void back(){

        new Execute().inputKeyevent(deviceUUID, Keyevent.BACK);
    }

    /**
     * 返回首页
     */
    public void home(){

        new Execute().inputKeyevent(deviceUUID,Keyevent.HOME);
    }

    /**
     * 清空app进程
     */
    public void kill(String packageName){

        new Execute().kill(deviceUUID,packageName);
    }

    /**
     * 上滑动
     */
    public void swipeUp(){

        new Execute().inputSwipe(deviceUUID,"500 700 500 100");
    }

    /**
     * 下滑动
     */
    public void swipeDown(){

        new Execute().inputSwipe(deviceUUID,"500 300 500 800");
    }

    /**
     * 左滑动
     */
    public void swipeLeft(){

        new Execute().shell(deviceUUID,"input swipe 100 500 800 500");
    }

    /**
     * 右滑动
     */
    public void swipeRight(){

        new Execute().inputSwipe(deviceUUID,"100 500 800 500");
    }

    /**
     * 导入媒体
     */
    public void inputMediaEventScript(String path, String fileName){

        new Execute().push(deviceUUID,path,"/sdcard/DCIM/" + fileName);

        new Execute().shell(deviceUUID,"am broadcast -a android.intent.action.MEDIA_MOUNTED -d file:///sdcard/DCIM");
    }

    /**
     * 清空目录
     */
    public void rmFileMediaEventScript(String path){

        new Execute().shell(deviceUUID,path);
        new Execute().shell(deviceUUID,"am broadcast -a android.intent.action.MEDIA_MOUNTED -d file:///sdcard/DCIM");
    }

    /**
     * 回车键
     */
    public void enter(){

        new Execute().inputKeyevent(deviceUUID,Keyevent.ENTER);
    }


    /**
     * 切换输入法 搜狗/ADB
     */
    public void switchKeyboardforSystem(KeyboardEnum keyboardEnum){
        switch (keyboardEnum){
            case SYSTEM_KEYBOARD:

                new Execute().shell(deviceUUID,"ime set com.google.android.inputmethod.pinyin/.PinyinIME");
                break;
            case ADB_KEYBOARD:

                new Execute().shell(deviceUUID,"ime set com.android.adbkeyboard/.AdbIME");
                break;
        }
    }

    /**
     * 导入通讯录
     * @param path path
     */
    public void addressBookImportEvent(String path){

        new Execute().push(deviceUUID,path,"/sdcard/contacts.vcf");
    }

    /**
     * 清空通讯录
     */
    public void addressBookClearEvent(){

        new Execute().shell(deviceUUID,"pm clear com.android.providers.contacts");
    }

    /**
     * 锁屏
     */
    public void lock(){

        new Execute().inputKeyevent(deviceUUID,Keyevent.POWER);
    }

    /**
     * 解屏
     */
    public void unLock(){

        new Execute().inputKeyevent(deviceUUID,Keyevent.POWER);
        new Execute().inputSwipe(deviceUUID,"500 600 500 100");
    }

    /**
     * 音量加
     */
    public void volumeUp(){

        new Execute().inputKeyevent(deviceUUID,Keyevent.VOLUME_UP);
    }

    /**
     * 音量减
     */
    public void volumeDown(){

        new Execute().inputKeyevent(deviceUUID,Keyevent.VOLUME_DOWN);
    }

    /**
     * 静音
     */
    public void volumeMute(){

        new Execute().inputKeyevent(deviceUUID,Keyevent.VOLUME_MUTE);
    }

    /**
     * 重启
     */
    public void reboot(){

        new Execute().reboot(deviceUUID);
    }

    /**
     * 导入文件
     * @param fromPath fromPath
     * @param toPath toPath
     */
    public void pushFile(String fromPath, String toPath){

        new Execute().push(deviceUUID,fromPath,toPath);
    }

    public void mkdir(String path){

        new Execute().mkdir(deviceUUID, path);
    }

    /**
     * 刷新相册
     */
    public void refreshPhotoAlbum(){

        new Execute().shell(deviceUUID,"am broadcast -a android.intent.action.MEDIA_MOUNTED -d file:///sdcard/DCIM");
    }

    /**
     * 安装软件
     * @param apkPath apkPath
     */
    public void install(String apkPath){

        new Execute().install(deviceUUID, apkPath);
    }

    /**
     * 卸载软件
     * @param apkName apkName
     */
    public void unInstall(String apkName){

        new Execute().unInstall(deviceUUID, apkName);
    }

    /**
     * 导入助手
     */
    public void inputHelperEventScript(){

        new Execute().shell(deviceUUID, "mkdir -p /data/local/tmp/local/tmp");
        new Execute().push(deviceUUID, "/", "/data/local/tmp/LvmamaXmlKit.jar");
    }

    /**
     * 导入刷机包
     */
    public void flashEventScript(String path){

        new Execute().push(deviceUUID, path,"/sdcard/update.zip");
        new Execute().shell(deviceUUID,"reboot recovery");
    }

    /**
     * 延时
     * @param millis millis
     */
    public void wait(int millis){

        try {
            Thread.sleep(millis);
        } catch (InterruptedException ignored) {
        }
    }
}