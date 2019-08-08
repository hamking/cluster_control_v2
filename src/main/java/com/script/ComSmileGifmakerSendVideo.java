package com.script;

import com.adb.auto.Auto;
import com.zciteam.bean.Device;
import com.zciteam.bean.Script;
import com.zciteam.enums.KeyboardEnum;
import com.zciteam.web.WebSocketDeviceLog;
import org.dom4j.DocumentException;

import java.io.IOException;

/**
 * 快手_发视频
 */
public class ComSmileGifmakerSendVideo {

    private String uuid;
    private Auto auto;
    private Script script;
    private Device device;
    private WebSocketDeviceLog log = new WebSocketDeviceLog();

    public void script(String uuid, Script script, Device device, int index) {
        this.uuid = uuid;
        this.script = script;
        this.device = device;
        auto = new Auto(uuid,script.getPackageName());
        auto.switchKeyboardforSystem(KeyboardEnum.ADB_KEYBOARD);


        log.push(uuid,"快手_发视频");

        //等待程序运行完毕
        auto.wait(15000);
        //去除权限
        try {
            auto.findByText("允许",true).click();
            auto.wait(1000);
            auto.findByText("允许",false).click();
        } catch (DocumentException e) { }

        try {
            auto.wait(5000);
            auto.findByText("我知道了",true).click();
        } catch (DocumentException e) { }

        start();
    }

    /**
     * 正常浏览内容操作
     */
    private void start() {
        //点击发布按钮
        try {
            auto.findByXpatch("//android.support.v4.widget.SlidingPaneLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.ImageView",true).click();
        } catch (DocumentException e) {
        }
        auto.wait(3000);
        //点击关闭权限
        for (int i = 0; i < 3; i++) {
            try {
                auto.findByText("允许",true).click();
            } catch (DocumentException e) {
            }
        }
        try {
            auto.findByText("相册",true).click();
        } catch (DocumentException e) {
        }
        auto.wait(2000);

        //  选取视频
        try {
            auto.findByXpatch("//android.support.v7.widget.RecyclerView/android.widget.FrameLayout[1]/android.widget.LinearLayout/android.widget.TextView",true).click();
        } catch (DocumentException e) {
        }

        //  点击下一步
        try {
            auto.findByText("下一步(1)",true).click();
        } catch (DocumentException e) {
        }

        //点击下一步
        boolean notReturn = true;
        try{
            for (int i = 0; i < 50 && notReturn; i++) {
                auto.wait(5000);
                try {
                    auto.findByText("下一步",true).click();
                } catch (DocumentException e) {
                    notReturn = false;
                }
            }
        }catch(Exception e){

        }

        auto.wait(1000);
        //开始输入文字
        String[] path1 = device.getFunction1().split("\\.");
        try {
            auto.findByText("说点什么...",true).sendKeys(path1[0]);
        } catch (DocumentException e) {
        }
        //点击发布
        try {
            auto.findByText("发布",true).click();
        } catch (DocumentException e) {
        }
        auto.wait(3000);
        auto.back();
    }
}
