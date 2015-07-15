package com.example.nguyenvantuan.testapp;

import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.nguyenvantuan.testapp.database.Database;
import com.example.nguyenvantuan.testapp.model.User;
import com.example.nguyenvantuan.testapp.util.Util;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.TextChange;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_login)
public class LoginActivity extends ActionBarActivity{

    @ViewById(R.id.tv_login_status)
    protected TextView tvLoginStatus;

    @ViewById(R.id.et_login_email)
    protected EditText etLoginEmail;

    @ViewById(R.id.et_login_password)
    protected EditText etLoginPassword;

    @ViewById(R.id.et_login_confirm_password)
    protected EditText etLoginConfirmPassword;

    @ViewById(R.id.btn_login)
    protected Button btnLogin;

    private User user;

    @Click(R.id.btn_login)
    public void login() {
        tvLoginStatus.setText("");
        if(user == null) {
            doCreateAccount();
        } else {
            doLogin();
        }
        finish();
    }


    @AfterViews
    public void init() {

        user = Database.getInstance(this).getUser();
        if(user == null) {
            etLoginConfirmPassword.setVisibility(View.GONE);
        }
    }

    public void doCreateAccount() {
        if (etLoginConfirmPassword.getText().toString().equals(etLoginPassword.getText().toString())) {
            PassCodeActivity_.intent(this).start();
        } else {
            tvLoginStatus.setText(getResources().getString(R.string.confirm_password_not_match));
        }
    }

    public void doLogin() {
        if(!etLoginEmail.getText().toString().equals(user.getEmail()) || !etLoginPassword.getText().toString().equals(user.getPassword())) {
            tvLoginStatus.setText(getResources().getString(R.string.login_fail));
        }
        else {
            PassCodeActivity_.intent(this).start();
        }
    }
}
