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
     * @param device device
     */
    public Auto(String device){

        deviceUUID = device;
    }

    /**
     * 启动应用
     * @param device device
     * @param packageName packageName
     */
    public Auto(String device, String packageName){

        deviceUUID = device;
        try {
            Execute.startApp(deviceUUID, packageName);
        } catch (IOException | InterruptedException ignored) {
        }
    }

    /**
     * 根据xpatch查找控件数组
     * @param xpath xpath
     * @param isSearch 是否解析界面
     */
    public List<Bound> findsByXpatch(String xpath, boolean isSearch) throws DocumentException {

        SAXReader reader = new SAXReader();
        if (isSearch){
            xmlFile = new LayoutXml ().getLayout(deviceUUID);
        }
        Document doc = reader.read(new File(xmlFile));
        new ChangeNodes ().getNodes(doc.getRootElement());
        List<Node> list = doc.selectNodes(xpath);

        List<Bound> bounds = new ArrayList<> ();
        list.forEach(node -> {

            final Bound[] bound = new Bound[1];
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
                    bound[0] = new Bound(value.toString(),noteMap,deviceUUID);
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
    public Bound findByXpatch(String xpath, boolean isSearch) throws DocumentException {

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
        return new Bound(bound[0],noteMap,deviceUUID);
    }

    /**
     * 根据text查找控件
     * @param text text
     * @param isSearch 是否解析界面
     */
    public Bound findByText(String text, boolean isSearch) throws DocumentException {

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
        return new Bound(bound[0],noteMap,deviceUUID);
    }


    /**
     * 根据text查找控件数组
     * @param text text
     * @param isSearch 是否解析界面
     */
    public List<Bound> findByTexts(String text, boolean isSearch) throws DocumentException {

        SAXReader reader = new SAXReader();
        if (isSearch){
            xmlFile = new LayoutXml().getLayout(deviceUUID);
        }
        Document doc = reader.read(new File(xmlFile));
        new ChangeNodes().getNodes(doc.getRootElement());
        List<Node> list = doc.selectNodes(String.format("//*[@text='%s']", text));

        List<Bound> bounds = new ArrayList<> ();
        list.forEach(node -> {

            final Bound[] bound = new Bound[1];
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
                    bound[0] = new Bound(value.toString(),noteMap,deviceUUID);
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
    public Bound findByXpatchParent(String xpath, boolean isSearch) throws DocumentException {

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
        return new Bound(bound[0],noteMap,deviceUUID);
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

        try {
            Execute.inputKeyevent(deviceUUID, Keyevent.BACK);
        } catch (IOException | InterruptedException ignored) {
        }
    }

    /**
     * 返回首页
     */
    public void home(){

        try {
            Execute.inputKeyevent(deviceUUID,Keyevent.HOME);
        } catch (IOException | InterruptedException ignored) {
        }
    }

    /**
     * 清空app进程
     */
    public void kill(String packageName){
        try {
            Execute.kill(deviceUUID,packageName);
        } catch (IOException | InterruptedException ignored) {
        }
    }

    /**
     * 上滑动
     */
    public void swipeUp(){

        try {
            Execute.inputSwipe(deviceUUID,"500 700 500 100");
        } catch (IOException | InterruptedException ignored) {
        }
    }

    /**
     * 下滑动
     */
    public void swipeDown(){

        try {
            Execute.inputSwipe(deviceUUID,"500 300 500 800");
        } catch (IOException | InterruptedException ignored) {
        }
    }

    /**
     * 左滑动
     */
    public void swipeLeft(){

        try {
            Execute.shell(deviceUUID,"input swipe 100 500 800 500");
        } catch (IOException | InterruptedException ignored) {
        }
    }

    /**
     * 右滑动
     */
    public void swipeRight(){

        try {
            Execute.inputSwipe(deviceUUID,"100 500 800 500");
        } catch (IOException | InterruptedException ignored) {
        }
    }

    /**
     * 导入媒体
     */
    public void inputMediaEventScript(String path, String fileName){

        try {
            Execute.push(deviceUUID,path,"/sdcard/DCIM/" + fileName);
        } catch (IOException | InterruptedException ignored) {
        }

        try {
            Execute.shell(deviceUUID,"am broadcast -a android.intent.action.MEDIA_MOUNTED -d file:///sdcard/DCIM");
        } catch (IOException | InterruptedException ignored) {
        }
    }

    /**
     * 清空目录
     */
    public void rmFileMediaEventScript(String path){

        try {
            Execute.shell(deviceUUID,path);
        } catch (IOException | InterruptedException ignored) {
        }
        try {
            Execute.shell(deviceUUID,"am broadcast -a android.intent.action.MEDIA_MOUNTED -d file:///sdcard/DCIM");
        } catch (IOException | InterruptedException ignored) {
        }
    }

    /**
     * 回车键
     */
    public void enter(){

        try {
            Execute.inputKeyevent(deviceUUID,Keyevent.ENTER);
        } catch (IOException | InterruptedException ignored) {
        }
    }


    /**
     * 切换输入法 搜狗/ADB
     */
    public void switchKeyboardforSystem(KeyboardEnum keyboardEnum){
        switch (keyboardEnum){
            case SYSTEM_KEYBOARD:

                try {
                    Execute.shell(deviceUUID,"ime set com.sohu.inputmethod.sogou/.SogouIME");
                } catch (IOException | InterruptedException ignored) {
                }
                break;
            case ADB_KEYBOARD:

                try {
                    Execute.shell(deviceUUID,"ime set com.android.adbkeyboard/.AdbIME");
                } catch (IOException | InterruptedException ignored) {
                }
                break;
        }
    }

    /**
     * 导入通讯录
     * @param path path
     */
    public void addressBookImportEvent(String path){

        try {
            Execute.push(deviceUUID,path,"/sdcard/contacts.vcf");
        } catch (IOException | InterruptedException ignored) {
        }
    }

    /**
     * 清空通讯录
     */
    public void addressBookClearEvent(){

        try {
            Execute.shell(deviceUUID,"pm clear com.android.providers.contacts");
        } catch (IOException | InterruptedException ignored) {
        }
    }

    /**
     * 锁屏
     */
    public void lock(){
        try {
            Execute.inputKeyevent(deviceUUID,Keyevent.POWER);
        } catch (IOException | InterruptedException ignored) {
        }
    }

    /**
     * 解屏
     */
    public void unLock(){
        try {
            Execute.inputKeyevent(deviceUUID,Keyevent.POWER);
            Execute.inputSwipe(deviceUUID,"500 600 500 100");
        } catch (IOException | InterruptedException ignored) {
        }
    }

    /**
     * 音量加
     */
    public void volumeUp(){
        try {
            Execute.inputKeyevent(deviceUUID,Keyevent.VOLUME_UP);
        } catch (IOException | InterruptedException ignored) {
        }
    }

    /**
     * 音量减
     */
    public void volumeDown(){
        try {
            Execute.inputKeyevent(deviceUUID,Keyevent.VOLUME_DOWN);
        } catch (IOException | InterruptedException ignored) {
        }
    }

    /**
     * 静音
     */
    public void volumeMute(){
        try {
            Execute.inputKeyevent(deviceUUID,Keyevent.VOLUME_MUTE);
        } catch (IOException | InterruptedException ignored) {
        }
    }

    /**
     * 重启
     */
    public void reboot(){
        try {
            Execute.reboot(deviceUUID);
        } catch (IOException | InterruptedException ignored) {
        }
    }

    /**
     * 导入文件
     * @param fromPath fromPath
     * @param toPath toPath
     */
    public void pushFile(String fromPath, String toPath){
        try {
            Execute.push(deviceUUID,fromPath,toPath);
        } catch (IOException | InterruptedException ignored) {
        }
    }

    public void mkdir(String path){
        try {
            Execute.mkdir(deviceUUID, path);
        } catch (IOException | InterruptedException ignored) {
        }
    }

    /**
     * 刷新相册
     */
    public void refreshPhotoAlbum(){
        try {
            Execute.shell(deviceUUID,"am broadcast -a android.intent.action.MEDIA_MOUNTED -d file:///sdcard/DCIM");
        } catch (IOException | InterruptedException ignored) {
        }
    }

    /**
     * 安装软件
     * @param apkPath apkPath
     */
    public void install(String apkPath){
        try {
            Execute.install(deviceUUID, apkPath);
        } catch (IOException | InterruptedException ignored) {
        }
    }

    /**
     * 卸载软件
     * @param apkName apkName
     */
    public void unInstall(String apkName){
        try {
            Execute.unInstall(deviceUUID, apkName);
        } catch (IOException | InterruptedException ignored) {
        }
    }

    /**
     * 导入助手
     */
    public void inputHelperEventScript(){
        try {
            Execute.shell(deviceUUID, "mkdir -p /data/local/tmp/local/tmp");
        } catch (IOException | InterruptedException ignored) {
        }
        try {
            Execute.push(deviceUUID, "/", "/data/local/tmp/LvmamaXmlKit.jar");
        } catch (IOException | InterruptedException ignored) {
        }
    }

    /**
     * 导入刷机包
     */
    public void flashEventScript(String path){
        try {
            Execute.push(deviceUUID, path,"/sdcard/update.zip");
        } catch (IOException | InterruptedException ignored) {
        }
        try {
            Execute.shell(deviceUUID,"reboot recovery");
        } catch (IOException | InterruptedException ignored) {
        }
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