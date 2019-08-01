package com.zciteam.service;

import com.zciteam.bean.ScriptForMy;
import org.apache.ibatis.annotations.Param;

import javax.script.ScriptException;

public interface ScriptEditService {

    /**
     * 根据脚本id获取脚本详情
     */
    ScriptForMy findScript(@Param("id") int id);

    /**
     * 删除脚本
     */
    int deleteScript(@Param("id") int id);

    /**
     * 更新脚本
     */
    int updateScript(@Param("id") int id,@Param("code") String code);

    /**
     * 插入脚本
     */
    int intertScript(@Param("scriptName") String scriptName, @Param("code") String code);

    /**
     * 运行脚本
     */
    void run(String code) throws ScriptException;

    /**
     * 终止运行
     */
    void stop();
}
