package com.adb.autoComponent;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class ExecuteTest {

    @Test
    public void shell() {
        try {
            Runtime.getRuntime().exec("adb -s 351BBIHHCJNW shell input keyevent 'KEYCODE_HOME'").waitFor();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace ();
        }
    }

    @Test
    public void pull() {
    }

    @Test
    public void push() {
    }

    @Test
    public void install() {
        try {
            install("88MFDMD332YV","/tmp/yunsheng/application/东方头条2.4.8.apk");
        } catch (InterruptedException | IOException e) {
            e.printStackTrace ();
        }
    }
    void install(String uuid, String apkPath) throws IOException, InterruptedException {

        System.out.println(Runtime.getRuntime().exec("adb -s " + uuid + " install " + apkPath).waitFor());
    }

    @Test
    public void unInstall() {
    }

    @Test
    public void startApp() {
    }

    @Test
    public void kill() {
    }
}