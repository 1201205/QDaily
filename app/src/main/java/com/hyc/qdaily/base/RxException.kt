package com.hyc.qdaily.base

import io.reactivex.annotations.NonNull
import io.reactivex.functions.Consumer
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Created by hyc on 2017/3/3.
 */

class RxException<T : Throwable> : Consumer<T> {
    @Throws(Exception::class)
    override fun accept(@NonNull t: T) {
        if (t is UnknownHostException) {
            //网络连接失败
        } else if (t is ConnectException) {
            //网络连接异常
        } else if (t is SocketTimeoutException) {
            //超时
        }
    }
}
