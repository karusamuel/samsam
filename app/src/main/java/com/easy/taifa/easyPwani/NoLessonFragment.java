package com.easy.taifa.easyPwani;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


/**
 * A simple {@link Fragment} subclass.
 */
public class NoLessonFragment extends Fragment {
 AdView noLessonBanner;
 Button button;

    public NoLessonFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
       View view=  inflater.inflate(R.layout.no_lesson_layout, container, false);

        noLessonBanner = view.findViewById(R.id.noLessonBanner);
        button = view.findViewById(R.id.add_timeTable2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),AddTimeTableActivity.class));

            }
        });

//
//        AdRequest request = new AdRequest.Builder().build();
//
//        noLessonBanner.loadAd(request);
        return view;
    }






}