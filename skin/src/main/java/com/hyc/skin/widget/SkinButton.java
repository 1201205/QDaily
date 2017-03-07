package com.hyc.skin.widget;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import com.hyc.skin.core.SkinAttrs;
import com.hyc.skin.core.SkinAttrsFactory;
import com.hyc.skin.core.SkinChangeable;
import com.hyc.skin.core.SkinResources;

/**
 * Created by ray on 17/2/20.
 */

public class SkinButton extends AppCompatButton implements SkinChangeable {
    private SkinAttrs mSkinAttrs;

    public SkinButton(Context context) {
        this(context,null);
    }


    public SkinButton(Context context, AttributeSet attrs) {
        this(context, attrs, android.support.v7.appcompat.R.attr.buttonStyle);
    }


    public SkinButton(Context context, AttributeSet attrs, int defStyleAttr) {
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
