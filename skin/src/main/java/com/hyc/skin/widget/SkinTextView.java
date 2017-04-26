package com.hyc.skin.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;

import android.view.View;
import android.widget.TextView;
import com.hyc.skin.core.SkinAttrs;
import com.hyc.skin.core.SkinAttrsFactory;
import com.hyc.skin.core.SkinChangeable;
import com.hyc.skin.core.SkinResources;

/**
 * Created by ray on 17/2/19.
 */

public class SkinTextView extends AppCompatTextView implements SkinChangeable {
    private SkinAttrs mSkinAttrs;


    public SkinTextView(Context context) {
        this(context, null);
    }


    public SkinTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public SkinTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mSkinAttrs = SkinAttrsFactory.getAttrs(attrs, context);
        if (SkinResources.getInstance().showingExternalSkin()) {
            skinChanged();
        }
    }


    @Override public void skinChanged() {
        //Log.e("hyc-test", mSkinAttrs.toString());
        mSkinAttrs.applySkin(this);
    }


    @Override public void setBackgroundResource(int resid) {
        ViewCompat.setBackground(this, SkinResources.getInstance().getDrawable(resid));
    }


    public static class TextSkinAttrs extends SkinAttrs {
        protected int drawableBottom;
        protected int drawableLeft;
        protected int drawableRight;
        protected int drawableTop;


        public void setDrawableBottom(int drawableBottom) {
            this.drawableBottom = drawableBottom;
        }


        public void setDrawableLeft(int drawableLeft) {
            this.drawableLeft = drawableLeft;
        }


        public void setDrawableRight(int drawableRight) {
            this.drawableRight = drawableRight;
        }


        public void setDrawableTop(int drawableTop) {
            this.drawableTop = drawableTop;
        }


        @Override public void applySkin(View view) {
            super.applySkin(view);

            Drawable left = null;
            Drawable top = null;
            Drawable right = null;
            Drawable bottom = null;
            if (drawableBottom != -1) {
                bottom = SkinResources.getInstance().getDrawable(drawableBottom);
            }
            if (drawableLeft != -1) {
                left = SkinResources.getInstance().getDrawable(drawableLeft);
            }
            if (drawableRight != -1) {
                right = SkinResources.getInstance().getDrawable(drawableRight);
            }
            if (drawableBottom != -1) {
                top = SkinResources.getInstance().getDrawable(drawableTop);
            }
            if (bottom != null || left != null || right != null || top != null) {
                TextView target = (TextView) view;
                target.setCompoundDrawables(left, top, right, bottom);
            }
        }
    }
}
