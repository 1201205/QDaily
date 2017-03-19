package com.hyc.qdaily.net

import android.text.TextUtils
import android.util.Log
import com.hyc.qdaily.MainApplication
import java.io.File
import java.io.IOException
import java.util.Locale
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by hyc on 2017/3/3.
 */

object RequestClient {
    private var sApi: API? =null
    private val sLock = Any()
    private val TAG = "request"
    private var sHttpCacheDirectory: File? = null
    private var sCache: Cache? = null
    private var sInterceptor: Interceptor? = null
    private var sClient: OkHttpClient? = null
    val api: API
        get() {
            if (sApi == null) {
                synchronized(sLock) {
                    if (sInterceptor == null) {
                        init()
                    }
                    if (sApi == null) {
                        sApi = Retrofit.Builder().baseUrl("http://app3.qdaily.com/").addCallAdapterFactory(
                            RxJava2CallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create()).client(sClient!!).build().create<API>(API::class.java)
                    }
                }
            }
            return sApi!!
        }

    private fun init() {
        sInterceptor = Interceptor { chain ->
            val request = chain.request()
            Log.v(TAG, "request:" + request.toString())
            val t1 = System.nanoTime()
            val response = chain.proceed(request)
            val t2 = System.nanoTime()
            Log.v(TAG, String.format(Locale.getDefault(), "Received response for %s in %.1fms%n%s",
                response.request().url(), (t2 - t1) / 1e6, response.headers()))
            var cacheControl = request.cacheControl().toString()
            if (TextUtils.isEmpty(cacheControl)) {
                cacheControl = "public, max-age=60"
            }
            response.newBuilder()
                .header("Cache-Control", cacheControl)
                .removeHeader("Pragma")
                .build()
        }
        //设置缓存路径
        sHttpCacheDirectory = File(MainApplication.instance.cacheDir, "responses")
        //设置缓存 50M
        sCache = Cache(sHttpCacheDirectory, (50 * 1024 * 1024).toLong())
        sClient = OkHttpClient.Builder()
            .addNetworkInterceptor(sInterceptor)
            .cache(sCache).build()
    }

}
