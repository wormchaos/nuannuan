package com.wormchaos.dao.entity;

/**
 * Created by wormchaos on 2015/6/13.
 */
public class Wardrobe {

    private int userId;

    private int clothId;

    private int delFlag;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getClothId() {
        return clothId;
    }

    public void setClothId(int clothId) {
        this.clothId = clothId;
    }

    public int getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(int delFlag) {
        this.delFlag = delFlag;
    }
}
