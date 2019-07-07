package com.zciteam.dao;

import com.zciteam.bean.Device;
import org.apache.ibatis.annotations.Param;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class DeviceDaoTest {

    @Autowired
    private DeviceDao deviceDao;
    /**
     * 查找设备
     */
//    List<Device> findAllDevice();
    @Test
    public void findAllDevice(){
        List<Device> deviceList = deviceDao.findAllDevice();
        System.out.println(deviceList.size());
    }

    /**
     * 根据uuid 查找设备
     */
//    Device findDevice(String uuid);
    @Test
    public void findDevice(){
        String uuid = "351BBIHHCJNW";
        Device device = deviceDao.findDevice(uuid);
        System.out.println(device.toString());
    }

    /**
     * 查找全部组id
     */
//    int findAllGroupId();
    @Test
    public void findAllGroupId(){
        List<String> strings = deviceDao.findAllGroupId();
        System.out.println(strings);
    }

    /**
     * 更新设备昵称
     */
//    int renameNickName(@Param("nickname") String nickname, @Param("uuid") String uuid);
    @Test
    public void renameNickName(){
        int num = deviceDao.renameNickName("测试","88MFDM626D3L");
        System.out.println(num);
    }

    /**
     * 重命名组昵称
     */
//    int renameGroupId(@Param("groupId") String groupId, @Param("uuid") String uuid);
    @Test
    public void renameGroupId(){
        int num = deviceDao.renameGroupId("88MFDM626D3L","");
        System.out.println(num);
    }

    /**
     * 更新给设备是否在运行
     */
//    int updateRunningState(@Param("isRun") int isRun, @Param("uuid") String uuid);
    @Test
    public void updateRunningState(){
        int num = deviceDao.updateRunningState ("88MFDM626D3L", 1);
        System.out.println(num);
    }

    /**
     * 更新设备状态 是否已上线
     */
//    int updateDeviceState(@Param("state") int state, @Param("uuid") String uuid);
    @Test
    public void updateDeviceState(){
        int num = deviceDao.updateDeviceState ("88MFDM626D3L", 1);
        System.out.println(num);
    }

    /**
     * 插入一条设备
     */
//    int intertDevice(@Param("nickname") String nickname, @Param("uuid") String uuid, @Param("state") String state);
    @Test
    public void intertDevice(){
        int num = deviceDao.intertDevice("fjsdhfjhsdkfhska","sfhsdhfjkhksd",1);
        System.out.println(num);
    }

    @Test
    public void findDeviceByGroupId(){
        List<Device> devices = deviceDao.findDeviceByGroupId("");
        System.out.println(devices.size());
    }
}