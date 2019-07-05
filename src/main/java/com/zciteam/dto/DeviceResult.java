package com.zciteam.dto;

/**
 * 返回常规设备list
 * @param <T>
 */
public class DeviceResult<T> {
    private T deviceList;

    public DeviceResult(T deviceList) {
        this.deviceList = deviceList;
    }

    public T getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(T deviceList) {
        this.deviceList = deviceList;
    }
}
