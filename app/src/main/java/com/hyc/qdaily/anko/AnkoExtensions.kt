package com.hyc.qdaily.anko

import android.content.Context
import android.content.res.ColorStateList
import android.view.ViewManager
import org.jetbrains.anko.AnkoException
import org.jetbrains.anko.custom.ankoView

/**
 * Created by hyc on 2017/4/25.
 */
val SimpleDraweeView = { ctx: Context -> com.facebook.drawee.view.SimpleDraweeView(ctx) }

val PullToRefreshView = { ctx: Context -> com.yalantis.phoenix.PullToRefreshView(ctx) }
inline fun ViewManager.simpleDraweeView(theme: Int = 0, init: com.facebook.drawee.view.SimpleDraweeView.() -> Unit): com.facebook.drawee.view.SimpleDraweeView {
    return ankoView(SimpleDraweeView, theme) { init() }
}

inline fun ViewManager.pullToRefreshView(theme: Int = 0, init: com.yalantis.phoenix.PullToRefreshView.() -> Unit): com.yalantis.phoenix.PullToRefreshView {
    return ankoView(PullToRefreshView, theme) { init() }
}

var android.widget.TextView.textColorList: ColorStateList
    get() = throw AnkoException("'android.widget.TextView.textColor' property does not have a getter")
    set(v) = setTextColor(v)
