package com.hyc.qdaily.model

import android.util.Log
import com.hyc.qdaily.contract.ArticleContract
import java.util.concurrent.CopyOnWriteArrayList

/**
 * Created by hyc on 2017/3/24.
 */
class ArticleModel : ArticleContract.Model {
    override fun saveFileUrlList(urls: List<String>?) {
        if (urls!=null) {
            urlList.addAll(urls)
        }
    }

    override fun downLoadSucceed(url: String): Boolean {
        urlList.remove(url)
        return urlList.size == 0
    }
    var content: String? = null
    var url: String? = null
    var urlList: CopyOnWriteArrayList<String> = CopyOnWriteArrayList()
    override fun saveContent(content: String?, url: String?) {
        this.content = content
        this.url = url
    }

}