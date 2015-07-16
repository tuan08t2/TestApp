package com.example.nguyenvantuan.testapp;

import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by nguyenvantuan on 7/16/15.
 */
public abstract class BaseActionBarActivity extends ActionBarActivity{
    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
        setTitle(title.toString());
    }

    @Override
    public void setTitle(int titleId) {
        super.setTitle(titleId);
        setTitle(getResources().getString(titleId));
    }

    public void setTitle(String title) {
        View v = findViewById(R.id.tool_bar);
        ImageView img = (ImageView) v.findViewById(R.id.img_toolbar_title);
        TextView tvTitle = (TextView) v.findViewById(android.R.id.title);
        img.setVisibility(View.GONE);
        tvTitle.setVisibility(View.VISIBLE);
        tvTitle.setText(title);
    }

    public void setTitle(Drawable drawable) {
        View v = findViewById(R.id.tool_bar);
        ImageView img = (ImageView) v.findViewById(R.id.img_toolbar_title);
        TextView tvTitle = (TextView) v.findViewById(android.R.id.title);
        img.setVisibility(View.VISIBLE);
        tvTitle.setVisibility(View.GONE);
        img.setImageDrawable(drawable);
    }
}
