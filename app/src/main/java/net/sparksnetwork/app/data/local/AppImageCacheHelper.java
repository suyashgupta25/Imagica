package net.sparksnetwork.app.data.local;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

import net.sparksnetwork.app.utils.AppConstants;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import static net.sparksnetwork.app.utils.AppConstants.TOTAL_PERCENT;

public class AppImageCacheHelper implements ImageCacheHelper {

    private Context mContext;

    @Inject
    public AppImageCacheHelper(Context context) {
        this.mContext = context;
    }

    @Override
    public File saveImageToCache(Uri filePath, String fileName) {
        //File filesDir = context.getFilesDir();
        File newFilePath = new File(getLocalImagesDirectory(), fileName);
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(mContext.getContentResolver(), filePath);
            FileOutputStream fos = new FileOutputStream(newFilePath);
            bitmap.compress(Bitmap.CompressFormat.PNG, (int)TOTAL_PERCENT, fos);
            fos.close();
            return newFilePath;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getImagesCountFromCache() {
        File directory = getLocalImagesDirectory();
        if (directory.isDirectory()) {
            return directory.listFiles().length;
        }
        return 0;
    }

    @Override
    public List<File> getFilesList() {
        File directory = getLocalImagesDirectory();
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            return Arrays.asList(files);
        }
        return new ArrayList<>();
    }

    @Override
    public File getLocalImagesDirectory() {
        ContextWrapper wrapper = new ContextWrapper(mContext.getApplicationContext());
        File directory = wrapper.getDir(AppConstants.LOCAL_CACHE_DIR, Context.MODE_PRIVATE);
        if (!directory.exists()) {
            directory.mkdir();
        }
        return directory;
    }
}
