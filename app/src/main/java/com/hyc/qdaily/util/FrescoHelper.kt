package com.hyc.qdaily.util

import android.text.TextUtils
import com.facebook.drawee.view.SimpleDraweeView

fun loadUrl(view: com.facebook.drawee.view.SimpleDraweeView, url: String?){
    if(!android.text.TextUtils.isEmpty(url)){
        view.setImageURI(url)
    }
}