package com.example.nguyenvantuan.testapp.fragment;


import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nguyenvantuan.testapp.MainActivity_;
import com.example.nguyenvantuan.testapp.R;
import com.example.nguyenvantuan.testapp.database.Database;
import com.example.nguyenvantuan.testapp.notify.AlarmReceiver;
import com.example.nguyenvantuan.testapp.notify.NotifyService;
import com.example.nguyenvantuan.testapp.util.PassCode;
import com.example.nguyenvantuan.testapp.util.PassCode.OnPassCodeChangeListener;
import com.example.nguyenvantuan.testapp.util.Util;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import java.util.Calendar;

@EFragment(R.layout.fragment_set_reminder)
public class SetReminderFragment extends Fragment{



    @AfterViews
    public void init() {


    }

    public void alarmMethod(){

        Intent myIntent = new Intent(getActivity() , NotifyService.class);
        //Intent myIntent = new Intent(getActivity() , AlarmReceiver.class);
        AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getService(getActivity(), 0, myIntent, 0);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.AM_PM, Calendar.AM);
        calendar.add(Calendar.DAY_OF_MONTH, 1);

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 1000 * 60 * 60 * 24, pendingIntent);
    }


}
