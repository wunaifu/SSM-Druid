package com.wnf.pojo;

import java.io.Serializable;

public class User implements Serializable {
    /**
     * id，自动增长
     */
    private Integer userId;

    /**
     * 手机（登录账号）
     */
    private String phone;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 联系地址
     */
    private String address;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别（0女，1男）
     */
    private Integer sex;

    /**
     * 简介
     */
    private String userInfo;

    /**
     * 头像
     */
    private String headicon;

    /**
     * 注册时间
     */
    private String registerTime;

    /**
     * 权限（0普通用户，1可接单用户，2被禁用用户）
     */
    private Integer permission;

    private static final long serialVersionUID = 1L;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo == null ? null : userInfo.trim();
    }

    public String getHeadicon() {
        return headicon;
    }

    public void setHeadicon(String headicon) {
        this.headicon = headicon == null ? null : headicon.trim();
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime == null ? null : registerTime.trim();
    }

    public Integer getPermission() {
        return permission;
    }

    public void setPermission(Integer permission) {
        this.permission = permission;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", phone=").append(phone);
        sb.append(", password=").append(password);
        sb.append(", nickname=").append(nickname);
        sb.append(", address=").append(address);
        sb.append(", age=").append(age);
        sb.append(", sex=").append(sex);
        sb.append(", userInfo=").append(userInfo);
        sb.append(", headicon=").append(headicon);
        sb.append(", registerTime=").append(registerTime);
        sb.append(", permission=").append(permission);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}