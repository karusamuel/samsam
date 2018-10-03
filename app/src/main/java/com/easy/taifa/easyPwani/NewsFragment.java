package com.easy.taifa.easyPwani;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

public class NewsFragment extends Fragment {
    LinearLayoutManager manager;
    DatabaseReference reference;
    List<News> list = new ArrayList<>();
    List<News> listTemp = new ArrayList<>();
    NewsAdapter  adapter;

    RecyclerView myView;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        reference = FirebaseDatabase.getInstance().getReference("News");
        list.clear();
        listTemp.clear();
        adapter = new NewsAdapter(getContext(),list);

        reference.addValueEventListener(new ValueEventListener() {
            @Override


            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("Tag", "onCreate: " );
                list.clear();
                listTemp.clear();



                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    News news =snapshot.getValue(News.class);

                    listTemp.add(news);


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

                Log.e("TAG", "onCancelled: ");

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
