package com.zciteam.service.other;

import com.adb.manager.IDeviceBean;
import com.android.ddmlib.IDevice;
import com.zciteam.bean.Device;
import com.zciteam.dao.DeviceDao;
import com.zciteam.web.WebSocketDevice;
import com.zciteam.web.WebSocketDeviceListStart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Observable;
import java.util.Observer;

//不使用@Service 在com.adb包中已注入
public class DeviceMessage implements Observer {

    private DeviceDao deviceDao;

    private WebSocketDeviceListStart webSocketDeviceListStart;
    private WebSocketDevice webSocketDevice;

    @Autowired
    public void setWebSocketDeviceListStart(WebSocketDeviceListStart webSocketDeviceListStart) {
        this.webSocketDeviceListStart = webSocketDeviceListStart;
    }

    @Autowired
    public void setWebSocketDevice(WebSocketDevice webSocketDevice) {
        this.webSocketDevice = webSocketDevice;
    }

    @Autowired
    public void setDeviceDao(DeviceDao deviceDao) {
        this.deviceDao = deviceDao;
    }

    //许可设总数备数
    private int deviceTotal = 100;

    public int getDeviceTotal() {
        return deviceTotal;
    }

    public DeviceMessage() {
    }

    /**
     * 1, 首次请求是 将初始化全部设备未在线状态
     * 2, update() 将重新对设备状态的赋值
     */
    private void initDevice(){
        if (deviceDao != null) {
            deviceDao.initDevice ();
        }
    }

    @Override
    public void update(Observable o, Object arg) {

        IDeviceBean bean = (IDeviceBean)arg;
        switch (bean.getState()){
            case ONLINE:
                onlineDevice(bean.getDevice());
                break;
            case OFFLINE:
                offlineDevice(bean.getDevice());
                break;
            case UNKNOWN:
                break;
        }

        //若设备连接状态发生变化 则更新该设备的运行状态为 0 表示未运行
        deviceDao.updateRunningState(bean.getDevice().getSerialNumber(),0);

        //发送捂手消息列表变化及 建立每个设备的捂手消息
        Device device = deviceDao.findDevice(bean.getDevice().getSerialNumber());
        webSocketDeviceListStart.push(device.toString());
        webSocketDevice.push(bean.getDevice().getSerialNumber(), device.toString());
    }

    private void onlineDevice(IDevice device){
        int deviceNum = deviceDao.findAllDevice().size();
        if (deviceNum >= deviceTotal){
            return;
        }
        int row = addDevice(device);
        // <= 0 表示数据库中已存在该条数据 更新他  否侧添加
        if(row <= 0){
            deviceDao.updateDeviceState (device.getSerialNumber(), 1);
        }
    }

    @Transactional
    int addDevice(IDevice device){
        return deviceDao.intertDevice(device.getSerialNumber(),device.getSerialNumber(),1);
    }

    private void offlineDevice(IDevice device){
        deviceDao.updateDeviceState (device.getSerialNumber(), 0);
    }
}