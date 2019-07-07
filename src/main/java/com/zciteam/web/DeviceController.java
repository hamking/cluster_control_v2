package com.zciteam.web;

import com.zciteam.bean.Device;
import com.zciteam.dto.DeviceResult;
import com.zciteam.dto.DeviceSituationResult;
import com.zciteam.dto.Result;
import com.zciteam.enums.CodeEnum;
import com.zciteam.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/device")
public class DeviceController {

    private DeviceService deviceService;

    @Autowired
    public void setDeviceService(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    /**
     * 获取设备日志接口
     * @param request request
     */
    @RequestMapping(value = "/getDeviceLog", method = RequestMethod.GET)
    @ResponseBody
    public Result getDeviceLog(HttpServletRequest request){

        return new Result<>(null, CodeEnum.CODE_200,"日志功能暂时未开通");
    }

    /**
     * 获取全部分组
     * @param request request
     */
    @RequestMapping(value = "/getGroudList",method = RequestMethod.GET)
    @ResponseBody
    public Result getGroudList(HttpServletRequest request){

        try{
            List<String>list = deviceService.grourdList();
            if (list != null){
                return new Result<>(new DeviceResult<>(list),CodeEnum.CODE_200);
            }
        }catch(RuntimeException e){
            return new Result<>(null,CodeEnum.CODE_503,e.getMessage());
        }
        return new Result<>(null,CodeEnum.CODE_400);
    }

    /**
     * 获取设备列表
     * @param request request
     */
    @RequestMapping(value = "/getDeviceList", method = RequestMethod.GET)
    @ResponseBody
    public Result getDeviceList(HttpServletRequest request){
        try{
            List<Device>list = deviceService.devlceList();
            if (list != null){
                return new Result<>(new DeviceResult<>(list),CodeEnum.CODE_200);
            }
        }catch(RuntimeException e){
            return new Result<>(null,CodeEnum.CODE_503,e.getMessage());
        }
        return new Result<>(null,CodeEnum.CODE_400);
    }

    /**
     * 获取设备在线情况  n台在线 n在离线 n授权设备
     * @param request request
     */
    @RequestMapping(value = "/getDeviceDetails",method = RequestMethod.GET)
    @ResponseBody
    public Result getDeviceDetails(HttpServletRequest request){
        try{
            DeviceSituationResult situationResult = deviceService.deviceDetails();
            if (situationResult != null){
                return new Result<>(situationResult,CodeEnum.CODE_200);
            }
        }catch(RuntimeException e){
            return new Result<>(null,CodeEnum.CODE_503,e.getMessage());
        }
        return new Result<>(null,CodeEnum.CODE_400);
    }

    /**
     * 重命名组
     * @param request request
     */
    @RequestMapping(value = "/groudRename",method = RequestMethod.POST)
    @ResponseBody
    public Result groudRename(HttpServletRequest request){

        try{
            String uuid = request.getParameter("uuid");
            String groupId = request.getParameter("groupId");
            int col = deviceService.groudRename(uuid, groupId);
            if (col>0){
                return new Result<>(null, CodeEnum.CODE_200);
            }
        }catch (RuntimeException e){
            return new Result<> (null, CodeEnum.CODE_503, e.getMessage());
        }
        return new Result<> (null, CodeEnum.CODE_400);
    }

    /**
     * 新建组
     * @param request request
     */
    @RequestMapping(value = "/addGroud",method = RequestMethod.POST)
    @ResponseBody
    public Result addGroud(HttpServletRequest request){

        try{
            String uuid = request.getParameter("uuid");
            String groupId = request.getParameter("groupId");
            int col = deviceService.groudRename(uuid, groupId);
            if (col>0){
                return new Result<>(null, CodeEnum.CODE_200);
            }
        }catch (RuntimeException e){
            return new Result<> (null, CodeEnum.CODE_503, e.getMessage());
        }
        return new Result<> (null, CodeEnum.CODE_400);
    }

    /**
     * 重命名设备昵称
     * @param request request
     */
    @RequestMapping(value = "/nickNameRename",method = RequestMethod.POST)
    @ResponseBody
    public Result nickNameRename(HttpServletRequest request){
        try{
            String uuid = request.getParameter("uuid");
            String nickName = request.getParameter("nickName");
            int col = deviceService.nickNameRename(uuid, nickName);
            if (col>0){
                return new Result<>(null, CodeEnum.CODE_200);
            }
        }catch (RuntimeException e){
            return new Result<> (null, CodeEnum.CODE_503, e.getMessage());
        }
        return new Result<> (null, CodeEnum.CODE_400);
    }
}
