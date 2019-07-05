package com.zciteam.service;

import com.zciteam.bean.Script;
import com.zciteam.dto.ScriptResult;

public interface ScriptService {

    /**
     * 获取全部脚本
     * @return List
     */
    ScriptResult getScriptResult();

    /**
     * 获取脚本
     * @return List
     */
    Script getScriptDetails(String suid);

    /**
     * 保存脚本设置
     * @param suid suid
     * @return int
     */
    int saveScriptSetting(String suid,
                          int isFocus,
                          int watchNum,
                          int numStart,
                          int focusNum,
                          int watchTime,
                          int isComment,
                          int isGiveLike,
                          String commentStr,
                          int feedingTime,
                          int watchTimeMan,
                          int watchTimeMin,
                          int isFocusAuthor,
                          String directMessages,
                          int isDirectMessages,
                          int watchTimeInterval,
                          int directMessagesNum,
                          int directMessagesType,
                          String directMessagesImage,
                          int isOnlyDirectMessages,
                          int isDirectMessagesOnAuthor);

    /**
     * 运行脚本
     * @param scope -2全部 -1当前 其他组id
     * @param suid 脚本id
     * @param uuid 设备uuid
     * @return int
     */
    int run(int scope, String suid, String uuid);

    /**
     * 停止脚本
     * @param scope -2全部 -1当前 其他组id
     * @param suid 脚本id
     * @param uuid 设备uuid
     * @return int
     */
    int stop(int scope, String uuid);
}
