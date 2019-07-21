package com.zciteam.service;

public interface ToolsService {

    /**
     * 初始化手机
     * @param scope 控制范围
     * @param uuid uuid
     * @return int
     */
    int getInitPhone(String scope, String uuid);

    /**
     * 设备关机
     */
    void shutdown();

    /**
     * 设备重启
     */
    void reboot();
}
