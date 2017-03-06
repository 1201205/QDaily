package com.hyc.qdaily.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by hyc on 16/4/19.
 */
public class BasePresenter<T extends BaseView> {
    protected CompositeDisposable mCompositeSubscription;
    protected T mView;

    public BasePresenter(T view) {
        this.mView = view;
        mCompositeSubscription = new CompositeDisposable();
    }


    public void attachView() {

    }

    protected void post(Disposable disposable){
        mCompositeSubscription.add(disposable);
    }
    public void detachView() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.dispose();
        }
        mCompositeSubscription = null;
        mView = null;
    }

}
