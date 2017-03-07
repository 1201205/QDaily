package com.hyc.skin.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.hyc.skin.core.SkinAttrs;
import com.hyc.skin.core.SkinAttrsFactory;
import com.hyc.skin.core.SkinChangeable;
import com.hyc.skin.core.SkinResources;

/**
 * Created by ray on 17/2/20.
 */

public class SkinLinearLayout extends LinearLayout implements SkinChangeable {
    private SkinAttrs mSkinAttrs;
    public SkinLinearLayout(Context context) {
        this(context,null);
    }


    public SkinLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }


    public SkinLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mSkinAttrs = SkinAttrsFactory.getAttrs(attrs, context);
        if (SkinResources.getInstance().showingExternalSkin()) {
            skinChanged();
        }
    }


    @Override public void skinChanged() {
        mSkinAttrs.applySkin(this);
    }
}
