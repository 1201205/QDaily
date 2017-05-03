package com.hyc.qdaily.view.adpter.holder

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import butterknife.ButterKnife
import com.hyc.qdaily.R
import com.hyc.qdaily.anko.TopicsProviderUI
import com.hyc.qdaily.beans.home.InsertContent
import com.hyc.qdaily.beans.view.ViewData
import com.hyc.qdaily.util.SpaceItemDecoration
import com.hyc.qdaily.util.dip2px
import com.hyc.qdaily.view.adpter.ViewAdapter

/**
 * Created by ray on 17/3/12.
 */
class TopicsProvider : ItemViewProvider<TopicsProvider.TopicsHolder,ViewData<ArrayList<ViewData<InsertContent>>>>() {
    override fun onCreateViewHolder(inflater: LayoutInflater, viewGroup: ViewGroup?): TopicsHolder {
        return TopicsHolder(TopicsProviderUI().bind(viewGroup!!.context))
    }

    override fun onBindViewHolder(holder: TopicsHolder, data: ViewData<ArrayList<ViewData<InsertContent>>>, position: Int, wrapper: ParamWrapper) {
        var inserts = data.content
        with(holder) {
            var context = rvTopic.context
            rvTopic.adapter = ViewAdapter.Builder(context, inserts!!as ArrayList<ViewData<*>>).build()
            rvTopic.layoutManager ?: let {
                rvTopic.layoutManager = LinearLayoutManager(context).also { it.orientation = LinearLayoutManager.HORIZONTAL }
                rvTopic.addItemDecoration(SpaceItemDecoration(dip2px(18f).toInt(), dip2px(3f).toInt()))
            }
        }
    }

    class TopicsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @BindView(R.id.rv_topic)
        lateinit var rvTopic: RecyclerView

        init {
            ButterKnife.bind(this, itemView)
        }
    }
}