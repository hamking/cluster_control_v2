package com.script;

import com.adb.auto.Auto;
import com.zciteam.bean.Device;
import com.zciteam.bean.Script;
import com.zciteam.enums.DeviceDirEnum;
import com.zciteam.enums.DirectoryEnum;
import com.zciteam.enums.KeyboardEnum;
import com.zciteam.util.Directory;
import com.zciteam.web.WebSocketDeviceLog;
import org.dom4j.DocumentException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 抖音_发视频
 */
public class ComUgcAwemeSendVideo {

    private String uuid;
    private Auto auto;
    private Script script;
    private Device device;
    private Directory directory = new Directory();
    private WebSocketDeviceLog log = new WebSocketDeviceLog();

    private String filename = "";
    private String filePath = "";

    public void script(String uuid, Script script, Device device, List<Device> devices) {
        this.uuid = uuid;
        this.script = script;
        this.device = device;
        auto = new Auto(uuid,script.getPackageName());
        auto.switchKeyboardforSystem(KeyboardEnum.ADB_KEYBOARD);

        log.push(uuid,"抖音_发视频");

        //获取到视频
        Map map = directory.getAllFile(DirectoryEnum.VIDEO.getStartInfo(), false);
        List<List> lists = new ArrayList<>();
        map.forEach((k,v)->{
            List a = new ArrayList();
            a.add(k);
            a.add(v);
            lists.add(a);
        });
        for (int i = 0; i < devices.size(); i++) {

            if (device.getUuid().equals(devices.get(i).getUuid())){
                filePath = lists.get(i).get(0).toString();
                filename = lists.get(i).get(1).toString();
                break;
            }
        }
        System.out.println("---------------- " + filename);
        log.push(uuid,"正在同步视频到手机");
        auto.rmFileMediaEventScript("rm -rf /sdcard/DCIM/*");
        auto.mkdir(DeviceDirEnum.VIDEO.getStartInfo());
        auto.pushFile(filePath, DeviceDirEnum.VIDEO.getStartInfo());
        auto.refreshPhotoAlbum();
        auto.wait(10000);
        log.push(uuid,"正在同步完成");
        System.out.println("---------------- " + filename);

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
            log.push(uuid,"点击用户权限");
            auto.findByText("同意",true).click();
            auto.wait(5000);
            auto.back();
        } catch (DocumentException e) {
        }
        System.out.println("---------------- " + filename);

        //版本检测
        try {
            log.push(uuid,"版本检测");
            auto.findByText("以后再说",true).click();
            auto.wait(5000);
        } catch (DocumentException e) {
        }
        System.out.println("---------------- " + filename);

        start();
        log.push(uuid,"执行完毕");
    }

    /**
     * 正常浏览内容操作
     */
    private void start() {
        auto.back();
        log.push(uuid,"开始发布 " + filename);
        try {
            auto.findByXpatch ("//android.widget.ImageView[@content-desc='拍摄，按钮']", true).click(50,50);
        } catch (DocumentException e) {
        }
        auto.wait (3000);
        //点击关闭权限
        for (int i = 0; i < 3; i++) {
            try {
                auto.findByText ("允许", true).click();
            } catch (DocumentException e) {
            }
        }
        auto.wait(2000);
        log.push(uuid,"开始上传 " + filename);
        try {
            auto.findByText ("上传", true).click();
        } catch (DocumentException e) {
        }
        auto.wait(2000);
        try {
            auto.findByXpatch("//android.support.v7.widget.RecyclerView/android.widget.RelativeLayout/android.widget.ImageView", true).click ();
        } catch (DocumentException e) {
        }
        auto.wait(2000);

        log.push(uuid,"下一步");
        try {
            auto.findByText("下一步", true).click();
        } catch (DocumentException e) {
            e.printStackTrace ();
        }
        boolean isOk = true;
        int col = 0;
        while (isOk){
            auto.wait(1000);
            try {
                auto.findByText("正在合成中", true).click();
            } catch (DocumentException e) {
                isOk = false;
            }
            col ++;
            if (col > 100){
                isOk = false;
            }
        }
        try {
            auto.findByText("下一步", true).click();
        } catch (DocumentException e) {
            e.printStackTrace ();
        }
        auto.wait(3000);

        log.push(uuid,"输入标题 " + filename);
        //开始输入文字
        String[] path1 = filename.split("\\.");
        try {
            auto.findByXpatch("//android.widget.EditText", true).sendKeys(path1[0]);
        } catch (DocumentException e) {
        }
        auto.wait(500);
        log.push(uuid,"确认发布 " + filename);
        try {
            auto.findByXpatch("//android.widget.FrameLayout[@content-desc='发布']", true).click();
        } catch (DocumentException e) {
        }

        auto.back();
    }
}
