package com.zciteam.web;

import com.zciteam.bean.ScriptForMy;
import com.zciteam.dto.Result;
import com.zciteam.enums.CodeEnum;
import com.zciteam.service.impl.ScriptEditServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/scriptedit")
public class ScriptEditController {

    private ScriptEditServiceImpl scriptEditService;

    @Autowired
    public void setScriptEditService(ScriptEditServiceImpl scriptEditService) {
        this.scriptEditService = scriptEditService;
    }

    /**
     * 根据id获取脚本
     * id
     */
    @RequestMapping(value = "/getScript", method = RequestMethod.POST)
    @ResponseBody
    public Result findScript(HttpServletRequest request){

        try{
            int id = Integer.parseInt(request.getParameter("id"));
            ScriptForMy scriptForMy = scriptEditService.findScript(id);
            if (scriptForMy != null){
                return new Result(scriptForMy, CodeEnum.CODE_200);
            }
        }catch (RuntimeException e){}
        return new Result(null, CodeEnum.CODE_400);
    }

    /**
     * 保存脚本
     * scriptName
     * code
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Result save(HttpServletRequest request){

        try{
            String code = request.getParameter("code");
            String scriptName = request.getParameter("scriptName");
            int col = scriptEditService.intertScript(scriptName, code);
            if (col > 0){
                return new Result(null, CodeEnum.CODE_200);
            }
        }catch(RuntimeException e){}
        return new Result(null, CodeEnum.CODE_400);
    }

    /**
     * 删除脚本
     * id
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Result delete(HttpServletRequest request){

        try{
            int id = Integer.parseInt(request.getParameter("id"));
            int col = scriptEditService.deleteScript(id);
            if (col > 0){
                return new Result(null, CodeEnum.CODE_200);
            }
        }catch (RuntimeException e){}
        return new Result(null, CodeEnum.CODE_400);
    }

    /**
     * 修改脚本
     * id
     * code
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Result update(HttpServletRequest request){

        try{
            int id = Integer.parseInt(request.getParameter("id"));
            String code = request.getParameter("code");
            int col = scriptEditService.updateScript(id,code);
            if (col > 0){
                return new Result(null, CodeEnum.CODE_200);
            }
        }catch(RuntimeException e){}
        return new Result(null, CodeEnum.CODE_400);
    }

    /**
     * 运行脚本
     * code
     */
    @RequestMapping(value = "/run", method = RequestMethod.POST)
    @ResponseBody
    public Result run(HttpServletRequest request){

        try{
            String code = request.getParameter("code");
            scriptEditService.run(code);
            return new Result(null, CodeEnum.CODE_200);
        }catch(RuntimeException e){}
        return new Result(null, CodeEnum.CODE_400);
    }

    /**
     * 停止脚本
     */
    @RequestMapping(value = "/stop", method = RequestMethod.GET)
    @ResponseBody
    public Result stop(){

        try{
            scriptEditService.stop();
            return new Result(null, CodeEnum.CODE_200);
        }catch(RuntimeException e){}
        return new Result(null, CodeEnum.CODE_400);
    }
}
