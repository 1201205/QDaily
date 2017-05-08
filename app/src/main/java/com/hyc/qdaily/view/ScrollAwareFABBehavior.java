package com.hyc.qdaily.view;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.View;
import com.hyc.qdaily.util.AppUtilKt;

/**
 * Created by hyc on 2017/3/20.
 */

public class ScrollAwareFABBehavior extends FloatingActionButton.Behavior {
    private int toolbarHeight = (int) AppUtilKt.dip2px(42f);

    private CoordinatorLayout.LayoutParams lp;


    public ScrollAwareFABBehavior() {
        super();
    }


    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, FloatingActionButton fab, View dependency) {
        return super.layoutDependsOn(parent, fab, dependency) ||
            (dependency instanceof AppBarLayout);
    }


    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, FloatingActionButton fab, View dependency) {
        boolean returnValue = super.onDependentViewChanged(parent, fab, dependency);
        if (dependency instanceof AppBarLayout) {
            if (lp == null) {
                lp = (CoordinatorLayout.LayoutParams) fab.getLayoutParams();
            }
            int fabBottomMargin = lp.bottomMargin;
            int distanceToScroll = fab.getHeight() + fabBottomMargin;
            float ratio = (float) dependency.getY() / (float) toolbarHeight;
            fab.setTranslationY(-distanceToScroll * ratio);
        }
        return returnValue;
    }
}
