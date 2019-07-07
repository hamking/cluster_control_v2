package com.zciteam.web;

import com.zciteam.dto.Result;
import com.zciteam.enums.CodeEnum;
import com.zciteam.service.impl.ToolsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/tools")
public class ToolsController {

    private ToolsServiceImpl toolsService;

    @Autowired
    public void setToolsService(ToolsServiceImpl toolsService) {
        this.toolsService = toolsService;
    }

    /**
     * 初始化手机
     * @return Result
     */
    @RequestMapping(value = "/initPhone", method = RequestMethod.POST)
    @ResponseBody
    public Result getScriptList(HttpServletRequest request){
        try{
            String scope = request.getParameter("scope");
            String uuid = request.getParameter("uuid");
            int i = toolsService.getInitPhone(scope, uuid);
            if (i>0){
                return new Result<>(null, CodeEnum.CODE_200);
            }else{
                return new Result<>(null, CodeEnum.CODE_400);
            }
        }catch(RuntimeException e){
            return new Result<>(null, CodeEnum.CODE_400,e.getMessage());
        }
    }

    /**
     * 返回服务器的ip及端口
     * @return Result
     */
    @RequestMapping(value = "/getAddrPort", method = RequestMethod.GET)
    @ResponseBody
    public Result getAddrPort(HttpServletRequest request){

        try{
            Map<String, String> map = new HashMap<>();
            map.put("addr",request.getLocalAddr());
            map.put("port", String.valueOf(request.getLocalPort()));

            return new Result(map, CodeEnum.CODE_200);
        }catch (RuntimeException e){
            return new Result(null, CodeEnum.CODE_400);
        }
    }
}
