package com.wormchaos.controller.dto;

import java.io.Serializable;

/**
 * Created by wormchaos on 2015/6/11.
 */
public class DecorationDto implements Serializable{

    private static final long serialVersionUID = -3216990902992486001L;

    public DecorationDto() {
        this.jianyue = "";
        this.huali = "";
        this.youya = "";
        this.huopo = "";
        this.chengshu = "";
        this.keai = "";
        this.xinggan = "";
        this.qingchun = "";
        this.qingliang = "";
        this.baonuan = "";
        this.label1 = "";
        this.label2 = "";
        this.label3 = "";
        this.getfrom = "";
        this.type = "";
    }

    private int id;

    private int num;

    private String name;

    private int level;

    private String jianyue;

    private String huali;

    private String youya;

    private String huopo;

    private String chengshu;

    private String keai;

    private String xinggan;

    private String qingchun;

    private String qingliang;

    private String baonuan;

    private String label1;

    private String label2;

    private String label3;

    private String getfrom;

    private String type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getJianyue() {
        return jianyue;
    }

    public void setJianyue(String jianyue) {
        this.jianyue = jianyue;
    }

    public String getHuali() {
        return huali;
    }

    public void setHuali(String huali) {
        this.huali = huali;
    }

    public String getYouya() {
        return youya;
    }

    public void setYouya(String youya) {
        this.youya = youya;
    }

    public String getHuopo() {
        return huopo;
    }

    public void setHuopo(String huopo) {
        this.huopo = huopo;
    }

    public String getChengshu() {
        return chengshu;
    }

    public void setChengshu(String chengshu) {
        this.chengshu = chengshu;
    }

    public String getKeai() {
        return keai;
    }

    public void setKeai(String keai) {
        this.keai = keai;
    }

    public String getXinggan() {
        return xinggan;
    }

    public void setXinggan(String xinggan) {
        this.xinggan = xinggan;
    }

    public String getQingchun() {
        return qingchun;
    }

    public void setQingchun(String qingchun) {
        this.qingchun = qingchun;
    }

    public String getQingliang() {
        return qingliang;
    }

    public void setQingliang(String qingliang) {
        this.qingliang = qingliang;
    }

    public String getBaonuan() {
        return baonuan;
    }

    public void setBaonuan(String baonuan) {
        this.baonuan = baonuan;
    }

    public String getLabel1() {
        return label1;
    }

    public void setLabel1(String label1) {
        this.label1 = label1;
    }

    public String getLabel2() {
        return label2;
    }

    public void setLabel2(String label2) {
        this.label2 = label2;
    }

    public String getLabel3() {
        return label3;
    }

    public void setLabel3(String label3) {
        this.label3 = label3;
    }

    public String getGetfrom() {
        return getfrom;
    }

    public void setGetfrom(String getfrom) {
        this.getfrom = getfrom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
