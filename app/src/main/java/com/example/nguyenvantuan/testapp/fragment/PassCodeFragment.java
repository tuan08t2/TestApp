package com.example.nguyenvantuan.testapp.fragment;


import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.nguyenvantuan.testapp.MainActivity;
import com.example.nguyenvantuan.testapp.MainActivity_;
import com.example.nguyenvantuan.testapp.R;
import com.example.nguyenvantuan.testapp.database.Database;
import com.example.nguyenvantuan.testapp.util.PassCode;
import com.example.nguyenvantuan.testapp.util.PassCode.OnPassCodeChangeListener;
import com.example.nguyenvantuan.testapp.util.Util;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_pass_code)
public class PassCodeFragment extends Fragment implements OnPassCodeChangeListener{

    public enum Mode {
        CONFIRM,
        CREATE
    }

    @ViewById(R.id.tv_pass_code_status)
    protected TextView tvPassCodeStatus;

    @ViewById(R.id.ll_pass_code)
    protected LinearLayout llPassCode;

    @FragmentArg
    protected Mode mode;

    private PassCode passCode;

    @AfterViews
    public void init() {
        getActivity().setTitle(R.string.title_activity_pass_code);
        int etWidth = (Util.getScreenWidth(getActivity()) - Util.convertDPToPixels(getActivity(), 30)) / 4;
        LinearLayout.LayoutParams params;
        for (int index = 0; index < llPassCode.getChildCount(); index++) {
            View view = llPassCode.getChildAt(index);
            params = (LinearLayout.LayoutParams) view.getLayoutParams();
            params.width = etWidth;
            view.setLayoutParams(params);
        }
        passCode = new PassCode(getActivity(), llPassCode);
        passCode.requestFocus(0);
        passCode.setOnChangeListener(this);
    }

    @Override
    public void onChange(String passCode) {
        Log.i("xxx", passCode + ", " + Database.getInstance(getActivity()).getUser().getPassCode());
        if(mode == Mode.CREATE) {
            Database.getInstance(getActivity()).updateUserPassCode(passCode);
            MainActivity_.intent(getActivity()).start();
        } else if(Database.getInstance(getActivity()).getUser().getPassCode().equals(passCode)){
            MainActivity_.intent(getActivity()).start();
        }
        else {
            tvPassCodeStatus.setText(getResources().getString(R.string.text_pass_code_wrong));
        }
    }
}
