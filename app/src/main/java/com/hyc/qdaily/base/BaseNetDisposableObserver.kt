package com.hyc.qdaily.base

import com.google.gson.JsonParseException
import com.hyc.qdaily.R
import com.hyc.qdaily.beans.BaseBean
import com.hyc.qdaily.util.AppUtil
import io.reactivex.exceptions.Exceptions
import io.reactivex.observers.DisposableObserver
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Created by hyc on 2017/3/6.
 */

open class BaseNetDisposableObserver<T : BaseBean<*>> : DisposableObserver<T> {
    private var toast: Boolean = false


    constructor(showToast: Boolean) {
        this.toast = showToast
    }

    constructor() {
        this.toast = true
    }


    override fun onNext(t: T) {
        if (t.error != null) {
            onError(RequestException())
            return
        }
    }

    protected fun onNoNetWork() {

    }

    override fun onError(throwable: Throwable) {
        throwable.printStackTrace()
        if (throwable is RequestException) {
            AppUtil.showToast(R.string.request_error)
        } else if (throwable is JsonParseException) {
            AppUtil.showToast(R.string.parse_error)
        } else if (throwable is NullPointerException) {
            AppUtil.showToast(R.string.none_point_error)
            Exceptions.propagate(throwable)
        } else if (throwable is SocketException) {
            AppUtil.showToast(R.string.net_error)
        } else if (throwable is UnknownHostException) {
            if (toast) {
                AppUtil.showToast(R.string.net_error)
            }
            onNoNetWork()
        } else if (throwable is SocketTimeoutException) {
            AppUtil.showToast(R.string.time_out)
        } else {
            AppUtil.showToast(R.string.net_error)
        }
    }

    override fun onComplete() {

    }
}
