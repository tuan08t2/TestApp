package com.example.nguyenvantuan.testapp;

import android.os.Handler;
import android.support.v7.app.ActionBarActivity;

import com.example.nguyenvantuan.testapp.database.Database;
import com.example.nguyenvantuan.testapp.model.User;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_splash)
public class SplashActivity extends ActionBarActivity {

    @AfterViews
    public void initHandler() {
        Handler mHandler = new Handler();
        Runnable mUpdateTimeTask = new Runnable() {
            public void run() {
                goToActivity();
                finish();
            }
        };
        mHandler.postDelayed(mUpdateTimeTask, 2000);
    }

    public void goToActivity() {
        User user = Database.getInstance(this).getUser();
        if(user == null || !user.isLogin()) {
            LoginActivity_.intent(this).start();

        } else {
            PassCodeActivity_.intent(this).start();
        }
    }
}
