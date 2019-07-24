package com.script;

import com.adb.auto.Auto;
import com.adb.auto.Bound;
import com.adb.util.ScriptUtils;
import com.zciteam.bean.Device;
import com.zciteam.bean.Script;
import com.zciteam.enums.KeyboardEnum;
import com.zciteam.web.WebSocketDeviceLog;
import org.dom4j.DocumentException;

import java.io.IOException;
import java.util.List;

/**
 * 快手_查找模糊匹配作品
 */
public class ComSmileGifmakerFindWorks {

    private String uuid;
    private Auto auto;
    private Script script;
    private WebSocketDeviceLog log = new WebSocketDeviceLog();

    public void script(String uuid, Script script, Device device) {
        this.uuid = uuid;
        this.script = script;
        auto = new Auto(uuid,script.getPackageName());


        log.push(uuid, "快手_查找模糊匹配作品");
        //等待程序运行完毕
        auto.wait(15000);
        //去除权限
        try {
            auto.findByText("允许",true).click();
            auto.wait(1000);
            auto.findByText("允许",false).click();
        } catch (DocumentException | IOException | InterruptedException e) { }

        try {
            auto.wait(5000);
            auto.findByText("我知道了",true).click();
        } catch (DocumentException | IOException | InterruptedException e) { }
        try {
            auto.findByXpatch("//android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.ImageButton", true).click();
        } catch (DocumentException | IOException | InterruptedException e) {
        }
        start();
    }

    /**
     * 正常浏览内容操作
     */
    private void start() {
        String additional = script.getCommentStr();
        int index = script.getWatchNum();
        auto.wait(2000);
        try {
            auto.findByXpatch("//android.widget.EditText", true).sendKeys(additional);
        } catch (DocumentException | IOException | InterruptedException e) { }
        auto.switchKeyboardforSystem(KeyboardEnum.SYSTEM_KEYBOARD);
        auto.wait(4000);
        auto.enter();
        auto.wait(2000);
        auto.switchKeyboardforSystem(KeyboardEnum.ADB_KEYBOARD);
        try {
            auto.findByText("作品", true).click();
        } catch (DocumentException | IOException | InterruptedException ee) { }
        auto.wait(2000);
        int num = 0;
        boolean isFor = true;
        for (int i = 0; num < index && isFor; i++) {
            try {
                List<Bound> bounds = auto.findsByXpatch("//*[@content-desc='头像']",true);
                if (bounds.size()<=1){
                    isFor = false;
                    return;
                }
                for (int i1 = 2; i1 < bounds.size () && isFor; i1++) {

                    if (script.getIsComment() == 1){
                        try{
                            bounds.get(i1).click();
                        }catch (IOException | InterruptedException e) { }
                    }else if (script.getIsDirectMessages() == 1){
                        try{
                            auto.findByText("关注",true).click();
                            auto.findByXpatch("//*[@content-desc='头像']",true).click();
                            auto.wait(2500);
                            //点击消息图标
                            try {
                                auto.findByXpatch("//android.widget.FrameLayout/android.widget.ImageButton",true).click();
                            } catch (DocumentException | IOException | InterruptedException e) { }
                            auto.wait(3000);
                            //准备发送消息
                            try {
                                String str = script.getDirectMessages();
                                if (str != null && str.length() > 0 && str.contains("\n")){
                                    String[] strings = str.split ("\n");
                                    int s = new ScriptUtils().random("随机评论", 0, strings.length);
                                    auto.findByText("请输入...",true).sendKeys(strings[s]);
                                }else if (str != null){
                                    auto.findByText("请输入...",true).sendKeys(str);
                                }
                            } catch (DocumentException | IOException | InterruptedException e) { }
                            try {
                                auto.findByText("发送",true).click();
                            } catch (DocumentException | IOException | InterruptedException e) { }

                        }catch (DocumentException | IOException | InterruptedException e) { }
                    }else if (script.getIsComment() == 1 && script.getIsDirectMessages() == 1) {
                        try{
                            bounds.get(i1).click();
                        }catch (IOException | InterruptedException e) { }
                        auto.wait(3000);
                        try{
                            auto.findByText("关注",true).click();
                            auto.findByXpatch("//*[@content-desc='头像']",true).click();
                            auto.wait(2500);
                            //点击消息图标
                            try {
                                auto.findByXpatch("//android.widget.FrameLayout/android.widget.ImageButton",true).click();
                            } catch (DocumentException | IOException | InterruptedException e) { }
                            auto.wait(3000);
                            //准备发送消息
                            try {
                                String str = script.getDirectMessages();
                                if (str != null && str.length() > 0 && str.contains("\n")){
                                    String[] strings = str.split ("\n");
                                    int s = new ScriptUtils().random("随机评论", 0, strings.length);
                                    auto.findByText("请输入...",true).sendKeys(strings[s]);
                                }else if (str != null){
                                    auto.findByText("请输入...",true).sendKeys(str);
                                }
                            } catch (DocumentException | IOException | InterruptedException e) { }
                            try {
                                auto.findByText("发送",true).click();
                            } catch (DocumentException | IOException | InterruptedException e) { }

                        }catch (DocumentException | IOException | InterruptedException e) { }

                    }
                    backToLabel();
                    num += 1;
                    isFor = num < index;
                }
            } catch (DocumentException e) { }
            auto.swipeUp();
            auto.swipeUp();
        }
        backToHome();
    }

    private void backToHome(){
        //返回到首页
        int index = 6;
        boolean isBock = true;
        while (isBock){
            try {
                auto.findByText("发现",true);
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

    private void backToLabel(){
        //返回到首页
        int index = 6;
        boolean isBock = true;
        while (isBock){
            try {
                auto.findByText("标签",true);
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
}
