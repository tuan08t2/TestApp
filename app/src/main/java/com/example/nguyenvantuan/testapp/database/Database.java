package com.example.nguyenvantuan.testapp.database;

import android.content.Context;

import com.example.nguyenvantuan.testapp.model.User;

import io.realm.Realm;

/**
 * Created by nguyenvantuan on 7/15/15.
 */
public class Database {

    public static Database mNewInstance;

    public static Database getInstance(Context context) {
        if(mNewInstance == null) {
            mNewInstance = new Database();
        }
        return mNewInstance;
    }

    public void createUser(String email, String password) {
        createUser(email, password, "");
    }

    public void createUser(final String email, final String password, final String passCode) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                User user = realm.createObject(User.class);
                user.setEmail(email);
                user.setPassword(password);
                user.setPassword(passCode);
                user.setIsLogin(true);
            }
        });
    }

    public User getUser() {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(User.class).findFirst();
    }
}
