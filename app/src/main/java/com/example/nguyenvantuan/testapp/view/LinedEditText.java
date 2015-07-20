package com.example.nguyenvantuan.testapp.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.EditText;

/**
 * Created by nguyenvantuan on 7/20/15.
 */
public class LinedEditText extends EditText {

    private Paint mPaint = new Paint();

    public LinedEditText(Context context) {
        super(context);
        initPaint();
    }

    public LinedEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public LinedEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initPaint();
    }

    private void initPaint() {
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(0x80000000);
        setGravity(Gravity.NO_GRAVITY);
    }

    @Override protected void onDraw(Canvas canvas) {
        int left = getLeft();
        int right = getWidth();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int height = getHeight();
        int lineHeight = getLineHeight();
        int count = (height-paddingTop-paddingBottom) / lineHeight;

        for (int i = 0; i < count; i++) {
            int baseline = lineHeight * (i+1) + paddingTop;
            canvas.drawLine(paddingLeft, baseline, right-paddingRight, baseline, mPaint);
        }

        super.onDraw(canvas);
    }

}