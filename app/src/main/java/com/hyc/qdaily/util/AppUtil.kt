package com.hyc.qdaily.util

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.util.DisplayMetrics
import android.widget.Toast
import com.hyc.qdaily.MainApplication
import java.lang.reflect.Field

/**
 * Created by Administrator on 2016/8/26.
 */
private var sStausHeight = -1
/**
 * 生成唯一设备识别id
 * @return
 */
/**

 * dp转px
 * @param dpValue
 * *
 * @return
 */
fun dip2px(dpValue: Float): Float {
    val scale = MainApplication.instance.getResources()?.getDisplayMetrics()?.density!!
    return dpValue * scale + 0.5f
}

/**
 * px转dp
 * @param pxValue
 * *
 * @return
 */
fun px2dip(pxValue: Float): Float {
    val scale = MainApplication.instance.getResources()?.getDisplayMetrics()?.density!!
    return pxValue / scale + 0.5f
}

fun getScreenWidth(context: Activity): Int {
    val dm = DisplayMetrics()
    context.windowManager.defaultDisplay.getMetrics(dm)
    val mScreenWidth = dm.widthPixels// 获取屏幕分辨率宽度
    return mScreenWidth
}

fun getScreenHeight(context: Activity): Int {
    val dm = DisplayMetrics()
    context.windowManager.defaultDisplay.getMetrics(dm)
    val mScreenHeight = dm.heightPixels
    return mScreenHeight
}

fun getStatusHeight(activity: Activity): Int {
    var statusBarHeight = 0
    try {
        val c = Class.forName("com.android.internal.R\$dimen")
        val o = c.newInstance()
        val field = c.getField("status_bar_height")
        val x = field.get(o) as Int
        statusBarHeight = activity.resources.getDimensionPixelSize(x)
    } catch (e: Exception) {
        e.printStackTrace()
        val frame = Rect()
        activity.window.decorView.getWindowVisibleDisplayFrame(frame)
        statusBarHeight = frame.top
    }

    return statusBarHeight
}

fun getStatusBarHeight(context: Context): Int {
    if (sStausHeight == -1) {
        val resourceId = context.resources
            .getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            sStausHeight = context.resources.getDimensionPixelSize(resourceId)
        }
    }

    return sStausHeight
}

fun showToast(s: String) {
    Toast.makeText(MainApplication.instance, s, Toast.LENGTH_LONG).show()
}

fun showToast(id: Int) {
    Toast.makeText(MainApplication.instance, id, Toast.LENGTH_LONG).show()
}

fun getColor(id: Int): Int? {
    return MainApplication.instance.getResources()?.getColor(id)
}

fun getString(id: Int): String? {
    return MainApplication.instance.getResources()?.getString(id)
}
