package com.example.nguyenvantuan.testapp.database;

import android.content.Context;

import com.example.nguyenvantuan.testapp.model.User;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by nguyenvantuan on 7/15/15.
 */
public class Database {

    public static Database mNewInstance;
    private static final String REAL_DEFAULT_CONFIG = "real_default_config";

    public static Database getInstance(Context context) {
        if(mNewInstance == null) {
            mNewInstance = new Database();
            RealmConfiguration realmConfig = new RealmConfiguration.Builder(context)
                    .name(REAL_DEFAULT_CONFIG)
                    //.encryptionKey(getKey())
                    .schemaVersion(42)
                    //.migration(new MyMigration())
                    .build();
            Realm.setDefaultConfiguration(realmConfig);
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

    public void updateUserPassCode(final String newPassCode) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                User user = realm.where(User.class).findFirst();
                user.setPassCode(newPassCode);
            }
        });

    }

    public void updateUserEmail(final String newEmail) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                User user = realm.where(User.class).findFirst();
                user.setEmail(newEmail);
            }
        });

    }

    public void updateUserPassword(final String newPassword) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                User user = realm.where(User.class).findFirst();
                user.setPassword(newPassword);
            }
        });

    }

    public User getUser() {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(User.class).findFirst();
    }

}
