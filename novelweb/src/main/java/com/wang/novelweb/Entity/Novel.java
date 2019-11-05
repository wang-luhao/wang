package com.wang.novelweb.Entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


public class Novel {
    @ApiModelProperty(value = "用户名")
    private int nid;
    @ApiModelProperty(value = "用户名")
    private String nname;
    @ApiModelProperty(value = "用户名")
    private String nauthor;
    @ApiModelProperty(value = "用户名")
    private String ntype;
    @ApiModelProperty(value = "用户名")
    private String nurl;

    public int getNid() {
        return nid;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    public String getNname() {
        return nname;
    }

    public void setNname(String nname) {
        this.nname = nname;
    }

    public String getNauthor() {
        return nauthor;
    }

    public void setNauthor(String nauthor) {
        this.nauthor = nauthor;
    }

    public String getNtype() {
        return ntype;
    }

    public void setNtype(String ntype) {
        this.ntype = ntype;
    }

    public String getNurl() {
        return nurl;
    }

    public void setNurl(String nurl) {
        this.nurl = nurl;
    }
}
