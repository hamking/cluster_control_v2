package com.script;

import com.adb.auto.Auto;
import com.adb.auto.Android;
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

        String scriptMsg = ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + "\n" +
                "脚本执行详情:" + "\n" +
                "一共观看几个直播: " + script.getWatchNum() + "\n" +
                "每个直播关注几个人: " + script.getFocusNum() + "\n" +
                ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>";
        log.push(uuid,scriptMsg);

        start();

        log.push(uuid,"本次脚本结束");
    }

    private void start(){

        auto.wait(15000);
        try {
            log.push(uuid,"点击青少年模式");
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
            log.push(uuid,"检测用户权限");
            auto.findByText("同意",true).click();
            auto.wait(5000);
            auto.back();
        } catch (DocumentException | IOException | InterruptedException e) {
        }

        //版本检测
        try {
            log.push(uuid,"版本检测");
            auto.findByText("以后再说",true).click();
            auto.wait(5000);
        } catch (DocumentException | IOException | InterruptedException e) {
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
            List<Android> boundList = auto.findsByXpatch("//android.support.v7.widget.RecyclerView/android.widget.RelativeLayout",true);
            try {
                boundList.get(0).click();
                for (int i = 0; i < script.getWatchNum(); i++) {
                    auto.wait (8000);
                    try{
                        auto.findByText("我知道了",true).click();
                    }catch (IOException | InterruptedException | DocumentException e) {
                        e.printStackTrace ();
                    }
                    for (int k = 0; k < script.getFocusNum (); k++) {
                        auto.wait (7000);
                        log.push(uuid,"开始关注第"+ (i+1) +"个视频, 第"+ (k+1) +"个用户");
                        //  点击用户
                        try {
                            List<Android> bounds = auto.findsByXpatch ("//android.support.v7.widget.RecyclerView/android.widget.RelativeLayout", true);

                            bounds.get(bounds.size() - 1).clickOffsetX(50);

                            log.push(uuid,"开始关注");
                            //  点击关注
                            auto.findByXpatch ("//android.widget.LinearLayout/android.widget.LinearLayout[3]/android.widget.LinearLayout/android.widget.TextView[1][@text='关注']", true).click ();
                            log.push(uuid,"已关注");
                            auto.wait(1000);
                            auto.back();
                            auto.wait(2000);
                        } catch (DocumentException e) {
                            if(auto.isFindNoteForText("/hierarchy/android.widget.FrameLayout","抖音号",true)){
                                auto.back ();
                                log.push(uuid,"关注异常");
                            }else{
                                log.push(uuid,"操作异常");
                            }
                            e.printStackTrace ();
                        }
                    }
                    auto.swipeUp();
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
                log.push(uuid,"直播观看异常");
            }
        } catch (DocumentException e) {
            e.printStackTrace();
            log.push(uuid,"直播广场异常");
        }
    }
}
