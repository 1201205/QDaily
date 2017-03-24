package com.hyc.qdaily.presenter

import com.hyc.qdaily.base.BasePresenter
import com.hyc.qdaily.base.SchedulerTransformer
import com.hyc.qdaily.contract.ArticleContract
import com.hyc.qdaily.model.ArticleModel
import com.hyc.qdaily.file.FileDownLoader
import com.hyc.qdaily.net.RequestClient

/**
 * Created by hyc on 2017/3/24.
 */
class ArticlePresenter(view: ArticleContract.View) : ArticleContract.Presenter, BasePresenter<ArticleContract.View>(view) {
    var model = ArticleModel()
    var retryCount = 0
    var mUrl:String?=null
    var downLoadListener = object : FileDownLoader.Listener {
        override fun onSuccess(url: String) {
            if (model.downLoadSucceed(url)) {
                mView.showArticle(model.content!!, mUrl!!)
            }

        }
        override fun onFail(url: String) {
            if (retryCount < 15) {
                FileDownLoader.instance.downLoad(url, this)
            } else {
                mView.onDownLoadFileError()
            }
        }

    }

    override fun getArticle(id: String) {
        mUrl="http://app3.qdaily.com/app3/articles/$id.html"
        RequestClient.api.getArticleByIndex(id).compose(SchedulerTransformer.create()).map {
            var article = it.response?.article
            article?.let {
                model.saveContent(article.body, mUrl)
                model.saveFileUrlList(it.css)
                model.saveFileUrlList(it.image)
                model.saveFileUrlList(it.js)
                downLoadHtmlFiles()
            }
        }.subscribe()
    }

    override fun downLoadHtmlFiles() {
            model.urlList.forEach {
                FileDownLoader.instance.downLoad(it, downLoadListener)
            }
    }

}