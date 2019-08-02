package com.zciteam.service.impl;

import com.scriptEditor.control.ScriptBridgeManager;
import com.zciteam.bean.ScriptForMy;
import com.zciteam.dao.ScriptForMyDao;
import com.zciteam.service.ScriptEditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.script.ScriptException;

@Service
public class ScriptEditServiceImpl implements ScriptEditService {

    private ScriptForMyDao scriptForMyDao;

    @Autowired
    public void setScriptForMyDao(ScriptForMyDao scriptForMyDao) {
        this.scriptForMyDao = scriptForMyDao;
    }

    @Override
    public ScriptForMy findScript(int suid) {
        return scriptForMyDao.findScript(suid);
    }

    @Override
    public int deleteScript(int suid) {
        return scriptForMyDao.deleteScript(suid);
    }

    @Override
    public int updateScript(int suid, String code, String workxml) {
        return scriptForMyDao.updateScript(suid,code,workxml);
    }

    @Override
    public int intertScript(String scriptName, String code, String workxml) {
        return scriptForMyDao.intertScript(scriptName,code,workxml);
    }

    private Thread thread = null;
    @Override
    public void run(String code) throws ScriptException{
        thread = new Thread (new Runnable () {
            @Override
            public void run() {
                try {
                    new ScriptBridgeManager().evel(code);
                } catch (ScriptException e) {
                }
            }
        });
        thread.start();
    }

    @Override
    public void stop() {
        if (thread != null){

            thread.stop();
            thread = null;
        }
    }
}
