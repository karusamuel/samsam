package com.example.taifa.last;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AccountActivity extends AppCompatActivity {
    ArrayList<TimetableContract> list;
    String[] courses ;
    Spinner spinner;
    SharedPreferences preference;
    TextView  course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_activity);
        TimetableDatabaseHelper helper = new TimetableDatabaseHelper(this);
        preference= getSharedPreferences("Course",MODE_PRIVATE);
        spinner = findViewById(R.id.spinner);
        course = findViewById(R.id.course);

        list = helper.fetchCourses();
        courses = new String[list.size()];

        toArray(list);

        spinner.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,courses));

            course.setText(preference.getString("course","Select a Course"));






    }
    public void toArray(ArrayList<TimetableContract> list){
        int i =0;
        while (!list.isEmpty()) {
            courses[i] = list.remove(0).unitCourse;

            i++;
        };

    }
    public  void save (View view){

        SharedPreferences.Editor editor = preference.edit();
        editor.putString("course",spinner.getSelectedItem().toString());
        editor.commit();
        Toast.makeText(this,spinner.getSelectedItem().toString()+ "Selected",Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }
}
