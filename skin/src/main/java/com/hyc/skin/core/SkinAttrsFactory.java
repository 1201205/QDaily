package com.hyc.skin.core;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import com.hyc.skin.widget.SkinTextView;

/**
 * Created by ray on 17/2/19.
 */

public class SkinAttrsFactory {
    public static final String[] ATTR_NAME = new String[] { "textColor", "background",
        "drawableBottom", "drawableLeft", "drawableRight", "drawableTop" };
    public static final int COUNT = ATTR_NAME.length;


    public static SkinAttrs getAttrs(AttributeSet attrs, Context context) {
        return getAttrs(attrs, context, null);
    }


    public static SkinAttrs getAttrs(AttributeSet attrs, Context context, SkinAttrs skinAttrs) {
        if (skinAttrs == null) {
            skinAttrs = new SkinAttrs();
        }
        int attrCount = attrs.getAttributeCount();
        for (int i = 0; i < attrCount; i++) {
            String attrName = attrs.getAttributeName(i);
            String attrValue = attrs.getAttributeValue(i);
            if ("style".equals(attrName)) {
                String styleName = attrValue.substring(attrValue.indexOf("/") + 1);
                int styleID = context.getResources()
                    .getIdentifier(styleName, "style", context.getPackageName());
                int[] attr = new int[] { android.R.attr.textColor, android.R.attr.background };
                TypedArray typedArray = context.getTheme().obtainStyledAttributes(styleID, attr);
                int textColorId = typedArray.getResourceId(0, -1);
                int backgroundId = typedArray.getResourceId(1, -1);
                if (textColorId != -1) {
                    skinAttrs.setTextColor(textColorId);
                }
                if (backgroundId != -1) {
                    String typeName = context.getResources().getResourceTypeName(textColorId);
                    if ("drawable".equals(typeName)) {
                        skinAttrs.setHasDrawable(true);
                    }
                    skinAttrs.setmBgRes(backgroundId);
                }
                typedArray.recycle();
            } else if (needParse(attrName) && attrValue.startsWith("@")) {
                if (skinAttrs instanceof SkinTextView.TextSkinAttrs) {
                    parse((SkinTextView.TextSkinAttrs) skinAttrs, attrName, attrValue, context);
                } else {
                    parse(skinAttrs, attrName, attrValue, context);
                }
            }
        }
        return skinAttrs;
    }


    private static void parse(SkinAttrs skinAttrs, String attrName, String attrValue, Context context) {
        int id = Integer.parseInt(attrValue.substring(1));
        String typeName = context.getResources().getResourceTypeName(id);
        switch (typeName) {
            case "drawable":
                if ("drawable".equals(typeName)) {
                    skinAttrs.setHasDrawable(true);
                }
                skinAttrs.setmBgRes(id);
                break;
            case "color":
                if (attrName.equals("textColor")) {
                    skinAttrs.setTextColor(id);
                } else if (attrName.equals("background")) {
                    skinAttrs.setmBgRes(id);
                }
                break;
        }
    }


    private static void parse(SkinTextView.TextSkinAttrs skinAttrs, String attrName, String attrValue, Context context) {
        int id = Integer.parseInt(attrValue.substring(1));
        String typeName = context.getResources().getResourceTypeName(id);
        switch (typeName) {
            case "drawable":
                if ("drawable".equals(typeName)) {
                    skinAttrs.setHasDrawable(true);
                }
                skinAttrs.setmBgRes(id);
                break;
            case "color":
                if (attrName.equals("textColor")) {
                    skinAttrs.setTextColor(id);
                } else if (attrName.equals("background")) {
                    skinAttrs.setmBgRes(id);
                }
                break;
            case "drawableBottom":
                skinAttrs.setDrawableBottom(id);
                break;
            case "drawableLeft":
                skinAttrs.setDrawableLeft(id);
                break;
            case "drawableRight":
                skinAttrs.setDrawableRight(id);
                break;
            case "drawableTop":
                skinAttrs.setDrawableTop(id);
                break;
        }
    }


    public static boolean needParse(String name) {
        for (int i = 0; i < COUNT; i++) {
            if (ATTR_NAME[i].equals(name)) {
                return true;
            }
        }
        return false;
    }
}
