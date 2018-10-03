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

public class NoTimeTableFragment extends Fragment {
    AdView noTimeTableBanner;
    Button button;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup parent,Bundle savedInstanceState){
     View view = inflater.inflate(R.layout.no_timetable_yet,parent,false);

     noTimeTableBanner = view.findViewById(R.id.noLessonBanner);
     button = view.findViewById(R.id.add_timeTable);
     button.setOnClickListener(new View.OnClickListener(){
         @Override
         public void onClick(View v) {
             startActivity(new Intent(getContext(),AddTimeTableActivity.class));

         }
     });
//        AdRequest request = new AdRequest.Builder().build();
//      noTimeTableBanner.loadAd(request);
     return view;

    }
}
