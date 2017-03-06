package com.hyc.qdaily.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.widget.Toast;
import com.hyc.qdaily.MainApplication;
import java.lang.reflect.Field;

/**
 * Created by Administrator on 2016/8/26.
 */
public class AppUtil {

    private static int sStausHeight = -1;
    /**
     * 生成唯一设备识别id
     * @return
     */
    /**
     *
     * dp转px
     * @param dpValue
     * @return
     */
    public static float dip2px(float dpValue) {
        final float scale = MainApplication.getInstance().getResources().getDisplayMetrics().density;
        return (dpValue * scale + 0.5f);
    }

    /**
     * px转dp
     * @param pxValue
     * @return
     */
    public static float px2dip(float pxValue) {
        final float scale = MainApplication.getInstance().getResources().getDisplayMetrics().density;
        return  (pxValue / scale + 0.5f);
    }

    public static int getScreenWidth(Activity context) {
        DisplayMetrics dm = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int mScreenWidth = dm.widthPixels;// 获取屏幕分辨率宽度
        return mScreenWidth;
    }


    public static int getScreenHeight(Activity context) {
        DisplayMetrics dm = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int mScreenHeight = dm.heightPixels;
        return mScreenHeight;
    }

    public static int getStatusHeight(Activity activity) {
        int statusBarHeight = 0;
        try {
            Class<?> c = Class.forName("com.android.internal.R$dimen");
            Object o = c.newInstance();
            Field field = c.getField("status_bar_height");
            int x = (Integer) field.get(o);
            statusBarHeight = activity.getResources().getDimensionPixelSize(x);
        } catch (Exception e) {
            e.printStackTrace();
            Rect frame = new Rect();
            activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
            statusBarHeight = frame.top;
        }
        return statusBarHeight;
    }


    public static int getStatusBarHeight(Context context) {
        if (sStausHeight == -1) {
            int resourceId = context.getResources()
                .getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                sStausHeight = context.getResources().getDimensionPixelSize(resourceId);
            }
        }

        return sStausHeight;
    }


    public static void showToast(String s) {
        Toast.makeText(MainApplication.getInstance(), s, Toast.LENGTH_LONG).show();
    }


    public static void showToast(int id) {
        Toast.makeText(MainApplication.getInstance(), id, Toast.LENGTH_LONG).show();
    }


    public static int getColor(int id) {
        return MainApplication.getInstance().getResources().getColor(id);
    }

    public static String getString(int id) {
        return MainApplication.getInstance().getResources().getString(id);
    }
}
