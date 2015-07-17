package com.example.nguyenvantuan.testapp.fragment;


import android.support.v4.app.Fragment;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.nguyenvantuan.testapp.R;
import com.example.nguyenvantuan.testapp.database.Database;
import com.example.nguyenvantuan.testapp.util.Util;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_setting_font)
public class SettingFontFragment extends Fragment{


    @AfterViews
    public void init() {
        getActivity().setTitle(R.string.title_activity_pass_code);
    }




}
