package com.wormchaos.common.Exception;

/**
 * Created by wormchaos on 2015/6/18.
 */
public class ErrorCodeException extends RuntimeException{

    private static final long serialVersionUID = 3063805789474333543L;

    /**
     * 错误码
     */
    private int error;

    private String desc;


    public ErrorCodeException() {
    }

    public ErrorCodeException(int error) {
        this.error = error;
    }
    public ErrorCodeException(int error, String desc) {
        this.error = error;
        this.desc = desc;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
