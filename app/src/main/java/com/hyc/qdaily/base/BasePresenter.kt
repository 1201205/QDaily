package com.hyc.qdaily.base

import com.google.gson.JsonParseException
import com.hyc.qdaily.R
import com.hyc.qdaily.util.showToast
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.exceptions.Exceptions
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Created by hyc on 16/4/19.
 */
open class BasePresenter<T : BaseView>(var mView: T) {
    var mCompositeSubscription: CompositeDisposable? = null

    init {
        mCompositeSubscription = CompositeDisposable()
    }


    fun attachView() {

    }

    fun post(disposable: Disposable) {
        mCompositeSubscription!!.add(disposable)
    }

    fun detachView() {
        mCompositeSubscription!!.dispose()
        mCompositeSubscription = null
    }

    fun onError(throwable: Throwable){
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
                showToast(R.string.net_error)
        } else if (throwable is SocketTimeoutException) {
            showToast(R.string.time_out)
        } else {
            showToast(R.string.net_error)
        }
    }

}
