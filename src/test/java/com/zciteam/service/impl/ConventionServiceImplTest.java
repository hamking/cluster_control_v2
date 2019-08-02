package com.zciteam.service.impl;

import org.junit.Test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ConventionServiceImplTest {

    @Test
    public void home() {

        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine nashorn = scriptEngineManager.getEngineByName("js");

        String name =
                "var Auto = Java.type(\"com.zciteam.service.impl.AdbControlTest\");\n" +
                "Auto.back1();";
        try {
            nashorn.eval(name);
        } catch(ScriptException e) {
            System.out.println("Error executing script: "+ e.getMessage());
        }
    }

    @Test
    public void javaTolua(){
    }

    @Test
    public void luaTojava(){
    }

    public void luaTest(String str){
        System.out.println("lua to java" + str);
    }
}