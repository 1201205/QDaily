package com.hyc.qdaily.base

import com.google.gson.JsonParseException
import com.hyc.qdaily.R
import com.hyc.qdaily.beans.BaseBean
import io.reactivex.exceptions.Exceptions
import io.reactivex.observers.DisposableObserver
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import com.hyc.qdaily.util.showToast

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
            showToast(R.string.request_error)
        } else if (throwable is JsonParseException) {
            showToast(R.string.parse_error)
        } else if (throwable is NullPointerException) {
            showToast(R.string.none_point_error)
            Exceptions.propagate(throwable)
        } else if (throwable is SocketException) {
            showToast(R.string.net_error)
        } else if (throwable is UnknownHostException) {
            if (toast) {
                showToast(R.string.net_error)
            }
            onNoNetWork()
        } else if (throwable is SocketTimeoutException) {
            showToast(R.string.time_out)
        } else {
            showToast(R.string.net_error)
        }
    }

    override fun onComplete() {

    }
}
