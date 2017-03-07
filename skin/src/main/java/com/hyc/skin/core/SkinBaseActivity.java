package com.hyc.skin.core;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;

/**
 * Created by ray on 17/2/19.
 */

public class SkinBaseActivity extends AppCompatActivity implements SkinChangeListener {
    private FactoryWrapper mFactory;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mFactory = new FactoryWrapper(this);
        LayoutInflaterCompat.setFactory(getLayoutInflater(), mFactory);
        SkinManager.getInstance().addListener(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        SkinManager.getInstance().removeListener(this);
        super.onDestroy();
    }

    @Override
    public void reloadSkin() {
        mFactory.changeSkin();
    }
}
