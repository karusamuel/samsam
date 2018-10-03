package com.easy.taifa.easyPwani;

import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    ViewPager timeTablePager;
    TabLayout tabLayout;
    ArrayList<TimetableContract> list;
    TimetablePagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        timeTablePager = findViewById(R.id.timeTableViewPager);
        tabLayout = findViewById(R.id.timeTableTablayout);


        tabLayout.setupWithViewPager(timeTablePager);





        TimetableDatabaseHelper myDb = new TimetableDatabaseHelper(this);
//        String course = getSharedPreferences("Course", MODE_PRIVATE).getString("course", null);
//        String year = getSharedPreferences("Course", MODE_PRIVATE).getString("year", null);
        list = myDb.fetchData();
        if (list.isEmpty()) {
            noTimetable();

        } else {
            populate();


        }
    }
    public void populate(){
        adapter= new TimetablePagerAdapter(getSupportFragmentManager());
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





        if(mondayList.isEmpty()){
            adapter.addItem(new NoLessonFragment(), "Monday");

        }else {
            adapter.addItem(monday, "Monday");
        }
        if(tuesdayList.isEmpty()){
            adapter.addItem(new NoLessonFragment(), "Tuesday");

        }
        else {
            adapter.addItem(tuesday, "Tuesday");
        }
        if(wednesdayList.isEmpty()){
            adapter.addItem(new NoLessonFragment(), "Wednesday");

        }else {
            adapter.addItem(wednesday, "Wednesday");
        }
        if(thursdayList.isEmpty()){
            adapter.addItem(new NoLessonFragment(), "Thursday");

        }
        else {
        adapter.addItem(thursday,"Thursday");}
        if(fridayList.isEmpty()){
            adapter.addItem(new NoLessonFragment(), "Friday");

        }
        else {
        adapter.addItem(friday,"Friday");
        }

        timeTablePager.setAdapter(adapter);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.getTabAt(0).setIcon(R.drawable.monday);
        tabLayout.getTabAt(1).setIcon(R.drawable.tuesday);
        tabLayout.getTabAt(2).setIcon(R.drawable.wenesday);
        tabLayout.getTabAt(3).setIcon(R.drawable.tuesday);
        tabLayout.getTabAt(4).setIcon(R.drawable.friday);



    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menuSetting:
                startActivity(new Intent(this, Settings.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
public void noTimetable(){
   adapter = new TimetablePagerAdapter(getSupportFragmentManager());

   adapter.addItem(new NoTimeTableFragment(),"No Timetable");

   timeTablePager.setAdapter(adapter);

}





}
