package com.manifestsoftwarelab.machinetestdemovijay;

import android.os.Environment;

/**
 * Created by Admin on 1/19/2017.
 */

public class Constants {


    public static final int DATABASE_VERSION = 1;
    public static final String DATABSE_NAME = "Student.db";
    public static final String DATABSE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Student/";

    /* ================== Table details ======================= */

    public static final String TABLE_STUDENT_DETAILS = "STUDENT_DATA";

    public static final String COLUMN_STUDENT_ID = "STUDENT_ID";
    public static final String COLUMN_STUDENT_NAME = "STUDENT_NAME";
    public static final String COLUMN_COURSE_NAME = "COURSE_NAME";
    public static final String COLUMN_COMPANY_NAME = "COMPANY_NAME";

}
