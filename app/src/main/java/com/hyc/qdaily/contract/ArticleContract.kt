package com.hyc.qdaily.contract

import android.support.v4.util.ArrayMap
import com.hyc.qdaily.base.BasePresenter
import com.hyc.qdaily.base.BaseView

/**
 * Created by hyc on 2017/3/24.
 */
interface ArticleContract{
    interface View :BaseView{
        fun showLoading()
        fun dismissLoading()
        fun showArticle(content:String,url:String)
        fun onDownLoadFileError()
    }

    interface Presenter{
        fun getArticle(id:String)
        fun downLoadHtmlFiles()
    }

    interface Model{
        fun saveContent(content: String?,url: String?)
        fun saveFileUrlList(urls:List<String>?)
        fun downLoadSucceed(url: String):Boolean

    }

}