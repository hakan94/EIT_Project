package com.example.hozturk.app_no_db;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;

import static android.content.ContentValues.TAG;


public class ClassesList extends ListActivity {

    Button addClass;
    TextView className;

    private ProgressDialog progressDialog;
    JSONParser jsonParser = new JSONParser();
    ArrayList<HashMap<String, String>> classList;
    ArrayList<Classes> classesList;

    String file = "ClassFile.txt";
    FileOutputStream fileOutputStream;
    FileInputStream fileInputStream;
    OutputStreamWriter outputStreamWriter;
    InputStreamReader inputStreamReader;
    BufferedReader bufferedReader;
    FileReader fileReader;
    String line;

    private static String url_all_classes = "http://192.168.15.12/GetClasses.php";

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_CLASSES = "classes";
    private static final String TAG_CID = "cid";
    private static final String TAG_CLASSNAME = "classname";

    JSONArray classes = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classes_list);

        classList = new ArrayList<HashMap<String, String>>();

        readClasses();

        ListView listView = (ListView) getListView();
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String cid = ((TextView) view.findViewById(R.id.classes_button)).getText().toString();

                Intent i = new Intent(getApplicationContext(), ClassManagement.class);
                i.putExtra(TAG_CID, cid);
                startActivity(i);
            }
        });

        addClass = (Button) findViewById(R.id.addClass);
        addClass.setText("Add New Class");
        addClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), AddNewClass.class);
                startActivity(i);
            }
        });
    }

    public void readClasses() {
        Classes classes;
        try {
            classesList = new ArrayList<>();
            fileReader = new FileReader("/data/data/com.example.hozturk.app_no_db/files/ClassFile.txt");
            bufferedReader = new BufferedReader(fileReader);

            File file = File.createTempFile("ClassFile.txt", null, getCacheDir());
            if (!file.exists()) {
                Log.d(TAG, "No ClassFile.txt availabe! Create a new Class");
            } else {

                while ((line = bufferedReader.readLine()) != null) {
                    String[] textFile = line.split("/");

                    classes = new Classes();

                    classes.className = textFile[0];
                    classes.numberOfStudents = textFile[1];
                    classes.numberOfRows = textFile[2];

                    classesList.add(classes);
                }

                for (Classes classes1 : classesList) {
                    className = (TextView) findViewById(R.id.classname);
                    className.setText(classes1.className);
                }
                Log.d(TAG, "Done!");
            }
            fileReader.close();
            bufferedReader.close();


        } catch (Exception e) {
            Log.i("Tag", "No File found");
            e.printStackTrace();
        }
    }

}
