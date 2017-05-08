package com.hyc.skin.core;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ray on 17/2/19.
 */

public class SkinManager {
    private static volatile SkinManager skinManager;
    private boolean showDefaultSkin;
    private List<SkinChangeListener> mSkinChangeListeners;
    private Context mContext;
    private String mPath= Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator+"skin";
    private SkinManager(){}
    public static SkinManager getInstance(){
        if (skinManager==null) {
            synchronized (SkinManager.class){
                if (skinManager==null) {
                    skinManager=new SkinManager();
                }
            }
        }
        return skinManager;
    }
    public void init(Context context){
        mContext=context.getApplicationContext();
        SkinResources.getInstance().init(mContext);
        mSkinChangeListeners=new ArrayList<>();
        File file=new File(mPath);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    public void addListener(SkinChangeListener changeListener){
        mSkinChangeListeners.add(changeListener);
    }
    public void removeListener(SkinChangeListener changeListener){
        mSkinChangeListeners.remove(changeListener);
    }
    public void loadSkinRes(String name){
        new AsyncTask<String,Void,Resources>(){
            @Override protected void onPostExecute(Resources resources) {
                super.onPostExecute(resources);
                if (resources!=null) {
                    for (SkinChangeListener listener:mSkinChangeListeners) {
                        listener.reloadSkin();
                        //Log.e("hyc-test","更换资源");
                    }
                }
            }


            @Override protected Resources doInBackground(String... strings) {
                String filePath = getFilePath(strings[0]);
                File skin=new File(filePath);
                if (!skin.exists()) {
                    return null;
                }
                try {
                    PackageManager packageManager=mContext.getPackageManager();
                    PackageInfo packageInfo=packageManager.getPackageArchiveInfo(filePath,PackageManager.GET_ACTIVITIES);
                    String packageName=packageInfo.packageName;
                    AssetManager assetManager = AssetManager.class.newInstance();
                    Method addAssetPath = assetManager.getClass().getMethod("addAssetPath", String.class);
                    addAssetPath.invoke(assetManager,filePath);
                    Resources resourze=mContext.getResources();
                    Resources resources=new Resources(assetManager, resourze.getDisplayMetrics(), resourze.getConfiguration());
                    SkinResources.getInstance().changeResources(resources,packageName);
                    return resources;
                }catch (Exception e){
                    //Log.e("hyc-test","失败");
                    return null;
                }
            }


            @Override protected void onPreExecute() {
                super.onPreExecute();
            }


            @Override protected void onCancelled() {
                super.onCancelled();
            }
        }.execute(name);
    }
    private String getSkinFilePath(String name){
        return mPath+File.separator+name+".skin";
    }

    /**
     *
     * @return 是否试默认皮肤
     */
    public boolean showingDefaultSkin(){
        return !SkinResources.getInstance().showingExternalSkin();
    }

    private String getFilePath(String name){
        File skinFile=new File(mPath,name+".skin");
        if (skinFile.exists()) {
            return skinFile.getAbsolutePath();
        }
        AssetManager manager=mContext.getAssets();
        try {
           InputStream is= manager.open(name+".skin");
            if (!skinFile.exists()) {
                skinFile.createNewFile();
            }
            OutputStream os = new FileOutputStream(skinFile.getAbsolutePath());
            int byteCount;
            byte[] bytes = new byte[1024];

            while ((byteCount = is.read(bytes)) != -1) {
                os.write(bytes, 0, byteCount);
            }
            os.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return skinFile.getAbsolutePath();
    }

    /**
     * 恢复默认
     */
    public void showDefault(){
        SkinResources.getInstance().closeExternalSkin();
        for (SkinChangeListener listener:mSkinChangeListeners) {
            listener.reloadSkin();
            //Log.e("hyc-test","更换资源");
        }

    }
    public interface SkinLoadListener{
        void loadSuccess();
        void loadFailed();
    }
}
