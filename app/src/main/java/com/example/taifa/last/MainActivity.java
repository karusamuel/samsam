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
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

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
        course= getPreferences(MODE_PRIVATE).getString("course",null);

        }



    public void goOn() {
        adapter = new HomeViewPagerAdapter(getSupportFragmentManager());
        timetableFragment = new TimetableFragment();
        helper = new TimetableDatabaseHelper(this);
        list = helper.fetchCourses();
        if (list.isEmpty()) {
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
                    populate();
                    homeViewPager.setVisibility(View.VISIBLE);


                }

            };
            try {
                InputStream Stream = getAssets().open("sam.xml");
                task.execute(Stream);
            } catch (IOException e) {

            }
        } else {


            if (course != null) {
                populate();

            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
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


        }
    }

    public void populate(){
        list = helper.fetchData(course);
        final Bundle bundle = new Bundle();
        bundle.putSerializable("list",list);

        timetableFragment.setArguments(bundle);

        adapter.addItem(timetableFragment,"Today");
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
        goOn();
        if(getSharedPreferences("Course",MODE_PRIVATE).contains("course")){
            Toast.makeText(this,"saaaaaaaaved",Toast.LENGTH_LONG).show();

        }
    }
}
