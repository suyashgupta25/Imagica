package net.sparksnetwork.app.di;


import android.app.Application;
import android.content.Context;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.sparksnetwork.app.data.AppDataManager;
import net.sparksnetwork.app.data.DataManager;
import net.sparksnetwork.app.data.remote.ApiHelper;
import net.sparksnetwork.app.data.remote.AppApiHelper;
import net.sparksnetwork.app.ui.gallery.GalleryViewModel;
import net.sparksnetwork.app.utils.AppConstants;
import net.sparksnetwork.app.utils.rx.AppSchedulerProvider;
import net.sparksnetwork.app.utils.rx.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }

    @Provides
    @Singleton
    Glide provideGlide(Context context) {
        return Glide.get(context);
    }

    @Provides
    @ApiInfo
    String provideApiKey() {
        return AppConstants.PARAM_VALUE_CONTENT_TYPE;
    }
}
