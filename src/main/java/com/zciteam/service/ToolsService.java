package com.zciteam.service;

public interface ToolsService {

    /**
     * 初始化手机
     * @param scope 控制范围
     * @param uuid uuid
     * @return int
     */
    int getInitPhone(String scope, String uuid);
}
