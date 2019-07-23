package com.script;

import com.adb.auto.Auto;
import com.adb.auto.Bound;
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
import java.util.ArrayList;
import java.util.List;

/**
 * 抖音_关注抖音号作者粉丝
 */
public class ComUgcAwemePrivateMsgForAuthor {

    private String uuid;
    private Auto auto;
    private Script script;
    private WebSocketDeviceLog log = new WebSocketDeviceLog();

    public void script(String uuid, Script script, Device device) {
        this.uuid = uuid;
        this.script = script;
        auto = new Auto(uuid,script.getPackageName());
        auto.switchKeyboardforSystem(KeyboardEnum.ADB_KEYBOARD);


        log.push(uuid,"抖音_关注抖音号作者粉丝");
        auto.wait(15000);
        try {
            new ScriptUtils().onLogin("点击青少年模式");
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
            new ScriptUtils().onLogin("点击用户权限");
            auto.findByText("同意",true).click();
            auto.wait(5000);
            auto.back();
        } catch (DocumentException | IOException | InterruptedException e) {
        }

        //版本检测
        try {
            new ScriptUtils().onLogin("版本检测");
            auto.findByText("以后再说",true).click();
            auto.wait(5000);
        } catch (DocumentException | IOException | InterruptedException e) {
        }
        start();
        log.push(uuid,"关注指定抖音号粉丝结束");
    }

    /**
     * 正常浏览内容操作
     */
    private void start() {

        String str = script.getCommentStr();

        //点击首页
        try {
            auto.findByText("首页", true).click();
        } catch (DocumentException | IOException | InterruptedException e) {
        }

        //点击搜索图标
        try {
            auto.findByXpatch("//android.widget.ImageView[@content-desc='搜索']", true).click();
        } catch (DocumentException | IOException | InterruptedException e) {
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
        } catch (DocumentException | IOException | InterruptedException e) { }

        //点击搜索
        try {
            auto.findByText("搜索", true).click();
        } catch (DocumentException | IOException | InterruptedException e) { }

        auto.wait(3000);
        //点击进入详情
        try {
            auto.findByText("关注", true).clickOffsetX(-100);
        } catch (DocumentException | IOException | InterruptedException e) { }
        try {
            auto.findByText("已关注", false).clickOffsetX(-100);
        } catch (DocumentException | IOException | InterruptedException e) { }

        auto.wait(3000);
        //点击点击进入粉丝界面
        try {
            auto.findByText("粉丝", true).click();
        } catch (DocumentException | IOException | InterruptedException e) { }

        auto.wait(3000);

        int index = script.getWatchNum();
        for (int i = 0; index > 0; i++) {
            List<Bound> bounds = new ArrayList<Bound> ();
            //开始关注粉丝
            try {
                bounds = auto.findByTexts("关注",true);
            } catch (DocumentException e) {
            }

            boolean isS = true;
            for (int i1 = 0; i1 < bounds.size() && isS; i1++) {
                try {
                    bounds.get(i1).click();
                } catch (IOException | InterruptedException e) {
                }
                auto.wait(1000);
                try {
                    auto.findByXpatch("//android.widget.LinearLayout/android.webkit.WebView",true);
                    log.push(uuid,"**关注已上限, 请验证后继续**");
                    index = 0;
                    isS = false;
                } catch (DocumentException e) { }
                index -= 1;
            }
            try {
                auto.findByXpatch("//android.widget.LinearLayout/android.webkit.WebView",true);
                log.push(uuid,"**关注已上限, 请验证后继续**");
                index = 0;
            } catch (DocumentException  e) { }
            try {
                auto.findByText("暂时没有更多了",true);
                log.push(uuid,"**该抖音号粉丝已完成关注**");
                index = 0;
            } catch (DocumentException e) { }

            auto.swipeUp();
            auto.wait(3000);
        }
        auto.back();
        auto.wait(3000);
        auto.back();
        auto.wait(3000);
        auto.back();
    }
}
