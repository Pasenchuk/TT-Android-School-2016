package com.ucsoftworks.fragmentsopening.extra;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import com.nupi.agent.R;
import com.nupi.agent.ui.dialogs.MessageBox;
import com.nupi.agent.utils.TimeUtils;

import java.io.File;

/**
 * Created by Pasenchuk Victor on 20.05.15
 */
public class CameraHandler {

    //TODO: check SD Card permission in Android 6+

    public static final String PHOTO_PATH = String.format("%s/DCIM/Nupi", Environment.getExternalStorageDirectory());

    public static final int REQUEST_IMAGE_CAPTURE = 45224;
    public static final int REQUEST_OPEN_IMAGE = 45225;

    private Activity activity;
    private Fragment fragment;
    private boolean callbackInFragment = false;

    public CameraHandler(Activity activity) {
        this.activity = activity;
    }

    public CameraHandler(Fragment fragment) {
        this.fragment = fragment;
        activity = fragment.getActivity();
        callbackInFragment = true;
    }

    public String openCamera(String folder_name) {
        String path = String.format("%s%s/%s.jpg", PHOTO_PATH, folder_name, TimeUtils.currentTimeInDbFormat());

        try {

            File file = new File(path);
            file.getParentFile().mkdirs();
            Uri outputFileUri = Uri.fromFile(file);
            Intent takePictureIntent = new Intent(
                    MediaStore.ACTION_IMAGE_CAPTURE);
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);


            if (takePictureIntent.resolveActivity(activity.getPackageManager()) != null) {
                if (callbackInFragment)
                    fragment.startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                else
                    activity.startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            } else
                MessageBox.show(activity.getString(R.string.no_camera), activity);

        } catch (Exception ignored) {
            MessageBox.show(activity.getString(R.string.no_sdcard_access), activity);
        }
        return path;
    }


    public void addToGallery(String path) {
        if (activity != null) {
            Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            File f = new File(path);
            Uri contentUri = Uri.fromFile(f);
            mediaScanIntent.setData(contentUri);
            activity.sendBroadcast(mediaScanIntent);
        }
    }
}
