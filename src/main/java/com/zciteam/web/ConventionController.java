package com.zciteam.web;

import com.zciteam.dto.Result;
import com.zciteam.enums.CodeEnum;
import com.zciteam.enums.DeviceControlEnum;
import com.zciteam.enums.KeyboardEnum;
import com.zciteam.service.ConventionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/convention")
public class ConventionController {

    private ConventionService conventionService;

    @Autowired
    public void setConventionService(ConventionService conventionService) {
        this.conventionService = conventionService;
    }

    /**
     * home 接口
     * String    uuid         设备uuid
     * int       scope        操作影响设备 -2所有设备 -1当前设备 其他数字为该组的id
     * @param request Result
     * @return Result
     */
    @RequestMapping(value = "/home", method = RequestMethod.POST)
    @ResponseBody
    public Result home(HttpServletRequest request) {
        try{
            String scope = request.getParameter("scope");
            String uuid = request.getParameter("uuid");
            int col = conventionService.home(scope,uuid);
            if (col>0){
                return new Result<>(null,CodeEnum.CODE_200);
            }
        }catch (RuntimeException e){
            return new Result<>(null, CodeEnum.CODE_503,e.getMessage());
        }
        return new Result<>(null, CodeEnum.CODE_400);
    }

    /**
     * back接口
     * String    uuid         设备uuid
     * int       scope        操作影响设备 -2所有设备 -1当前设备 其他数字为该组的id
     * @param request Result
     * @return Result
     */
    @RequestMapping(value = "/back", method = RequestMethod.POST)
    @ResponseBody
    public Result back(HttpServletRequest request) {
        try{
            String scope = request.getParameter("scope");
            String uuid = request.getParameter("uuid");
            int col = conventionService.back(scope,uuid);
            if (col>0){
                return new Result<>(null,CodeEnum.CODE_200);
            }
        }catch (RuntimeException e){
            return new Result<>(null, CodeEnum.CODE_503,e.getMessage());
        }
        return new Result<>(null, CodeEnum.CODE_400);
    }

    /**
     * killProgress接口
     * String    uuid         设备uuid
     * int       scope        操作影响设备 -2所有设备 -1当前设备 其他数字为该组的id
     * @param request Result
     * @return Result
     */
    @RequestMapping(value = "/killProgress", method = RequestMethod.POST)
    @ResponseBody
    public Result killProgress(HttpServletRequest request) {
        try{
            String scope = request.getParameter("scope");
            String uuid = request.getParameter("uuid");
            int col = conventionService.killProgress(scope,uuid);
            if (col>0){
                return new Result<>(null,CodeEnum.CODE_200);
            }
        }catch (RuntimeException e){
            return new Result<>(null, CodeEnum.CODE_503,e.getMessage());
        }
        return new Result<>(null, CodeEnum.CODE_400);
    }

    /**
     * lock接口
     * String    uuid         设备uuid
     * int       scope        操作影响设备 -2所有设备 -1当前设备 其他数字为该组的id
     * int       state        0锁定屏幕 1解锁屏幕
     * @param request Result
     * @return Result
     */
    @RequestMapping(value = "/lock", method = RequestMethod.POST)
    @ResponseBody
    public Result lock(HttpServletRequest request) {
        try{
            String scope = request.getParameter("scope");
            int state = Integer.parseInt (request.getParameter("state"));
            String uuid = request.getParameter("uuid");
            int col = conventionService.lock(scope, DeviceControlEnum.stateOf(state),uuid);
            if (col>0){
                return new Result<>(null,CodeEnum.CODE_200);
            }        }catch (RuntimeException e){
            return new Result<>(null, CodeEnum.CODE_503,e.getMessage());
        }
        return new Result<>(null, CodeEnum.CODE_400);
    }

    /**
     * volume接口
     * String    uuid         设备uuid
     * int       scope        操作影响设备 -2所有设备 -1当前设备 其他数字为该组的id
     * int       state        2音量加 3音量减 4静音
     * @param request Result
     * @return Result
     */
    @RequestMapping(value = "/volume", method = RequestMethod.POST)
    @ResponseBody
    public Result volume(HttpServletRequest request) {
        try{
            String scope = request.getParameter("scope");
            int state = Integer.parseInt (request.getParameter("state"));
            String uuid = request.getParameter("uuid");
            int col = conventionService.volume(scope,DeviceControlEnum.stateOf(state),uuid);
            if (col>0){
                return new Result<>(null,CodeEnum.CODE_200);
            }        }catch (RuntimeException e){
            return new Result<>(null, CodeEnum.CODE_503,e.getMessage());
        }
        return new Result<>(null, CodeEnum.CODE_400);
    }

