package com.hyc.qdaily.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

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

}
