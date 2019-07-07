package com.zciteam.util;

import com.zciteam.bean.Device;
import com.zciteam.dao.DeviceDao;
import com.zciteam.enums.ScopeEnum;

import java.util.Arrays;
import java.util.List;

public class ScopeDevice {

    public static List<Device> getDevice(String scope, String uuid, DeviceDao deviceDao){
        List<Device> devices;
        switch (ScopeEnum.stateOf(scope)){
            case ALL_DEVICE:
                devices = deviceDao.findAllDevice();
                break;
            case CURRENT_DEVICE:
                devices = Arrays.asList(deviceDao.findDevice(uuid));
                break;
            default:
                devices = deviceDao.findDeviceByGroupId(scope);
                break;
        }
        return devices;
    }
}
