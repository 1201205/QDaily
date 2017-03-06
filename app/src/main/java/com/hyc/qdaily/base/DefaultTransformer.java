package com.hyc.qdaily.base;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;

/**
 * Created by hyc on 2016/7/7.
 */
public class DefaultTransformer<T>
    implements ObservableTransformer<T, T> {

    public DefaultTransformer() {
    }


    @Override public ObservableSource<T> apply(Observable<T> upstream) {
        return upstream
            .compose(new SchedulerTransformer<T>());
    }
}
