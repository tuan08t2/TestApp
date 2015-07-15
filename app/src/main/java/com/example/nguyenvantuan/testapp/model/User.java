package com.example.nguyenvantuan.testapp.model;

import io.realm.RealmObject;

/**
 * Created by nguyenvantuan on 7/15/15.
 */
public class User extends RealmObject {

    private String email;
    private String password;
    private String passCode;
    private boolean isLogin;

    public boolean isLogin() {
        return isLogin;
    }

    public void setIsLogin(boolean isLogin) {
        this.isLogin = isLogin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassCode() {
        return passCode;
    }

    public void setPassCode(String passCode) {
        this.passCode = passCode;
    }
}
