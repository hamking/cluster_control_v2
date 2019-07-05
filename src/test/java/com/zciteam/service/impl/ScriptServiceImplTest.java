package com.zciteam.service.impl;

import com.zciteam.bean.Script;
import com.zciteam.bean.ScriptType;
import com.zciteam.dao.ScriptDao;
import com.zciteam.dao.ScriptTypeDao;
import com.zciteam.dto.ScriptResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-service.xml", "classpath:spring/spring-dao.xml"})
public class ScriptServiceImplTest {

    private ScriptDao scriptDao;
    private ScriptTypeDao scriptTypeDao;

    @Autowired
    public void setScriptDao(ScriptDao scriptDao) {
        this.scriptDao = scriptDao;
    }

    @Autowired
    public void setScriptTypeDao(ScriptTypeDao scriptTypeDao) {
        this.scriptTypeDao = scriptTypeDao;
    }


    @Test
    public void getScriptList() {


    }
}