package com.scriptEditor.control;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * 处理java与js交互
 */
public class ScriptBridgeManager {

    private String globalCode =
                    "var AdbControl = Java.type(\"com.scriptEditor.control.AdbControl\");\n" +
                    "var Auto = new AdbControl();\n" +
                    "function sleep(n) {\n" +
                    "    var start = new Date().getTime();\n" +
                    "    while(true)  if(new Date().getTime()-start > n) break;\n" +
                    "}\n";

    private String tryStr =
                    "try{\n" +
                        "code\n" +
                    "}catch(e){}\n";

    public void evel(String code) throws ScriptException{
        String scriptStr = globalCode;

        String[] codes = code.split("\n");
        for (int i = 0; i < codes.length; i++) {
            if (codes[i].contains("Auto") && (codes[i].contains("click") || codes[i].contains("sendKeys"))){
                codes[i] = tryStr.replace("code",codes[i]);
            }
        }

        StringBuilder scriptTryStr = new StringBuilder ();
        for (int i = 0; i < codes.length; i++) {
            scriptTryStr.append(codes[i]).append ("\n");
        }

        String codeStr = (scriptTryStr.toString().replace("var AndroidStart","Auto.start(uuid,action)"));

        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine nashorn = scriptEngineManager.getEngineByName("js");
        nashorn.eval(scriptStr + codeStr);
    }
}
