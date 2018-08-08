package net.sparksnetwork.app.ui.gallery.grid;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import net.sparksnetwork.app.data.DataManager;
import net.sparksnetwork.app.data.firebase.FirebaseUploadCallback;
import net.sparksnetwork.app.ui.base.BaseViewModel;
import net.sparksnetwork.app.utils.rx.SchedulerProvider;

import java.io.File;
import java.util.List;

public class GalleryGridViewModel extends BaseViewModel<GalleryGridNavigator> implements FirebaseUploadCallback {

    public final ObservableBoolean isGalleryEmpty = new ObservableBoolean(false);

    public final ObservableField<File> addImageToGallery = new ObservableField<File>();

    public FragmentActivity fragmentActivity;

    public GalleryGridViewModel(SchedulerProvider mSchedulerProvider, DataManager mDataManager) {
        super(mSchedulerProvider, mDataManager);
    }

    public void onCameraIconClick() {
        getNavigator().openPictureEditing();
    }

    public List<File> getFilesList() {
        return getDataManager().getFilesList();
    }

    @Override
    public void onPicUploadSuccess(Uri filePath, String picFullName) {
        setIsLoading(false);
        isGalleryEmpty.set(false);
        File savedFilePath = getDataManager().saveImageToCache(filePath, picFullName);
        if (savedFilePath != null) getNavigator().addImageToGallery(savedFilePath);
        Log.d("GalleryGridViewModel", "onPicUploadSuccess");
    }

    @Override
    public void onPicUploadFailure(String msg, Exception e) {
        setIsLoading(false);
        Log.d("GalleryGridViewModel", "onPicUploadFailure");
        getNavigator().showErrorMsg(msg);
    }

    @Override
    public void onPicUploadProgress(double progress) {
        setIsLoading(true);
        Log.d("GalleryGridViewModel", "onPicUploadProgress");
    }

}
