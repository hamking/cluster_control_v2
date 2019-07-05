package com.zciteam.dao;

import com.zciteam.bean.ScriptType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ScriptTypeDao {

    List<ScriptType> findAllScriptType();

    /**
     * 新建脚本分类
     * @param scriptName 脚本名字
     * @param type 脚本类型 使用apk软件包名命名
     * @return int
     */
    int intertScriptType(@Param("scriptName")  String scriptName,
                         @Param("type")        String type);
}
