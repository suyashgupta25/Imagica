package net.sparksnetwork.app.ui.gallery.grid;

import android.arch.lifecycle.ViewModelProvider;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;

import net.sparksnetwork.app.data.DataManager;
import net.sparksnetwork.app.data.firebase.AppFirebaseHelper;
import net.sparksnetwork.app.data.firebase.FirebaseHelper;
import net.sparksnetwork.app.data.firebase.FirebaseUploadCallback;
import net.sparksnetwork.app.utils.ViewModelProviderFactory;
import net.sparksnetwork.app.utils.rx.SchedulerProvider;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

import static net.sparksnetwork.app.utils.AppConstants.GALLERY_COLUMNS;

@Module
public class GalleryGridModule {

    @Provides
    GalleryGridViewModel galleryGridViewModel(SchedulerProvider schedulerProvider, DataManager dataManager) {
        return new GalleryGridViewModel(schedulerProvider, dataManager);
    }

    @Provides
    ViewModelProvider.Factory provideGalleryGridViewModel(GalleryGridViewModel galleryGridViewModel) {
        return new ViewModelProviderFactory<>(galleryGridViewModel);
    }

    @Provides
    GridLayoutManager provideLinearLayoutManager(GalleryGridFragment fragment) {
        return new GridLayoutManager(fragment.getActivity(), GALLERY_COLUMNS);
    }

    @Provides
    GalleryGridAdapter provideAdapter(GalleryGridFragment fragment) {
        return new GalleryGridAdapter(new ArrayList<>());
    }

    @Provides
    FirebaseUploadCallback provideFirebaseHelper(GalleryGridFragment gridFragment) {
        return gridFragment.getViewModel();
    }

    @Provides
    FirebaseHelper provideAppFirebaseHelper(FirebaseUploadCallback callback) {
        return new AppFirebaseHelper(callback);
    }
}
