package com.hyc.skin;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.view.View;

import com.hyc.skin.core.SkinBaseActivity;
import com.hyc.skin.core.SkinManager;

public class MainActivity extends BaseActivity {
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        verifyStoragePermissions(this);
        SkinManager.getInstance().init(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        findViewById(R.id.activity_main).postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                SkinManager.getInstance().loadSkinRes("night.skin");
//            }
//        },5000);
        findViewById(R.id.change).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SkinManager.getInstance().showingDefaultSkin()) {
                    SkinManager.getInstance().loadSkinRes("red");
                } else {
                    SkinManager.getInstance().showDefault();
                }
            }
        });
    }

    public static void verifyStoragePermissions(Activity activity) {
// Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
// We don't have permission so prompt the user
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE);
        }
    }

}
