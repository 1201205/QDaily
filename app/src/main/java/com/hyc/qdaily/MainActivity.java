package com.hyc.qdaily;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.hyc.qdaily.base.BaseNetDisposableObserver;
import com.hyc.qdaily.base.SchedulerTransformer;
import com.hyc.qdaily.beans.BaseBean;
import com.hyc.qdaily.beans.home.Article;
import com.hyc.qdaily.beans.home.Home;
import com.hyc.qdaily.net.RequestClient;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RequestClient.getApi().getArticleByIndex("34566").compose(SchedulerTransformer.<BaseBean<Article>>create()).subscribeWith(
           new BaseNetDisposableObserver<BaseBean<Article>>(){
               @Override public void onNext(BaseBean<Article> articleBaseBean) {
                   super.onNext(articleBaseBean);
               }
           });
        RequestClient.getApi().getHomeDataByIndex("0").subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<BaseBean<Home>>() {
            @Override public void accept(@NonNull BaseBean<Home> homeBaseBean) throws Exception {
                Log.e("hyc-test",homeBaseBean.getResponse().getBanners().size()+"-----"+Thread.currentThread().getId()+"-----"+getMainLooper().getThread().getId());
            }
        }, new Consumer<Throwable>() {
            @Override public void accept(@NonNull Throwable throwable) throws Exception {

            }
        }, new Action() {
            @Override public void run() throws Exception {

            }
        });
    }
}
