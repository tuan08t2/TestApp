package com.example.nguyenvantuan.testapp.fragment;


import android.support.v4.app.Fragment;
import android.widget.EditText;

import com.example.nguyenvantuan.testapp.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_feedback)
public class FeedbackFragment extends Fragment{

    @ViewById(R.id.et_feedback_msg)
    protected EditText etFeedback;

    @Click(R.id.btn_feedback_send)
    protected void sendFeedback() {

    }

    @AfterViews
    public void init() {
        getActivity().setTitle(R.string.title_activity_pass_code);
    }




}
