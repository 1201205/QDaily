package com.hyc.qdaily.base;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by hyc on 2017/3/6.
 */
public class SchedulerTransformer<T> implements ObservableTransformer<T, T> {
    public static <T> SchedulerTransformer<T> create() {
        return new SchedulerTransformer<>();
    }

    @Override public ObservableSource<T> apply(Observable<T> upstream) {
        return upstream
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());
    }
}