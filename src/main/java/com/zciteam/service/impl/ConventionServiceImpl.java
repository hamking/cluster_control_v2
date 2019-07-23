package com.zciteam.service.impl;

import com.adb.auto.Auto;
import com.zciteam.bean.Device;
import com.zciteam.dao.DeviceDao;
import com.zciteam.enums.DeviceControlEnum;
import com.zciteam.enums.DeviceDirEnum;
import com.zciteam.enums.KeyboardEnum;
import com.zciteam.service.ConventionService;
import com.zciteam.util.Directory;
import com.zciteam.enums.DirectoryEnum;
import com.zciteam.util.ScopeDevice;
import com.zciteam.web.WebSocketDeviceLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConventionServiceImpl implements ConventionService {

    private DeviceDao deviceDao;

    @Autowired
    public void setDeviceDao(DeviceDao deviceDao) {
        this.deviceDao = deviceDao;
    }

    @Override
    public int home(String scope, String uuid){

        List<Device> devices = new ScopeDevice().getDevice(scope,uuid,deviceDao);
        devices.forEach(device -> {
            new Thread (new Runnable () {
                @Override
                public void run() {
                    new Auto(device.getUuid()).home();
                    new WebSocketDeviceLog().push(device.getUuid(),"主页操作");
                }
            }).start();
        });
        return devices.size();
    }

    @Override
    public int back(String scope, String uuid) {
        List<Device> devices = new ScopeDevice().getDevice(scope,uuid,deviceDao);
        devices.forEach(device -> {
            new Thread (new Runnable () {
                @Override
                public void run() {
                    new Auto(device.getUuid()).back();
                    new WebSocketDeviceLog().push(device.getUuid(),"返回操作");
                }
            }).start();
        });
        return devices.size();
    }

    @Override
    public int killProgress(String scope, String uuid) {
        List<Device> devices = new ScopeDevice().getDevice(scope,uuid,deviceDao);
        devices.forEach(device -> {
            new Thread (new Runnable () {
                @Override
                public void run() {
                    List<String> list = new ArrayList<> ();
                    list.add("com.ss.android.ugc.aweme");
                    list.add("com.smile.gifmaker");

//                    list.add("com.ss.android.article.lite");
//                    list.add("com.sohu.infonews");
//                    list.add("com.jifen.qukan");
//                    list.add("com.songheng.eastnews");
//                    list.add("com.qiushibaike.inews");
//                    list.add("com.baidu.minivideo");
//                    list.add("com.lite.infoflow");
//                    list.add("com.ldzs.zhangxin");
//                    list.add("com.huolea.bull");
//                    list.add("com.coohua.xinwenzhuan");
//                    list.add("com.yanhui.qktx");
//                    list.add("com.zhangku.qukandian");
//                    list.add("com.expflow.reading");
//                    list.add("com.cashtoutiao");
//                    list.add("com.toutiao.news");
//                    list.add("com.ly.taotoutiao");
//                    list.add("com.jm.video");
//                    list.add("com.android.dialer");
                    list.forEach(l->{
                        new Auto(device.getUuid()).kill(l);
                        new WebSocketDeviceLog().push(device.getUuid(),"清空进程: "+l);
                    });
                }
            }).start();
        });
        return devices.size();
    }

    @Override
    public int lock(String scope, DeviceControlEnum controlEnum, String uuid) {
        List<Device> devices = new ScopeDevice().getDevice(scope,uuid,deviceDao);
        devices.forEach(device -> {
            new Thread (new Runnable () {
                @Override
                public void run() {
                    switch (controlEnum){
                        case LOCK:
                            new Auto(device.getUuid()).lock();
                            new WebSocketDeviceLog().push(device.getUuid(),"锁屏幕");
                            break;
                        case UNLOCK:
                            new Auto(device.getUuid()).unLock();
                            new WebSocketDeviceLog().push(device.getUuid(),"解锁");
                    }
                }
            }).start();
        });
        return devices.size();
    }

    @Override
    public int volume(String scope, DeviceControlEnum controlEnum, String uuid) {
        List<Device> devices = new ScopeDevice().getDevice(scope,uuid,deviceDao);
        devices.forEach(device -> {
            new Thread (new Runnable () {
                @Override
                public void run() {
                    switch (controlEnum){
                        case VOLUMEUP:
                            new Auto(device.getUuid()).volumeUp();
                            new WebSocketDeviceLog().push(device.getUuid(),"音量加");
                            break;
                        case VOLUMEDOWN:
                            new Auto(device.getUuid()).volumeDown();
                            new WebSocketDeviceLog().push(device.getUuid(),"音量减");
                        case MUTE:
                            new Auto(device.getUuid()).volumeMute();
                            new WebSocketDeviceLog().push(device.getUuid(),"静音");
                    }
                }
            }).start();
        });
        return devices.size();
    }

    @Override
    public int reboot(String scope, String uuid) {
        List<Device> devices = new ScopeDevice().getDevice(scope,uuid,deviceDao);
        devices.forEach(device -> {
            new Thread (new Runnable () {
                @Override
                public void run() {
                    new Auto(device.getUuid()).reboot();
                    new WebSocketDeviceLog().push(device.getUuid(),"设备重启...\n设备断开...");
                }
            }).start();
        });
        return devices.size();
    }

    @Override
    public int switchKeyboard(String scope, KeyboardEnum keyboardEnum, String uuid) {
        List<Device> devices = new ScopeDevice().getDevice(scope,uuid,deviceDao);
        devices.forEach(device -> {
            new Thread (new Runnable () {
                @Override
                public void run() {
                    new Auto(device.getUuid()).switchKeyboardforSystem(keyboardEnum);
                    new WebSocketDeviceLog().push(device.getUuid(),keyboardEnum.getStateInfo());
                }
            }).start();
        });
        return devices.size();
    }

    @Override
    public int installApp(String scope, String uuid, List<MultipartFile> multipartFiles) {

        multipartFiles.forEach(file->{
            //保存文件到指定目录
            String filePath = new Directory().saveFile(DirectoryEnum.APPLICATION,file);
            List<Device> devices = new ScopeDevice().getDevice (scope, uuid, deviceDao);
            devices.forEach (device -> {
                new Thread (new Runnable () {
                    @Override
                    public void run() {
                        new WebSocketDeviceLog().push(device.getUuid(),"安装软件请稍后...");
                        new WebSocketDeviceLog().push(device.getUuid(),"注意手机安装提示...");
                        new Auto(device.getUuid()).install(filePath);
                        new WebSocketDeviceLog().push(device.getUuid(),"安装成功");
                    }
                }).start ();
            });
        });

        if (multipartFiles != null && !multipartFiles.isEmpty()) {
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public int uploadVideo(String scope, String uuid, List<MultipartFile> multipartFiles) {

        multipartFiles.forEach(multipartFile->{
            String filePath = new Directory().saveFile(DirectoryEnum.VIDEO,multipartFile);
            List<Device> devices = new ScopeDevice().getDevice (scope, uuid, deviceDao);
            devices.forEach (device -> {
                new Thread (new Runnable () {
                    @Override
                    public void run() {
                        new WebSocketDeviceLog().push(device.getUuid(),"导入视频");
                        new Auto(device.getUuid()).rmFileMediaEventScript("rm -rf /sdcard/DCIM/*");
                        new Auto(device.getUuid()).mkdir(DeviceDirEnum.VIDEO.getStartInfo());
                        new Auto(device.getUuid()).pushFile(filePath, DeviceDirEnum.VIDEO.getStartInfo());
                        new Auto(device.getUuid()).refreshPhotoAlbum();

                        //保存到设备的功能附加字段中
                        deviceDao.updateFunction1(device.getUuid(), multipartFile.getOriginalFilename());
                    }
                }).start();
            });
        });

        if (multipartFiles != null && !multipartFiles.isEmpty()) {
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public int uploadImage(String scope, String uuid, List<MultipartFile> multipartFiles) {

        multipartFiles.forEach(multipartFile->{
            String filePath = new Directory().saveFile(DirectoryEnum.IMAGE,multipartFile);
            List<Device> devices = new ScopeDevice().getDevice (scope, uuid, deviceDao);
            devices.forEach (device -> {
                new Thread (new Runnable () {
                    @Override
                    public void run() {
                        new WebSocketDeviceLog().push(device.getUuid(),"导入图片");
//                        new Auto(device.getUuid()).rmFileMediaEventScript("rm -rf /sdcard/DCIM/*");
                        new Auto(device.getUuid()).pushFile(filePath, DeviceDirEnum.IMAGE.getStartInfo());
                        new Auto(device.getUuid()).refreshPhotoAlbum();
                    }
                }).start();
            });
        });

        if (multipartFiles != null && !multipartFiles.isEmpty()) {
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public int uploadJar(String scope, String uuid, List<MultipartFile> multipartFiles) {

        multipartFiles.forEach(multipartFile->{
            String filePath = new Directory().saveFile(DirectoryEnum.OYHER,multipartFile);
            List<Device> devices = new ScopeDevice().getDevice (scope, uuid, deviceDao);
            devices.forEach (device -> {
                new Thread (new Runnable () {
                    @Override
                    public void run() {
                        new WebSocketDeviceLog().push(device.getUuid(),"导入助手");
                        new Auto(device.getUuid()).mkdir(DeviceDirEnum.JAR_UI.getStartInfo());
                        new Auto(device.getUuid()).pushFile(filePath, DeviceDirEnum.JAR_HELPER.getStartInfo() + "LvmamaXmlKit.jar");
                    }
                }).start();
            });
        });

        if (multipartFiles != null && !multipartFiles.isEmpty()) {
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public int uploadZip(String scope, String uuid, List<MultipartFile> multipartFiles) {

        multipartFiles.forEach(multipartFile->{
            String filePath = new Directory().saveFile(DirectoryEnum.OYHER,multipartFile);
            List<Device> devices = new ScopeDevice().getDevice (scope, uuid, deviceDao);
            devices.forEach (device -> {
                new Thread (new Runnable () {
                    @Override
                    public void run() {
                        new Auto(device.getUuid()).pushFile(filePath, DeviceDirEnum.ZIP.getStartInfo());
                    }
                }).start();
            });
        });

        if (multipartFiles != null && !multipartFiles.isEmpty()) {
            return 1;
        }else {
            return 0;
        }
    }
}
