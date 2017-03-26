package com.hyc.qdaily.net

import com.hyc.qdaily.beans.BaseBean
import com.hyc.qdaily.beans.home.Article
import com.hyc.qdaily.beans.home.ColumnContent
import com.hyc.qdaily.beans.home.Home
import com.hyc.qdaily.beans.home.Paper
import com.hyc.qdaily.beans.other.Category
import com.hyc.qdaily.beans.other.LeftSideBar
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

/**
 * Created by ray on 17/3/2.
 */

interface API {
    /**
     * 首页
     * http://app3.qdaily.com/app3/homes/index/0.json
     * 规则：第3条数据前后显示广告  广告过后会显示好奇研究的内容


     * 首页columns
     * http://app3.qdaily.com/app3/columns/index/29/0.json
     * articles
     * http://app3.qdaily.com/app3/articles/detail/38314.json
     * papers
     * http://app3.qdaily.com/app3/papers/index/0.json
     *
     * left_sidebar
     * http://app3.qdaily.com/app3/homes/left_sidebar.json
     *
     * 长文章
     * http://app3.qdaily.com/app3/categories/index/1/0.json
     */
    @GET("app3/homes/index/{index}.json")
    fun getHomeDataByIndex(@Path("index") index: String): Observable<BaseBean<Home>>

    @GET("app3/articles/detail/{index}.json")
    fun getArticleByIndex(@Path("index") index: String): Observable<BaseBean<Article>>

    @GET("app3/columns/index/{id}/{index}.json")
    fun getColumn(@Path("id") id: String, @Path("index") index: String): Observable<BaseBean<ColumnContent>>

    @GET("app3/papers/index/{index}.json")
    fun getLabByIndex(@Path("index") index: String): Observable<BaseBean<Paper>>

    @GET
    fun downLoadFile(@Url url: String): Observable<ResponseBody>

    @GET("app3/homes/left_sidebar.json")
    fun getLeftBar(): Observable<LeftSideBar>

    @GET("app3/categories/index/{id}/{index}.json")
    fun getCategory(@Path("id") id: String, @Path("index") index: String): Observable<BaseBean<Category>>

}
