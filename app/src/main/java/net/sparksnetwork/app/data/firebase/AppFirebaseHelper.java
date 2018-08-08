package net.sparksnetwork.app.data.firebase;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.webkit.MimeTypeMap;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import net.sparksnetwork.app.utils.AppConstants;

import java.util.UUID;

import javax.inject.Inject;

import static net.sparksnetwork.app.utils.AppConstants.DOT;
import static net.sparksnetwork.app.utils.AppConstants.FIREBASE_FOLDER_NAME;
import static net.sparksnetwork.app.utils.AppConstants.TOTAL_PERCENT;

public class AppFirebaseHelper implements FirebaseHelper {

    private FirebaseUploadCallback callback;

    @Inject
    public AppFirebaseHelper(FirebaseUploadCallback callback) {//remove it
        this.callback = callback;
    }

    @Override
    public void uploadImage(Uri filePath) {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageReference = storage.getReference();
        String fileExtension = MimeTypeMap.getFileExtensionFromUrl(filePath.toString());
        String name = UUID.randomUUID().toString();
        String fullName = name + DOT + fileExtension;

        StorageReference ref = storageReference.child(FIREBASE_FOLDER_NAME + fullName);
        ref.putFile(filePath)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        callback.onPicUploadSuccess(filePath, fullName);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        callback.onPicUploadFailure(e.getMessage(), e);
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        double progress = (TOTAL_PERCENT * taskSnapshot.getBytesTransferred() / taskSnapshot
                                .getTotalByteCount());
                        callback.onPicUploadProgress(progress);
                    }
                });
    }
}
