package com.adb.manager;

import com.android.ddmlib.*;
import com.android.ddmlib.AndroidDebugBridge.IDeviceChangeListener;
import com.zciteam.enums.DeviceStartEnum;
import com.zciteam.service.other.DeviceMessage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Observable;

public class DeviceChangeListener extends Observable implements IDeviceChangeListener {

    @Autowired
    private DeviceMessage deviceService;

    public void addObserver(){
        this.addObserver(deviceService);
    }

    /**
     * 设备连接
     */
    @Override
    public void deviceConnected(IDevice device) {

        System.out.println("设备连接 " + device.getSerialNumber());

        IDeviceBean bean = new IDeviceBean(device, DeviceStartEnum.ONLINE);
        this.setChanged();
        this.notifyObservers(bean);
    }

    /**
     * 设备断开
     */
    @Override
    public void deviceDisconnected(IDevice device) {

        System.out.println("设备断开 " + device.getSerialNumber());

        IDeviceBean bean = new IDeviceBean(device, DeviceStartEnum.OFFLINE);
        this.setChanged();
        this.notifyObservers(bean);
    }


    /**
     * 当设备数据更改时发送，或者当设备上的客户机启动/终止时发送。
     */
    @Override
    public void deviceChanged(IDevice device, int changeMask) {

    }
}