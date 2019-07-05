package com.adb.manager;

import com.android.ddmlib.IDevice;
import com.zciteam.enums.DeviceStartEnum;

public class IDeviceBean {

    //安卓设备对象
    private IDevice device;

    //安卓设备联机状态
    private DeviceStartEnum state;

    public IDevice getDevice() {
        return device;
    }

    public void setDevice(IDevice device) {
        this.device = device;
    }

    public DeviceStartEnum getState() {
        return state;
    }

    public void setState(DeviceStartEnum state) {
        this.state = state;
    }

    /**
     * 构造方法
     * @param device 设备
     * @param state  转态  在线 离线
     */
    public IDeviceBean(IDevice device, DeviceStartEnum state){
        setDevice(device);
        setState(state);
    }
}
