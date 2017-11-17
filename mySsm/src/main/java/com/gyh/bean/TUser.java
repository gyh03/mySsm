package com.gyh.bean;

import java.util.Date;

import com.gyh.common.validation.IsString;

public class TUser {
    private Integer id;
    
    @IsString(minLength=1,maxLength=15)
    private String username;
    @IsString(minLength=1,maxLength=20)
    private String password;
    @IsString(minLength=11,maxLength=11)
    private String mobile;

    private Boolean sex;

    private Date createtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}