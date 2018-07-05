package com.example.taifa.last;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    DrawerLayout homeDrawer;
    TabLayout homeTablayout;
    ViewPager homeViewPager;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        homeDrawer = findViewById(R.id.homeDrawer);
        homeTablayout = findViewById(R.id.hometabview);
        homeViewPager = findViewById(R.id.homeViewPager);
        homeDrawer.openDrawer(Gravity.START);
        homeTablayout.setupWithViewPager(homeViewPager);
        navigationView = findViewById(R.id.navigation);

        HomeViewPagerAdapter adapter = new HomeViewPagerAdapter(getSupportFragmentManager());

        adapter.addItem(new TimetableFragment(),"Today");
        adapter.addItem(new NewsFragment(),"News");
        adapter.addItem(new NotificationFragment(),"Notification");
        adapter.addItem(new NoticeBoardFragment(),"Notice Board");

        homeViewPager.setAdapter(adapter);
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




                    default:
                    return false;


                }
            }
        });


    }
}
