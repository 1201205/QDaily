package com.hyc.qdaily.view.adpter.holder

import android.graphics.Rect
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.facebook.drawee.view.SimpleDraweeView
import com.hyc.qdaily.R
import com.hyc.qdaily.beans.view.ViewData
import com.hyc.qdaily.util.dip2px
import com.hyc.qdaily.util.loadUrl
import com.hyc.qdaily.view.adpter.ViewAdapter

/**
 * Created by ray on 17/3/12.
 */
class TopicsProvider : ItemViewProvider<TopicsProvider.TopicsHolder>() {
    override fun onCreateViewHolder(inflater: LayoutInflater, viewGroup: ViewGroup?): TopicsHolder {
        return TopicsHolder(inflater.inflate(R.layout.item_topics,viewGroup,false))
    }

    override fun onBindViewHolder(holder: TopicsHolder, data: ViewData, position: Int, wrapper: ParamWrapper) {
        var inserts=data.inserts
        with(holder){
            var context=rvTopic.context
            rvTopic.adapter=ViewAdapter.Builder(context,inserts!!).build()
            rvTopic.layoutManager?:let {
                rvTopic.layoutManager=LinearLayoutManager(context).also { it.orientation=LinearLayoutManager.HORIZONTAL }
                rvTopic.addItemDecoration(SpaceItemDecoration(dip2px(18f).toInt(), dip2px(3f).toInt()))
            }
        }
    }

    class TopicsHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        @BindView(R.id.rv_topic)
        lateinit var rvTopic:RecyclerView
        init {
            ButterKnife.bind(this,itemView)
        }
    }
    class SpaceItemDecoration(border: Int,center:Int) : RecyclerView.ItemDecoration() {
        var border:Int = border
        var center:Int = center

        override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
            when(parent?.getChildAdapterPosition(view)){
                0->outRect?.set(border,0,center,0)
                (parent?.adapter?.itemCount!! -1)->outRect?.set(center,0,border,0)
                else->outRect?.set(center,0,center,0)
            }
        }
    }
//    public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
//        private int space;
//
//        public SpacesItemDecoration(int space) {
//            this.space = space;
//        }
//
//        @Override
//        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//            int position = parent.getChildPosition(view);
//            if (position == 0) {
//                outRect.set(space, 0, 0, 0);
//            } else if (position == parent.getAdapter().getItemCount() - 1) {
//                outRect.set(0, 0, space, 0);
//            }
//
//        }
//    }
}