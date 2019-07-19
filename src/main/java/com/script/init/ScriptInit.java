package com.script.init;

import com.script.*;
import com.zciteam.dao.ScriptDao;
import com.zciteam.dao.ScriptTypeDao;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 初始化脚本信息
 */
public class ScriptInit {

    private ScriptDao scriptDao;
    private ScriptTypeDao scriptTypeDao;

    @Autowired
    public void setScriptTypeDao(ScriptTypeDao scriptTypeDao) {
        this.scriptTypeDao = scriptTypeDao;
    }
    @Autowired
    public void setScriptDao(ScriptDao scriptDao) {
        this.scriptDao = scriptDao;
    }

    /*
    int intertScript(@Param("scriptName")  String scriptName,     脚本名字
                     @Param("type")        String type,           隶属于那个软件的脚本  例如 快手 则使用包名命名
                     @Param("packageName") String packageName,    软件的包名
                     @Param("suid")        String suid);          脚本的类名

    int intertScriptType(@Param("scriptName")  String scriptName, 脚本名字
                         @Param("type")        String type);      隶属于那个软件的脚本  例如 快手 则使用包名命名
     */
    private void init(){

        scriptTypeDao.intertScriptType(
                "快手",
                "comsmilegifmaker");
        scriptTypeDao.intertScriptType(
                "抖音",
                "comssandroidugcaweme");


        //临时处理清空脚本表
        scriptDao.deleteAllScript();

        //快手
        scriptDao.intertScript(
                "快手_养号",
                "comsmilegifmaker",
                "com.smile.gifmaker/com.yxcorp.gifshow.HomeActivity",
                ComSmileGifmakerRaises.class.getSimpleName());
        scriptDao.intertScript(
                "快手_私信",
                "comsmilegifmaker",
                "com.smile.gifmaker/com.yxcorp.gifshow.HomeActivity",
                ComSmileGifmakerPrivateMsg.class.getSimpleName());
        scriptDao.intertScript(
                "快手_查找模糊匹配用户",
                "comsmilegifmaker",
                "com.smile.gifmaker/com.yxcorp.gifshow.HomeActivity",
                ComSmileGifmakerFindUser.class.getSimpleName());
        scriptDao.intertScript(
                "快手_查找模糊匹配作品",
                "comsmilegifmaker",
                "com.smile.gifmaker/com.yxcorp.gifshow.HomeActivity",
                ComSmileGifmakerFindWorks.class.getSimpleName());
        scriptDao.intertScript(
                "快手_查找模糊匹配标签",
                "comsmilegifmaker",
                "com.smile.gifmaker/com.yxcorp.gifshow.HomeActivity",
                ComSmileGifmakerFindLabel.class.getSimpleName());
        scriptDao.intertScript(
                "快手_发视频",
                "comsmilegifmaker",
                "com.smile.gifmaker/com.yxcorp.gifshow.HomeActivity",
                ComSmileGifmakerSendVideo.class.getSimpleName());

        //抖音
        scriptDao.intertScript(
                "抖音_养号",
                "comssandroidugcaweme",
                "com.ss.android.ugc.aweme/.splash.SplashActivity",
                ComUgcAwemeRaises.class.getSimpleName());
        scriptDao.intertScript(
                "抖音_私信",
                "comssandroidugcaweme",
                "com.ss.android.ugc.aweme/.splash.SplashActivity",
                ComUgcAwemePrivateMsg.class.getSimpleName());
        scriptDao.intertScript(
                "抖音_关注指定抖音号",
                "comssandroidugcaweme",
                "com.ss.android.ugc.aweme/.splash.SplashActivity",
                ComUgcAwemeFindUserNumber.class.getSimpleName());
        scriptDao.intertScript(
                "抖音_关注抖音号作者粉丝",
                "comssandroidugcaweme",
                "com.ss.android.ugc.aweme/.splash.SplashActivity",
                ComUgcAwemePrivateMsgForAuthor.class.getSimpleName());
        scriptDao.intertScript(
                "抖音_关注直播评论人",
                "comssandroidugcaweme",
                "com.ss.android.ugc.aweme/.splash.SplashActivity",
                ScriptComUgcAwemeLive.class.getSimpleName());
        scriptDao.intertScript(
                "抖音_发视频",
                "comssandroidugcaweme",
                "com.ss.android.ugc.aweme/.splash.SplashActivity",
                ComUgcAwemeSendVideo.class.getSimpleName());
    }
}
