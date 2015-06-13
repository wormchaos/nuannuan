package com.wormchaos.controller.dto;

import java.io.Serializable;

/**
 * Created by wormchaos on 2015/6/13.
 */
public class LoginBean implements Serializable {

    private static final long serialVersionUID = -5002916306100117668L;

    private int id;

    private String username;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
