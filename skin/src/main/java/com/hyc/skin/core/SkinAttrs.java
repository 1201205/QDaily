package com.hyc.skin.core;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created by ray on 17/2/19.
 */

public class SkinAttrs {
    private int mTextColor = -1;
    private int mBgRes = -1;
    private boolean hasDrawable;

    @Override
    public String toString() {
        return "SkinAttrs{" +
                "mTextColor=" + mTextColor +
                ", mBgRes=" + mBgRes +
                ", hasDrawable=" + hasDrawable +
                '}';
    }

    public int getTextColor() {
        return mTextColor;
    }


    public void setTextColor(int fontColor) {
        this.mTextColor = fontColor;
    }


    public int getBgRes() {
        return mBgRes;
    }


    public void setmBgRes(int bgRes) {
        this.mBgRes = bgRes;
    }


    public boolean hasDrawable() {
        return hasDrawable;
    }


    public void setHasDrawable(boolean hasDrawble) {
        this.hasDrawable = hasDrawble;
    }


    public void applySkin(View view) {
        if (mTextColor != -1) {
            ColorStateList color = SkinResources.getInstance().getColorStateList(mTextColor);
            ((TextView)view).setTextColor(color);
        }
        if (hasDrawable) {
            ViewCompat.setBackground(view, SkinResources.getInstance().getDrawable(mBgRes));
        } else if (mBgRes != -1) {
            view.setBackgroundColor(SkinResources.getInstance().getColor(mBgRes));
        }
    }

}
