package com.cpunisher.model;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class User {

    private int id;
    @NotBlank(message = "ID不能为空")
    private String openId;
    @NotBlank(message = "密码不能为空")
    @Length(min = 6, max = 15, message = "密码长度必须是6-15位")
    private String password;
    @NotBlank(message = "昵称不能为空")
    private String nickname;
    private String iconUrl;

    public User() {}

    public User(int id, String openId, String password, String nickname, String iconUrl) {
        this.id = id;
        this.openId = openId;
        this.password = password;
        this.nickname = nickname;
        this.iconUrl = iconUrl;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", openId='" + openId + '\'' +
                ", nickname='" + nickname + '\'' +
                ", iconUrl='" + iconUrl + '\'' +
                '}';
    }
}
