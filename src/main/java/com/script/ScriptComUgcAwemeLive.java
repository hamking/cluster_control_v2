package com.script;

import com.adb.auto.Auto;
import com.adb.auto.Bound;
import com.zciteam.bean.Device;
import com.zciteam.bean.Script;
import com.zciteam.web.WebSocketDeviceLog;
import org.dom4j.DocumentException;

import java.io.IOException;
import java.util.List;

/**
 * 抖音_关注直播评论人
 */
public class ScriptComUgcAwemeLive {

    private String uuid;
    private Auto auto;
    private Script script;
    private WebSocketDeviceLog log = new WebSocketDeviceLog();

    public void script(String uuid, Script script, Device device) {
        this.uuid = uuid;
        this.script = script;
        auto = new Auto(uuid, script.getPackageName ());

        log.push(uuid,"抖音_关注直播评论人");
        start();
    }

    private void start(){

        log.push(uuid,"点击青少年模式");
        try {
            auto.findByText("我知道了",true).click();
        } catch (IOException | DocumentException | InterruptedException e) {
            e.printStackTrace();
        }
        auto.wait(5000);
        for (int i = 0; i < 4; i++) {
            try {
                auto.findByText("允许",true).click();
                auto.wait(3000);
            } catch (IOException | InterruptedException | DocumentException e) {
                e.printStackTrace ();
            }
        }

        //  点击直播广场
        try {
            auto.findByXpatch("//android.widget.ImageView[@content-desc='live']",true).click();
            auto.wait(5000);
        } catch (IOException | DocumentException | InterruptedException e) {
            e.printStackTrace();
        }

        //  点击条目
        try {
            List<Bound> boundList = auto.findsByXpatch("//android.support.v7.widget.RecyclerView/android.widget.RelativeLayout",true);
            try {
                boundList.get(0).click();
                for (int i = 0; i < script.getWatchNum(); i++) {
                    for (int k = 0; k < script.getFocusNum (); k++) {
                        //  点击用户
                        try {
                            List<Bound> bounds = auto.findsByXpatch ("//android.support.v7.widget.RecyclerView/android.widget.RelativeLayout", true);
                            try {
                                bounds.get(0).clickOffsetX(50);
                            } catch (IOException e) {
                                e.printStackTrace ();
                            }
                            auto.wait (2000);
                        } catch (DocumentException e) {
                            e.printStackTrace ();
                        }

                        //  点击关注
                        try {
                            auto.findByText ("关注", true).click ();
                            auto.wait (1000);
                        } catch (IOException | DocumentException e) {
                            e.printStackTrace ();
                        }
                        auto.back();
                    }
                    auto.swipeUp();
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
            auto.wait(10000);

        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
