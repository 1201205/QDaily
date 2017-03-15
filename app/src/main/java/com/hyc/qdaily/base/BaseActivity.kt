package com.hyc.qdaily.base

import android.os.Bundle
import butterknife.ButterKnife
import com.hyc.skin.BaseActivity

/**
 * Created by hyc on 2016/5/13.
 */
abstract class BaseActivity<T : BasePresenter<*>> : BaseActivity(), BaseView {
    protected  var mPresenter: T? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutID)
        ButterKnife.bind(this)
        if (intent != null) {
            handleIntent()
        }
        initPresenterAndData()
            mPresenter?.attachView()
        initView()
    }

    protected abstract fun handleIntent()

    protected abstract val layoutID: Int

    protected abstract fun initPresenterAndData()

    protected abstract fun initView()

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.detachView()
    }

    //    protected void setShareElementTransition(){
    //        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP) {
    //            getWindow().setSharedElementEnterTransition(
    //                DraweeTransition.createTransitionSet(ScalingUtils.ScaleType.CENTER_CROP,
    //                    ScalingUtils.ScaleType.CENTER_CROP));
    //            getWindow().setSharedElementReturnTransition(
    //                DraweeTransition.createTransitionSet(ScalingUtils.ScaleType.CENTER_CROP,
    //                    ScalingUtils.ScaleType.CENTER_CROP));
    //        }
    //    }
}
