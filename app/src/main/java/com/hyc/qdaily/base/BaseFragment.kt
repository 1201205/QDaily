package com.hyc.qdaily.base

import android.os.Bundle
import android.support.v4.app.Fragment
import io.reactivex.Observable

/**
 * Created by ray on 16/8/31.
 */
abstract class BaseFragment<T : BasePresenter<*>> : Fragment() {
    protected var mPresenter: T? = null

    protected abstract fun initPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initPresenter()
    }
}
