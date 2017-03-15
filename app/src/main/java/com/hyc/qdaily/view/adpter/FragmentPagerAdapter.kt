package com.hyc.qdaily.view.adpter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

/**
 * Created by ray on 17/3/15.
 */
class FragmentPagerAdapter constructor(manager: FragmentManager) : FragmentStatePagerAdapter(manager) {
    var mList: ArrayList<Fragment>

    init {
        mList = ArrayList()
    }

    constructor(manager: FragmentManager, list: ArrayList<Fragment>) : this(manager) {
        mList = list
    }

    override fun getItem(position: Int): Fragment {
        return mList[position]
    }

    override fun getCount(): Int {
        return mList.size
    }

    fun add(fragment: Fragment) {
        mList.add(fragment)
    }
}