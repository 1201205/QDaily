package com.hyc.qdaily

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import com.hyc.qdaily.view.adpter.holder.ViewModelPool
import com.hyc.skin.core.SkinManager

/**
 * Created by hyc on 2017/3/3.
 */

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
        Fresco.initialize(this)
        SkinManager.getInstance().init(this)
        ViewModelPool.instance.init()
    }

    companion object {
        lateinit var instance: MainApplication
            private set
    }
}
