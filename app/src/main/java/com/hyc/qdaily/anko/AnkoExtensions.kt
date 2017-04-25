package com.hyc.qdaily.anko

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewManager
import android.widget.ImageView
import android.widget.RadioGroup
import com.facebook.drawee.view.SimpleDraweeView
import org.jetbrains.anko.`$$Anko$Factories$Sdk15View`
import org.jetbrains.anko.custom.ankoView

/**
 * Created by hyc on 2017/4/25.
 */
val SimpleDraweeView = { ctx: Context -> com.facebook.drawee.view.SimpleDraweeView(ctx) }
inline fun ViewManager.simpleDraweeView(theme: Int = 0, init: com.facebook.drawee.view.SimpleDraweeView.() -> Unit): com.facebook.drawee.view.SimpleDraweeView {
    return ankoView(SimpleDraweeView, theme) { init() }
}