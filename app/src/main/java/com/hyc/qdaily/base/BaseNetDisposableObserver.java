package com.hyc.qdaily.base;

import com.google.gson.JsonParseException;
import com.hyc.qdaily.R;
import com.hyc.qdaily.beans.BaseBean;
import com.hyc.qdaily.util.AppUtil;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.observers.DisposableObserver;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * Created by hyc on 2017/3/6.
 */

public class BaseNetDisposableObserver<T extends BaseBean> extends DisposableObserver<T> {
    private boolean toast;


    public BaseNetDisposableObserver(boolean showToast) {
        this.toast = showToast;
    }


    public BaseNetDisposableObserver() {
        this.toast = true;
    }


    @Override public void onNext(T t) {
        if (t.getError() != null) {
            onError(new RequestException());
            return;
        }
    }


    protected void onNoNetWork() {

    }


    @Override public void onError(Throwable throwable) {
        throwable.printStackTrace();
        if (throwable instanceof RequestException) {
            AppUtil.showToast(R.string.request_error);
        } else if (throwable instanceof JsonParseException) {
            AppUtil.showToast(R.string.parse_error);
        } else if (throwable instanceof NullPointerException) {
            AppUtil.showToast(R.string.none_point_error);
            Exceptions.propagate(throwable);
        } else if (throwable instanceof SocketException) {
            AppUtil.showToast(R.string.net_error);
        } else if (throwable instanceof UnknownHostException) {
            if (toast) {
                AppUtil.showToast(R.string.net_error);
            }
            onNoNetWork();
        } else if (throwable instanceof SocketTimeoutException) {
            AppUtil.showToast(R.string.time_out);
        } else {
            AppUtil.showToast(R.string.net_error);
        }
    }


    @Override public void onComplete() {

    }
}
