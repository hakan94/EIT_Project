package com.example.hozturk.app_no_db;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Dashboard extends Activity {

    String file = "ClassFile.txt";
    FileOutputStream fileOutputStream;
    FileInputStream fileInputStream;
    Button button, button1, button2, button3;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        Context context = getBaseContext();
        File classFile = new File(context.getFilesDir(), file);
//        if (classFile.exists()) {
//
//        }

        button = (Button) findViewById(R.id.classes);
        button1 = (Button) findViewById(R.id.events);
        button2 = (Button) findViewById(R.id.profile);
        button3 = (Button) findViewById(R.id.settings);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(Dashboard.this, ClassesList.class);
                startActivity(i);
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(Dashboard.this, ClassManagement.class);
                startActivity(i);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(Dashboard.this, StudentsList.class);
                startActivity(i);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(Dashboard.this, AddNewClass.class);
                startActivity(i);
            }
        });

    }
}
