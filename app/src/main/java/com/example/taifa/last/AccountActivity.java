package com.example.taifa.last;

import android.content.SharedPreferences;
import android.preference.Preference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class AccountActivity extends AppCompatActivity {
    ArrayList<TimetableContract> list;
    String[] courses ;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_activity);
        TimetableDatabaseHelper helper = new TimetableDatabaseHelper(this);
        spinner = findViewById(R.id.spinner);

        list = helper.fetchCourses();
        courses = new String[list.size()];

        toArray(list);

        spinner.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,courses));



    }
    public void toArray(ArrayList<TimetableContract> list){
        int i =0;
        do{
            courses[i] = list.remove(i).unitCourse;

            i++;
        }while (!list.isEmpty());

    }
    public  void save (View view){
        SharedPreferences preference = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = preference.edit();
        editor.putString("course",spinner.getSelectedItem().toString());
        editor.commit();
        Toast.makeText(this,spinner.getSelectedItem().toString()+ "Selected",Toast.LENGTH_LONG).show();

    }
}
