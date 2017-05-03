package com.hyc.qdaily.anko

import android.view.View

/**
 * Created by hyc on 2017/4/25.
 */
interface ViewBinder<in T>{
    fun bind(c: T): View
    fun unbind(t: T)
}