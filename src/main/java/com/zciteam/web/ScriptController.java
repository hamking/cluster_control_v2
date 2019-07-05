package com.zciteam.web;

import com.zciteam.dto.Result;
import com.zciteam.dto.ScriptResult;
import com.zciteam.enums.CodeEnum;
import com.zciteam.service.impl.DeviceServiceImpl;
import com.zciteam.service.impl.ScriptServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/script")
public class ScriptController {

    private ScriptServiceImpl scriptService;
    private DeviceServiceImpl deviceService;

    @Autowired
    public void setDeviceService(DeviceServiceImpl deviceService) {
        this.deviceService = deviceService;
    }
    @Autowired
    public void setScriptService(ScriptServiceImpl scriptService) {
        this.scriptService = scriptService;
    }

    /**
     * 获取全部脚本
     * @return Result
     */
    @RequestMapping(value = "/getScriptList", method = RequestMethod.GET)
    @ResponseBody
    public Result getScriptList(){

        try{
            ScriptResult scriptResults = scriptService.getScriptResult();
            if (scriptResults != null) {
                return new Result<> (scriptResults, CodeEnum.CODE_200);
            }
        }catch(RuntimeException e){
            return new Result<>(null, CodeEnum.CODE_400,e.getMessage());
        }
        return new Result<>(null, CodeEnum.CODE_400);
    }

    /**
     * 保存脚本设置
     * @param request request
     * @return Result
     */
    @RequestMapping(value = "/saveScriptSetting",method = RequestMethod.POST)
    @ResponseBody
    public Result saveScriptSetting(HttpServletRequest request){

        /**
         * //    private String ;
         * ////    private int ;
         * ////    private int ;
         * ////    private int individuationVar2;
         */
        try{
            String uuid                     = request.getParameter("uuid");
            String suid                     = request.getParameter("suid");
            int    isFocus                  = Integer.parseInt(request.getParameter("isFocus"));
            int    watchNum                 = Integer.parseInt(request.getParameter("watchNum"));
            int    numStart                 = Integer.parseInt(request.getParameter("numStart"));
            int    focusNum                 = Integer.parseInt(request.getParameter("focusNum"));
            int    watchTime                = Integer.parseInt(request.getParameter("watchTime"));
            int    isComment                = Integer.parseInt(request.getParameter("isComment"));
            int    isGiveLike               = Integer.parseInt(request.getParameter("isGiveLike"));
            String commentStr               = request.getParameter("commentStr");
            int    feedingTime              = Integer.parseInt(request.getParameter("feedingTime"));
            int    watchTimeMan             = Integer.parseInt(request.getParameter("watchTimeMan"));
            int    watchTimeMin             = Integer.parseInt(request.getParameter("watchTimeMin"));
            int    isFocusAuthor            = Integer.parseInt(request.getParameter("isFocusAuthor"));
            String directMessages           = request.getParameter("directMessages");
            int    isDirectMessages         = Integer.parseInt(request.getParameter("isDirectMessages"));
            int    watchTimeInterval        = Integer.parseInt(request.getParameter("watchTimeInterval"));
            int    directMessagesNum        = Integer.parseInt(request.getParameter("directMessagesNum"));
            int    directMessagesType       = Integer.parseInt(request.getParameter("directMessagesType"));
            String directMessagesImage      = request.getParameter("directMessagesImage");
            int    isOnlyDirectMessages     = Integer.parseInt(request.getParameter("isOnlyDirectMessages"));
            int    isDirectMessagesOnAuthor = Integer.parseInt(request.getParameter("isDirectMessagesOnAuthor"));

            //保存到设备表中 用于个性化操作
            String individuationString      = request.getParameter("individuationString");
            int individuationInt            = Integer.parseInt (request.getParameter("individuationInt"));
            int individuationVar1           = Integer.parseInt (request.getParameter("individuationVar1"));
            int individuationVar2           = Integer.parseInt (request.getParameter("individuationVar2"));

            int col = scriptService.saveScriptSetting(suid, isFocus, watchNum, numStart, focusNum, watchTime, isComment, isGiveLike, commentStr, feedingTime, watchTimeMan, watchTimeMin, isFocusAuthor, directMessages, isDirectMessages, watchTimeInterval, directMessagesNum, directMessagesType, directMessagesImage, isOnlyDirectMessages, isDirectMessagesOnAuthor);
            deviceService.saveIndividuation(uuid, individuationString, individuationInt, individuationVar1, individuationVar2);
            if (col>0) {
                return new Result<> (null, CodeEnum.CODE_200);
            }
        }catch(RuntimeException e){
            return new Result<>(null, CodeEnum.CODE_400,e.getMessage());
        }
        return new Result<>(null, CodeEnum.CODE_400);
    }

    /**
     * 运行脚本
     * scope -2全部 -1当前 其他组id
     * suid 脚本id
     * uuid 当前设备id
     * @param request request
     * @return Result
     */
    @RequestMapping(value = "/run",method = RequestMethod.POST)
    @ResponseBody
    public Result run(HttpServletRequest request){

        try{
            String suid = request.getParameter("suid");
            String uuid = request.getParameter("uuid");
            int scope = Integer.parseInt(request.getParameter("scope"));
            int col = scriptService.run(scope, suid, uuid);
            if (col>0) {
                return new Result<> (null, CodeEnum.CODE_200);
            }
        }catch(RuntimeException e){
            return new Result<>(null, CodeEnum.CODE_400,e.getMessage());
        }
        return new Result<>(null, CodeEnum.CODE_400);
    }

    /**
     * 停止脚本
     * scope -2全部 -1当前 其他组id
     * suid 脚本id
     * uuid 当前设备id
     * @param request request
     * @return Result
     */
    @RequestMapping(value = "/stop",method = RequestMethod.POST)
    @ResponseBody
    public Result stop(HttpServletRequest request){

        try{
            String uuid = request.getParameter("uuid");
            int scope = Integer.parseInt(request.getParameter("scope"));
            int col = scriptService.stop(scope, uuid);
            if (col>0) {
                return new Result<> (null, CodeEnum.CODE_200);
            }
        }catch(RuntimeException e){
            return new Result<>(null, CodeEnum.CODE_400,e.getMessage());
        }
        return new Result<>(null, CodeEnum.CODE_400);
    }
}
