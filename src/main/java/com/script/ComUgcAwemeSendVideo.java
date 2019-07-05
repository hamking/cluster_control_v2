package com.script;

import com.adb.auto.Auto;
import com.adb.util.ScriptUtils;
import com.android.ddmlib.AdbCommandRejectedException;
import com.android.ddmlib.ShellCommandUnresponsiveException;
import com.android.ddmlib.TimeoutException;
import com.zciteam.bean.Device;
import com.zciteam.bean.Script;
import com.zciteam.web.WebSocketDeviceLog;
import org.dom4j.DocumentException;

import java.io.IOException;

/**
 * 抖音_发视频
 */
public class ComUgcAwemeSendVideo {

    private String uuid;
    private Auto auto;
    private Script script;
    private Device device;
    private WebSocketDeviceLog log = new WebSocketDeviceLog();

    public void script(String uuid, Script script, Device device) {
        this.uuid = uuid;
        this.script = script;
        this.device = device;
        auto = new Auto(uuid,script.getPackageName());


        log.push(uuid,"抖音_发视频");
        auto.wait(15000);
        try {
            ScriptUtils.onLogin("点击青少年模式");
            auto.findByText("我知道了",true).click();
            auto.wait(5000);
        } catch (DocumentException | IOException | InterruptedException e) {
        }

        for (int i = 0; i < 4; i++) {
            try {
                auto.findByText("允许",true).click();
                auto.wait(3000);
            } catch (DocumentException | IOException | InterruptedException e) {
            }
        }

        try {
            auto.findByText("继续播放", true).click();
        } catch (DocumentException | IOException | InterruptedException e) {
        }

        //  用户隐私协议
        try {
            ScriptUtils.onLogin("点击用户权限");
            auto.findByText("同意",true).click();
            auto.wait(5000);
            auto.back();
        } catch (DocumentException | IOException | InterruptedException e) {
        }

        //版本检测
        try {
            ScriptUtils.onLogin("版本检测");
            auto.findByText("以后再说",true).click();
            auto.wait(5000);
        } catch (DocumentException | IOException | InterruptedException e) {
        }
        start();
    }

    /**
     * 正常浏览内容操作
     */
    private void start() {
        auto.back();
        try {
            auto.findByXpatch ("//android.widget.ImageView[@content-desc='拍摄，按钮']", true).click ();
        } catch (DocumentException | IOException | InterruptedException e) {
        }
        auto.wait (3000);
        //点击关闭权限
        for (int i = 0; i < 3; i++) {
            try {
                auto.findByText ("允许", true).click ();
            } catch (DocumentException | IOException | InterruptedException e) {
            }
        }
        auto.wait(2000);
        try {
            auto.findByText ("上传", true).click ();
        } catch (DocumentException | IOException | InterruptedException e) {
        }
        auto.wait(2000);
        try {
            auto.findByXpatch("//android.support.v7.widget.RecyclerView/android.widget.RelativeLayout/android.widget.ImageView", true).click ();
        } catch (DocumentException | IOException | InterruptedException e) {
        }
        auto.wait(2000);
        try {
            auto.findByText("下一步", true).click ();
        } catch (DocumentException | IOException | InterruptedException e) {
        }
        auto.wait(100000);
        try {
            auto.findByText("下一步", true).click ();
        } catch (DocumentException | IOException | InterruptedException e) {
        }
        auto.wait(3000);

        //开始输入文字
        String[] path1 = device.getFunction1().split("\\.");
        try {
            auto.findByXpatch("//android.widget.EditText", true).sendKeys(path1[0]);
        } catch (DocumentException | IOException | InterruptedException e) {
        }
        auto.wait(500);
        try {
            auto.findByXpatch("//android.widget.FrameLayout[@content-desc='发布']", true).click();
        } catch (DocumentException | IOException | InterruptedException e) {
        }

        auto.back();
    }
}
