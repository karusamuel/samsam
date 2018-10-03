package com.easy.taifa.easyPwani;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class TimetablePagerAdapter extends FragmentStatePagerAdapter {
    ArrayList<Fragment> list;
    ArrayList<String> titleList;
    public TimetablePagerAdapter(FragmentManager fm) {
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
