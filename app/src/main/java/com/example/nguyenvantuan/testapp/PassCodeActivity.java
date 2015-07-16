package com.example.nguyenvantuan.testapp;

import android.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.nguyenvantuan.testapp.fragment.PassCodeFragment;
import com.example.nguyenvantuan.testapp.fragment.PassCodeFragment_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_pass_code)
public class PassCodeActivity extends BaseActionBarActivity {

    @ViewById(R.id.tool_bar)
    protected Toolbar mToolbar;

    @AfterViews
    public void init() {
        setUpActionBar();
        PassCodeFragment passCodeFragment = PassCodeFragment_.builder().build();
        getSupportFragmentManager().beginTransaction().replace(R.id.fr_pass_code, passCodeFragment).commit();
    }

    public void setUpActionBar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.bg_empty);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}
