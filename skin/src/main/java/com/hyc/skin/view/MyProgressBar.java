package com.hyc.skin.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.hyc.skin.R;

/**
 * Created by hyc on 2017/5/25.
 */

public class MyProgressBar extends View {
    private Paint mPaint;
    private int mWidth, mHeight, mRealWidth;
    private int mMainHeight, mContentHeight, mLeftCenter, mRightCenter;
    private RectF mContent, mMain, mProgressRect;
    private int mMainBg, mContentBg, leftIn, leftOut, rightIn, rightOut, progressIn, progressOut;
    private int mBitmapWidth, mBitmapHeight, mCircleWidth;
    private float mProgress = 0f;
    private int mRealPosition, mBitmapTop;
    private Context mContext;
    private Bitmap mBitmap;


    public MyProgressBar(Context context) {
        this(context, null);
    }


    public MyProgressBar(Context context,
                         @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public MyProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mMainBg = Color.parseColor("#E7E7E8");
        mContentBg = Color.parseColor("#DCDDDE");
        leftIn = Color.parseColor("#EEEEEE");
        leftOut = Color.parseColor("#FFEAA1");
        rightIn = Color.parseColor("#EEEEEE");
        rightOut = Color.parseColor("#FFC900");
        progressIn = Color.parseColor("#FFC81C");
        progressOut = Color.parseColor("#FFF6D5");

        mContext = context;
    }


    @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    @Override protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w != mWidth || mHeight != h) {
            mWidth = w;
            mHeight = h;
            initAllSize();
        }
    }


    private void initAllSize() {
        // BitmapFactory.Options op=new BitmapFactory.Options();
        // op.inJustDecodeBounds=true;
        // BitmapFactory.decodeResource(mContext.getResources(),R.drawable.icon_lab_tots_default_user,op);
        compressBitmap();
        mMainHeight = mHeight / 3;
        mContentHeight = mHeight / 6;
        mLeftCenter = mHeight / 6 + 5;
        mRightCenter = mWidth - mHeight / 6 - 5;
        mRealWidth = mWidth - mBitmapWidth - 20;
        mBitmapTop = (mHeight - mBitmapHeight) / 2;
        mRealPosition = (int) (mBitmapHeight / 2 + 10 + mRealWidth * mProgress);
        mMain = new RectF(0, mMainHeight, mWidth, mMainHeight * 2);
        mContent = new RectF(mLeftCenter, mHeight * 5 / 12, mRightCenter, mHeight * 7 / 12);
    }
    public float getProgress(){
        return mProgress;
    }


    private void compressBitmap() {
        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(),
            R.drawable.icon_lab_tots_default_user);
        int bitWidth = bitmap.getWidth();
        int bitHeight = bitmap.getHeight();
        Matrix matrix = new Matrix();
        float scaleWidth = ((float) mHeight - 20) / bitHeight;
        matrix.postScale(scaleWidth, scaleWidth);
        mBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitWidth,
            bitHeight, matrix, true);
        mBitmapWidth = mBitmap.getWidth();
        mBitmapHeight = mBitmap.getHeight();
        mCircleWidth = mBitmapWidth / 2 + 10;
    }

    // @Override public boolean dispatchTouchEvent(MotionEvent event) {
    //     if (event.getAction()== MotionEvent.ACTION_DOWN) {
    //         return true;
    //     }
    //     Log.e("hyc-dispatchTouchEvent",event.getAction()+"----"+event.getX());
    //     return super.dispatchTouchEvent(event);
    // }


    @Override protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mBitmap=null;
    }


    @Override public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                float x = event.getX();
                mProgress = x / mWidth;
                if (mProgress < 0) {
                    mProgress = 0;
                } else if (mProgress > 1) {
                    mProgress = 1;
                }
                mRealPosition = (int) (mBitmapHeight / 2 + 10 + mRealWidth * mProgress);
                invalidate();
                break;
        }
        return true;
    }


    @Override protected void onDraw(Canvas canvas) {
        mPaint.setColor(mMainBg);
        canvas.drawRoundRect(mMain, mMainHeight / 2, mMainHeight / 2, mPaint);
        mPaint.setColor(mContentBg);
        canvas.drawRect(mContent, mPaint);
        mPaint.setColor(progressOut);
        canvas.drawRect(mLeftCenter, mHeight * 5 / 12, mRealPosition, mHeight * 7 / 12, mPaint);
        mPaint.setColor(progressIn);
        canvas.drawRect(mLeftCenter, mHeight * 11 / 24, mRealPosition, mHeight * 13 / 24, mPaint);
        mPaint.setColor(leftOut);
        canvas.drawCircle(mLeftCenter, mHeight / 2, mContentHeight / 2, mPaint);
        mPaint.setColor(leftIn);
        canvas.drawCircle(mLeftCenter, mHeight / 2, mContentHeight / 4, mPaint);
        mPaint.setColor(rightOut);
        canvas.drawCircle(mRightCenter, mHeight / 2, mContentHeight / 2, mPaint);
        mPaint.setColor(rightIn);
        canvas.drawCircle(mRightCenter, mHeight / 2, mContentHeight / 4, mPaint);
        mPaint.setColor(progressIn);
        canvas.drawCircle(mRealPosition, mHeight / 2, mCircleWidth, mPaint);
        canvas.drawBitmap(mBitmap, mRealPosition - mBitmapHeight / 2, mBitmapTop, mPaint);
        // canvas.drawRoundRect();

    }
}
