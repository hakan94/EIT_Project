package com.example.hozturk.app_no_db;

/**
 * Created by H.Ozturk on 16.12.17.
 */

public class Classes {

    String className, numberOfStudents, numberOfRows;

    public Classes(){}

    public Classes (String className, String numberOfRows, String numberOfStudents){
        this.className = className;
        this.numberOfRows = numberOfRows;
        this.numberOfStudents = numberOfStudents;
    }

    public String toString(){
        return className + ";" + numberOfRows + ";" + numberOfStudents;
    }
}
