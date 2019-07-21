package com.zciteam.service.impl;

import com.zciteam.bean.Device;
import com.zciteam.bean.Script;
import com.zciteam.bean.ScriptType;
import com.zciteam.dao.DeviceDao;
import com.zciteam.dao.ScriptDao;
import com.zciteam.dao.ScriptTypeDao;
import com.zciteam.dto.ScriptDetails;
import com.zciteam.dto.ScriptResult;
import com.zciteam.service.ScriptService;
import com.zciteam.util.ScopeDevice;
import com.zciteam.web.WebSocketDeviceLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ScriptServiceImpl implements ScriptService {

    private ScriptDao scriptDao;
    private ScriptTypeDao scriptTypeDao;
    private DeviceDao deviceDao;

    @Autowired
    public void setDeviceDao(DeviceDao deviceDao) {
        this.deviceDao = deviceDao;
    }

    @Autowired
    public void setScriptDao(ScriptDao scriptDao) {
        this.scriptDao = scriptDao;
    }

    @Autowired
    public void setScriptTypeDao(ScriptTypeDao scriptTypeDao) {
        this.scriptTypeDao = scriptTypeDao;
    }


    @Override
    public ScriptResult getScriptResult() {

        ScriptResult scriptResult = null;
        List<ScriptDetails>scriptResults = new ArrayList<>();
        List<ScriptType> scriptTypes = scriptTypeDao.findAllScriptType();
        scriptTypes.forEach (scriptType -> {
            List<Script> scriptList = scriptDao.findScriptForType(scriptType.getType());
            if (scriptList.size()>0){
                ScriptDetails scriptDetails = new ScriptDetails(scriptType.getScriptName(),scriptType.getType(),scriptList);
                scriptResults.add(scriptDetails);
            }
        });
        if (scriptResults.size()>0){
            scriptResult = new ScriptResult<> (scriptResults);
        }
        return scriptResult;
    }

    @Override
    public Script getScriptDetails(String suid) {
        if (scriptDao.findScript (suid) != null) {
            return scriptDao.findScript (suid);
        }else{
            return null;
        }
    }

    @Override
    public int saveScriptSetting(String suid, int isFocus, int watchNum, int numStart, int focusNum, int watchTime, int isComment, int isGiveLike, String commentStr, int feedingTime, int watchTimeMan, int watchTimeMin, int isFocusAuthor, String directMessages, int isDirectMessages, int watchTimeInterval, int directMessagesNum, int directMessagesType, String directMessagesImage, int isOnlyDirectMessages, int isDirectMessagesOnAuthor) {
        int col = scriptDao.updateScript(
                suid,
                isFocus,
                watchNum,
                numStart,
                focusNum,
                watchTime,
                isComment,
                isGiveLike,
                commentStr,
                feedingTime,
                watchTimeMan,
                watchTimeMin,
                isFocusAuthor,
                directMessages,
                isDirectMessages,
                watchTimeInterval,
                directMessagesNum,
                directMessagesType,
                directMessagesImage,
                isOnlyDirectMessages,
                isDirectMessagesOnAuthor);
        return col;
    }

    //脚本线程map
    private Map<String, Thread> threadMap = new HashMap<>();
    @Override
    public int run(String scope, String suid, String uuid) {

        //执行前先结束当前设备正在运行的脚本
        stop(scope,uuid);

        //获取设备 scope
        List<Device> devices = ScopeDevice.getDevice(scope,uuid,deviceDao);

        //获取脚本
        Script script = scriptDao.findScript(suid);

        if (devices.size() > 0 && script != null) {
            devices.forEach (device -> {
                Thread thread = new Thread(new Runnable () {
                    @Override
                    public void run() {
                        deviceDao.updateRunningState(device.getUuid(),1);

                        //根据suid得到相应的类并初始化
                        try {
                            Class c = Class.forName("com.script." + suid);
                            Object o = c.newInstance();
                            Method[] methods = c.getMethods();
                            for (Method method : methods) {
                                if (method.getName().equals("script")){
                                    method.invoke(o,device.getUuid(),script,device);
                                }
                            }
                        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                            e.printStackTrace ();
                        }
                    }
                });
                thread.start();
                threadMap.put(device.getUuid(),thread);
            });
            return 1;
        }else{
            return 0;
        }
    }

    @Override
    public int stop(String scope, String uuid) {

        //获取设备 scope
        List<Device> devices = ScopeDevice.getDevice(scope,uuid,deviceDao);

        devices.forEach(device -> {
            new Thread (new Runnable () {
                @Override
                public void run() {
                    //TODO 停止脚本逻辑
                    System.out.println("停止脚本");
                    threadMap.forEach((k, v)->{
                        if (k.equals(device.getUuid())){
                            v.stop();
                            threadMap.remove(k);
                            new WebSocketDeviceLog ().push(device.getUuid(),"停止脚本");
                        }
                    });
                }
            }).start();
        });
        return devices.size();
    }
}
