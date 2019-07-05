package com.adb.manager;

import org.springframework.beans.factory.annotation.Autowired;

public class DeviceManager {

    /**
     * 包装类
     */
    @Autowired
    private AndroidDebugBridgeWrapper androidDebugBridgeWrapper;

    /**
     * 设备监听器
     */
    @Autowired
    private DeviceChangeListener deviceChangeListener;

    /**
     * 启动方法
     */
    public void start() {

        new Thread (new Runnable () {
            @Override
            public void run() {
                androidDebugBridgeWrapper.addDeviceChangeListener(deviceChangeListener);
                androidDebugBridgeWrapper.init(false);

                System.out.println("Device manager start successful.");
            }
        }).start();
    }

    /**
     * 销毁方法
     */
    public void destory() {

        if (androidDebugBridgeWrapper == null) {

            return;
        }

        androidDebugBridgeWrapper.removeDeviceChangeListener(deviceChangeListener);
        androidDebugBridgeWrapper.terminate();
    }

}