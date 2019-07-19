package com.zciteam.dao;

import com.zciteam.bean.Script;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ScriptDao {

    /**
     * 查询所有脚本信息
     * @return List
     */
    List<Script> findAllScript();

    /**
     * 查询指定脚本信息
     * @param suid suid
     * @return Script
     */
    Script findScript(String suid);

    /**
     * 根据类型查找
     * @param type type
     * @return List
     */
    List<Script> findScriptForType(String type);

    /**
     * 删除指定脚本
     * @param suid suid
     * @return int
     */
    int deleteScript(String suid);
    int deleteAllScript();


    /**
     * 更新脚本信息
     * @return int
     */
    int updateScript(@Param("suid")                     String suid,
                     @Param("isFocus")                  int isFocus,
                     @Param("watchNum")                 int watchNum,
                     @Param("numStart")                 int numStart,
                     @Param("focusNum")                 int focusNum,
                     @Param("watchTime")                int watchTime,
                     @Param("isComment")                int isComment,
                     @Param("isGiveLike")               int isGiveLike,
                     @Param("commentStr")               String commentStr,
                     @Param("feedingTime")              int feedingTime,
                     @Param("watchTimeMan")             int watchTimeMan,
                     @Param("watchTimeMin")             int watchTimeMin,
                     @Param("isFocusAuthor")            int isFocusAuthor,
                     @Param("directMessages")           String directMessages,
                     @Param("isDirectMessages")         int isDirectMessages,
                     @Param("watchTimeInterval")        int watchTimeInterval,
                     @Param("directMessagesNum")        int directMessagesNum,
                     @Param("directMessagesType")       int directMessagesType,
                     @Param("directMessagesImage")      String directMessagesImage,
                     @Param("isOnlyDirectMessages")     int isOnlyDirectMessages,
                     @Param("isDirectMessagesOnAuthor") int isDirectMessagesOnAuthor);

    /**
     * 插入脚本
     * @param scriptName  脚本名字
     * @param type        隶属于那个软件的脚本  例如 快手 则使用包名命名
     * @param packageName 软件的包名
     * @param suid        脚本的类名
     * @return
     */
    int intertScript(@Param("scriptName")  String scriptName,
                     @Param("type")        String type,
                     @Param("packageName") String packageName,
                     @Param("suid")        String suid);
}