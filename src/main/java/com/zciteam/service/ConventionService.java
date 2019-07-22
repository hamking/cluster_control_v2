package com.zciteam.service;

import com.zciteam.dto.Result;
import com.zciteam.enums.DeviceControlEnum;
import com.zciteam.enums.KeyboardEnum;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ConventionService {

    /**
     * 主页
     * @param scope 控制范围: -2 全部, -1 当前, 0~... 组Id
     */
    int home(String scope, String uuid);

    /**
     * 主页
     * @param scope 控制范围
     */
    int back(String scope, String uuid);

    /**
     * 清空进程
     * @param scope 控制范围
     */
    int killProgress(String scope, String uuid);

    /**
     * 安装软件
     * @param scope 控制范围
     */
    int installApp(String scope, String uuid, List<MultipartFile> multipartFiles);

    /**
     * 锁屏操作
     * @param scope 控制范围
     * @param controlEnum controlEnum
     */
    int lock(String scope, DeviceControlEnum controlEnum, String uuid);

    /**
     * 音量操作
     * @param scope 控制范围
     * @param controlEnum controlEnum
     */
    int volume(String scope, DeviceControlEnum controlEnum, String uuid);

    /**
     * 重启设备
     * @param scope 控制范围
     */
    int reboot(String scope, String uuid);

    /**
     * 切换键盘
     * @param scope 控制范围
     * @param keyboardEnum keyboardEnum
     */
    int switchKeyboard(String scope, KeyboardEnum keyboardEnum, String uuid);

    /**
     * 上传文件
     * @param scope scope
     * @param multipartFile multipartFile
     * @param uuid uuid
     */
    int uploadVideo(String scope, String uuid, List<MultipartFile> multipartFiles);
    int uploadImage(String scope, String uuid, List<MultipartFile> multipartFiles);
    int uploadJar(String scope, String uuid, List<MultipartFile> multipartFiles);
    int uploadZip(String scope, String uuid, List<MultipartFile> multipartFiles);
}
