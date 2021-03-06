package com.zciteam.service.impl;

import com.zciteam.bean.Device;
import com.zciteam.dao.DeviceDao;
import com.zciteam.dto.DeviceSituationResult;
import com.zciteam.service.DeviceService;
import com.zciteam.service.other.DeviceMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {

    private DeviceDao deviceDao;
    private DeviceMessage deviceMessage;

    @Autowired
    public void setDeviceDao(DeviceDao deviceDao) {
        this.deviceDao = deviceDao;
    }

    @Autowired
    public void setDeviceMessage(DeviceMessage deviceMessage) {
        this.deviceMessage = deviceMessage;
    }

    @Override
    public int deviceLog(String uuid) {
        deviceDao.findDeviceByGroupId(uuid);
        return 0;
    }

    @Override
    public List<String> grourdList() {

        List<String> list = deviceDao.findAllGroupId();
        List<String> results = new ArrayList<>();
        if (list != null){
            list.forEach (l->{
                if (!results.contains(l)){
                    results.add(l);
                }
            });
            return results;
        }
        return null;
    }

    @Override
    public List<Device> devlceList() {
        List<Device> list = deviceDao.findAllDevice();
        if (list != null){
            return list;
        }
        return null;
    }

    @Override
    public DeviceSituationResult deviceDetails() {

        List<Integer> online = deviceDao.online();
        List<Integer> offline = deviceDao.offline();
        int authorization = deviceMessage.getDeviceTotal();

        return new DeviceSituationResult(online.size(),offline.size(),authorization);
    }

    @Override
    public int groudRename(String uuid, String groudId) {

        int result = deviceDao.renameGroupId(uuid,groudId);
        return result;
    }

    @Override
    public int addGroud(String uuid, String groudId) {

        int result = deviceDao.renameGroupId(uuid,groudId);
        return result;
    }

    @Override
    public int nickNameRename(String uuid,  String nickName) {
        int result = deviceDao.renameNickName(uuid,nickName);
        return result;
    }

    @Override
    public int delDevice(String uuid) {
        int result = deviceDao.delDevice(uuid);
        return result;
    }

    @Override
    public int saveIndividuation(String uuid, String individuationString, int individuationInt, int individuationVar1, int individuationVar2) {
        int result = deviceDao.updateIndividuationInt(uuid , individuationInt);
        deviceDao.updateIndividuationString(uuid , individuationString);
        deviceDao.updateIndividuationVar1(uuid , individuationVar1);
        deviceDao.updateIndividuationVar2(uuid , individuationVar2);

        return result;
    }


}
