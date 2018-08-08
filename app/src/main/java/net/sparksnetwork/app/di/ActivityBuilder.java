package net.sparksnetwork.app.di;

import net.sparksnetwork.app.ui.gallery.GalleryActivity;
import net.sparksnetwork.app.ui.gallery.GalleryModule;
import net.sparksnetwork.app.ui.gallery.grid.GalleryGridProvider;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = {
            GalleryModule.class,
            GalleryGridProvider.class})
    abstract GalleryActivity bindGalleryActivity();
}
