package com.easy.taifa.easyPwani;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

public class AddTimeTableActivity extends AppCompatActivity {
    String day = null;
    String venue = null;
    String unitName = null;
    String unitCode = null;
    String unitTime = null;
    String lectureName = "Lecture Name N/A";
    Spinner daySpinner,timeSpinner;
    TextInputLayout  textVenue,textName,textCode,textLectureName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.no_timetable_scrollable);

        daySpinner=findViewById(R.id.spinnerDay);
        timeSpinner=findViewById(R.id.spinnerTime);
        textName = findViewById(R.id.textInputLayoutUnitName);
        textVenue = findViewById(R.id.textInputLayoutVenue);
        textCode = findViewById(R.id.textInputLayoutUnitCode);
        textLectureName = findViewById(R.id.textInputLayoutLecture);




    }

    public void save(View view){
        day = daySpinner.getSelectedItem().toString();
        unitTime = timeSpinner.getSelectedItem().toString();
        lectureName = textLectureName.getEditText().getText().toString();
        venue = textVenue.getEditText().getText().toString();
        unitCode = textCode.getEditText().getText().toString();
        unitName = textName.getEditText().getText().toString();





        if(day==null||venue==null||unitName==null||unitCode==null){


        }else{
           TimetableDatabaseHelper helper = new TimetableDatabaseHelper(this);

           helper.insertData(new TimetableContract(unitName,unitCode,venue,unitTime,lectureName,day));


        }




    }




}
