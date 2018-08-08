package net.sparksnetwork.app.ui.gallery.grid;

import android.databinding.ObservableField;

import java.io.File;

public class GalleryGridItemViewModel {

    public final ObservableField<File> mFile;

    public GalleryGridItemViewModel(File file) {
        mFile = new ObservableField<File>(file);
    }

}
