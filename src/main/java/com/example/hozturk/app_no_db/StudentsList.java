package com.example.hozturk.app_no_db;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Toast;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.content.ContentValues.TAG;


public class StudentsList extends ListActivity {
    StudentListAdapter expandableListAdapter;
    ExpandableListView expandableListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    private ProgressDialog progressDialog;
    JSONParser jsonParser = new JSONParser();
    ArrayList<HashMap<String, String>> studentList;
    ArrayList<Students> studentsList;
    HashMap<String, List<Students>> studentsIDs;

    FileReader fileReader;
    BufferedReader bufferedReader;
    String line;

    float attendance[] = {};
    String attendanceStatus[] = {};
    Button sendEmail;

    private static String url_all_students = "http://192.168.15.12/GetStudents.php";

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_STUDENTS = "students";
    private static final String TAG_SID = "sid";
    private static final String TAG_STUDENTNAME = "name";
    private static final String TAG_FIRSTNAME = "firstname";

    JSONArray students = null;

    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_list);

        expandableListView = (ExpandableListView) findViewById(android.R.id.list);
        prepareListData();
        expandableListAdapter = new StudentListAdapter (this, listDataHeader, listDataChild);
        expandableListView.setAdapter(expandableListAdapter);

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View view, int position, long id) {
                return false;
            }
        });

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                onGroupExpand(groupPosition);
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View view, int groupPosition,
                                        int childPosition, long id) {
                Toast.makeText(getApplicationContext(), listDataHeader.get(groupPosition) + ": " +
                        listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition),
                        Toast.LENGTH_SHORT);
                return false;
            }
        });

//        setupPieChart();

        sendEmail = (Button) findViewById(R.id.Send_Mail);
//        sendEmail.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//                Intent intent = new Intent(Intent.ACTION_SENDTO);
//                intent.setType("*/*");
//                intent.putExtra(Intent.EXTRA_EMAIL, );
//                intent.putExtra(Intent.EXTRA_SUBJECT, );
//                intent.putExtra(Intent.EXTRA_STREAM);
//            }
//        });

    }

    private void prepareListData() {
        studentList = new ArrayList<>();
        studentsIDs = new HashMap<>();
        try {
            fileReader = new FileReader("/Users/H.Ozturk/Documents/University/Abroad_Semester/Software_Project/students.rtf");
            bufferedReader = new BufferedReader(fileReader);

            File file = new File("students.rtf");
            if (!file.exists()){
                Log.d(TAG, "File not available!");
            }else {

                while ((line = bufferedReader.readLine()) != null) {
                    String[] textFile = line.split(",");
                    String[] textfile = line.split("\n");

                    Students students = new Students();

                    students.prename = textfile[0];
                    students.surname = textfile[1];
                    students.studentID = textfile[2];

                    studentsList.add(students);
                }

                for (Students students: studentsList) {

                }
                Log.d(TAG, "Done!");
            }
            fileReader.close();
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

//    private void setupPieChart() {
//        List<PieEntry> pieEntries = new ArrayList<>();
//        for (int i = 0; i < attendance.length; i++){
//            pieEntries.add(new PieEntry(attendance[i], attendanceStatus[i]));
//        }
//
//        PieDataSet pieDataSet = new PieDataSet(pieEntries, "Attendance Status");
//        pieDataSet.setColors(ColorTemplate.createColors(new int[]{GREEN, YELLOW, RED}));
//        PieData pieData = new PieData(pieDataSet);
//
//        PieChart pieChart = (PieChart) findViewById(R.id.pieChart);
//        pieChart.setData(pieData);
//        pieChart.animateY(1000);
//        pieChart.invalidate();
//    }

//    class LoadStudents extends AsyncTask<String, String, String>{
//
//        protected void onPreExecute(){
//            super.onPreExecute();
//            progressDialog = new ProgressDialog(StudentsList.this);
//            progressDialog.setMessage("Loading students. Please wait..");
//            progressDialog.setIndeterminate(false);
//            progressDialog.setCancelable(false);
//            progressDialog.show();
//        }
//
//        protected String doInBackground(String... args) {
//            List<NameValuePair> params = new ArrayList<NameValuePair>();
//            JSONObject json = jsonParser.makeHttpRequest(url_all_students, "GET", params);
//            Log.d("All Students: ", json.toString());
//
//            try {
//                int success = json.getInt(TAG_SUCCESS);
//
//                if (success == 1){
//                    students = json.getJSONArray(TAG_STUDENTS);
//
//                    for (int i = 0; i < students.length(); i++){
//                        JSONObject jsonObject = students.getJSONObject(i);
//                        StudentsListGroup group = new StudentsListGroup("Students List");
//
//
//                        String sid = jsonObject.getString(TAG_SID);
//                        String name = jsonObject.getString(TAG_STUDENTNAME);
//                        String firstname = jsonObject.getString(TAG_FIRSTNAME);
//
//                        HashMap<String, String> map = new HashMap<String, String>();
//                        map.put(TAG_SID, sid);
//                        map.put(TAG_STUDENTNAME, name);
//                        map.put(TAG_FIRSTNAME, firstname);
//                        studentList.add(map);
//
//                        group.children.add("Student: " + sid + " " + name + " " + firstname);
//
//                    }
//                } else{
//                    Intent i = new Intent(getApplicationContext(), ClassManagement.class);
//                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    startActivity(i);
//                }
//            }catch (JSONException e){
//                e.printStackTrace();
//            }
//            return null;
//        }
//
//        protected void onPostExecute (String file_url){
//            progressDialog.dismiss();
//            runOnUiThread(new Runnable() {
//                public void run() {
//                    ListAdapter adapter = new SimpleAdapter(
//                            StudentsList.this, studentList, R.layout.student_list_item, new String[]{
//                                    TAG_SID, TAG_STUDENTNAME, TAG_FIRSTNAME}, new int[]{R.id.studentID,
//                            R.id.studentLastName, R.id.studentFirstName});
//                    setListAdapter(adapter);
//                }
//            });
//        }
//
//    }
}
