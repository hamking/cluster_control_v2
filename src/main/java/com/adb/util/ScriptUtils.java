package com.adb.util;

/** 脚本工具类 */
public class ScriptUtils {

    /** 随机数(start~num) */
    public int random(String msg,int start,int num) {
        int v = (int) (Math.random() * num) + start;
        System.out.println(msg + v);
        return v;
    }

    /** 日志 */
    public static void onLogin(String msg) {
        System.out.println(msg);
    }
}
