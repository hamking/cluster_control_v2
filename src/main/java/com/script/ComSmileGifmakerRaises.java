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
import java.util.List;

/**
 *  快手_养号
 */
public class ComSmileGifmakerRaises {

    private String uuid;
    private Auto auto;
    private Script script;
    private WebSocketDeviceLog log = new WebSocketDeviceLog();

    public void script(String uuid, Script script, Device device, List<Device> devices) {
        this.uuid = uuid;
        this.script = script;
        auto = new Auto(uuid,script.getPackageName());
        auto.switchKeyboardforSystem(KeyboardEnum.ADB_KEYBOARD);

        log.push(uuid, "快手_养号");
        start();
    }

    /**
     * 正常浏览内容操作
     */
    private void start(){
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
        } catch (DocumentException e) { }

        try {
            auto.wait(5000);
            auto.findByText("我知道了",true).click();
        } catch (DocumentException e) { }

        //是否是同城或是热门执行不同操作
        if (script.getDirectMessagesType() == 4){ //同城
            System.out.println("同城操作");
            log.push(uuid, "同城操作");
            try {
                auto.findByText("//android.widget.HorizontalScrollView/android.widget.LinearLayout/android.view.View[2]", true).click();
            } catch (DocumentException e) {
            }
        }else { //热门
            System.out.println("热门操作");
            System.out.println("热门操作");
            try {
                auto.findByText("//android.widget.HorizontalScrollView/android.widget.LinearLayout/android.view.View[3]", true).click();
            } catch (DocumentException e) {
            }
        }

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
            } catch (DocumentException e) { }
            List<Android> bounds = new ArrayList<Android> ();
            try {
                bounds = auto.findsByXpatch("//*[@content-desc='头像']",true);
            } catch (DocumentException e) { }

            for (int i1 = 1; i1 < bounds.size(); i1++) {
                try {
                    auto.wait(1000);
                    auto.findByText("我知道了",true).click();
                } catch (DocumentException e) { }
                    bounds.get(i1).click();
//                    phoneItem.terminalLog("开始观看视频" + scriptDao.getScript(script).getWatchTime() + "秒");
                    auto.wait(1000 * script.getWatchTime());

                    //如果有置地按钮测点击
                    try{
                        auto.findByXpatch("(//android.widget.ImageView[@content-desc='喜欢'])[2]", true).click();
                        auto.wait(3000);
                        auto.swipeUp();
                    }catch (DocumentException e) { }
                    auto.swipeUp();

                    giveLike(script.getIsGiveLike () == 1);
                    comment(script.getIsComment () == 1);
                    focusOnAuthor(script.getIsFocusAuthor () == 1);
                    focusOnComment(script.getIsFocus () == 1);

                    auto.swipeUp();
                    auto.swipeUp();
                    auto.swipeUp();

                    satchNum -= 1;
                backToHome();
                //设置间隔
                auto.wait(1000 * script.getWatchTimeInterval());
            }
            auto.swipeUp();
            auto.swipeUp();
        }
    }

    /**
     * 是否点赞
     * @param isGiveLike isGiveLike
     */
    private void giveLike(boolean isGiveLike) {
        if (isGiveLike) {
            System.out.println("点赞操作");
            log.push(uuid, "点赞操作");
            try {
                auto.findByXpatch ("//*[@content-desc='喜欢']", true).click ();
            } catch (DocumentException e) { }
        }
    }

    /**
     * 是否评论
     * @param isComment isComment
     */
    private void comment(boolean isComment){
        if (isComment) {
            System.out.println("评论操作");
            log.push(uuid, "评论操作");
            try {
                String str = script.getCommentStr();
                if (str != null && str.length() > 0 && str.contains("\n")){
                    String[] strings = str.split ("\n");
                    int s = new ScriptUtils().random("", 0, strings.length);
                    auto.findByText("说点什么...",true).sendKeys(strings[s]);
                }else if (str != null){
                    auto.findByText("说点什么...",true).sendKeys(str);
                }
            } catch (DocumentException e) { }
            try {
                auto.findByText("发送",true).click();
            } catch (DocumentException e) { }
        }
    }

    /**
     * 是否关注作者
     * @param isFocus isFocus
     */
    private void focusOnAuthor(boolean isFocus){
        if (isFocus){
            System.out.println("开始关注作者");
            log.push(uuid, "开始关注作者");
            try {
                auto.findByText("关注",true).click();
            } catch (DocumentException e) { }
        }
    }

    /**
     * 是否关注评论者
     * @param isFocus isFocus
     */
    private void focusOnComment(boolean isFocus){
        if (isFocus){
            System.out.println("开始关注评论者");
            log.push(uuid, "开始关注评论者");
            //如果是图片切存在下标则点击
            try {
                int num = script.getFocusNum();
                boolean isFor = true;
                for (int j= 0; num > 0 && isFocus; j++) {
                    List<Android> bounds = auto.findsByXpatch("//android.widget.TableLayout/android.widget.TableRow",true);
                    if (bounds.size() <= 0){
                        isFor = false;
                        backToHome();
                        return;
                    }
                    for (int i = 1; i < (bounds.size() - 1) && isFocus; i++) {
                        bounds.get(i).clickOffsetX(-80);
                        auto.wait(2000);
                        try {
                            auto.findByText("i 关注",true).click();
                            auto.back();
                        } catch (DocumentException e) { }
                        num -= 1;
                    }
                    auto.swipeUp();
                }
            } catch (DocumentException e) { }
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
}