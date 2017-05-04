package com.hyc.qdaily.anko

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Typeface
import android.support.v4.content.res.ResourcesCompat
import android.view.ViewManager
import android.widget.TextView
import com.facebook.drawee.generic.RoundingParams
import com.facebook.drawee.view.SimpleDraweeView
import org.jetbrains.anko.AnkoException
import org.jetbrains.anko.custom.ankoView

/**
 * Created by hyc on 2017/4/25.
 */

val SkinDateView = { ctx: Context -> com.hyc.skin.widget.SkinDateView(ctx) }
val SimpleDraweeView = { ctx: Context -> com.facebook.drawee.view.SimpleDraweeView(ctx) }
val ExpandableTextView = { ctx: Context -> com.hyc.skin.view.ExpandableTextView(ctx) }
val PullToRefreshView = { ctx: Context -> com.yalantis.phoenix.PullToRefreshView(ctx) }
inline fun ViewManager.simpleDraweeView(theme: Int = 0, init: com.facebook.drawee.view.SimpleDraweeView.() -> Unit): com.facebook.drawee.view.SimpleDraweeView {
    return ankoView(SimpleDraweeView, theme) { init() }
}

inline fun ViewManager.pullToRefreshView(theme: Int = 0, init: com.yalantis.phoenix.PullToRefreshView.() -> Unit): com.yalantis.phoenix.PullToRefreshView {
    return ankoView(PullToRefreshView, theme) { init() }
}

inline fun ViewManager.skinDateView(theme: Int = 0, init: com.hyc.skin.widget.SkinDateView.() -> Unit): com.hyc.skin.widget.SkinDateView {
    return ankoView(SkinDateView, theme) { init() }
}

inline fun ViewManager.expandableTextView(theme: Int = 0, init: com.hyc.skin.view.ExpandableTextView.() -> Unit): com.hyc.skin.view.ExpandableTextView {
    return ankoView(ExpandableTextView, theme) { init() }
}
var android.widget.TextView.textColorList: ColorStateList
    get() = throw AnkoException("'android.widget.TextView.textColor' property does not have a getter")
    set(v) = setTextColor(v)


inline fun SimpleDraweeView.roundAsCircle() {
    var params = RoundingParams()
    params.roundAsCircle = true
    hierarchy.roundingParams = params
}

inline fun TextView.drawableLeft(id: Int) {
    var left = ResourcesCompat.getDrawable(context.resources, id, null)
    left!!.setBounds(0, 0, left.minimumWidth, left.minimumHeight)
    setCompoundDrawables(left, null, null, null)
}

inline fun TextView.drawableRight(id: Int) {
    var right = ResourcesCompat.getDrawable(context.resources, id, null)
    right!!.setBounds(0, 0, right.minimumWidth, right.minimumHeight)
    setCompoundDrawables(null, null, right, null)
}

inline fun TextView.drawableTop(id: Int) {
    var top = ResourcesCompat.getDrawable(context.resources, id, null)
    top!!.setBounds(0, 0, top.minimumWidth, top.minimumHeight)
    setCompoundDrawables(null, top, null, null)
}

inline fun TextView.bold() {
    typeface = Typeface.DEFAULT_BOLD
}