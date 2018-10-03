package com.easy.taifa.easyPwani;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
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

public class NoticeBoardFragment extends Fragment {
    GridLayoutManager manager;
    DatabaseReference myref;
    List<Notice>  list = new ArrayList<>();
    List<Notice>  listTemp = new ArrayList<>();

    NoticeBoardAdapter adapter;

    RecyclerView myView;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        manager = new GridLayoutManager(getContext(),2);
        manager.setOrientation(GridLayoutManager.VERTICAL);
        myref = FirebaseDatabase.getInstance().getReference("NoticeBoard");
       adapter = new NoticeBoardAdapter(getContext(),list);
       list.clear();
       listTemp.clear();

        myref.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listTemp.clear();
                list.clear();

                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    Notice notice = snapshot.getValue(Notice.class);
                    listTemp.add(notice);





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

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup parent,Bundle savedInstanceState){
      View view = inflater.inflate(R.layout.basicrecyclerview,parent,false);
      myView=view.findViewById(R.id.basicRecycler);
      myView.setLayoutManager(manager);

      myView.setAdapter(adapter);


        return view;
    }


}
