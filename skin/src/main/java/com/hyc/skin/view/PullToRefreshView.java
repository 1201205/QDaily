package com.hyc.skin.view;

import android.animation.Animator;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;
import android.widget.Scroller;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.hyc.skin.R;

/**
 * Created by hyc on 2016/8/29.
 */
public class PullToRefreshView extends FrameLayout {
    View mHead;
    LoadingAnimView mLoadingView;
    RecyclerView mTarget;
    private boolean mHasHead;
    private LinearLayoutManager mManager;
    private boolean mCanScroll;
    private float mCurrentX;
    private float mCurrentY;
    private int mHeadHeight;
    private int mAnimHeight;
    private Animator mScrollAnimator;
    private Scroller mScroller;
    private int mTextHeight;
    private int mTouchSlop;
    private boolean mIsMoving;
    private boolean mHasDown;
    private RefreshListener mRefreshListener;
    private long mNextPushTime;
    private Context mContext;
    public PullToRefreshView(Context context) {
        this(context, null);
    }

    public PullToRefreshView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PullToRefreshView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext=context;

        mScroller = new Scroller(getContext());
        mTouchSlop = ViewConfiguration.getTouchSlop();
    }


    public void setRefreshListener(RefreshListener refreshListener) {
        mRefreshListener = refreshListener;
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        int count = getChildCount();
        if (count <= 0) {
            return;
        }
        mHead=findViewById(R.id.head);
        mLoadingView= (LoadingAnimView) findViewById(R.id.loading);
        mTarget= (RecyclerView) findViewById(R.id.target);
        //mHead = findViewById(R.id.head);
        //if (mHead != null) {
        //    mHasHead = true;
        //    mLoadingView = (LoadingAnimView) findViewById(R.id.loading);
        //    mRemainTime = (TextView) findViewById(R.id.tv_remain);
        //    mTarget= (RecyclerView) findViewById(R.id.target);
        //} else {
        //    mHasHead=false;
        //}

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mHeadHeight = mHead.getMeasuredHeight();
        //Log.e("----","mAnimHeight+++"+mAnimHeight+"++++mHeadHeight++++"+mHeadHeight);

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        float y = ev.getRawY();
        //Log.e("hyc-TouchEvent","--pull--"+ev.getAction());
        boolean resume = false;
        if (mManager == null) {
            mManager = (LinearLayoutManager) mTarget.getLayoutManager();
        }
        if (mManager==null) {
            return false;
        }
        //Log.e("hyc-TouchEvent","--findFirstCompletelyVisibleItemPosition--"+mManager.findFirstCompletelyVisibleItemPosition());

        if (mManager.findFirstCompletelyVisibleItemPosition() == 0 && !mLoadingView.isLoading()) {
            mCanScroll = true;
        } else {
            mCanScroll = false;
        }
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
//                if (!mScroller.isFinished()) {
//                    mScroller.forceFinished(true);
//                }
                mHasDown = true;
                mCurrentY = y;
            case MotionEvent.ACTION_MOVE:
                float deltaY = y - mCurrentY;
                if (!mIsMoving) {
                    if (Math.abs(deltaY) > mTouchSlop) {
                        mIsMoving = true;
                    }
                }
                //Log.e("----","y--"+y+"--deltaY--"+deltaY+"--getScrollY--"+getScrollY());
                if (mCanScroll && mIsMoving) {
                    int scroll = (int) -deltaY * 2 / 3;
                    if (deltaY > 0) {
                        resume = true;
                        if (Math.abs(getScrollY()) < mHeadHeight) {
                            scrollBy(0, scroll);
                        }
                    } else if (getScrollY() < 0) {
                        resume = true;
                        if ((getScrollY() - deltaY * 2 / 3) > 0) {
                            scrollTo(0, 0);
                        } else {
                            scrollBy(0, scroll);
                        }
                    }
                    alphaAnimate();
                    round(scroll);
                }
                mCurrentY = y;
                if (resume) {
                    if (mHasDown) {
                        MotionEvent event = MotionEvent.obtain(ev);
                        event.setAction(MotionEvent.ACTION_CANCEL);
                        super.dispatchTouchEvent(event);
                        mHasDown = false;
                    }
                    //后面还需要------
                    //super.dispatchTouchEvent(ev);
                    return resume;
                } else {
                    return super.dispatchTouchEvent(ev);
                }
            case MotionEvent.ACTION_UP:
                //Log.e("hyc-TouchEvent","--mCanScroll--"+mCanScroll+"---mIsMoving---"+mIsMoving+"-getScrollY()"+(-getScrollY())+"++++"+(mHeadHeight - mAnimHeight));
                mHasDown = false;
                if (mCanScroll && mIsMoving) {
                    if (-getScrollY() > (mHeadHeight - mAnimHeight)) {
                        //Log.e("hyc-TouchEvent","回退---开始");
                        startScroll(Math.abs(getScrollY()) - (mHeadHeight - mAnimHeight));
                        mLoadingView.startAnim();
                        mIsMoving = false;
                        checkRefresh();
                        return true;
                    } else if ((mHeadHeight - mAnimHeight) / 2 < -getScrollY()) {
                        //Log.e("hyc-TouchEvent","向上---开始");
                        startScroll(-(mHeadHeight - mAnimHeight - Math.abs(getScrollY())));
                        mLoadingView.startAnim();
                        mIsMoving = false;
                        checkRefresh();
                        return true;
                    } else if ((mHeadHeight - mAnimHeight) / 2 > -getScrollY()) {
                        startScroll((Math.abs(getScrollY())));
//                        Log.e("hyc-TouchEvent", "回退---");
                        mIsMoving = false;
                        return true;
                    }
                }
                mIsMoving = false;
                break;
        }
        return super.dispatchTouchEvent(ev);
    }


    private void round(int scroll) {
        mLoadingView.round(scroll);
    }


    private void alphaAnimate() {
        if (getScrollY() < -mAnimHeight) {
            float x = ((Math.abs(getScrollY()) - mAnimHeight)) * 1f /
                    (mHeadHeight - mAnimHeight - dip2px(10));
            //Log.e("XXXX",x+"");
        }
    }


    //    private void startScroll(float scroll){
