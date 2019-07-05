package com.zciteam.dto;

/**
 * 返回手机在线情况
 */
public class DeviceSituationResult {

    private int online;
    private int offline;
    //授权设备
    private int authorization;

    public DeviceSituationResult(int online, int offline, int authorization) {
        this.online = online;
        this.offline = offline;
        this.authorization = authorization;
    }

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }

    public int getOffline() {
        return offline;
    }

    public void setOffline(int offline) {
        this.offline = offline;
    }

    public int getAuthorization() {
        return authorization;
    }

    public void setAuthorization(int authorization) {
        this.authorization = authorization;
    }
}
