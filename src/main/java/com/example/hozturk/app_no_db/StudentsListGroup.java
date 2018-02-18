package com.example.hozturk.app_no_db;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by H.Ozturk on 01.11.17.
 */

public class StudentsListGroup {
    public String string;
    public final List<String> children = new ArrayList<String>();

    public StudentsListGroup(String string) {
        this.string = string;
    }
}
