package com.zciteam.dto;

import com.zciteam.enums.CodeEnum;

/**
 * 返回结果
 * @param <T>
 */
public class Result<T> {

    private T data;
    private int code;
    private String message;

    public Result(T data, CodeEnum codeEnum) {
        this.data = data;
        this.code = codeEnum.getState();
        this.message = codeEnum.getStateInfo();
    }

    public Result(T data, CodeEnum codeEnum, String message) {
        this.data = data;
        this.code = codeEnum.getState();
        this.message = codeEnum.getStateInfo() + ":" + message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
