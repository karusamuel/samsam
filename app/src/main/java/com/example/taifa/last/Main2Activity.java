package com.example.taifa.last;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Switch;
import android.widget.TableLayout;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    ViewPager timeTablePager;
    TabLayout tabLayout;
    ArrayList<TimetableContract> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        timeTablePager = findViewById(R.id.timeTableViewPager);
        tabLayout = findViewById(R.id.timeTableTablayout);


        tabLayout.setupWithViewPager(timeTablePager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        TimetablePagerAdapter adapter = new TimetablePagerAdapter(getSupportFragmentManager());

        TimetableDatabaseHelper myDb = new TimetableDatabaseHelper(this);
        list = myDb.fetchData();

        Bundle mondayBundle = new Bundle();
        Bundle tuesdayBundle = new Bundle();
        Bundle wednesdayBundle = new Bundle();
        Bundle thursdayBundle = new Bundle();
        Bundle fridayBundle = new Bundle();

        ArrayList<TimetableContract> mondayList = new ArrayList<>();
        ArrayList<TimetableContract> tuesdayList = new ArrayList<>();
        ArrayList<TimetableContract> wednesdayList = new ArrayList<>();
        ArrayList<TimetableContract> thursdayList = new ArrayList<>();
        ArrayList<TimetableContract> fridayList = new ArrayList<>();

        for(int i=0;i<list.size();i++) {
            switch (list.get(i).getUnitDay()) {

                case "Monday":
                    mondayList.add(list.get(i));

                    break;
                case "Tuesday":
                    tuesdayList.add(list.get(i));

                    break;
                case "Wednesday":
                    wednesdayList.add(list.get(i));

                    break;
                case "Thursday":
                    thursdayList.add(list.get(i));

                    break;
                case "Friday":
                    fridayList.add(list.get(i));

                    break;


            }

        }



        mondayBundle.putSerializable("list",mondayList);
        tuesdayBundle.putSerializable("list",tuesdayList);
        wednesdayBundle.putSerializable("list",wednesdayList);
        thursdayBundle.putSerializable("list",thursdayList);
        fridayBundle.putSerializable("list",fridayList);

        TimetableFragment monday = new TimetableFragment();
        monday.setArguments(mondayBundle);
        TimetableFragment tuesday = new TimetableFragment();
        tuesday.setArguments(tuesdayBundle);
        TimetableFragment wednesday = new TimetableFragment();
        wednesday.setArguments(wednesdayBundle);
        TimetableFragment thursday = new TimetableFragment();
        thursday.setArguments(thursdayBundle);
        TimetableFragment friday = new TimetableFragment();
        friday.setArguments(fridayBundle);






        adapter.addItem(monday,"Monday");
        adapter.addItem(tuesday,"Tuesday");
        adapter.addItem(wednesday,"Wednesday");
        adapter.addItem(thursday,"Thursday");
        adapter.addItem(friday,"Friday");

        timeTablePager.setAdapter(adapter);


    }





    public  void onStart(){
        super.onStart();

    }
}
