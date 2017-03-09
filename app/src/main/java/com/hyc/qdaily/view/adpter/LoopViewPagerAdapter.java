package com.hyc.qdaily.view.adpter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.hyc.qdaily.R;
import com.hyc.qdaily.beans.home.Feed;
import java.util.ArrayList;
import java.util.List;
import com.hyc.qdaily.util.AppUtilKt;
import com.hyc.qdaily.util.FrescoHelperKt;

public class LoopViewPagerAdapter extends BaseLoopPagerAdapter {
    private final List<Feed> mDatas;
    private final ViewGroup mIndicators;
    private ItemClickListener mItemClickListener;
    private int mLastPosition;


    public LoopViewPagerAdapter(ViewPager viewPager, ViewGroup indicators) {
        super(viewPager);
        mIndicators = indicators;
        mDatas = new ArrayList<>();
    }


    public void setList(ArrayList<Feed> datas) {
        mDatas.clear();
        mDatas.addAll(datas);
    }


    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;
    }


    /**
     * oh shit! An indicator view is badly needed!
     * this shit have no animation at all.
     */
    private void initIndicators() {
        if (mIndicators.getChildCount() != mDatas.size() && mDatas.size() > 1) {
            mIndicators.removeAllViews();
            Resources res = mIndicators.getResources();
            int size = (int) AppUtilKt.dip2px(6);
            int margin = size;
            for (int i = 0; i < getPagerCount(); i++) {
                ImageView indicator = new ImageView(mIndicators.getContext());
                indicator.setAlpha(240);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(size, size);
                lp.setMargins(margin, margin, 0, margin);
                lp.gravity = Gravity.CENTER;
                indicator.setLayoutParams(lp);
                Drawable drawable = res.getDrawable(R.drawable.selector_indicator);
                indicator.setImageDrawable(drawable);
                mIndicators.addView(indicator);
            }
        }
    }


    @Override
    public void notifyDataSetChanged() {
        initIndicators();
        super.notifyDataSetChanged();
    }


    @Override
    public int getPagerCount() {
        return mDatas.size();
    }


    @Override
    public Feed getItem(int position) {
        return mDatas.get(position);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        Log.e("hyc-test","View");
        if (convertView == null) {
            Log.e("hyc-------",parent.getContext()+"----");
            LayoutInflater inflater= (LayoutInflater) parent.getContext().getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
            if (inflater==null) {
                Log.e("hyc-------inflaterView","inflaterView----");
            }
            convertView = inflater
                .inflate(R.layout.adpter_banner, parent, false);

            Log.e("hyc-------convertView",convertView+"----");
            holder = new ViewHolder();
            holder.sdvBanner = (SimpleDraweeView) convertView.findViewById(R.id.sdv_banner);
            holder.tvBanner= (TextView) convertView.findViewById(R.id.tv_banner);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final Feed data = mDatas.get(position);
        if (mItemClickListener != null) {
            holder.sdvBanner.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClickListener.itemClicked(data);
                }
            });
        }
        FrescoHelperKt.loadUrl(holder.sdvBanner, data.getImage());
        holder.tvBanner.setText(data.getPost().getTitle());
        return convertView;
    }


    @Override
    public void onPageItemSelected(int position) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            mIndicators.getChildAt(mLastPosition).setActivated(false);
            mIndicators.getChildAt(position).setActivated(true);
        }
        mLastPosition = position;
    }


    public interface ItemClickListener {
        void itemClicked(Feed data);
    }


    public static class ViewHolder {
        SimpleDraweeView sdvBanner;
        TextView tvBanner;
    }
}
