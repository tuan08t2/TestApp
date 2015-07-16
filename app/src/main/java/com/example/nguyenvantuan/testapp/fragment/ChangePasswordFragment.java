package com.example.nguyenvantuan.testapp.fragment;


import android.support.v4.app.Fragment;
import android.widget.EditText;
import android.widget.TextView;

import com.example.nguyenvantuan.testapp.R;
import com.example.nguyenvantuan.testapp.database.Database;
import com.example.nguyenvantuan.testapp.util.Util;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_change_password)
public class ChangePasswordFragment extends Fragment{

    @ViewById(R.id.tv_change_password_status)
    protected TextView tvStatus;

    @ViewById(R.id.et_change_password_current)
    protected EditText etCurrentPassword;

    @ViewById(R.id.et_change_password_new)
    protected EditText etNewPassword;

    @ViewById(R.id.et_change_password_confirm)
    protected EditText etConfirmPassword;

    @Click(R.id.btn_change_password)
    protected void changePassword() {

        String password = Util.encrypt(etNewPassword.getText().toString());
        String newPassword = etCurrentPassword.getText().toString();
        String confirmPassword = etConfirmPassword.getText().toString();
        if(Database.getInstance(getActivity()).getUser().getPassword().equals(password)) {
            if(newPassword.isEmpty() || confirmPassword.isEmpty()) {
                tvStatus.setText(getResources().getString(R.string.text_edit_text_empty));
            }
            if(!newPassword.equals(confirmPassword)) {
                tvStatus.setText(getResources().getString(R.string.text_confirm_password_fail));
            } else {
                //todo
                tvStatus.setText(getResources().getString(R.string.text_change_password_success));
            }
        } else {
            tvStatus.setText(getResources().getString(R.string.text_confirm_password_fail));
            etNewPassword.setText("");
        }
    }

    @AfterViews
    public void init() {
        getActivity().setTitle(R.string.title_activity_pass_code);
    }




}
