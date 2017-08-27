package com.manifestsoftwarelab.machinetestdemovijay;


import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Button;
import android.widget.RelativeLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    SQLiteHelperClass helper;
    JSONObject jsonObject = null;

    private ProductListAdapter pAdapter;
    private List<StudentPojo> productList;

    private RecyclerView rv_productList;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper = new SQLiteHelperClass(this);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build();
        StrictMode.setThreadPolicy(policy);



        rv_productList = (RecyclerView) findViewById(R.id.id_recycleview_productList);
        rv_productList.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(MainActivity.this);
        rv_productList.setLayoutManager(layoutManager);
        //Initializing our superheroes list
        productList = new ArrayList<>();

        jsonParsing();
        getDataAndSetToList();
    }


    public String createJson() {

        JSONObject mainObj = new JSONObject();

        JSONObject job = new JSONObject();
        JSONArray jarr = new JSONArray();
        try {
            mainObj.put("company_name", "abc");
            mainObj.put("course_name", "xyz");

            job.put("student_id", "01");
            job.put("student_name", "VIJAY");
            jarr.put(job);


            JSONObject job2 = new JSONObject();
            job2.put("student_id", "02");
            job2.put("student_name", "sachin");

            jarr.put(job2);


            mainObj.put("students", jarr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.e("***", mainObj.toString());
        return mainObj.toString();
    }

    public void jsonParsing() {
        if (createJson() != null) {

            try {
                jsonObject = new JSONObject(createJson());
                JSONArray jsonArray = jsonObject.getJSONArray("students");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject studentsJsonObject = jsonArray.getJSONObject(i);
                    StudentPojo studentPojo = new StudentPojo();
                    studentPojo.setStudent_id(studentsJsonObject.getString("student_id"));
                    studentPojo.setStudent_name(studentsJsonObject.getString("student_name"));
                    studentPojo.setCompany_name(jsonObject.getString("company_name"));
                    studentPojo.setCourse_name(jsonObject.getString("course_name"));

                    helper.insertIntoTable(studentsJsonObject.getString("student_name"),
                            jsonObject.getString("course_name"),
                            jsonObject.getString("course_name"));
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }

   public void  getDataAndSetToList()
    {
        productList =  helper.getAllTableDetails();

        pAdapter = new ProductListAdapter(productList, MainActivity.this);
        rv_productList.setAdapter(pAdapter);
    }

}

