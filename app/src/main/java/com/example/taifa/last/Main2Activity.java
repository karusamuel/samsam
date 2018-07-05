package com.example.taifa.last;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;

public class Main2Activity extends AppCompatActivity {
    ViewPager timeTablePager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        timeTablePager = findViewById(R.id.timeTableViewPager);
        tabLayout = findViewById(R.id.timeTableTablayout);

        tabLayout.setupWithViewPager(timeTablePager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        TimetablePagerAdapter adapter = new TimetablePagerAdapter(getSupportFragmentManager());
        adapter.addItem(new TimetableFragment(),"Monday");
        adapter.addItem(new TimetableFragment(),"Tuesday");
        adapter.addItem(new TimetableFragment(),"Wednesday");
        adapter.addItem(new TimetableFragment(),"Thursday");
        adapter.addItem(new TimetableFragment(),"Friday");

        timeTablePager.setAdapter(adapter);


    }





    public  void onStart(){
        super.onStart();

    }
}
