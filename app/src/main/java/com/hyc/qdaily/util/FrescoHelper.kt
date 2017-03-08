package com.hyc.qdaily.util

import android.text.TextUtils
import com.facebook.drawee.view.SimpleDraweeView

fun loadUrl(view: SimpleDraweeView, url: String){
    if(!TextUtils.isEmpty(url)){
        view.setImageURI(url)
    }
}