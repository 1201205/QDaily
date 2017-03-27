package com.hyc.qdaily.view.adpter.holder

import android.content.Context
import android.content.Intent
import android.support.v4.view.ViewPager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import butterknife.BindView
import butterknife.ButterKnife
import com.hyc.qdaily.view.activity.MainActivity
import com.hyc.qdaily.R
import com.hyc.qdaily.beans.home.Feed
import com.hyc.qdaily.beans.view.ViewData
import com.hyc.qdaily.view.adpter.LoopViewPagerAdapter

/**
 * Created by hyc on 2017/3/8.
 */
class BannerProvider : ItemViewProvider<BannerProvider.BannerViewHolder, ViewData<ArrayList<Feed>>>() {
    override fun onBindViewHolder(holder: BannerViewHolder, data: ViewData<ArrayList<Feed>>, position: Int, wrapper: ParamWrapper) {
        with(holder) {
            val context: Context = itemView.context
            vpBanner.adapter ?: let {
                var adapter: LoopViewPagerAdapter = LoopViewPagerAdapter(holder.vpBanner, holder.llIndicator)
                adapter.setList(data.content)
                vpBanner.adapter = adapter
                vpBanner.addOnPageChangeListener(adapter)
                adapter.notifyDataSetChanged()
                adapter.setItemClickListener { data ->
                    var intent = Intent(context, MainActivity::class.java)
                    intent.action = data.image
                    context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(inflater: LayoutInflater, viewGroup: ViewGroup?): BannerProvider.BannerViewHolder {
        return BannerViewHolder(inflater.inflate(R.layout.item_pager, viewGroup, false))
    }

    class BannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @BindView(R.id.vp_banner)
        lateinit var vpBanner: ViewPager
        @BindView(R.id.ll_indicator)
        lateinit var llIndicator: LinearLayout

        init {
            ButterKnife.bind(this, itemView)
        }
    }
}