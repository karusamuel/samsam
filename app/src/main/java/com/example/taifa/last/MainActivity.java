package com.example.taifa.last;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    DrawerLayout homeDrawer;
    TabLayout homeTablayout;
    ViewPager homeViewPager;
    NavigationView navigationView;
    ArrayList<TimetableContract> list;
    TimetableDatabaseHelper helper;
    HomeViewPagerAdapter adapter;
    TimetableFragment timetableFragment;
    String course;
    ArrayList<TimetableContract> newList;
    Calendar c;
    int dayOfWeek;
    Toolbar toolbar;


    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        homeDrawer = findViewById(R.id.homeDrawer);
        homeTablayout = findViewById(R.id  .hometabview);
        homeViewPager = findViewById(R.id.homeViewPager);
        homeDrawer.openDrawer(Gravity.START);
        homeTablayout.setupWithViewPager(homeViewPager);
        navigationView = findViewById(R.id.navigation);
        course= getSharedPreferences("Course",MODE_PRIVATE).getString("course",null);
        c = Calendar.getInstance();
        dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        newList = new ArrayList<>();
        list = new ArrayList<>();
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.last);



        }




    public void goOn() {
        adapter = new HomeViewPagerAdapter(getSupportFragmentManager());
        timetableFragment = new TimetableFragment();
        helper = new TimetableDatabaseHelper(this);
        list = helper.fetchCourses();

        if (list.isEmpty()) {
            Toast.makeText(this,"Loading please Wait",Toast.LENGTH_LONG).show();
            @SuppressLint("StaticFieldLeak") AsyncTask<InputStream, Void, Void> task = new AsyncTask<InputStream, Void, Void>() {


                @Override
                protected Void doInBackground(InputStream... stream) {
                    homeViewPager.setVisibility(View.GONE);

                    new Parser(stream[0], getApplicationContext());

                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    courseCheck();
                    homeViewPager.setVisibility(View.VISIBLE);


                }

            };
            try {
                InputStream Stream = getAssets().open("timetable.xml");
                task.execute(Stream);
            } catch (IOException e) {
                Log.e("My Tag",e.getMessage());

            }
        } else {
            courseCheck();





        }
    }

    public void populate(){
        list = helper.fetchData(course);
       int i=0;
        while (i<list.size()){
            Log.e("ghfilkhlirhbg","vjhsevjfvjsebgvfjsvf");
            String weekday = new DateFormatSymbols().getWeekdays()[dayOfWeek];
            Log.e("ghfilkhlirhbg",weekday);
            if(list.get(i).getUnitDay().equals(weekday)){
                Log.e("Entered", "populate: ");

                newList.add(list.get(i));
            }

            i++;


        }
        final Bundle bundle = new Bundle();
        bundle.putSerializable("list",newList);

        timetableFragment.setArguments(bundle);
        if(newList.isEmpty()){
            adapter.addItem(new NoLessonFragment(), "Today");

        }else {
            adapter.addItem(timetableFragment, "Today");
        }
        adapter.addItem(new NewsFragment(),"News");
        adapter.addItem(new NotificationFragment(),"Notification");
        adapter.addItem(new NoticeBoardFragment(),"Notice Board");

        homeViewPager.setAdapter(adapter);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.timetable:

                        startActivity(new Intent(getApplicationContext(),Main2Activity.class));
                        return true;
                    case R.id.account:
                        startActivity(new Intent(getApplicationContext(),AccountActivity.class));
                        return true;
                    case R.id.settings:
                        startActivity(new Intent(getApplicationContext(),Settings.class));
                        return true;
                    case R.id.examTimetable:
                        notAvailable();

                        return true;
                    case R.id.about:
                        startActivity(new Intent(getApplicationContext(),About.class));
                        return true;

                    default:
                        return false;


                }
            }
        });
        homeTablayout.getTabAt(0).setIcon(R.drawable.calender);
        homeTablayout.getTabAt(1).setIcon(R.drawable.news2);
        homeTablayout.getTabAt(2).setIcon(R.drawable.notifcations2);
        homeTablayout.getTabAt(3).setIcon(R.drawable.board);





    }
    public void notAvailable(){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.about);
        builder.setTitle("Not Available");
        builder.setMessage("Exam Timetable is Only Available During Exam Period");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
         builder.create().show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        list.clear();
        goOn();
    }

    public void courseCheck(){
        if (course != null) {
            populate();

        } else {
            selectCourse();


        }
    }
    public void selectCourse(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("Select Course");
        builder.setMessage("You Need to Select a Course to Continue");
        builder.setIcon(R.drawable.about);
        builder.setPositiveButton("Select Course", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(new Intent(getApplicationContext(), AccountActivity.class));
            }
        });

        builder.create().show();
    }
    public void onBackPressed(){
        if(homeDrawer.isDrawerOpen(Gravity.START)){
            homeDrawer.closeDrawer(Gravity.START);

        }else{
            super.onBackPressed();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case android.R.id.home:
                if(!homeDrawer.isDrawerOpen(Gravity.START)){
                homeDrawer.openDrawer(Gravity.START,true);
                }else{
                    homeDrawer.closeDrawer(Gravity.START,true);
                }
                return true;
            case R.id.menuSetting:
                startActivity(new Intent(this,Settings.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
