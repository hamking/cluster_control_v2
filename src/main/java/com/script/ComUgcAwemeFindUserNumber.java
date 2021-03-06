package com.script;

import com.adb.auto.Auto;
import com.adb.util.ScriptUtils;
import com.android.ddmlib.AdbCommandRejectedException;
import com.android.ddmlib.ShellCommandUnresponsiveException;
import com.android.ddmlib.TimeoutException;
import com.zciteam.bean.Device;
import com.zciteam.bean.Script;
import com.zciteam.enums.KeyboardEnum;
import com.zciteam.web.WebSocketDeviceLog;
import org.dom4j.DocumentException;

import java.io.IOException;
import java.util.List;

/**
 * 抖音_指定抖音号
 */
public class ComUgcAwemeFindUserNumber {

    private String uuid;
    private Auto auto;
    private Script script;
    private WebSocketDeviceLog log = new WebSocketDeviceLog();

    public void script(String uuid, Script script, Device device, List<Device> devices) {
        this.uuid = uuid;
        this.script = script;
        auto = new Auto(uuid,script.getPackageName());
        auto.switchKeyboardforSystem(KeyboardEnum.ADB_KEYBOARD);


        log.push(uuid,"抖音_指定抖音号");
        auto.wait(15000);
        try {
            new ScriptUtils().onLogin("点击青少年模式");
            auto.findByText("我知道了",true).click();
            auto.wait(5000);
        } catch (DocumentException e) {
        }

        for (int i = 0; i < 4; i++) {
            try {
                auto.findByText("允许",true).click();
                auto.wait(3000);
            } catch (DocumentException e) {
            }
        }

        try {
            auto.findByText("继续播放", true).click();
        } catch (DocumentException e) {
        }

        //  用户隐私协议
        try {
            new ScriptUtils().onLogin("点击用户权限");
            auto.findByText("同意",true).click();
            auto.wait(5000);
            auto.back();
        } catch (DocumentException e) {
        }

        //版本检测
        try {
            new ScriptUtils().onLogin("版本检测");
            auto.findByText("以后再说",true).click();
            auto.wait(5000);
        } catch (DocumentException e) {
        }

        start();
    }

    /**
     * 正常浏览内容操作
     */
    private void start() {

        log.push(uuid,"开始关注指定作者");
        String str = script.getCommentStr();

        //点击首页
        try {
            auto.findByText("首页", true).click();
        } catch (DocumentException e) {
        }

        //点击搜索图标
        try {
            auto.findByXpatch("//android.widget.ImageView[@content-desc='搜索']", true).click();
        } catch (DocumentException e) {
        }

        if (str.length() > 0) {
            if (str.contains ("\n")) {
                String[] s = str.split ("\n");
                for (int i = 0; i < s.length; i++) {
                    operation(s[i]);
                }
            } else {
                operation(str);
            }
        }
        auto.wait(3000);
        auto.back();
    }

    private void operation(String str){

        //点击输入框并输入文字
        try {
            auto.findByXpatch("//android.widget.EditText", true).sendKeys(str);
        } catch (DocumentException e) { }

        //点击搜索
        try {
            auto.findByText("搜索", true).click();
        } catch (DocumentException e) { }

        auto.wait(3000);
        //点击关注
        try {
            auto.findByText("关注", true).click();
        } catch (DocumentException e) { }
        auto.back();
    }
}
