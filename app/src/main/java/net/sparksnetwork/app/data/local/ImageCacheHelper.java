package net.sparksnetwork.app.data.local;

import android.content.Context;
import android.net.Uri;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface ImageCacheHelper {


    File saveImageToCache(Uri filePath, String fileName);

    int getImagesCountFromCache();

    List<File> getFilesList();

    File getLocalImagesDirectory();

}
