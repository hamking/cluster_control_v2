package com.zciteam.service;

import com.zciteam.bean.ScriptForMy;
import org.apache.ibatis.annotations.Param;

import javax.script.ScriptException;

public interface ScriptEditService {

    /**
     * 根据脚本id获取脚本详情
     */
    ScriptForMy findScript(@Param("suid") int suid);

    /**
     * 删除脚本
     */
    int deleteScript(@Param("suid") int suid);

    /**
     * 更新脚本
     */
    int updateScript(@Param("suid") int suid,@Param("code") String code,@Param("workxml") String workxml);

    /**
     * 插入脚本
     */
    int intertScript(@Param("scriptName") String scriptName, @Param("code") String code, @Param("workxml") String workxml);

    /**
     * 运行脚本
     */
    void run(String code) throws ScriptException;

    /**
     * 终止运行
     */
    void stop();
}
