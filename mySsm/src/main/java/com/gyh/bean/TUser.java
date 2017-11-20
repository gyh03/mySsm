package com.gyh.bean;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import com.gyh.utils.validator.Checker;

public class TUser {
    private Integer id;

    @NotNull(message="用户名不可为空")
    private String username;
    
    @Size(min=6,max=20,message="密码长度应大于6位，小于10位")
    @NotNull(message="密码不可为空")
    private String password;
    
    @Email(message="不是正确的email格式")
    private String email;

    //正则匹配手机号
    @Pattern(regexp=Checker.REGEX_MOBILE,message="手机号格式不正确")
    private String mobile;

    private Boolean sex;

//    自定义
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Past(message="出生日期必须是过去的时间")
    private Date birthday;
    
    @NumberFormat(pattern="#.###.##")
    private BigDecimal salary;

    private Date createtime;

    private Boolean delfalg;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Boolean getDelfalg() {
        return delfalg;
    }

    public void setDelfalg(Boolean delfalg) {
        this.delfalg = delfalg;
    }
}