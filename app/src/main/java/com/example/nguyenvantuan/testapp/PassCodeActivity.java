package com.example.nguyenvantuan.testapp;

import android.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.nguyenvantuan.testapp.fragment.PassCodeFragment;
import com.example.nguyenvantuan.testapp.fragment.PassCodeFragment_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_pass_code)
public class PassCodeActivity extends ActionBarActivity {

    @AfterViews
    public void init() {
        PassCodeFragment passCodeFragment = PassCodeFragment_.builder().build();
        getSupportFragmentManager().beginTransaction().replace(R.id.fr_pass_code, passCodeFragment).commit();
    }
}
