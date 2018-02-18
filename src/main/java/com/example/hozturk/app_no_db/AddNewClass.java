package com.example.hozturk.app_no_db;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by H.Ozturk on 25.11.17.
 */

public class AddNewClass extends Activity{

    private ProgressDialog progressDialog;
    JSONParser jsonParser = new JSONParser();
    EditText inputClassName;
    EditText inputNumberOfRows;
    EditText inputNumberOfStudents;

    String file = "ClassFile.txt";
    FileOutputStream fileOutputStream;
    FileInputStream fileInputStream;
    OutputStreamWriter outputStreamWriter;
    InputStreamReader inputStreamReader;
    Context context;

    private static String url_create_class = "http://192.168.15.12/AddNewClass.php";

    private static final String TAG_SUCCESS = "success";

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_class);

        inputClassName = (EditText) findViewById(R.id.classNameInput);
        inputNumberOfRows = (EditText) findViewById(R.id.numberOfRowsInput);
        inputNumberOfStudents = (EditText) findViewById(R.id.numberOfStudentsInput);

        Button createClass = (Button) findViewById(R.id.addNewClass);
        createClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               writeClass();
            }
        });
    }

    private void writeClass() {
        try{

            fileOutputStream = openFileOutput(file, MODE_PRIVATE);
            outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            outputStreamWriter.append(inputClassName.getText().toString() + "/");
            outputStreamWriter.append(inputNumberOfRows.getText().toString() + "/");
            outputStreamWriter.append(inputNumberOfStudents.getText().toString() + ";");
            outputStreamWriter.close();

            Toast.makeText(getBaseContext(), "Class Saved successfully!", Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