//        mScrollAnimator=ObjectAnimator.ofFloat(this,"y",scroll);
//        mScrollAnimator.setDuration((long) Math.abs(scroll)*5);
//        mScrollAnimator.setInterpolator(new DecelerateInterpolator());
//        mScrollAnimator.start();
//
//    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    private void startScroll(float scroll) {
        //Log.e("test1",scroll+"---");
        mScroller.startScroll(getScrollX(), getScrollY(), getScrollX(), (int) scroll, (int) Math.abs(scroll) * 4);
        invalidate();
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            //Log.e("mScroller",mScroller.getCurrY()+"---");
            alphaAnimate();
            if (getScrollY() == 0 && mLoadingView.isLoading()) {
                mLoadingView.stopAnim();
            }
            postInvalidate();
        }
        super.computeScroll();
    }


    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    public void setNextPushTime(long time) {
        mNextPushTime = time;
    }

    public void endAnim() {
        startScroll((Math.abs(getScrollY())));
    }

    private void checkRefresh() {
        if (System.currentTimeMillis() > mNextPushTime&&mRefreshListener != null) {
        mRefreshListener.handleRefresh();
        } else {
            postEndAnim();
        }
    }

    private void postEndAnim() {
        postDelayed(new Runnable() {
            @Override
            public void run() {
                startScroll((Math.abs(getScrollY())));
            }
        }, 2000);
    }

    public interface RefreshListener {
        void handleRefresh();
    }
    public float dip2px(float dpValue) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (dpValue * scale + 0.5f);
    }
}
