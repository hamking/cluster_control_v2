package com.zciteam.enums;

public enum CodeEnum {
    CODE_200(200,"OK"),
    CODE_202(202,"202"),
    CODE_404(404,"404"),
    CODE_400(400,"参数错误"),
    CODE_403(403,"文件不存在"),
    CODE_503(503,"服务器错误"),
    CODE_50001(50001,"设备掉线, 请检查设备!"),
    CODE_50002(50002,"没有找到这部设备"),
    CODE_50003(50003,"手机初始化失败"),
    CODE_50004(50004,"设备同步超时"),
    CODE_60001(60001,"程序安装失败"),
    CODE_70001(70001,"文件类型不受支持!");

    private int state;
    private String stateInfo;

    CodeEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static CodeEnum stateOf(int index){

        for(CodeEnum codeEnum: values()){
            if (codeEnum.getState() == index){
                return codeEnum;
            }
        }
        return null;
    }
}
