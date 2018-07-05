package com.example.taifa.last;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;

public class HomeViewPagerAdapter extends FragmentStatePagerAdapter {
    ArrayList<Fragment> list;
    ArrayList<String> titleList;
    public HomeViewPagerAdapter(FragmentManager fm) {
        super(fm);
        list = new ArrayList<>();
        titleList = new ArrayList<>();


    }
    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }

    public Fragment getItem(int position){

        return list.get(position);

    }

    public int getCount(){
        return list.size();

    }

    public void addItem(Fragment fragment,String titleList){
        list.add(fragment);
        this.titleList.add(titleList);

    }


}
