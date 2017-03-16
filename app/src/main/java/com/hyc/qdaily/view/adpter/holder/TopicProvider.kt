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
class TopicProvider : ItemViewProvider<TopicProvider.TopicHolder>() {
    override fun onCreateViewHolder(inflater: LayoutInflater, viewGroup: ViewGroup?): TopicHolder {
        return TopicHolder(inflater.inflate(R.layout.item_topic,viewGroup,false))
    }

    override fun onBindViewHolder(holder: TopicHolder, data: ViewData, position: Int, wrapper: ParamWrapper) {
        var insert = data.insertContent
        with(holder){
            loadUrl(sdvImg,insert?.image)
            tvTitle.text=insert?.title
            tvDes.text=insert?.description
        }
    }

    class TopicHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        @BindView(R.id.sdv_img)
        lateinit var sdvImg:SimpleDraweeView
        @BindView(R.id.tv_title)
        lateinit var tvTitle:TextView
        @BindView(R.id.tv_des)
        lateinit var tvDes:TextView
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