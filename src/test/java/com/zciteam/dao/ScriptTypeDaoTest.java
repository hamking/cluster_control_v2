package com.zciteam.dao;

import com.zciteam.bean.ScriptType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class ScriptTypeDaoTest {

    @Autowired
    private ScriptTypeDao scriptTypeDao;

    @Test
    public void findAllScriptType(){
        System.out.println(scriptTypeDao.findAllScriptType());
    }
}