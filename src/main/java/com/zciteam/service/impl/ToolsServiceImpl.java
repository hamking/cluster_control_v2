package com.zciteam.service.impl;

import com.adb.auto.Auto;
import com.zciteam.bean.Device;
import com.zciteam.dao.DeviceDao;
import com.zciteam.enums.DeviceDirEnum;
import com.zciteam.service.ToolsService;
import com.zciteam.util.ScopeDevice;
import com.zciteam.web.WebSocketDeviceLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ToolsServiceImpl implements ToolsService {

    private DeviceDao deviceDao;
    @Autowired
    public void setDeviceDao(DeviceDao deviceDao) {
        this.deviceDao = deviceDao;
    }

    @Override
    public int getInitPhone(String scope, String uuid) {
        List<Device> devices = new ScopeDevice().getDevice (scope, uuid, deviceDao);
        devices.forEach (device -> {
            new Thread (new Runnable () {
                @Override
                public void run() {
                    new WebSocketDeviceLog ().push(device.getUuid(),"设备初始化... %0");
                    new Auto(device.getUuid()).mkdir("/data/local/tmp/local/tmp/");
                    new WebSocketDeviceLog ().push(device.getUuid(),"设备初始化... %30");
                    new Auto(device.getUuid()).pushFile("/opt/LvmamaXmlKit.jar","/data/local/tmp/LvmamaXmlKit.jar");
                    new WebSocketDeviceLog ().push(device.getUuid(),"设备初始化... %50");
                    new Auto(device.getUuid()).install("/opt/ADBKeyboard.apk");
                    new WebSocketDeviceLog ().push(device.getUuid(),"设备初始化... %70");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace ();
                    }
                    new Auto(device.getUuid()).install("/opt/googleKeyboard.apk");
                    new WebSocketDeviceLog ().push(device.getUuid(),"设备初始化... %100");

                }
            }).start ();
        });
        return devices.size ();
    }

    @Override
    public void shutdown() {
        try {
            Runtime.getRuntime().exec("shutdown -h now").waitFor();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace ();
        }
    }

    @Override
    public void reboot() {
        try {
            Runtime.getRuntime().exec("shutdown -r now").waitFor();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace ();
        }
    }
}
