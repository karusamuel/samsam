package com.example.taifa.last;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NoticeBoardFragment extends Fragment {
    GridLayoutManager manager;

    RecyclerView myView;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        manager = new GridLayoutManager(getContext(),2);
        manager.setOrientation(GridLayoutManager.VERTICAL);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup parent,Bundle savedInstanceState){
      View view = inflater.inflate(R.layout.basicrecyclerview,parent,false);
      myView=view.findViewById(R.id.basicRecycler);
      myView.setLayoutManager(manager);

      myView.setAdapter(new NoticeBoardAdapter(getContext()));


        return view;
    }


}
