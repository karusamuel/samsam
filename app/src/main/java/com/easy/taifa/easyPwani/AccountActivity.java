package com.easy.taifa.easyPwani;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class AccountActivity extends AppCompatActivity {
    ArrayList<TimetableContract> list;
    ArrayList<TimetableContract> listYear;

    String[] courses ;
    Spinner courseSpinner;
    Spinner yearSpinner;

    SharedPreferences preference;
    String[] years;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_activity);
        TimetableDatabaseHelper helper = new TimetableDatabaseHelper(this);
        preference= getSharedPreferences("Course",MODE_PRIVATE);
        courseSpinner = findViewById(R.id.coursespinner);
        yearSpinner = findViewById(R.id.yearspinner);



//        list = helper.fetchCourses();
//        listYear = helper.fetchYears();
        courses = new String[list.size()];
        years = new String[listYear.size()];



        toArray(list);
        toArrayYear(listYear);

        courseSpinner.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,courses));
        yearSpinner.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,years));






        courseSpinner.setSelection(preference.getInt("coursePosition",0));
        yearSpinner.setSelection(preference.getInt("yearPosition",0));

    }
    public void toArray(ArrayList<TimetableContract> list){
        int i =0;
        while (!list.isEmpty()) {
            courses[i] = list.remove(0).unitCourse;
            i++;

        }

    }

    public void toArrayYear(ArrayList<TimetableContract> listYear){

        int i =0;

        while (!listYear.isEmpty()) {
            years[i] = listYear.remove(0).year;
            i++;

        }

    }




    public  void save (View view){

        SharedPreferences.Editor editor = preference.edit();
        editor.putString("course",courseSpinner.getSelectedItem().toString());
        editor.putInt("coursePosition",courseSpinner.getSelectedItemPosition());


        editor.putString("year",yearSpinner.getSelectedItem().toString());
        editor.putInt("yearPosition",yearSpinner.getSelectedItemPosition());
        editor.apply();
        Toast.makeText(this,courseSpinner.getSelectedItem().toString() + yearSpinner.getSelectedItem().toString()+ "Selected" ,Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

        finish();

    }
}
