package com.script;

import com.adb.auto.Auto;
import com.adb.auto.Android;
import com.adb.util.ScriptUtils;
import com.zciteam.bean.Device;
import com.zciteam.bean.Script;
import com.zciteam.enums.KeyboardEnum;
import com.zciteam.web.WebSocketDeviceLog;
import org.dom4j.DocumentException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 抖音_私信
 */
public class ComUgcAwemePrivateMsg {

    private String uuid;
    private Auto auto;
    private Script script;
    private WebSocketDeviceLog log = new WebSocketDeviceLog();

    public void script(String uuid, Script script, Device device, List<Device> devices) {
        this.uuid = uuid;
        this.script = script;
        auto = new Auto(uuid,script.getPackageName());
        auto.switchKeyboardforSystem(KeyboardEnum.ADB_KEYBOARD);


        log.push(uuid,"抖音_私信");
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
        isUserFriend();
    }

    private void isUserFriend(){
        log.push (uuid,"开始私信");
        try {
            log.push(uuid,"点击我的页面，进入个人中心");
            auto.findByXpatch("//android.widget.FrameLayout[2]/android.widget.LinearLayout/android.widget.FrameLayout[5]",true).click();
            auto.wait(5000);
        } catch (DocumentException e) {}
        try {
            log.push(uuid,"点击进入用户关注列表");
            auto.findByText("关注",true).click();
            auto.wait(5000);
        } catch (DocumentException e) {
        }

        List<String> nameList = new ArrayList<> ();
        //  遍历私信所有好友
        int num = script.getDirectMessagesNum();
        //获取一屏幕有几个人
        int nameArr = 0;
        int index = 0;
        try {
            nameArr = auto.findsByXpatch("//android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView",true).size();
        } catch (DocumentException e) {
        }
        for (int j = 0; j < (num > nameArr ? (num / nameArr) : 1); j++) {
            List<Android> bounds = new ArrayList<Android>();
            //获取当前界面有几个可点击项
            try {
                bounds = auto.findsByXpatch("//android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView",true);
            } catch (DocumentException e) {
            }
            for (int i = 1; i < bounds.size(); i++) {
                Map<String, String> map = new HashMap<> ();
                map = bounds.get(i).getNoteMap();
                if (nameList.contains(map.get("text"))) {
                    continue;
                }
                nameList.add(map.get("text"));
                try {
                    switch (script.getDirectMessagesType()){
                        case 0:
                            bounds.get(i).click();
                            break;
                        case 1:
                            auto.findText("已关注", true).clickOffsetX (-100);
                            break;
                        case 2:
                            auto.findText("互相关注", true).clickOffsetX (-100);
                            break;
                    }
                    auto.wait(5000);

                    //点击发送消息
                    log.push(uuid,"准备发送消息");
                    try {
                        auto.findByXpatch ("//android.widget.ImageView[@content-desc='私信']", true).click();
                    } catch (DocumentException e) {
                    }
                    //点击多余提示
                    try {
                        auto.findByXpatch ("//android.support.v7.widget.LinearLayoutCompat/android.widget.ScrollView/android.widget.LinearLayout/android.widget.Button[1]", true).click ();
                    } catch (DocumentException e) { }
                    //获取数据库私信内容
                    String directMessage = script.getDirectMessages();
                    if (directMessage.contains("\n")){
                        String[] strings = directMessage.split ("\n");
                        //点击文本框并输入文字
                        int s = new ScriptUtils().random("随机私信", 0, strings.length);
                        try {
                            auto.findByXpatch ("//android.widget.LinearLayout/android.widget.FrameLayout/android.widget.EditText", true).sendKeys (strings[s]);
                        } catch (DocumentException e) {
                        }
                        //发送文字
                        auto.wait (1000);
                        try {
                            auto.findByXpatch ("//android.widget.ImageView[@content-desc='发送']", true).click ();
                        } catch (DocumentException e) {
                        }
                    }else {
                        try {
                            auto.findByXpatch ("//android.widget.LinearLayout/android.widget.FrameLayout/android.widget.EditText", true).sendKeys(directMessage);
                        } catch (DocumentException e) {
                        }
                        //发送文字
                        auto.wait (1000);
                        try {
                            auto.findByXpatch ("//android.widget.ImageView[@content-desc='发送']", true).click ();
                        } catch (DocumentException e) {
                        }
                    }

                    //==1是单项好友不发送图片
                    if (script.getDirectMessagesType() == 2){
                        //获取私信图片
                        String directMessageImage = script.getDirectMessagesImage();
                        if (directMessageImage.length() > 0){
                            //点击图标
                            try {
                                auto.findByXpatch ("//android.widget.ImageView[@content-desc='图片']", true).click();
                            } catch (DocumentException e) {
                            }
                            //选择图片
                            auto.wait (500);
                            try {
                                auto.findByXpatch ("//android.widget.FrameLayout[1]/android.support.v7.widget.RecyclerView/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ImageView", true).click ();
                            } catch (DocumentException e) {
                            }

                            //发送图片
                            try {
                                auto.findByText ("发送(1)", true).click ();
                            } catch (DocumentException e) {
                            }
                        }
                    }

                    auto.wait(1000);
                    auto.back();
                    auto.wait(2000);
                    auto.back();
                    auto.wait(2000);
                } catch (DocumentException e) {
                    log.push (uuid,"审查中...");
                }
            }
            auto.swipeUp();
        }
        auto.back();
    }
}
