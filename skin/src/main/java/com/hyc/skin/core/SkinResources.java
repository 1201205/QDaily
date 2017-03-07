package com.hyc.skin.core;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;

/**
 * Created by ray on 17/2/19.
 */

public class SkinResources {
    private static volatile SkinResources skinResources;
    private Context mContext;
    private Resources mResources;
    private String mPackageName;


    private SkinResources() {
    }

    public static SkinResources getInstance() {
        if (skinResources == null) {
            synchronized (SkinResources.class) {
                skinResources = new SkinResources();
            }
        }
        return skinResources;
    }

    public void init(Context context) {
        mContext = context.getApplicationContext();
    }


    public void changeResources(Resources resources, String packageName) {
        mResources = resources;
        mPackageName = packageName;
    }


    public int getColor(int resID) {
        int color = 0;
        Log.e("hyc-test", "id---" + resID + "-----" + mPackageName + "++++" + (mResources == null));
        if (TextUtils.isEmpty(mPackageName) || mResources == null) {
            return ContextCompat.getColor(mContext, resID);
        }
        String resName = mContext.getResources().getResourceEntryName(resID);
        Log.e("hyc-test", "id---" + resID + "-----" + resName);
        int target = mResources.getIdentifier(resName, "color", mPackageName);
        color = mResources.getColor(target);
        if (color == 0) {
            return ContextCompat.getColor(mContext, resID);
        }
        Log.e("hyc-test", "id---" + resID + "---resName--" + resName + "---color---" + color);
        return color;
    }

    public boolean showingExternalSkin() {
        return !TextUtils.isEmpty(mPackageName) && mResources != null;
    }

    public void closeExternalSkin() {
        mPackageName = null;
        mResources = null;
    }

    public Drawable getDrawable(int resID) {
        Log.e("hyc-test-Drawable", "id---" + resID + "-----" + mPackageName + "++++" + (mResources == null));
        if (TextUtils.isEmpty(mPackageName) || mResources == null) {
            return ContextCompat.getDrawable(mContext, resID);
        }
        String resName = mContext.getResources().getResourceEntryName(resID);
        int res = mResources.getIdentifier(resName, "drawable", mPackageName);
        if (res == 0) {
            return ContextCompat.getDrawable(mContext, resID);
        }
        return mResources.getDrawable(res);
    }
    public ColorStateList getColorStateList(int resId) {
        ColorStateList colorStateList = ContextCompat.getColorStateList(mContext, resId);
        if (!showingExternalSkin()) {
            return colorStateList;
        }

        String resName = mContext.getResources().getResourceEntryName(resId);

        int targetResId = mResources.getIdentifier(resName, "color", mPackageName);

        return targetResId == 0 ? colorStateList : mResources.getColorStateList(targetResId);
    }
}
