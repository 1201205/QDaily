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
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ray on 17/2/20.
 */

public class SkinDateView extends View implements SkinChangeable {

    String[] weekDays = { "SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT" };
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
    private RectF mBottom;
    private Path mTopPath;
    private float mStrokeWidth;
    private String week;
    private String day;
    private int mTopLine;
    private int mBottomLine;
    private float topTextSize;
    private float bottomTextSize;


    public SkinDateView(Context context) {
        this(context, null);
    }


    public SkinDateView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public SkinDateView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SkinDateView);
        mBorderColor = typedArray.getResourceId(R.styleable.SkinDateView_border_color, 0);
        mTopBgColor = typedArray.getResourceId(R.styleable.SkinDateView_top_color, 0);
        mTopFontColor = typedArray.getResourceId(R.styleable.SkinDateView_top_font_color, 0);
        mBottomBgColor = typedArray.getResourceId(R.styleable.SkinDateView_content_color, 0);
        mBottomFontColor = typedArray.getResourceId(R.styleable.SkinDateView_bottom_font_color, 0);
        typedArray.recycle();
        mStrokeWidth = dip2px(1, context);
        mCorner = 4 * mStrokeWidth;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPath = new Path();
        topTextSize= dip2px(11,context);
        bottomTextSize=  dip2px(12,context);
        skinChanged();

    }


    @Override protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        mRectF = new RectF(mStrokeWidth / 2, mStrokeWidth / 2, w - mStrokeWidth / 2,
            h - mStrokeWidth / 2);
        mContent = new RectF(mStrokeWidth, mStrokeWidth, w - mStrokeWidth,
            h - mStrokeWidth);
        mTop = new RectF(mStrokeWidth / 2, mStrokeWidth / 2, w - mStrokeWidth / 2, h * 0.45f);
        mBottom = new RectF(mStrokeWidth / 2, h * 0.45f, w - mStrokeWidth / 2,
            w - mStrokeWidth / 2);
        mPaint.setTextSize(20);
        Paint.FontMetricsInt fontMetrics = mPaint.getFontMetricsInt();
        mTopLine = (int) ((mTop.bottom + mTop.top - fontMetrics.bottom - fontMetrics.top) / 2);
        mBottomLine = (int) ((mBottom.bottom + mBottom.top - fontMetrics.bottom - fontMetrics.top) /
            2);
        Date date = new Date(System.currentTimeMillis());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        day = String.valueOf(getDay(calendar));
        week = getWeek(calendar);
    }


    private RectF mContent;


    @Override protected void onDraw(Canvas canvas) {
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(mRealBorder);
        mPaint.setStrokeWidth(mStrokeWidth);
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
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setColor(mRealTopFont);
        mPaint.setTextSize(topTextSize);
        canvas.drawText(week, mTop.centerX(), mTopLine, mPaint);
        mPaint.setColor(mRealBottomFont);
        mPaint.setTextSize(bottomTextSize);
        canvas.drawText(day, mBottom.centerX(), mBottomLine, mPaint);
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


    private String getWeek(Calendar calendar) {

        int week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        return weekDays[week];
    }


    private int getDay(Calendar calendar) {
        return calendar.get(Calendar.DAY_OF_MONTH);
    }
}
