package com.hyc.qdaily.view.adpter.holder

import android.graphics.Rect
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.facebook.drawee.view.SimpleDraweeView
import com.hyc.qdaily.R
import com.hyc.qdaily.beans.view.ViewData
import com.hyc.qdaily.events.LoadMoreEventX
import com.hyc.qdaily.util.dip2px
import com.hyc.qdaily.util.loadUrl
import com.hyc.qdaily.view.adpter.ViewAdapter
import org.greenrobot.eventbus.EventBus

/**
 * Created by ray on 17/3/12.
 */
class RecyclerProvider : ItemViewProvider<RecyclerProvider.RecyclerHolder>() {
    override fun onCreateViewHolder(inflater: LayoutInflater, viewGroup: ViewGroup?): RecyclerHolder {
        return RecyclerHolder(inflater.inflate(R.layout.item_recycler,viewGroup,false))
    }

    override fun onBindViewHolder(holder: RecyclerHolder, data: ViewData, position: Int, wrapper: ParamWrapper) {
        var columnContent=data.columnContent
        with(holder){
            var adapter =rvLanguage.adapter
            if (adapter is ViewAdapter) {
                if (adapter.getData(0).feed?.post?.id== data.columnContent?.feeds?.get(0)?.feed?.post?.id) {
                    return
                }
            }
            var context=rvLanguage.context
            loadUrl(sdvIcon,columnContent?.icon)
            tvName.text=columnContent?.name
            rvLanguage.adapter=ViewAdapter.Builder(context,columnContent?.feeds!!).build()
            rvLanguage.layoutManager?:let {
                rvLanguage.layoutManager=LinearLayoutManager(context).also { it.orientation=LinearLayoutManager.HORIZONTAL }
                rvLanguage.addItemDecoration(SpaceItemDecoration(dip2px(18f).toInt(), dip2px(3f).toInt()))
            }
            rvLanguage.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                    if (!columnContent.requesting&&!TextUtils.isEmpty(columnContent.lastIndex)&&(rvLanguage.layoutManager as
                            LinearLayoutManager).findLastVisibleItemPosition()>=rvLanguage.adapter.itemCount-2) {
                        EventBus.getDefault().post(LoadMoreEventX(columnContent.id))
                    }
                }
            })
        }
    }

    class RecyclerHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        @BindView(R.id.rv_language)
        lateinit var rvLanguage:RecyclerView
        @BindView(R.id.sdv_icon)
        lateinit var sdvIcon:SimpleDraweeView
        @BindView(R.id.tv_name)
        lateinit var tvName:TextView
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