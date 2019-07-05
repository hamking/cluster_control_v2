package com.script;

import com.adb.auto.Auto;
import com.adb.auto.Bound;
import com.adb.util.ScriptUtils;
import com.zciteam.bean.Device;
import com.zciteam.bean.Script;
import com.zciteam.web.WebSocketDeviceLog;
import org.dom4j.DocumentException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 快手_私信
 */
public class ComSmileGifmakerPrivateMsg {

    private String uuid;
    private Auto auto;
    private Script script;
    private WebSocketDeviceLog log = new WebSocketDeviceLog();

    public void script(String uuid, Script script, Device device) {
        this.uuid = uuid;
        this.script = script;
        auto = new Auto(uuid,script.getPackageName());


        log.push(uuid, "快手_私信");
        start();
    }

    /**
     * 正常浏览内容操作
     */
    private void start() {

        System.out.println("开始执行");
        //等待程序运行完毕
        auto.wait(15000);

        log.push(uuid, "去除权限");
        System.out.println("去除权限");
        //去除权限
        try {
            auto.findByText("允许",true).click();
            auto.wait(1000);
            auto.findByText("允许",false).click();
        } catch (DocumentException | IOException | InterruptedException  e) { }

        try {
            auto.wait(5000);
            auto.findByText("我知道了",true).click();
        } catch (DocumentException | IOException | InterruptedException e) { }

        //从第几个开始观看
        for (int i = 0; i < script.getNumStart(); i++) {
            auto.swipeUp();
        }

        System.out.println("浏览几个");
        System.out.println("开始浏览");
        //浏览几个次
        int satchNum = script.getWatchNum();
        for (int i = 0; satchNum > 0; i++) {
            try {
                auto.wait(1000);
                auto.findByText("我知道了",true).click();
            } catch (DocumentException | IOException | InterruptedException e) { }
            List<Bound> bounds = new ArrayList<Bound> ();
            try {
                bounds = auto.findsByXpatch("//*[@content-desc='头像']",true);
            } catch (DocumentException e) { }

            for (int i1 = 1; i1 < bounds.size(); i1++) {
                try {
                    auto.wait(1000);
                    auto.findByText("我知道了",true).click();
                } catch (DocumentException | IOException | InterruptedException e) { }
                try {
                    bounds.get(i1).click();
//                    phoneItem.terminalLog("开始观看视频" + scriptDao.getScript(script).getWatchTime() + "秒");
                    auto.wait(1000 * script.getWatchTime());

                    //如果有置地按钮测点击
                    try{
                        auto.findByXpatch("(//android.widget.ImageView[@content-desc='喜欢'])[2]", true).click();
                        auto.wait(3000);
                        auto.swipeUp();
                    }catch (DocumentException | IOException | InterruptedException e) { }
                    auto.swipeUp();

                    directMessagesFocus(script.getIsDirectMessages() == 1 && script.getIsDirectMessagesOnAuthor() == 0);
                    directMessagesOnAuthor(script.getIsDirectMessages() == 1 && script.getIsDirectMessagesOnAuthor() == 1);

                    auto.swipeUp();
                    auto.swipeUp();
                    auto.swipeUp();

                    satchNum -= 1;
                }catch (IOException | InterruptedException e) { }
                backToHome();
                //设置间隔
                auto.wait(1000 * script.getWatchTimeInterval());
            }
            auto.swipeUp();
            auto.swipeUp();
        }
    }

    /**
     * 是否私信评论者
     * @param isDirectMessages isDirectMessages
     */
    private void directMessagesFocus(boolean isDirectMessages){
        if (isDirectMessages) {
            log.push(uuid,"开始私信评论者");
            try {
                int num = script.getDirectMessagesNum();
                boolean isFor = true;
                for (int j = 0; num > 0 && isFor; j++) {
                    List<Bound> bounds = auto.findsByXpatch("//android.widget.TableLayout/android.widget.TableRow",true);
                    if (bounds.size() <= 0){
                        isFor = false;
                        backToHome();
                    }
                    for (int i = 1; i < (bounds.size() - 1) && isFor; i++) {

                        try {
                            bounds.get (i).clickOffsetX (-80);
                        }catch (IOException | InterruptedException e) {
                        }

                        auto.wait(4000);
                        //点击消息图标
                        try {
                            auto.findByXpatch("//android.widget.FrameLayout/android.widget.ImageButton",true).click();
                        } catch (IOException | InterruptedException e) { }
                        auto.wait(3000);
                        //准备发送消息
                        try {
                            String str = script.getDirectMessages();
                            if (str != null && str.length() > 0 && str.contains("\n")){
                                String[] strings = str.split ("\n");
                                int s = ScriptUtils.random("随机评论", 0, strings.length);
                                auto.findByText("请输入...",true).sendKeys(strings[s]);
                            }else if (str != null){
                                auto.findByText("请输入...",true).sendKeys(str);
                            }
                        } catch (IOException | InterruptedException e) { }
                        try {
                            auto.findByText("发送",true).click();
                        } catch (IOException | InterruptedException e) { }
                        num -= 1;
                        backToComment();
                    }
                    auto.wait(500);
                    auto.swipeUp();
                }
            } catch (DocumentException e) {
            }
        }
    }

    /**
     * 是否私信作者
     * @param isDirectMessages isDirectMessages
     */
    private void directMessagesOnAuthor(boolean isDirectMessages){
        if (isDirectMessages){
            log.push(uuid,"开始私信作者");
            //点击头像
            try {
                auto.findByXpatch("//*[@content-desc='头像']",true).click();
            } catch (IOException | InterruptedException | DocumentException e) { }
            auto.wait(4000);
            //点击消息图标
            try {
                auto.findByXpatch("//android.widget.FrameLayout/android.widget.ImageButton",true).click();
            } catch (IOException | InterruptedException | DocumentException e) { }
            auto.wait(3000);
            //准备发送消息
            try {
                String str = script.getDirectMessages();
                if (str != null && str.length() > 0 && str.contains("\n")){
                    String[] strings = str.split ("\n");
                    int s = ScriptUtils.random("随机评论", 0, strings.length);
                    auto.findByText("请输入...",true).sendKeys(strings[s]);
                }else if (str != null){
                    auto.findByText("请输入...",true).sendKeys(str);
                }
            } catch (IOException | InterruptedException | DocumentException e) { }
            try {
                auto.findByText("发送",true).click();
            } catch (IOException | InterruptedException | DocumentException e) { }
        }
    }

    private void backToHome(){
        //返回到首页
        int index = 6;
        boolean isBock = true;
        while (isBock){
            try {
                auto.findByXpatch("//android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout",true);
                isBock = false;
            } catch (DocumentException e) {
                auto.back();
                if (index <= 0){
                    isBock = false;
                }
                index -= 1;
            }
        }
    }

    private void backToComment(){
        //返回到首页
        int index = 5;
        boolean isBock = true;
        try {
            //当前页
            auto.findByText("请输入...",true);
            //返回到
            while (isBock){
                try {
                    auto.findByText("说点什么...",true);
                    isBock = false;
                } catch (DocumentException e) {
                    auto.back();
                    if (index <= 0){
                        isBock = false;
                    }
                    index -= 1;
                }
            }
        } catch (DocumentException e) {
            auto.back();
        }
    }
}
