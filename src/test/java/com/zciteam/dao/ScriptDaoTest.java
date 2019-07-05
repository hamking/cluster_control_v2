package com.zciteam.dao;

import com.script.ComSmileGifmakerRaises;
import com.zciteam.bean.Script;
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
public class ScriptDaoTest {

    @Autowired
    private ScriptDao scriptDao;

    @Test
    public void findScript(){
        String suid = "ScriptComJifenQukan";
        Script script = scriptDao.findScript(suid);
        System.out.println(script);
    }



    @Test
    public void findScriptForType(){
        scriptDao.intertScript(
                "快手_养号",
                "comsmilegifmaker",
                "com.smile.gifmaker/com.yxcorp.gifshow.HomeActivity",
                "jjjjj");
    }
}