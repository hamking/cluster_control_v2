package com.script;

import com.adb.auto.Auto;
import com.adb.auto.Android;
import com.adb.util.ScriptUtils;
import com.zciteam.bean.Device;
import com.zciteam.bean.Script;
import com.zciteam.enums.KeyboardEnum;
import com.zciteam.web.WebSocketDeviceLog;
import org.dom4j.DocumentException;

import javax.script.ScriptEngineManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 抖音_养号
 */
public class ComUgcAwemeRaises {

    private String uuid;
    private Auto auto;
    private Script script;
    private WebSocketDeviceLog log = new WebSocketDeviceLog();

    public void script(String uuid, Script script, Device device, List<Device> devices) {
        this.uuid = uuid;
        this.script = script;
        auto = new Auto(uuid,script.getPackageName());
        auto.switchKeyboardforSystem(KeyboardEnum.ADB_KEYBOARD);


        log.push(uuid,"抖音_养号");

        String scriptMsg = ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + "\n" +
                "脚本执行详情:" + "\n" +
                "点赞: " + (script.getIsGiveLike() == 1 ? "是":"否") + "\n" +
                "评论: " + (script.getIsComment() == 1 ? "是":"否") + "\n"+
                "关注评论者: " + (script.getIsFocus() == 1 ? "是":"否") + "\n"+
                "每个视频关注总量: " + script.getFocusNum() + "\n"+
                "关注作者: " + (script.getIsFocus() == 1 ? "是":"否") + "\n"+
                "每个视频浏览时长: " + script.getWatchTime() + "毫秒" + "\n"+
                "浏览视频间隔时长: " + script.getWatchTimeInterval() + "毫秒" + "\n"+
                "观看总量: " + script.getWatchNum() + "个" + "\n"+
                "从第n个视频观看: " + script.getNumStart() + "\n"+
                "评论话术: " + script.getCommentStr() + "\n" +
                ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>";
        log.push(uuid,scriptMsg);

        start();

        log.push(uuid,"本次操作结束");
    }

    /**
     * 正常浏览内容操作
     */
    private void start() {

        auto.wait(15000);
        try {
            log.push(uuid,"点击青少年模式");
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
            log.push(uuid,"检测用户权限");
            auto.findByText("同意",true).click();
            auto.wait(5000);
            auto.back();
        } catch (DocumentException e) {
        }

        //版本检测
        try {
            log.push(uuid,"版本检测");
            auto.findByText("以后再说",true).click();
            auto.wait(5000);
        } catch (DocumentException e) {
        }

        readArticle(script.getWatchNum());
    }

    public void readArticle(int total) {

        auto.swipeUp();

        //如果有设置视频从第几个开始测滑动到改视频
        for (int i = 0; i < script.getNumStart(); i++) {
            auto.swipeUp();
        }

            for (int i = 0; i < total; i++) {
                //是广告则跳出
                try {
                    //广告检测
                    if (auto.isFindNoteForText("//*[@content-desc='视频']","[t]",true)) {
                        auto.swipeUp();
                        log.push(uuid,"跳出广告");
                        continue;
                    }
                } catch (DocumentException e) {}
                //  观看视频
                readVideo(script.getWatchTime() * 1000);
                //  是否点赞
                isCheckLike(script.getIsGiveLike());
                //  是否评论该视频
                isComment(script.getIsComment());
                //  是否关注当前视频用户
                isCheckAttention(script.getIsFocusAuthor());
                //  是否关注评论的人
                isCheckUserLikeList(script.getIsFocus());

                auto.wait(script.getWatchTimeInterval() * 1000);

                auto.swipeUp();
            }
    }

    /** 是否评论该视频 -- */
    private void isComment(int isComment) {
        if (isComment == 1) {
            log.push(uuid,"评论该视频");
            try {
                //  todo:提示xpath不存在 -已解决
                new ScriptUtils().onLogin("点击消息按钮，进入消息列表");
                auto.findByXpatch("//android.widget.FrameLayout[3]/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ImageView",true).click();
                auto.wait(5000);
            } catch (DocumentException e) {
            }
            try {
                //  todo:提示xpath不存在 -已解决
                new ScriptUtils().onLogin("点击评论窗口");
                auto.findByText("留下你的精彩评论吧",true).click();
                auto.wait(5000);
            } catch (DocumentException e) {
                auto.wait(3000);
                auto.back();
            }
            try {
                //  todo：提示越界异常 -已解决
                new ScriptUtils().onLogin("输入文字");
                String[] strings = script.getCommentStr().split ("\n");
                int s = new ScriptUtils().random("随机评论", 0, strings.length);
                auto.findByXpatch("//android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.EditText",true).sendKeys(strings[s]);
            } catch (DocumentException e) {
                auto.wait(3000);
                auto.back();
            }
            try {
                new ScriptUtils().onLogin("点击发送文本");
                auto.findByXpatch("//android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.ImageView[3]",true).click();
            } catch (DocumentException e) {
                auto.wait(3000);
                auto.back();
            }
            auto.wait(1000);
            backToHome();
            log.push(uuid,"评论结束");
        }else if (isComment == 0){
            log.push(uuid,"跳过评论该视频");
        }
    }

