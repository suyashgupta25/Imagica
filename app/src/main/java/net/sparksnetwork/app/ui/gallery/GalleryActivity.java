package net.sparksnetwork.app.ui.gallery;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import net.sparksnetwork.app.BR;
import net.sparksnetwork.app.R;
import net.sparksnetwork.app.databinding.ActivityGalleryBinding;
import net.sparksnetwork.app.ui.base.BaseActivity;
import net.sparksnetwork.app.ui.gallery.grid.GalleryGridFragment;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class GalleryActivity extends BaseActivity<ActivityGalleryBinding, GalleryViewModel>
        implements HasSupportFragmentInjector, GalleryNavigator {

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;
    @Inject
    GalleryViewModel galleryViewModel;
    private ActivityGalleryBinding activityGalleryBinding;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_gallery;
    }

    @Override
    public GalleryViewModel getViewModel() {
        return galleryViewModel;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityGalleryBinding = getViewDataBinding();
        galleryViewModel.setNavigator(this);
        setup();
    }

    private void setup() {
        GalleryGridFragment galleryGridFragment = GalleryGridFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(activityGalleryBinding.flGalleryContent.getId(),
                        galleryGridFragment, GalleryGridFragment.class.getSimpleName())
                .commit();
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }
}
