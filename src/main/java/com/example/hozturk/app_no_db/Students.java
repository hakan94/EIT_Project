package com.example.hozturk.app_no_db;

import android.media.Image;

/**
 * Created by H.Ozturk on 18.12.17.
 */

public class Students {
    String prename, surname, studentID;
    Image studentPicture;

    public Students(){}

    public Students(String prename, String surname, String studentID, Image studentPicture){
        this.prename = prename;
        this.surname = surname;
        this.studentID = studentID;
        this.studentPicture = studentPicture;
    }
}
