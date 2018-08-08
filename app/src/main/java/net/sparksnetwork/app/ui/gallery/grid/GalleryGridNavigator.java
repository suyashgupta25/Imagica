package net.sparksnetwork.app.ui.gallery.grid;

import java.io.File;

public interface GalleryGridNavigator {

    void openPictureEditing();

    void showErrorMsg(String message);

    void addImageToGallery(File savedFilePath);
}
