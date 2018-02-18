package com.example.hozturk.app_no_db;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.QuickContactBadge;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

/**
 * Created by H.Ozturk on 26.11.17.
 */

public class StudentListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listDataChild;
    public LayoutInflater inflater;
    public Activity activity;
    public ImageView imageView;

    public StudentListAdapter(Context context, List<String> listDataHeader,
                              HashMap<String, List<String>> listDataChild) {
        this.context = context;
        this.listDataHeader = listDataHeader;
        this.listDataChild = listDataChild;
    }

    public Object getChild(int groupPosition, int childPosition){
        return this.listDataChild.get(this.listDataHeader.get(groupPosition)).get(childPosition);
    }

    public long getChildId(int groupPosition, int childPosition){
        return childPosition;
    }

    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild,
                             View convertView, ViewGroup parent){
        final String childern = (String) getChild(groupPosition, childPosition);
        EditText text, text2 = null;
        QuickContactBadge contactBadge;
        if (convertView == null){
            convertView = inflater.inflate(R.layout.student_list_item, null);
        }

        text = (EditText) convertView.findViewById(R.id.studentFirstName);
        text2 = (EditText) convertView.findViewById(R.id.studentLastName);
        text.setText(childern);
        text2.setText("Lastname");

        imageView = (ImageView) convertView.findViewById(R.id.quickContactBadge);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(activity, Camera.class);
                activity.startActivity(i);
            }
        });

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity, childern, Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }

    public int getChildrenCount(int groupPosition){
        return this.listDataChild.get(this.listDataHeader.get(groupPosition)).size();
    }

    public Object getGroup(int groupPosition){
        return this.listDataHeader.get(groupPosition);
    }

    public int getGroupCount() {
        return 5;
    }

    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.student_list_group, null);
        }

        TextView lblListHeader = (TextView) convertView.findViewById(R.id.students_list_group);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    public boolean hasStableIds() {
        return false;
    }

    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}

