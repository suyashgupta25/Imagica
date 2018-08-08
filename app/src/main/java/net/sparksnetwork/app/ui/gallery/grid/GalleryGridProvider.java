package net.sparksnetwork.app.ui.gallery.grid;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class GalleryGridProvider {

    @ContributesAndroidInjector(modules = GalleryGridModule.class)
    abstract GalleryGridFragment provideGalleryGridFragmentFactory();
}
