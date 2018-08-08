package net.sparksnetwork.app.data;

import android.content.Context;
import android.net.Uri;

import com.google.gson.Gson;

import net.sparksnetwork.app.data.local.AppImageCacheHelper;
import net.sparksnetwork.app.data.remote.AppApiHelper;

import java.io.File;
import java.util.List;

import javax.inject.Inject;

public class AppDataManager implements DataManager {

    private final Context mContext;

    private final Gson mGson;

    private final AppImageCacheHelper mAppImageCacheHelper;

    private final AppApiHelper mAppApiHelper;

    @Inject
    public AppDataManager(AppImageCacheHelper appImageCacheHelper, Context context, Gson gson, AppApiHelper appApiHelper) {
        this.mAppImageCacheHelper = appImageCacheHelper;
        this.mContext = context;
        this.mGson = gson;
        this.mAppApiHelper = appApiHelper;
    }

    @Override
    public File saveImageToCache(Uri filePath, String fileName) {
        return mAppImageCacheHelper.saveImageToCache(filePath, fileName);
    }

    @Override
    public int getImagesCountFromCache() {
        return mAppImageCacheHelper.getImagesCountFromCache();
    }

    @Override
    public List<File> getFilesList() {
        return mAppImageCacheHelper.getFilesList();
    }

    @Override
    public File getLocalImagesDirectory() {
        return mAppImageCacheHelper.getLocalImagesDirectory();
    }
}
