package com.adb.autoComponent;

import com.adb.enums.Keyevent;

import java.io.IOException;

public class Execute {

    public void shell(String uuid, String command) throws IOException, InterruptedException {

        Runtime.getRuntime().exec("adb -s " + uuid + " shell " + command).waitFor();
    }

    public void inputKeyevent(String uuid, Keyevent keyevent) throws IOException, InterruptedException {

        Runtime.getRuntime().exec("adb -s " + uuid + " shell input keyevent " + keyevent.getStartInfo() + "").waitFor();
    }

    public void inputSwipe(String uuid, String command) throws IOException, InterruptedException {

        Runtime.getRuntime().exec("adb -s " + uuid + " shell input swipe " + command).waitFor();
    }

    public void pull(String uuid, String fromPath, String toPath) throws IOException, InterruptedException {

        Runtime.getRuntime().exec("adb -s " + uuid + " pull " + fromPath + " " + toPath+"").waitFor();
    }

    public void push(String uuid, String fromPath, String toPath) throws IOException, InterruptedException {

        Runtime.getRuntime().exec("adb -s " + uuid + " push " + fromPath + " " + toPath).waitFor();
    }

    public void install(String uuid, String apkPath) throws IOException, InterruptedException {

        Runtime.getRuntime().exec("adb -s " + uuid + " install " + apkPath).waitFor();
    }

    public void unInstall(String uuid, String apkName) throws IOException, InterruptedException {

        Runtime.getRuntime().exec("adb -s " + uuid + " uninstall " + apkName).waitFor();
    }

    public void startApp(String uuid, String packageName) throws IOException, InterruptedException {

        Runtime.getRuntime().exec("adb -s " + uuid + " shell am start " + packageName).waitFor();
    }

    public void kill(String uuid, String packageName) throws IOException, InterruptedException {
        Runtime.getRuntime().exec("adb -s " + uuid + " shell am force-stop " + packageName).waitFor();
    }

    public void mkdir(String uuid, String path) throws IOException, InterruptedException {
        Runtime.getRuntime().exec("adb -s " + uuid + " shell mkdir -p " + path).waitFor();
    }

    public void reboot(String uuid) throws IOException, InterruptedException {
        Runtime.getRuntime().exec("adb -s " + uuid + " reboot").waitFor();
    }
}
