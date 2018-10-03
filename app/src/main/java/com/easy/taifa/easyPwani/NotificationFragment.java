package com.easy.taifa.easyPwani;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class NotificationFragment extends Fragment {
    LinearLayoutManager manager;
    DatabaseReference myref;
    List<Not> list = new ArrayList<>();
    List<Not> listTemp = new ArrayList<>();
    NotificationAdapter adapter;

    RecyclerView myView;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        myref = FirebaseDatabase.getInstance().getReference("Notifications");

        list.clear();
        listTemp.clear();



        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list.clear();
                listTemp.clear();
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){

                    Not not = snapshot.getValue(Not.class);
                    listTemp.add(not);



                }


                int p = listTemp.size()-1;
                while (!listTemp.isEmpty()&&p>-1){
                    list.add(listTemp.get(p));
                    p--;
                    adapter.notifyDataSetChanged();

                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {


            }
        });

        adapter =  new NotificationAdapter(getContext(),list);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup parent,Bundle savedInstanceState){
      View view = inflater.inflate(R.layout.basicrecyclerview,parent,false);
      myView=view.findViewById(R.id.basicRecycler);
      myView.setLayoutManager(manager);

      myView.setAdapter(adapter);


        return view;
    }


}
