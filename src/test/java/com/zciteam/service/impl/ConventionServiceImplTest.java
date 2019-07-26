package com.zciteam.service.impl;

import org.junit.Test;
import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.JsePlatform;

public class ConventionServiceImplTest {

    @Test
    public void home() {

    }

    @Test
    public void javaTolua(){
        String luaStr =
                        "function test(str)\n" +
                        "print('data from java is:'..str)\n" +
                        "return 'haha'\n" +
                        "end";
        Globals globals = JsePlatform.standardGlobals();
        globals.load(luaStr).call();

        LuaValue luaValue = globals.get("test");
        luaValue.call("hello Lua");
    }

    @Test
    public void luaTojava(){
        String luatojava =
                        "--使用luajava创建java类的实例（对象）\n" +
                        "local logger = luajava.newInstance('com.zciteam.service.impl.ConventionServiceImplTest')\n" +
                        "--调用对象方法\n" +
                        "logger:luaTest(\"Test call java in lua0\")";

        Globals globals = JsePlatform.standardGlobals();
        globals.load(luatojava).call();
    }

    public void luaTest(String str){
        System.out.println("lua to java" + str);
    }
}