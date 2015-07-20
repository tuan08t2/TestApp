package com.example.nguyenvantuan.testapp;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.example.nguyenvantuan.testapp.fragment.ChangeEmailFragment;
import com.example.nguyenvantuan.testapp.fragment.ChangeEmailFragment_;
import com.example.nguyenvantuan.testapp.fragment.FeedbackFragment;
import com.example.nguyenvantuan.testapp.fragment.FeedbackFragment_;
import com.example.nguyenvantuan.testapp.fragment.SettingFontFragment;
import com.example.nguyenvantuan.testapp.fragment.SettingFontFragment_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_change_email)
public class ChangeEmailActivity extends BaseActionBarActivity {

    @ViewById(R.id.tool_bar)
    protected Toolbar mToolbar;

    @AfterViews
    public void init() {
        setUpActionBar();
        //ChangeEmailFragment changeEmailFragment = ChangeEmailFragment_.builder().build();
//        SettingFontFragment f = SettingFontFragment_.builder().build();
        FeedbackFragment f = FeedbackFragment_.builder().build();
        getSupportFragmentManager().beginTransaction().replace(R.id.fr_change_email, f).commit();
    }

    public void setUpActionBar() {
        setSupportActionBar(mToolbar);
    }
}
