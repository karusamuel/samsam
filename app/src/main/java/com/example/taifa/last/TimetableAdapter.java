package com.example.taifa.last;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class TimetableAdapter extends RecyclerView.Adapter<TimetableAdapter.MyHolder> {
    Context context;
    ArrayList<TimetableContract> list;
    public TimetableAdapter(Context context,ArrayList<TimetableContract> list){
        this.list=list;
        this.context=context;
    }

    public  MyHolder onCreateViewHolder(ViewGroup parent,int type){
        LayoutInflater inflater = LayoutInflater.from(context);

     return  new MyHolder(inflater.inflate(R.layout.timetablestrip,parent,false));
    }

    public void onBindViewHolder(MyHolder holder,int position){
        holder.unitCode.setText(list.get(position).unitCode);
        holder.unitName.setText(list.get(position).unitName);
        holder.lecturer.setText(list.get(position).lectureName);
        holder.unitVenue.setText(list.get(position).unitVenue);
        holder.time.setText(list.get(position).unitTime);


    }

    public int getItemCount(){

        return list.size();
    }



    class MyHolder extends RecyclerView.ViewHolder{
        TextView unitName ;
        TextView unitCode;
        TextView unitVenue;
        TextView time;
        TextView lecturer;

        public MyHolder(View view){
            super(view);
            unitName = view.findViewById(R.id.subjectNameTextView);
            unitCode = view.findViewById(R.id.unitCodeTextview);
            unitVenue = view.findViewById(R.id.unitVenue);
            time = view.findViewById(R.id.unitTime);
            lecturer = view.findViewById(R.id.lecturerName);



        }

    }
}
