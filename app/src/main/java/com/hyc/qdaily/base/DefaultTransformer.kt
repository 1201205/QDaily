package com.hyc.qdaily.base

import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer

/**
 * Created by hyc on 2016/7/7.
 */
class DefaultTransformer<T> : ObservableTransformer<T, T> {

    override fun apply(upstream: Observable<T>): ObservableSource<T> {
        return upstream
            .compose(SchedulerTransformer<T>())
    }
}
