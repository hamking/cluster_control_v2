package com.zciteam.dao;

import com.zciteam.bean.Device;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeviceDao {
    /**
     * 查找设备
     */
    List<Device> findAllDevice();

    /**
     * 初始化全部设备为为上线转态
     */
    int initDevice();

    /**
     * 根据uuid 查找设备
     * @param uuid uuid
     */
    Device findDevice(String uuid);

    /**
     * 查找全部组id
     */
    List<String> findAllGroupId();

    /**
     * 通过组id查找所有设备
     * @param groupId groupId
     * @return List
     */
    List<Device> findDeviceByGroupId(@Param("groupId") String groupId);

    /**
     * 上线设备
     * @return int
     */
    List<Integer> online();

    /**
     * 离线设备
     * @return int
     */
    List<Integer> offline();

    /**
     * 更新设备昵称
     * @param nickname nickname
     * @param uuid uuid
     */
    int renameNickName(@Param("uuid") String uuid, @Param("nickname") String nickname);

    /**
     * 重命名组昵称
     * @param groupId groupId
     * @param uuid uuid
     */
    int renameGroupId(@Param("uuid") String uuid, @Param("groupId") String groupId);

    /**
     * 更新给设备是否在运行
     * @param isRun isRun
     * @param uuid uuid
     */
    int updateRunningState(@Param("uuid") String uuid, @Param("isRun") int isRun);

    /**
     * 更新设备状态 是否已上线
     * @param state state
     * @param uuid uuid
     */
    int updateDeviceState(@Param("uuid") String uuid, @Param("state") int state);

    /**
     * 设备个性化操作的字段 具体用途随业务变化而变化
     */
    int updateIndividuationString(@Param("uuid") String uuid, @Param("individuationString") String individuationString);
    int updateIndividuationInt(@Param("uuid") String uuid, @Param("individuationInt") int individuationInt);
    int updateIndividuationVar1(@Param("uuid") String uuid, @Param("individuationVar1") int individuationVar1);
    int updateIndividuationVar2(@Param("uuid") String uuid, @Param("individuationVar2") int individuationVar2);

    String function1(@Param("uuid") String uuid);
    int updateFunction1(@Param("uuid") String uuid, @Param("function1") String function1);

    /**
     * 插入一条设备
     * @param nickname nickname
     * @param uuid uuid
     * @param state state
     */
    int intertDevice(@Param("uuid") String uuid, @Param("nickname") String nickname, @Param("state") int state);
}
