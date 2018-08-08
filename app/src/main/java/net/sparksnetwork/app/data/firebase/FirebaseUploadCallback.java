package net.sparksnetwork.app.data.firebase;

import android.net.Uri;

public interface FirebaseUploadCallback {

    void onPicUploadSuccess(Uri uri, String picFullName);

    void onPicUploadFailure(String msg, Exception e);

    void onPicUploadProgress(double progress);

}
