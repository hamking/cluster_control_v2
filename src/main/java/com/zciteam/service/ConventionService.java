package com.zciteam.service;

import com.zciteam.dto.Result;
import com.zciteam.enums.DeviceControlEnum;
import com.zciteam.enums.KeyboardEnum;
import org.springframework.web.multipart.MultipartFile;

public interface ConventionService {

    /**
     * 主页
     * @param scope 控制范围: -2 全部, -1 当前, 0~... 组Id
     */
    int home(int scope, String uuid);

    /**
     * 主页
     * @param scope 控制范围
     */
    int back(int scope, String uuid);

    /**
     * 清空进程
     * @param scope 控制范围
     */
    int killProgress(int scope, String uuid);

    /**
     * 安装软件
     * @param scope 控制范围
     */
    int installApp(int scope, String uuid, MultipartFile multipartFile);

    /**
     * 锁屏操作
     * @param scope 控制范围
     * @param controlEnum controlEnum
     */
    int lock(int scope, DeviceControlEnum controlEnum, String uuid);

    /**
     * 音量操作
     * @param scope 控制范围
     * @param controlEnum controlEnum
     */
    int volume(int scope, DeviceControlEnum controlEnum, String uuid);

    /**
     * 重启设备
     * @param scope 控制范围
     */
    int reboot(int scope, String uuid);

    /**
     * 切换键盘
     * @param scope 控制范围
     * @param keyboardEnum keyboardEnum
     */
    int switchKeyboard(int scope, KeyboardEnum keyboardEnum, String uuid);

    /**
     * 上传文件
     * @param scope scope
     * @param multipartFile multipartFile
     * @param uuid uuid
     */
    int uploadVideo(int scope, String uuid, MultipartFile multipartFile);
    int uploadImage(int scope, String uuid, MultipartFile multipartFile);
    int uploadJar(int scope, String uuid, MultipartFile multipartFile);
    int uploadZip(int scope, String uuid, MultipartFile multipartFile);
}
