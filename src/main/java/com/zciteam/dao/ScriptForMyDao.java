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
     * @param id id
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
}