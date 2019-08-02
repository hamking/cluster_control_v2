package com.adb.autoComponent;

import com.adb.enums.Keyevent;

import java.io.IOException;

public class Execute {

    public void shell(String uuid, String command){

        try {
            Runtime.getRuntime().exec("adb -s " + uuid + " shell " + command).waitFor();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace ();
        }
    }

    public void inputKeyevent(String uuid, Keyevent keyevent){

        try {
            Runtime.getRuntime().exec("adb -s " + uuid + " shell input keyevent " + keyevent.getStartInfo() + "").waitFor();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace ();
        }
    }

    public void inputSwipe(String uuid, String command){

        try {
            Runtime.getRuntime().exec("adb -s " + uuid + " shell input swipe " + command).waitFor();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace ();
        }
    }

    public void pull(String uuid, String fromPath, String toPath){

        try {
            Runtime.getRuntime().exec("adb -s " + uuid + " pull " + fromPath + " " + toPath+"").waitFor();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace ();
        }
    }

    public void push(String uuid, String fromPath, String toPath){

        try {
            Runtime.getRuntime().exec("adb -s " + uuid + " push " + fromPath + " " + toPath).waitFor();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace ();
        }
    }

    public void install(String uuid, String apkPath){

        try {
            Runtime.getRuntime().exec("adb -s " + uuid + " install " + apkPath).waitFor();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace ();
        }
    }

    public void unInstall(String uuid, String apkName){

        try {
            Runtime.getRuntime().exec("adb -s " + uuid + " uninstall " + apkName).waitFor();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace ();
        }
    }

    public void startApp(String uuid, String packageName){

        try {
            Runtime.getRuntime().exec("adb -s " + uuid + " shell am start " + packageName).waitFor();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace ();
        }
    }

    public void kill(String uuid, String packageName){
        try {
            Runtime.getRuntime().exec("adb -s " + uuid + " shell am force-stop " + packageName).waitFor();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace ();
        }
    }

    public void mkdir(String uuid, String path){
        try {
            Runtime.getRuntime().exec("adb -s " + uuid + " shell mkdir -p " + path).waitFor();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace ();
        }
    }

    public void reboot(String uuid){
        try {
            Runtime.getRuntime().exec("adb -s " + uuid + " reboot").waitFor();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace ();
        }
    }
}
