package com.adb.autoComponent;

import com.adb.util.FileUtil;
import java.io.IOException;

public class LayoutXml {
    /**
     * 获取界面布局信息
     * @param deviceUUID deviceUUID
     * @return 返回xml路径
     */
    public String getLayout(String deviceUUID){

        String path = "/tmp/yunsheng/book/";
        new FileUtil().createDir(path);

        try {
            new Execute().shell(deviceUUID, "uiautomator runtest LvmamaXmlKit.jar");
        } catch (IOException | InterruptedException ignored) {
        }

        try {
            new Execute().pull(deviceUUID, "/data/local/tmp/local/tmp/uidump.xml", path + deviceUUID + ".xml");
        } catch (IOException | InterruptedException ignored) {
        }

        String xmlFile = path + deviceUUID + ".xml";

        return xmlFile;
    }
}
