package com.easy.taifa.easyPwani;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdView;


/**
 * A simple {@link Fragment} subclass.
 */
public class NoLessonFragmentTwo extends Fragment {
 AdView noLessonBanner;

    public NoLessonFragmentTwo() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
       View view=  inflater.inflate(R.layout.no_lesson_layout_two, container, false);

        noLessonBanner = view.findViewById(R.id.noLessonBanner);
//
//        AdRequest request = new AdRequest.Builder().build();
//
//        noLessonBanner.loadAd(request);
        return view;
    }

}