    /** 关注评论的人 -- */
    private void isCheckUserLikeList(int i) {
        if (i == 1) {
            log.push(uuid,"关注评论的人");
            //关注了第几个变量
            int index = 0;

            try {
                auto.findByXpatch("//android.widget.FrameLayout[3]/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ImageView", true).click();
            } catch (DocumentException e) { }
            auto.wait(4000);
            for (int j = 0; j < script.getFocusNum() && (index < script.getFocusNum()); j++) {
                //获取有几个评论的人
                List<Android> bounds = new ArrayList<>();
                try {
                    bounds = auto.findsByXpatch("//android.widget.LinearLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.widget.FrameLayout", true);
                } catch (DocumentException e) {
                }
                auto.wait(2000);
                log.push(uuid,"当前界面可关注数: "+bounds.size());
                if (bounds.size() == 0){
                    auto.back();
                    index = script.getFocusNum() + 1;
                }
                //去除一头一尾 (防止未知错误)
                boolean isS = true;
                for (int k = 1; k < bounds.size() - 1 && isS; k++) {
                    index += 1;
                    auto.wait(1000);
                    try {
                        auto.findByXpatch("//android.widget.LinearLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.widget.FrameLayout["+ (k+1) +"]/android.view.View/android.widget.ImageView[1]",true).click();
                    } catch (DocumentException e) {
                        log.push(uuid,"**异常! 进行下一轮");
                        continue;
                    }

                    auto.wait(3000);
                    try {
                        auto.findByText("关注",true).click();
                    } catch (DocumentException e) { }

                    auto.wait(3000);
                    try {
                        auto.findByXpatch("//android.widget.LinearLayout/android.webkit.WebView",true);
                        index = script.getFocusNum() + 1;
                        log.push(uuid,"**关注已上限, 请验证后继续**");
                        isS = false;
                    } catch (DocumentException e) { }

                    auto.back();

                    //如果发现'没有更多就返回'文字
                    try {
                        auto.findByText("暂时没有更多了",true);
                        index = script.getFocusNum() + 1;
                        auto.back();
                        isS = false;
                    } catch (DocumentException e) { }

                    log.push(uuid,"关注第 " + (index + 1) + "   共: " + script.getFocusNum());
                    if (index > script.getFocusNum()){
                        auto.back();
                        return;
                    }
                }
                auto.swipeUp();
            }
            auto.back();
        }else if (i == 0) {
            new ScriptUtils().onLogin("跳过关注评论的人");
        }
    }

    /** 是否关注当前作者 -- */
    private void isCheckAttention(int b) {
        if (b == 1) {
            try {
                log.push(uuid,"点击关注该视频作者");
                auto.findByXpatch("//android.widget.LinearLayout/android.widget.FrameLayout[1]/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.ImageView",true).click();
                auto.wait(6000);
            } catch (DocumentException e) {
            }
        } else if (b == 0) {
            log.push(uuid,"跳过关注该视频作者");
        }
    }

    /** 是否点赞 -- */
    private void isCheckLike(int b) {
        if (b == 1){
            try {
                log.push(uuid,"点赞");
                auto.findByXpatch("//android.widget.FrameLayout[2]/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ImageView",true).click();
                auto.wait(6000);
            } catch (DocumentException e) {
            }
        }else if (b == 0) {
            log.push(uuid,"跳过点赞");
        }

    }

    /** 观看视频时长 -- */
    private void readVideo(int time) {
        log.push(uuid,"开始观看视频" + time / 1000 + "秒");
        auto.wait(time);
    }

    private void backToHome(){
        //返回到首页
        int index = 3;
        boolean isBock = true;
        while (isBock){
            try {
                auto.findByXpatch("//android.widget.ImageView[@content-desc='表情']",true);
                auto.back();
                isBock = false;
            } catch (DocumentException e) {
                if (index <= 0){
                    isBock = false;
                }
                index -= 1;
            }
        }
    }
}
