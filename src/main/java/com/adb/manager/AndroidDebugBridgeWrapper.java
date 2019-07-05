package com.adb.manager;

import com.android.ddmlib.AndroidDebugBridge;
import com.android.ddmlib.AndroidDebugBridge.IDeviceChangeListener;
import com.android.ddmlib.DdmPreferences;

public class AndroidDebugBridgeWrapper {
    /**
     * android bridge
     */
    private AndroidDebugBridge mAdbBridge;

    public void init(boolean clientSupport) {

        AndroidDebugBridge.init(clientSupport);

        String os = System.getProperty("os.name").toLowerCase();
        String path = "";
        if (os.toLowerCase().startsWith("mac")){
            path = "/usr/local/bin/adb";
        }else{
            path = "/usr/bin/adb";
        }
        mAdbBridge = AndroidDebugBridge.createBridge(path, false);

        //设置超时时间
        DdmPreferences.setTimeOut(100);
    }

    /**
     * 注册设备监听器
     *
     * @param listener 监听器
     */
    public void addDeviceChangeListener(IDeviceChangeListener listener) {

        AndroidDebugBridge.addDeviceChangeListener(listener);
    }

    /**
     * 移除监听器
     *
     * @param listener 监听器
     */
    public void removeDeviceChangeListener(IDeviceChangeListener listener) {

        AndroidDebugBridge.removeDeviceChangeListener(listener);
    }

    public void terminate() {

        AndroidDebugBridge.terminate();
    }

    public void disconnectBridge() {

        AndroidDebugBridge.disconnectBridge();
    }
}
