package com.zciteam.dao;

import com.zciteam.bean.ScriptForMy;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ScriptForMyDao {

    /**
     * 获取全部脚本
     * @return list
     */
    List<ScriptForMy> findAllScript();

    /**
     * 根据脚本id获取脚本详情
     * @param suid suid
     */
    ScriptForMy findScript(@Param("suid") int suid);

    /**
     * 删除脚本
     */
    int deleteScript(@Param("suid") int suid);

    /**
     * 更新脚本
     */
    int updateScript(@Param("suid") int suid, @Param("code") String code, @Param("workxml") String workxml);

    /**
     * 插入脚本
     */
    int intertScript(@Param("scriptName") String scriptName, @Param("code") String code, @Param("workxml") String workxml);
}