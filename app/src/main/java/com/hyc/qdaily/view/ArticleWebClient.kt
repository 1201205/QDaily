package com.hyc.qdaily.view

import android.annotation.SuppressLint
import android.text.TextUtils
import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import com.hyc.qdaily.file.FileCacheLoader
import java.io.FileInputStream
import java.io.InputStream

/**
 * Created by hyc on 2017/3/24.
 */
open class ArticleWebClient : WebViewClient() {
    @SuppressLint("NewApi")
    override fun shouldInterceptRequest(view: WebView?, request: WebResourceRequest?): WebResourceResponse {
        var response: WebResourceResponse? = null
        var url: String? = request?.url.toString()
        if (TextUtils.isEmpty(url)) {
            return super.shouldInterceptRequest(view, request)
        }
        Log.e("hyc-4",url+"------")
        var mineType: String = ""
        if (url!!.contains(".png") || url.contains(".jpg") || url.contains(".jpeg")) {
            mineType = "image/jpeg"
        } else if (url.contains("ico")) {
            mineType = "image/x-icon"
        } else if (url.contentEquals("gif")) {
            mineType = "image/gif"
        } else if (url.contains("css")) {
            mineType = "text/css"
        } else if (url.contains("js")) {
            mineType = "application/javascript"
        }
        if (!TextUtils.isEmpty(mineType)) {
            var input = FileCacheLoader.instance.loadFormCache(url)
            if (input != null) {
                response = WebResourceResponse(mineType, "UTF-8", input)
            }
        }
        if (response == null) {
            response = WebResourceResponse("application/javascript", "UTF-8",FileInputStream(FileCacheLoader.instance.getFile()) )
        }
        return response!!
    }

    override fun shouldInterceptRequest(view: WebView?, url: String?): WebResourceResponse {
        return WebResourceResponse("application/javascript", "UTF-8",FileInputStream(FileCacheLoader.instance.getFile()) )
    }
}
