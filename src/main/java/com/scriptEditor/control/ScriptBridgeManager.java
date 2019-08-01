package com.scriptEditor.control;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * 处理java与js交互
 */
public class ScriptBridgeManager {

    public String globalCode =
                    "var AdbControl = Java.type(\"com.scriptEditor.control.AdbControl\");\n" +
                    "var Auto = new AdbControl();\n" +
                    "function sleep(n) {\n" +
                    "    var start = new Date().getTime();\n" +
                    "    while(true)  if(new Date().getTime()-start > n) break;\n" +
                    "}\n";

    public void evel(String code) throws ScriptException{
        String scriptStr = globalCode;

        String codeStr = (code.replace("var AndroidStart","Auto.start(uuid,action)"));

        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine nashorn = scriptEngineManager.getEngineByName("js");

        nashorn.eval(scriptStr + codeStr);
    }
}
