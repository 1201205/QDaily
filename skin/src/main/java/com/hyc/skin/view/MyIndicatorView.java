package com.hyc.skin.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import com.hyc.skin.R;

/**
 * Created by hyc on 2017/5/15.
 */

public class MyIndicatorView extends View {
    private Paint mPaint;
    private int mWidth, mHeight;
    private RectF mTop, mBottom;
    private float mTopTextSize, mBottomTextSize;
    private int mTopLine;
    private int mBottomLine;
    private float mBorder;
    private int mBackGroundColor, mTextColor, mLineColor, mBorderColor;
    private String mCount, mCurrent;


    public MyIndicatorView(Context context) {
        this(context, null);
    }


    public MyIndicatorView(Context context,
                           @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public MyIndicatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MyIndicatorView,
            defStyleAttr, 0);
        mBorder = array.getDimension(R.styleable.MyIndicatorView_border, 0f);
        array.recycle();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBackGroundColor = Color.parseColor("#FCDF63");
        mTextColor = Color.parseColor("#2A2A2A");
        mLineColor = Color.parseColor("#FF5454");
        mBorderColor = Color.parseColor("#FFFFFF");
        mTopTextSize = getTextSize(context, 18);
        mBottomTextSize = getTextSize(context, 16);
        mPaint.setColor(Color.RED);
    }


    public void setIndicator(int count, int current) {
        mCount = String.valueOf(count);
        mCurrent = String.valueOf(current);
        invalidate();
    }


    @Override protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (mWidth != w || mHeight != h) {
            mWidth = w;
            mHeight = h;
            mTop = getTopRect();
            mBottom = getBottomRect();
            mPaint.setTextSize(mTopTextSize);
            Paint.FontMetricsInt fontMetrics = mPaint.getFontMetricsInt();
            mTopLine = (int) ((mTop.bottom + mTop.top - fontMetrics.bottom - fontMetrics.top) / 2);
            mPaint.setTextSize(mBottomTextSize);
            fontMetrics = mPaint.getFontMetricsInt();
            mBottomLine = (int) (
                (mBottom.bottom + mBottom.top - fontMetrics.bottom - fontMetrics.top) /
                    2);
        }

    }


    private RectF getTopRect() {
        float right = (mWidth - 2 * mBorder) * 0.55f + mBorder;
        float bottom = right;
        float top = bottom - mTopTextSize;
        float left = (float) ((mWidth) / 2 -
            (Math.sqrt(Math.pow((mWidth - 2 * mBorder) / 2, 2) - Math.pow(mWidth / 2 - top, 2))));
        return new RectF(left, top, right, bottom);
    }


    private RectF getBottomRect() {
        float top = (mWidth - 2 * mBorder) * 0.55f + mBorder;
        float left = top;
        float bottom = top + mBottomTextSize;
        float right = (float) (mWidth / 2 +
            (Math.sqrt(
                Math.pow((mWidth - 2 * mBorder) / 2, 2) - Math.pow(bottom - (mWidth / 2), 2))));
        return new RectF(left, top, right, bottom);
    }


    @Override protected void onDraw(Canvas canvas) {
        // canvas.drawCircle(mWidth/2,mWidth/2,mWidth/2,mPaint);
        mPaint.setColor(mBorderColor);
        if (mBorder > 0) {
            canvas.drawCircle(mWidth / 2, mHeight / 2, mWidth / 2, mPaint);
        }
        mPaint.setColor(mBackGroundColor);

        canvas.drawCircle(mWidth / 2, mHeight / 2, mWidth / 2 - mBorder, mPaint);
        mPaint.setColor(mLineColor);
        canvas.save();
        canvas.rotate(-45f, (mWidth - mBorder) * 0.5f, mWidth * 0.5f);
        canvas.drawLine(0.1f * mHeight + mBorder, (mWidth - 2 * mBorder) * 0.55f + mBorder,
            0.9f * mHeight - mBorder, (mWidth - 2 * mBorder) * 0.55f + mBorder, mPaint);
        canvas.restore();
        mPaint.setTextSize(mTopTextSize);
        //canvas.drawRect(mTop,mPaint);
        //canvas.drawRect(mBottom,mPaint);
        mPaint.setColor(mTextColor);
        canvas.drawText(mCurrent, mTop.left, mTopLine, mPaint);
        mPaint.setTextSize(mBottomTextSize);
        canvas.drawText(mCount, mBottom.left
            , mBottomLine, mPaint);
        //

    }


    private float getTextSize(Context context, int size) {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP, size, context.getResources().getDisplayMetrics());
    }
}
