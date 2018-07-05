package com.example.taifa.last;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class TimetableFragment extends Fragment {
    LinearLayoutManager manager;
    ArrayList<TimetableContract> list;

    RecyclerView myView;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        list =(ArrayList<TimetableContract>)getArguments().getSerializable("list");


    }

    public View onCreateView(LayoutInflater inflater, ViewGroup parent,Bundle savedInstanceState){
      View view = inflater.inflate(R.layout.basicrecyclerview,parent,false);
      myView=view.findViewById(R.id.basicRecycler);
      myView.setLayoutManager(manager);


      myView.setAdapter(new TimetableAdapter(getContext(),list));


        return view;
    }

}
