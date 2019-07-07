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

        List<Device> devices = ScopeDevice.getDevice(scope,uuid,deviceDao);
        devices.forEach(device -> {
            new Thread (new Runnable () {
                @Override
                public void run() {
                    new Auto(device.getUuid()).home();
                }
            }).start();
        });
        return devices.size();
    }

    @Override
    public int back(String scope, String uuid) {
        List<Device> devices = ScopeDevice.getDevice(scope,uuid,deviceDao);
        devices.forEach(device -> {
            new Thread (new Runnable () {
                @Override
                public void run() {
                    new Auto(device.getUuid()).back();
                }
            }).start();
        });
        return devices.size();
    }

    @Override
    public int killProgress(String scope, String uuid) {
        List<Device> devices = ScopeDevice.getDevice(scope,uuid,deviceDao);
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
                    });
                }
            }).start();
        });
        return devices.size();
    }

    @Override
    public int lock(String scope, DeviceControlEnum controlEnum, String uuid) {
        List<Device> devices = ScopeDevice.getDevice(scope,uuid,deviceDao);
        devices.forEach(device -> {
            new Thread (new Runnable () {
                @Override
                public void run() {
                    switch (controlEnum){
                        case LOCK:
                            new Auto(device.getUuid()).lock();
                            break;
                        case UNLOCK:
                            new Auto(device.getUuid()).unLock();
                    }
                }
            }).start();
        });
        return devices.size();
    }

    @Override
    public int volume(String scope, DeviceControlEnum controlEnum, String uuid) {
        List<Device> devices = ScopeDevice.getDevice(scope,uuid,deviceDao);
        devices.forEach(device -> {
            new Thread (new Runnable () {
                @Override
                public void run() {
                    switch (controlEnum){
                        case VOLUMEUP:
                            new Auto(device.getUuid()).volumeUp();
                            break;
                        case VOLUMEDOWN:
                            new Auto(device.getUuid()).volumeDown();
                        case MUTE:
                            new Auto(device.getUuid()).volumeMute();
                    }
                }
            }).start();
        });
        return devices.size();
    }

    @Override
    public int reboot(String scope, String uuid) {
        List<Device> devices = ScopeDevice.getDevice(scope,uuid,deviceDao);
        devices.forEach(device -> {
            new Thread (new Runnable () {
                @Override
                public void run() {
                    new Auto(device.getUuid()).reboot();
                }
            }).start();
        });
        return devices.size();
    }

    @Override
    public int switchKeyboard(String scope, KeyboardEnum keyboardEnum, String uuid) {
        List<Device> devices = ScopeDevice.getDevice(scope,uuid,deviceDao);
        devices.forEach(device -> {
            new Thread (new Runnable () {
                @Override
                public void run() {
                    new Auto(device.getUuid()).switchKeyboardforSystem(keyboardEnum);
                }
            }).start();
        });
        return devices.size();
    }

    @Override
    public int installApp(String scope, String uuid, MultipartFile file) {

        //保存文件到指定目录
        String filePath = Directory.saveFile(DirectoryEnum.APPLICATION,file);

        if (filePath != null && !filePath.isEmpty()) {
            List<Device> devices = ScopeDevice.getDevice (scope, uuid, deviceDao);
            devices.forEach (device -> {
                new Thread (new Runnable () {
                    @Override
                    public void run() {
                        new Auto(device.getUuid()).install(filePath);
                    }
                }).start ();
            });
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public int uploadVideo(String scope, String uuid, MultipartFile multipartFile) {
        String filePath = Directory.saveFile(DirectoryEnum.VIDEO,multipartFile);

        if (filePath != null && !filePath.isEmpty()) {
            List<Device> devices = ScopeDevice.getDevice (scope, uuid, deviceDao);
            devices.forEach (device -> {
                new Thread (new Runnable () {
                    @Override
                    public void run() {
                        new Auto(device.getUuid()).rmFileMediaEventScript("rm -rf /sdcard/DCIM/*");
                        new Auto(device.getUuid()).mkdir(DeviceDirEnum.VIDEO.getStartInfo());
                        new Auto(device.getUuid()).pushFile(filePath, DeviceDirEnum.VIDEO.getStartInfo());
                        new Auto(device.getUuid()).refreshPhotoAlbum();

                        //保存到设备的功能附加字段中
                        deviceDao.updateFunction1(device.getUuid(), multipartFile.getOriginalFilename());
                    }
                }).start();
            });
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public int uploadImage(String scope, String uuid, MultipartFile multipartFile) {
        String filePath = Directory.saveFile(DirectoryEnum.IMAGE,multipartFile);

        if (filePath != null && !filePath.isEmpty()) {
            List<Device> devices = ScopeDevice.getDevice (scope, uuid, deviceDao);
            devices.forEach (device -> {
                new Thread (new Runnable () {
                    @Override
                    public void run() {
                        new Auto(device.getUuid()).rmFileMediaEventScript("rm -rf /sdcard/DCIM/*");
                        new Auto(device.getUuid()).pushFile(filePath, DeviceDirEnum.IMAGE.getStartInfo());
                        new Auto(device.getUuid()).refreshPhotoAlbum();
                    }
                }).start();
            });
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public int uploadJar(String scope, String uuid, MultipartFile multipartFile) {
        String filePath = Directory.saveFile(DirectoryEnum.OYHER,multipartFile);

        if (filePath != null && !filePath.isEmpty()) {
            List<Device> devices = ScopeDevice.getDevice (scope, uuid, deviceDao);
            devices.forEach (device -> {
                new Thread (new Runnable () {
                    @Override
                    public void run() {
                        new Auto(device.getUuid()).mkdir(DeviceDirEnum.JAR_UI.getStartInfo());
                        new Auto(device.getUuid()).pushFile(filePath, DeviceDirEnum.JAR_HELPER.getStartInfo() + "LvmamaXmlKit.jar");
                    }
                }).start();
            });
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public int uploadZip(String scope, String uuid, MultipartFile multipartFile) {
        String filePath = Directory.saveFile(DirectoryEnum.OYHER,multipartFile);

        if (filePath != null && !filePath.isEmpty()) {
            List<Device> devices = ScopeDevice.getDevice (scope, uuid, deviceDao);
            devices.forEach (device -> {
                new Thread (new Runnable () {
                    @Override
                    public void run() {
                        new Auto(device.getUuid()).pushFile(filePath, DeviceDirEnum.ZIP.getStartInfo());
                    }
                }).start();
            });
            return 1;
        }else {
            return 0;
        }
    }
}
