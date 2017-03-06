package com.hyc.qdaily.base;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * Created by hyc on 2017/3/3.
 */

public class RxException<T extends Throwable> implements Consumer<T> {
    @Override public void accept(@NonNull T t) throws Exception {
        if (t instanceof UnknownHostException) {
            //网络连接失败
        } else if (t instanceof ConnectException) {
            //网络连接异常
        } else if (t instanceof SocketTimeoutException) {
            //超时
        }
    }
}
