package com.hyc.skin.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;
import com.hyc.skin.core.SkinAttrs;
import com.hyc.skin.core.SkinAttrsFactory;
import com.hyc.skin.core.SkinChangeable;
import com.hyc.skin.core.SkinResources;

/**
 * Created by ray on 17/2/20.
 */

public class SkinRelativeLayout extends RelativeLayout implements SkinChangeable {
    private SkinAttrs mSkinAttrs;


    public SkinRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mSkinAttrs = SkinAttrsFactory.getAttrs(attrs, context);
        if (SkinResources.getInstance().showingExternalSkin()) {
            skinChanged();
        }
    }


    public SkinRelativeLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public SkinRelativeLayout(Context context) {
        this(context, null);
    }


    @Override public void skinChanged() {
        //Log.e("hyc-test", mSkinAttrs.toString());
        mSkinAttrs.applySkin(this);
    }

}
