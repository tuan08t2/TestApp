package com.example.nguyenvantuan.testapp.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.nguyenvantuan.testapp.R;

public class FontSizeSeekBar extends View {
	protected Bitmap mFullSeekBarBitmap;
	protected Canvas mFullSeekBarCanvas;
	protected Bitmap mSeekBarBitmap;
	protected Canvas mSeekBarCanvas;
	protected int mSeekBarOffsetX;
	protected int mThumbRadius = 20;
	protected int mSeekBarHeight;
	protected int mOldSeekBarHeight;
	protected float mValue = 0.5f;
	private Paint mSeekBarPaint = PaintBuilder.newPaint().build();
	private Paint mSolid = PaintBuilder.newPaint().build();
	private int mColor = Color.BLUE;
	private Paint clearingStroke = PaintBuilder.newPaint().color(0xffffffff).xPerMode(PorterDuff.Mode.CLEAR).build();
	private Context mContext;

	public FontSizeSeekBar(Context context) {
		super(context);
		mContext = context;
	}

	public FontSizeSeekBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
	}

	public FontSizeSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		mContext = context;
	}

	public void init() {
		mThumbRadius = getDimension(R.dimen.colorpickerview__default_slider_handler_radius);
		mSeekBarHeight = getDimension(R.dimen.colorpickerview__default_slider_height);
		mOldSeekBarHeight = mSeekBarHeight;
		mSeekBarOffsetX = mThumbRadius;
	}

	protected void updateBar() {

		if (mSeekBarBitmap == null) {
			createSeekBar();
			createFullSeekBar();
		}
		drawSeekBar();
	}

	public void createSeekBar() {

		int width = getWidth();

		if(mSeekBarBitmap != null) {
			mSeekBarBitmap.recycle();
		}
		mSeekBarBitmap = Bitmap.createBitmap(width - mSeekBarOffsetX * 2, mSeekBarHeight, Bitmap.Config.ARGB_8888);//seekbar visible
		mSeekBarCanvas = new Canvas(mSeekBarBitmap);
	}

	protected void createFullSeekBar() {

		int width = getWidth();
		int height = getHeight();

		if (mFullSeekBarBitmap == null || mFullSeekBarBitmap.getWidth() != width || mFullSeekBarBitmap.getHeight() != height) {
			if (mFullSeekBarBitmap != null) {
				mFullSeekBarBitmap.recycle();
			}
			mFullSeekBarBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888); // full seekbar
			mFullSeekBarCanvas = new Canvas(mFullSeekBarBitmap);
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (mSeekBarBitmap != null && mFullSeekBarCanvas != null) {
			if(mOldSeekBarHeight != mSeekBarHeight) {
				mOldSeekBarHeight = mSeekBarHeight;
				createSeekBar();
				drawSeekBar();
			}
			mFullSeekBarCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
			mFullSeekBarCanvas.drawBitmap(mSeekBarBitmap, mSeekBarOffsetX, (getHeight() - mSeekBarBitmap.getHeight()) / 2, null);

			/** Draw thumb **/
			float x = mThumbRadius + mValue * (getWidth() - mThumbRadius * 2);
			float y = getHeight() / 2f;

			mFullSeekBarCanvas.drawCircle(x, y, mThumbRadius, clearingStroke);
			mSolid.setColor(mColor);
			mFullSeekBarCanvas.drawCircle(x, y, mThumbRadius * 0.75f, mSolid);

			/** Draw all **/
			canvas.drawBitmap(mFullSeekBarBitmap, 0, 0, null);
		}
	}

	public void handleSeekBarHeight(float posX) {
		int d = mFullSeekBarBitmap.getWidth()/8;
		for(int index = 1; index <= 8; index++) {
			if(index * d >= posX) {
				mSeekBarHeight = convertDPToPixels(index);
				return;
			}
		}
	}

	public void drawSeekBar() {
		int width = mSeekBarCanvas.getWidth();
		int height = mSeekBarCanvas.getHeight();

		mSeekBarPaint.setColor(mColor);
		mSeekBarCanvas.drawRect(0, 0, width, height, mSeekBarPaint);
	}

	public void onValueChanged(float value) {

	}

	@Override
	public void onWindowFocusChanged(boolean hasWindowFocus) {
		super.onWindowFocusChanged(hasWindowFocus);
		init();
		updateBar();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int width = 0;
		if (widthMode == MeasureSpec.UNSPECIFIED)
			width = widthMeasureSpec;
		else if (widthMode == MeasureSpec.AT_MOST)
			width = MeasureSpec.getSize(widthMeasureSpec);
		else if (widthMode == MeasureSpec.EXACTLY)
			width = MeasureSpec.getSize(widthMeasureSpec);

		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int height = 0;
		if (heightMode == MeasureSpec.UNSPECIFIED)
			height = heightMeasureSpec;
		else if (heightMode == MeasureSpec.AT_MOST)
			height = MeasureSpec.getSize(heightMeasureSpec);
		else if (heightMode == MeasureSpec.EXACTLY)
			height = MeasureSpec.getSize(heightMeasureSpec);

		setMeasuredDimension(width, height);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
			case MotionEvent.ACTION_MOVE: {
				mValue = (event.getX() - mSeekBarOffsetX) / mSeekBarBitmap.getWidth();
				mValue = Math.max(0, Math.min(mValue, 1));
				handleSeekBarHeight(event.getX());
				invalidate();
				break;
			}
			case MotionEvent.ACTION_UP: {
				onValueChanged(mValue);
				invalidate();
			}
		}
		return true;
	}

	public int convertDPToPixels(int dp) {
		float density = mContext.getResources().getDisplayMetrics().density;
		return (int) (dp * density);
	}

	public int getDimension(int id) {
		return getResources().getDimensionPixelSize(id);
	}
	public void setColor(int color) {
		mColor = color;
		if (mSeekBarBitmap != null) {
			updateBar();
			invalidate();
		}
	}
}