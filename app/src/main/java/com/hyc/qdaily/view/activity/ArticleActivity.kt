package com.hyc.qdaily.view.activity

import android.webkit.WebView
import android.webkit.WebViewClient
import butterknife.BindView
import com.hyc.qdaily.R
import com.hyc.qdaily.base.BaseActivity
import com.hyc.qdaily.contract.ArticleContract
import com.hyc.qdaily.presenter.ArticlePresenter
import com.hyc.qdaily.view.ArticleWebClient

/**
 * Created by hyc on 2017/3/24.
 */
class ArticleActivity:BaseActivity<ArticlePresenter>(),ArticleContract.View{
    override fun showLoading() {
    }

    override fun dismissLoading() {
    }

    override fun showArticle(content: String, url: String) {
        wbArticle.post {
            wbArticle.loadDataWithBaseURL(url,content,"text/html","UTF-8",url)
        }
    }

    override fun onDownLoadFileError() {
    }

    @BindView(R.id.wb_article)
    lateinit var wbArticle:WebView
    lateinit var mUrl:String
     var mID:Int=0

    override fun onNetError() {
    }

    override fun handleIntent() {
        mUrl=intent.getStringExtra("url")
//        mID=intent.getIntExtra("url",0)
    }

    override val layoutID: Int= R.layout.activity_article

    override fun initPresenterAndData() {
        mPresenter= ArticlePresenter(this)
        mPresenter!!.getArticle(mUrl.toString())
    }

    override fun initView() {
        var client=ArticleWebClient()
        wbArticle.settings.javaScriptEnabled=true
        wbArticle.setWebViewClient(client)
    }

}