    /**
     * reboot接口
     * String    uuid         设备uuid
     * int       scope        操作影响设备 -2所有设备 -1当前设备 其他数字为该组的id
     * @param request Result
     * @return Result
     */
    @RequestMapping(value = "/reboot", method = RequestMethod.POST)
    @ResponseBody
    public Result reboot(HttpServletRequest request) {
        try{
            String scope = request.getParameter("scope");
            String uuid = request.getParameter("uuid");
            int col = conventionService.reboot(scope,uuid);
            if (col>0){
                return new Result<>(null,CodeEnum.CODE_200);
            }        }catch (RuntimeException e){
            return new Result<>(null, CodeEnum.CODE_503,e.getMessage());
        }
        return new Result<>(null, CodeEnum.CODE_400);
    }

    /**
     * switchKeyboard接口
     * files     files
     * int       keyboardEnum 0adb键盘 1系统键盘
     * String    uuid         设备uuid
     * int       scope        操作影响设备 -2所有设备 -1当前设备 其他数字为该组的id
     * @param request Result
     * @return Result
     */
    @RequestMapping(value = "/switchKeyboard", method = RequestMethod.POST)
    @ResponseBody
    public Result switchKeyboard(HttpServletRequest request) {
        try{
            String scope = request.getParameter("scope");
            int keyboardEnum = Integer.parseInt (request.getParameter("keyboardEnum"));
            String uuid = request.getParameter("uuid");
            int col = conventionService.switchKeyboard(scope,KeyboardEnum.stateOf(keyboardEnum),uuid);
            if (col>0){
                return new Result<>(null,CodeEnum.CODE_200);
            }        }catch (RuntimeException e){
            return new Result<>(null, CodeEnum.CODE_503,e.getMessage());
        }
        return new Result<>(null, CodeEnum.CODE_400);
    }

    /**
     * installApp接口
     * String    uuid         设备uuid
     * int       scope        操作影响设备 -2所有设备 -1当前设备 其他数字为该组的id
     * files     files
     * @param request Result
     * @return Result
     */
    @RequestMapping(value = "/installApp", method = RequestMethod.POST)
    @ResponseBody
    public Result installApp(HttpServletRequest request) {
        try{
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)(request);
            List<MultipartFile> file = multiRequest.getFiles("files");
            String uuid = request.getParameter("uuid");
            String scope = request.getParameter("scope");
            int col = 0;
            if (file!=null && file.get(0).getContentType().contains("application/vnd.android.package-archive")){
                col = conventionService.installApp(scope,uuid,file);
            }else{
                return new Result<>(null, CodeEnum.CODE_60001);
            }
            if (col>0){
                return new Result<>(null,CodeEnum.CODE_200,"导入完成!若出现安装提示,请点击安装.若没有出现,请等待安装完成即可.");
            }
        }catch (RuntimeException e){
            return new Result<>(null, CodeEnum.CODE_503,e.getMessage());
        }
        return new Result<>(null, CodeEnum.CODE_400);
    }

    /**
     * uploadFile接口
     * files     files
     * String    uuid         设备uuid
     * int       scope        操作影响设备 -2所有设备 -1当前设备 其他数字为该组的id
     * @param request Result
     * @return Result
     */
    @RequestMapping(value = "/uploadFile",method = RequestMethod.POST)
    @ResponseBody
    public Result uploadFile(HttpServletRequest request){

        try{
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)(request);
            List<MultipartFile> files = multiRequest.getFiles("files");
            String uuid = request.getParameter("uuid");
            String scope = request.getParameter("scope");

            if (files.isEmpty()){
                return new Result<>(null,CodeEnum.CODE_403);
            }
                if (files.get(0).getContentType().contains("video")){
                    //是视频文件
                    conventionService.uploadVideo(scope,uuid,files);

                }else if (files.get(0).getContentType().contains("image")){
                    //是图片文件
                    conventionService.uploadImage(scope,uuid,files);

                }else if (files.get(0).getContentType().contains("application/java-archive")){
                    //是jar文件
                    conventionService.uploadJar(scope,uuid,files);
                }else if (files.get(0).getContentType().contains("application/x-zip-compressed")){
                    //是jar文件
                    conventionService.uploadZip(scope,uuid,files);
                }else{
                    return new Result<>(null,CodeEnum.CODE_70001);
                }
            return new Result<>(null,CodeEnum.CODE_200);
        }catch (RuntimeException e){
            return new Result<>(null, CodeEnum.CODE_503,e.getMessage());
        }
    }
}
