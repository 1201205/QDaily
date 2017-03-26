package com.hyc.qdaily.view

import android.annotation.SuppressLint
import android.text.TextUtils
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import com.hyc.qdaily.file.FileCacheLoader
import java.io.FileInputStream

/**
 * Created by hyc on 2017/3/24.
 */
open class ArticleWebClient : WebViewClient() {
    @SuppressLint("NewApi")
    override fun shouldInterceptRequest(view: WebView?, request: WebResourceRequest?): WebResourceResponse {
        var url: String? = request?.url.toString()
        return getCacheResource(url)
    }

    override fun shouldInterceptRequest(view: WebView?, url: String?): WebResourceResponse {
        return getCacheResource(url)
    }

    fun getCacheResource(url: String?): WebResourceResponse {
        var response: WebResourceResponse? = null
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
            response = WebResourceResponse("application/javascript", "UTF-8", FileInputStream(FileCacheLoader.instance.getFile()))
        }
        return response!!
    }
}
