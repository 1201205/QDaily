package com.hyc.qdaily.net

import com.hyc.qdaily.beans.BaseBean
import com.hyc.qdaily.beans.home.Article
import com.hyc.qdaily.beans.home.ColumnContent
import com.hyc.qdaily.beans.home.Home
import com.hyc.qdaily.beans.home.Paper
import com.hyc.qdaily.beans.other.Category
import com.hyc.qdaily.beans.other.LeftSideBar
import com.hyc.qdaily.beans.paper.Choices
import com.hyc.qdaily.beans.paper.PaperDetail
import com.hyc.qdaily.beans.paper.PaperOptions
import com.hyc.qdaily.beans.paper.Tot
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
     * detail
     * http://app3.qdaily.com/app3/papers/detail/1605.json
     *
     * options
     * http://app3.qdaily.com/app3/options/index/1605/0.json
     *
     * 你猜选项
     * http://app3.qdaily.com/app3/paper/choices/1605.json
     *
     * 42%
     * http://app3.qdaily.com/app3/paper/tots/1550
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
    fun getLeftBar(): Observable<BaseBean<ArrayList<LeftSideBar>>>

    @GET("app3/categories/index/{id}/{index}.json")
    fun getCategory(@Path("id") id: String, @Path("index") index: String): Observable<BaseBean<Category>>

    @GET("app3/papers/detail/{id}.json")
    fun getPaperDetail(@Path("id") id: String): Observable<BaseBean<PaperDetail>>

    @GET("app3/options/index/{id}/{index}.json")
    fun getOptions(@Path("id") id: String, @Path("index") index: String): Observable<BaseBean<PaperOptions>>

    @GET("app3/paper/choices/{id}.json")
    fun getChoices(@Path("id") id: String): Observable<BaseBean<Choices>>

    @GET("app3/paper/tots/1550")
    fun getTot(@Path("id") id:String):Observable<BaseBean<Tot>>

}
