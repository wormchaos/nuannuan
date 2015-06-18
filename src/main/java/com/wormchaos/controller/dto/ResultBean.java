package com.wormchaos.controller.dto;

import java.io.Serializable;

/**
 * Created by wormchaos on 2015/6/18.
 */
public class ResultBean implements Serializable{

    private static final long serialVersionUID = 5323150505725749159L;
    private int code;

    private String desc;

    private Object content;

    public ResultBean(){
        this.code = 0;
    }

    public ResultBean(int code){
        this.code = code;
    }

    public ResultBean(int code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
