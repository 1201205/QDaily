package com.hyc.qdaily.util

import android.net.Uri
import android.text.TextUtils
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.interfaces.DraweeController
import com.facebook.drawee.view.SimpleDraweeView

fun loadUrl(view: SimpleDraweeView, url: String?) {
    if (!TextUtils.isEmpty(url)) {
        var controller = Fresco.newDraweeControllerBuilder().setUri(Uri.parse(url)).setAutoPlayAnimations(true).build()
        view.controller = controller as DraweeController?
    }
}