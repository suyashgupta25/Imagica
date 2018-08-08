package net.sparksnetwork.app.ui.gallery.grid;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.Observable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.widget.Toast;

import net.sparksnetwork.app.BR;
import net.sparksnetwork.app.R;
import net.sparksnetwork.app.data.firebase.FirebaseHelper;
import net.sparksnetwork.app.databinding.FragmentGridGalleryBinding;
import net.sparksnetwork.app.ui.base.BaseFragment;
import net.sparksnetwork.app.utils.ui.cropper.CropImage;
import net.sparksnetwork.app.utils.ui.cropper.CropImageView;

import java.io.File;
import java.util.List;

import javax.inject.Inject;

import static android.app.Activity.RESULT_OK;

public class GalleryGridFragment extends BaseFragment<FragmentGridGalleryBinding, GalleryGridViewModel> implements GalleryGridNavigator {

    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    @Inject
    GridLayoutManager gridLayoutManager;

    @Inject
    GalleryGridAdapter galleryGridAdapter;

    @Inject
    FirebaseHelper firebaseHelper;

    private GalleryGridViewModel mGalleryGridViewModel;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_grid_gallery;
    }

    @Override
    public GalleryGridViewModel getViewModel() {
        mGalleryGridViewModel = ViewModelProviders.of(this, mViewModelFactory).get(GalleryGridViewModel.class);
        return mGalleryGridViewModel;
    }

    public static GalleryGridFragment newInstance() {
        GalleryGridFragment fragment = new GalleryGridFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGalleryGridViewModel.setNavigator(this);
        mGalleryGridViewModel.fragmentActivity = getActivity();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setup();
    }

    private void setup() {
        mGalleryGridViewModel.setIsLoading(false);
        List<File> filesList = mGalleryGridViewModel.getFilesList();
        if (filesList.isEmpty()) mGalleryGridViewModel.isGalleryEmpty.set(true);
        galleryGridAdapter.addAllItemsAndRefresh(filesList);
        getViewDataBinding().rvGridGallery.setLayoutManager(gridLayoutManager);
        getViewDataBinding().rvGridGallery.setItemAnimator(new DefaultItemAnimator());
        getViewDataBinding().rvGridGallery.setAdapter(galleryGridAdapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                //mGalleryGridViewModel.uploadPictureToFirebase(result.getUri());
                firebaseHelper.uploadImage(result.getUri());
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
                showErrorMsg(error.getMessage());
            }
        }
    }

    @Override
    public void addImageToGallery(File savedFilePath) {
        galleryGridAdapter.addItemAndRefresh(savedFilePath);

    }

    @Override
    public void showErrorMsg(String message) {
        Toast.makeText(getActivity(), message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void openPictureEditing() {
        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .start(getActivity(), this);
    }
}
