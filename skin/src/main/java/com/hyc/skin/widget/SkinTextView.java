package com.hyc.skin.widget;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;

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
        Log.e("hyc-test",mSkinAttrs.toString());
        mSkinAttrs.applySkin(this);
    }

    @Override public void setBackgroundResource(int resid) {
        ViewCompat.setBackground(this, SkinResources.getInstance().getDrawable(resid));
    }
}
