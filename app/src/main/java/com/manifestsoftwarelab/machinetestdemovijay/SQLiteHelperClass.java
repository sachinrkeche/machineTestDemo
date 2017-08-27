package com.manifestsoftwarelab.machinetestdemovijay;

import android.content.ContentValues;
import android.content.Context;

import static com.manifestsoftwarelab.machinetestdemovijay.Constants.*;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Admin on 1/19/2017.
 */

public class SQLiteHelperClass extends SQLiteOpenHelper {


    public SQLiteHelperClass(Context context) {
        super(context, Constants.DATABSE_PATH + Constants.DATABSE_NAME, null, Constants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_STUDENT_DETAILS = "CREATE TABLE " + TABLE_STUDENT_DETAILS + " ("
                + COLUMN_STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_STUDENT_NAME + " TEXT, "
                + COLUMN_COMPANY_NAME + " TEXT, "
                + COLUMN_COURSE_NAME + " TEXT )";


        Log.d("onCreate ", "onCreate: " + CREATE_STUDENT_DETAILS);
        db.execSQL(CREATE_STUDENT_DETAILS);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertIntoTable(String stud_name, String course_name, String company_name) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valuess = new ContentValues();

        valuess.put(COLUMN_STUDENT_NAME, stud_name);
        valuess.put(COLUMN_COURSE_NAME, course_name);
        valuess.put(COLUMN_COMPANY_NAME, company_name);

        db.insert(TABLE_STUDENT_DETAILS, null, valuess);
        db.close();
        Log.e("***insertIntoTable***", "insertion successfully");
    }


    public ArrayList<StudentPojo> getAllTableDetails() {

        ArrayList<StudentPojo> list = new ArrayList<>();
        StudentPojo pojo = null;

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT  * FROM " + TABLE_STUDENT_DETAILS;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor != null) {
            Log.d("EMessanger..", "Reading Records");
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {

                String student_id = cursor.getString(cursor.getColumnIndex(COLUMN_STUDENT_ID));
                String student_name = cursor.getString(cursor.getColumnIndex(COLUMN_STUDENT_NAME));
                String company_name = cursor.getString(cursor.getColumnIndex(COLUMN_COMPANY_NAME));
                String course_name = cursor.getString(cursor.getColumnIndex(COLUMN_COURSE_NAME));

                pojo = new StudentPojo();

                pojo.setStudent_id(student_id);
                pojo.setStudent_name(student_name);
                pojo.setCompany_name(company_name);
                pojo.setCourse_name(course_name);

                list.add(pojo);


                cursor.moveToNext();

            }

        } else {
            Log.d("stuent..", "Cursor is null");
        }
        return list;
    }

}