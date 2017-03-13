package com.hyc.skin.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.hyc.skin.R;
import com.hyc.skin.core.SkinAttrs;
import com.hyc.skin.core.SkinChangeable;
import com.hyc.skin.core.SkinResources;

/**
 * Created by ray on 17/2/20.
 */

public class SkinDateView extends View implements SkinChangeable {
    private SkinAttrs mSkinAttrs;
    private int mBorderColor;
    private int mTopBgColor;
    private int mTopFontColor;
    private int mBottomBgColor;
    private int mBottomFontColor;

    private int mRealBorder;
    private int mRealTop;
    private int mRealTopFont;
    private int mRealBottom;
    private int mRealBottomFont;
    private Paint mPaint;
    private int mWidth;
    private int mHeight;
    private float mCorner;
    private RectF mRectF;
    private Path mPath;
    private RectF mTop;
    private Path mTopPath;


    public SkinDateView(Context context) {
        this(context, null);
    }


    public SkinDateView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public SkinDateView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SkinDateView);
        mBorderColor = typedArray.getColor(R.styleable.SkinDateView_border_color, 0);
        mTopBgColor = typedArray.getColor(R.styleable.SkinDateView_top_color, 0);
        mTopFontColor = typedArray.getColor(R.styleable.SkinDateView_top_font_color, 0);
        mBottomBgColor = typedArray.getColor(R.styleable.SkinDateView_content_color, 0);
        mBottomFontColor = typedArray.getColor(R.styleable.SkinDateView_bottom_font_color, 0);
        typedArray.recycle();
        mCorner = dip2px(20, context);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPath = new Path();
        skinChanged();

    }


    @Override protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        mRectF = new RectF(mCorner / 40, mCorner / 40, w - mCorner / 20, h - mCorner / 20);
        mContent = new RectF(mCorner * 3 / 40, mCorner * 3 / 40, w - mCorner * 3 / 40,
            h - mCorner * 3 / 40);
        mTop = new RectF(mCorner / 40, mCorner / 40, w - mCorner / 20, h * 0.4f);
    }


    private RectF mContent;


    @Override protected void onDraw(Canvas canvas) {
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(mRealBorder);
        mPaint.setStrokeWidth(mCorner / 20);
        canvas.drawRoundRect(mRectF, mCorner, mCorner, mPaint);
        int sc = canvas.saveLayer(0, 0, mWidth, mHeight, mPaint, Canvas.ALL_SAVE_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(mRealBottom);
        canvas.drawRoundRect(mContent, mCorner, mCorner, mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(mRealTop);
        canvas.drawRect(mTop, mPaint);
        mPaint.setXfermode(null);
        canvas.restoreToCount(sc);
    }


    @Override public void skinChanged() {
        mRealBorder = SkinResources.getInstance().getColor(mBorderColor);
        mRealBottom = SkinResources.getInstance().getColor(mBottomBgColor);
        mRealBottomFont = SkinResources.getInstance().getColor(mBottomFontColor);
        mRealTop = SkinResources.getInstance().getColor(mTopBgColor);
        mRealTopFont = SkinResources.getInstance().getColor(mTopFontColor);
        postInvalidate();
    }


    private float dip2px(float dpValue, Context context) {
        return context.getResources().getDisplayMetrics().density * dpValue + 0.5f;
    }
}
