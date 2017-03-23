package com.hyc.qdaily.util

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by hyc on 17/3/23.
 */
private val MINUTE_TIME = 60
private val HOUR_TIME = 60 * MINUTE_TIME
private val DAY_TIME = 24 * HOUR_TIME
fun getTime(time: Long): String {
    var diff = System.currentTimeMillis() / 1000 - time
    var builder = StringBuilder()

    if (diff < MINUTE_TIME) {
        builder.append("刚刚")
    } else if (diff < HOUR_TIME) {
        builder.append(diff / MINUTE_TIME)
        builder.append("分钟前")
    } else if (diff < DAY_TIME) {
        builder.append(diff / HOUR_TIME)
        builder.append("小时前")
    } else {
        builder.append(SimpleDateFormat("MM月dd日", Locale.getDefault()).format(Date(time*1000)))
    }
    return builder.toString()
}