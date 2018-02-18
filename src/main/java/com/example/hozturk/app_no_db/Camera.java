package com.example.hozturk.app_no_db;

import android.app.Activity;
import android.content.Intent;
import android.provider.MediaStore;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by H.Ozturk on 26.11.17.
 */

public class Camera extends Activity{
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final String JPEG_FILE_PREFIX = "IMG_";

    private void createFileName() throws IOException{
        String timestamp = new SimpleDateFormat("YYYYMMDD_HHMMSS").format(new Date());
        String imageName = JPEG_FILE_PREFIX + timestamp + "_";

    }

    private void dispatchTakePictureIntent(){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null){
            startAcitivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    private void startAcitivityForResult(Intent takePictureIntent, int requestImageCapture) {
    }

}