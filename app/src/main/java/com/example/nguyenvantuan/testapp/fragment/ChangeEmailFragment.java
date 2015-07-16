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

@EFragment(R.layout.fragment_change_email)
public class ChangeEmailFragment extends Fragment{

    @ViewById(R.id.scroll_change_email)
    protected ScrollView mScrollChangeEmail;

    @ViewById(R.id.tv_change_email_status)
    protected TextView tvStatus;

    @ViewById(R.id.tv_change_email_current)
    protected TextView tvCurrentEmail;

    @ViewById(R.id.et_change_email_new)
    protected EditText etNewEmail;

    @ViewById(R.id.et_change_email_password)
    protected EditText etPassword;

    @ViewById(R.id.et_change_email_confirm)
    protected EditText etConfirmEmail;

    @Click(R.id.btn_change_email)
    protected void changeEmail() {

        String password = Util.encrypt(etPassword.getText().toString());
        String newEmail = etNewEmail.getText().toString();
        String confirmEmail = etConfirmEmail.getText().toString();
        if(Database.getInstance(getActivity()).getUser().getPassword().equals(password)) {
            if(newEmail.isEmpty() || confirmEmail.isEmpty()) {
                tvStatus.setText(getResources().getString(R.string.text_edit_text_empty));
            }
            if(!newEmail.equals(confirmEmail)) {
                tvStatus.setText(getResources().getString(R.string.text_confirm_email_fail));
            } else {
                //todo
                tvStatus.setText(getResources().getString(R.string.text_change_email_success));
            }
        } else {
            tvStatus.setText(getResources().getString(R.string.text_password_wrong));
            etPassword.setText("");
        }
    }

    @AfterViews
    public void init() {
        getActivity().setTitle(R.string.title_activity_pass_code);
    }




}
