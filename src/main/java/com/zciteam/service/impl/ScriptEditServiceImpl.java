package com.zciteam.service.impl;

import com.zciteam.bean.ScriptForMy;
import com.zciteam.dao.ScriptForMyDao;
import com.zciteam.service.ScriptEditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScriptEditServiceImpl implements ScriptEditService {

    private ScriptForMyDao scriptForMyDao;

    @Autowired
    public void setScriptForMyDao(ScriptForMyDao scriptForMyDao) {
        this.scriptForMyDao = scriptForMyDao;
    }

    @Override
    public ScriptForMy findScript(int id) {
        return scriptForMyDao.findScript(id);
    }

    @Override
    public int deleteScript(int id) {
        return scriptForMyDao.deleteScript(id);
    }

    @Override
    public int updateScript(int id, String code) {
        return scriptForMyDao.updateScript(id,code);
    }

    @Override
    public int intertScript(String scriptName, String code) {
        return scriptForMyDao.intertScript(scriptName,code);
    }

    private Thread thread = null;
    @Override
    public void run(String code) {
        thread = new Thread (new Runnable () {
            @Override
            public void run() {
                System.out.println(code);
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
