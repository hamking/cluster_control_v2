package com.zciteam.service;

import com.zciteam.bean.Device;
import com.zciteam.dto.DeviceSituationResult;

import java.util.List;

public interface DeviceService {

    /**
     * 获取设备日志
     * @param uuid uuid
     * @return int
     */
    int deviceLog(String uuid);

    /**
     * 获取全部分组
     * @return int
     */
    List<String> grourdList();

    /**
     * 获取设备列表
     * @return int
     */
    List<Device> devlceList();

    /**
     * 获取设备在线情况  n台在线 n在离线 n授权设备
     * @return
     */
    DeviceSituationResult deviceDetails();

    /**
     * 重命名组
     * @param uuid uuid
     * @return int
     */
    int groudRename(String uuid, String groudId);

    /**
     * 新建组
     * @param uuid uuid
     * @return int
     */
    int addGroud(String uuid, String groudId);

    /**
     * 重命名设备昵称
     * @param uuid uuid
     * @return int
     */
    int nickNameRename(String uuid, String nickName);

    /**
     * 保存设备的个性化字段
     */
    int saveIndividuation(String uuid, String individuationString, int individuationInt, int individuationVar1, int individuationVar2);

    /**
     * 删除设备
     * @param uuid uuid
     * @return int
     */
    int delDevice(String uuid);
}
