package com.hyc.qdaily.view.adpter.holder

import android.support.v7.widget.RecyclerView
import android.util.Log
import com.hyc.qdaily.beans.home.Feed
import com.hyc.qdaily.beans.view.ViewData
import kotlin.properties.Delegates

/**
 * Created by hyc on 2017/3/8.
 */
class ViewModelPool private constructor() {
    private var mTypeMap: HashMap<String, ItemViewProvider<RecyclerView.ViewHolder, ViewData<*>>> = HashMap()
    private var mProviders: ArrayList<ItemViewProvider<RecyclerView.ViewHolder, ViewData<*>>> = ArrayList()

    companion object {
        var instance: ViewModelPool = ViewModelPool()
    }

    private fun putInPool(type: String, provider: ItemViewProvider<RecyclerView.ViewHolder, ViewData<*>>) {
        mTypeMap.put(type, provider)
        mProviders.add(provider)
    }

    fun getType(type: String): Int = mProviders.indexOf(mTypeMap[type])
    fun getProviderByType(type: String): ItemViewProvider<RecyclerView.ViewHolder, ViewData<*>>? = mTypeMap[type]
    fun getProvider(index: Int): ItemViewProvider<RecyclerView.ViewHolder, *> {
        Log.e("hyc-we", this.toString() + "Provider---" + mProviders.size + "++++++" + index)
        return mProviders[index]
    }

    fun init() {
        putInPool("banner", BannerProvider() as ItemViewProvider<RecyclerView.ViewHolder, ViewData<*>>)
        putInPool("feed", FeedProvider() as ItemViewProvider<RecyclerView.ViewHolder, ViewData<*>>)
        putInPool("curiosity", CuriosityProvider() as ItemViewProvider<RecyclerView.ViewHolder, ViewData<*>>)
        putInPool("vertical", VerticalProvider() as ItemViewProvider<RecyclerView.ViewHolder, ViewData<*>>)
        putInPool("recycler", RecyclerProvider() as ItemViewProvider<RecyclerView.ViewHolder, ViewData<*>>)
        putInPool("language", LanguageProvider() as ItemViewProvider<RecyclerView.ViewHolder, ViewData<*>>)
        putInPool("headline", HeadLineProvider() as ItemViewProvider<RecyclerView.ViewHolder, ViewData<*>>)
        putInPool("book", BookProvider() as ItemViewProvider<RecyclerView.ViewHolder, ViewData<*>>)
        putInPool("lab", LabProvider() as ItemViewProvider<RecyclerView.ViewHolder, ViewData<*>>)
        putInPool("topic", TopicProvider() as ItemViewProvider<RecyclerView.ViewHolder, ViewData<*>>)
        putInPool("topics", TopicsProvider() as ItemViewProvider<RecyclerView.ViewHolder, ViewData<*>>)
        putInPool("classify", ClassifyProvider() as ItemViewProvider<RecyclerView.ViewHolder, ViewData<*>>)
        putInPool("submit", SubmitProvider() as ItemViewProvider<RecyclerView.ViewHolder, ViewData<*>>)
        putInPool("vote_option", VoteProvider() as ItemViewProvider<RecyclerView.ViewHolder, ViewData<*>>)
        putInPool("say", SayProvider() as ItemViewProvider<RecyclerView.ViewHolder, ViewData<*>>)

        Log.e("hyc-we", this.toString() + "init---" + mProviders.size)
    }

}