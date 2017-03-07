package com.hyc.skin.widget;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.hyc.skin.core.SkinAttrs;
import com.hyc.skin.core.SkinAttrsFactory;
import com.hyc.skin.core.SkinChangeable;
import com.hyc.skin.core.SkinResources;

/**
 * Created by Administrator on 2017/2/20.
 */

public class SkinImageView extends AppCompatImageView implements SkinChangeable{
    private SkinAttrs mSkinAttrs;
    public SkinImageView(Context context) {
        this(context,null);
    }

    public SkinImageView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SkinImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mSkinAttrs = SkinAttrsFactory.getAttrs(attrs, context);
        if (SkinResources.getInstance().showingExternalSkin()) {
            skinChanged();
        }
    }

    @Override
    public void skinChanged() {
        mSkinAttrs.applySkin(this);
    }
}
