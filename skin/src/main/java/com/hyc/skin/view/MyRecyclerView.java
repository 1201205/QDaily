package com.hyc.skin.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by hyc on 2017/5/8.
 */

public class MyRecyclerView extends RecyclerView{
    private RecyclerView.OnScrollListener mOnScrollListener;

    public MyRecyclerView(Context context) {
        this(context,null);
    }


    public MyRecyclerView(Context context,
                          @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }



    public MyRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mOnScrollListener=new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == SCROLL_STATE_IDLE) {
                    Fresco.getImagePipeline().resume();
                } else {
                    Fresco.getImagePipeline().pause();
                }
            }
        };
        addOnScrollListener(mOnScrollListener);
    }


    @Override protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeOnScrollListener(mOnScrollListener);
    }
}
