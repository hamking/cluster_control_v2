package com.zciteam.service.impl;

import com.zciteam.service.ConventionService;
import com.zciteam.web.ConventionController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-service.xml", "classpath:spring/spring-dao.xml"})
public class ConventionServiceImplTest {

    @Autowired
    private ConventionService conventionService;

    @Test
    public void home() {
        conventionService.home(1,"");
    }
}