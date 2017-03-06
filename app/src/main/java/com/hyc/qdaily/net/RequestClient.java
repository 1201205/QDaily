package com.hyc.qdaily.net;

import android.text.TextUtils;
import android.util.Log;
import com.hyc.qdaily.MainApplication;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hyc on 2017/3/3.
 */

public class RequestClient {
    private static API sApi;
    private static volatile Object sLock=new Object();
    private static String TAG = "request";
    private static File sHttpCacheDirectory;
    private static Cache sCache;
    private static Interceptor sInterceptor;
    private static OkHttpClient sClient;
    public static API getApi(){
        if (sApi==null) {
            synchronized (sLock){
                if (sInterceptor==null) {
                    init();
                }
                if (sApi==null) {
                    sApi=new Retrofit.Builder().baseUrl("http://app3.qdaily.com/").addCallAdapterFactory(
                        RxJava2CallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create()).client(sClient).build().create(API.class);
                }
            }
        }
        return sApi;
    }

    private static void init(){
        sInterceptor= new Interceptor() {
            @Override public Response intercept(Chain chain) throws IOException {
                Request request=chain.request();
                Log.v(TAG, "request:" + request.toString());
                long t1 = System.nanoTime();
                Response response = chain.proceed(request);
                long t2 = System.nanoTime();
                Log.v(TAG, String.format(Locale.getDefault(), "Received response for %s in %.1fms%n%s",
                    response.request().url(), (t2 - t1) / 1e6d, response.headers()));
                String cacheControl = request.cacheControl().toString();
                if (TextUtils.isEmpty(cacheControl)) {
                    cacheControl = "public, max-age=60";
                }
                return response.newBuilder()
                    .header("Cache-Control", cacheControl)
                    .removeHeader("Pragma")
                    .build();
            }
        };
        //设置缓存路径
        sHttpCacheDirectory = new File(MainApplication.getInstance().getCacheDir(), "responses");
        //设置缓存 50M
        sCache = new Cache(sHttpCacheDirectory, 50 * 1024 * 1024);
        sClient = new OkHttpClient.Builder()
            .addNetworkInterceptor(sInterceptor)
            .cache(sCache).build();
    }

}
