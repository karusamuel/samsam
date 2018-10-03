package com.easy.taifa.easyPwani;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    SharedPreferences myPref;
    DrawerLayout homeDrawer;
    TabLayout homeTablayout;
    ViewPager homeViewPager;
    NavigationView navigationView;
    ArrayList<TimetableContract> list;
    TimetableDatabaseHelper helper;
    HomeViewPagerAdapter adapter;
    TimetableFragment timetableFragment;
    String course;
    String year;
    ArrayList<TimetableContract> newList;
    Calendar c;
    int dayOfWeek;
    Toolbar toolbar;
    FirebaseAnalytics firebaseAnalytics;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;
    AlertDialog dialog=null;
    Boolean timeTableNotAvailable = false;
    AdView mainActivityAddView;
    int position =0;


    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//

//        myPref = getSharedPreferences("version",MODE_PRIVATE);

        firebaseAnalytics = FirebaseAnalytics.getInstance(this);

            firebaseDatabase = FirebaseDatabase.getInstance();

//        myRef =firebaseDatabase.getReference("Timetables");


        setContentView(R.layout.activity_main);
        homeDrawer = findViewById(R.id.homeDrawer);
        homeTablayout = findViewById(R.id  .hometabview);
        homeViewPager = findViewById(R.id.homeViewPager);
        homeDrawer.openDrawer(Gravity.START);
        homeTablayout.setupWithViewPager(homeViewPager);
        navigationView = findViewById(R.id.navigation);
        mainActivityAddView = findViewById(R.id.noLessonBanner);
        c = Calendar.getInstance();
        dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        newList = new ArrayList<>();
        list = new ArrayList<>();
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.last);
//        AdRequest request = new AdRequest.Builder().build();
//
//        mainActivityAddView.loadAd(request);






        }




    public void goOn() {
        adapter = new HomeViewPagerAdapter(getSupportFragmentManager());
        timetableFragment = new TimetableFragment();
        helper = new TimetableDatabaseHelper(this);
        list = helper.fetchData();

        if (list.isEmpty()) {
            goOn2();

        } else {
            populate();





        }
    }

    public void populate() {
        list = helper.fetchData();
        newList.clear();

            int i = 0;
        while (i < list.size()) {
            String weekday = new DateFormatSymbols().getWeekdays()[dayOfWeek];

            Toast.makeText(this,weekday,Toast.LENGTH_LONG).show();
            if (list.get(i).getUnitDay().equals(weekday)) {



                newList.add(list.get(i));
            }

            i++;


        }
        final Bundle bundle = new Bundle();
        bundle.putSerializable("list", newList);

        timetableFragment.setArguments(bundle);

        if(newList.isEmpty()){
            adapter.addItem(new NoLessonFragment(), "Today");

        }else{
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
                        if(myPref.getInt("version", 0) == 0){
//                            noOfflineTimeTable();
                        }else {
                            startActivity(new Intent(getApplicationContext(), AccountActivity.class));
                            return true;
                        }
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


        homeViewPager.setCurrentItem(position);


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


//    public void courseCheck(){
//        if (getSharedPreferences("Course",MODE_PRIVATE).getString("course",null) != null) {
//            populate();
//
//        } else {
//            selectCourse();
//
//
//        }
//    }
//    public void selectCourse(){
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setCancelable(false);
//        builder.setTitle("Select Course");
//        builder.setMessage("You Need to Select a Course to Continue");
//        builder.setIcon(R.drawable.about);
//        builder.setPositiveButton("Select Course", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                startActivity(new Intent(getApplicationContext(), AccountActivity.class));
//            }
//        });
//
//        builder.create().show();
//    }
    public void onBackPressed(){


        if(homeDrawer.isDrawerOpen(Gravity.START)){
            homeDrawer.closeDrawer(Gravity.START);

        }else{
            super.onBackPressed();
        }

        if(dialog!=null){
            dialog.setCancelable(true);
           finish();

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



//    public void noTimetable (final TimetableVersionCheck  check){
//
//        AlertDialog.Builder builder= new AlertDialog.Builder(this);
//        builder.setIcon(R.drawable.about);
//        builder.setTitle("New Version");
//        builder.setMessage("An update of The TimeTable is Available");
//        builder.setPositiveButton("update", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                Intent intent = new Intent(getApplicationContext(),DownloadActivity.class);
//                intent.putExtra("link",check.link);
//                intent.putExtra("version",check.version);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);
//
//            }
//        });
//        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//
//            }
//        });
//
//       AlertDialog alertDialog = builder.create();
//       alertDialog.setCancelable(false);
//       alertDialog.show();
//
//
//    }

//    public void newUser(final TimetableVersionCheck  check){
//        AlertDialog.Builder builder= new AlertDialog.Builder(this);
//        builder.setIcon(R.drawable.about);
//        builder.setTitle("New User");
//        builder.setMessage("Welcome Press OK to download the Timetable");
//        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                Intent intent = new Intent(getApplicationContext(),DownloadActivity.class);
//                intent.putExtra("link",check.link);
//                intent.putExtra("version",check.version);
//
//                startActivity(intent);
//
//            }
//        });
//
//
//        AlertDialog alertDialog = builder.create();
//        alertDialog.setCancelable(false);
//        alertDialog.show();
//
//
//
//    }
    public void onResume() {
        super.onResume();

        goOn();












//        course= getSharedPreferences("Course",MODE_PRIVATE).getString("course",null);
//        year = getSharedPreferences("Course",MODE_PRIVATE).getString("year",null);
//
//
//        ConnectivityManager conn = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo info = conn.getActiveNetworkInfo();
//        if (myPref.getInt("version", 0) == 0) {
//
//            if (info == null) {
//
//                AlertDialog.Builder builder = new AlertDialog.Builder(this);
//                builder.setIcon(R.drawable.about);
//                builder.setTitle("First Time Setup");
//                builder.setMessage("An internet connection is Required For firstTime Setup");
//                builder.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        onResume();
//
//
//                    }
//                });
//
//                AlertDialog alertDialog = builder.create();
//                alertDialog.setCancelable(false);
//                alertDialog.show();
//
//                dialog = alertDialog;
//
//
//
//            }else{
//
//                versionCheck();
//            }
//
//
//
//        }else{
//            versionCheck();
//        }
//
//        list.clear();
    }

//    public void versionCheck(){
//        homeDrawer.closeDrawer(Gravity.START);
//
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                if (dataSnapshot.hasChildren()) {
//                    TimetableVersionCheck versionCheck = null;
//                    for (DataSnapshot data : dataSnapshot.getChildren()) {
//                        data.getValue();
//                        versionCheck = data.getValue(TimetableVersionCheck.class);
//                        findViewById(R.id.hideProgressBar).setVisibility(View.GONE);
//                        findViewById(R.id.hideTextview).setVisibility(View.GONE);
//
//                    }
//
//                    if(versionCheck.version==1){
//                        timeTableNotAvailable=true;
//                        goOn2();
//
//                    } else if (versionCheck != null) {
//                        if (versionCheck.version > myPref.getInt("version", 0)) {
//
//                            noTimetable(versionCheck);
//
//                        } else {
//                            goOn();
//                        }
//
//                    }
//
//                }
//            }
//
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//    }

//    public void noOfflineTimeTable(){
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setIcon(R.drawable.about);
//        builder.setTitle("No Timetable");
//        builder.setMessage("Please Download The Timetable First");
//        AlertDialog alertDialog = builder.create();
//        alertDialog.show();
//
//
//    }
 public void goOn2(){
        adapter = new HomeViewPagerAdapter(getSupportFragmentManager());
        adapter.addItem(new NoTimeTableFragment() , "Today");
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
                        if(myPref.getInt("version", 0) == 0){
//                            noOfflineTimeTable();
                            return true;
                        }else {
//                            startActivity(new Intent(getApplicationContext(), AccountActivity.class));
                            return true;
                        }
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

    public void onPause(){
        super.onPause();
        position=homeViewPager.getCurrentItem();
    }


}
