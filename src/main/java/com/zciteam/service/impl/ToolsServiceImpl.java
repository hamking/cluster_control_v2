package com.zciteam.service.impl;

import com.adb.auto.Auto;
import com.zciteam.bean.Device;
import com.zciteam.dao.DeviceDao;
import com.zciteam.enums.DeviceDirEnum;
import com.zciteam.service.ToolsService;
import com.zciteam.util.ScopeDevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToolsServiceImpl implements ToolsService {

    private DeviceDao deviceDao;
    @Autowired
    public void setDeviceDao(DeviceDao deviceDao) {
        this.deviceDao = deviceDao;
    }

    @Override
    public int getInitPhone(int scope, String uuid) {
        List<Device> devices = ScopeDevice.getDevice (scope, uuid, deviceDao);
        devices.forEach (device -> {
            new Thread (new Runnable () {
                @Override
                public void run() {
                    new Auto(device.getUuid()).mkdir("/data/local/tmp/local/tmp/");
                    new Auto(device.getUuid()).pushFile("/opt/LvmamaXmlKit.jar","/data/local/tmp/LvmamaXmlKit.jar");
                    new Auto(device.getUuid()).install("/opt/ADBKeyboard.apk");
                }
            }).start ();
        });
        return devices.size ();
    }
}
