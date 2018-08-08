package net.sparksnetwork.app.ui.gallery;

import net.sparksnetwork.app.data.DataManager;
import net.sparksnetwork.app.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class GalleryModule {

    @Provides
    GalleryViewModel provideCoursesViewModel(SchedulerProvider schedulerProvider, DataManager dataManager) {
        return new GalleryViewModel(schedulerProvider, dataManager);
    }
}
