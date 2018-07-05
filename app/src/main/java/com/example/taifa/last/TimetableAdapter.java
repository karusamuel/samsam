package com.example.taifa.last;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TimetableAdapter extends RecyclerView.Adapter<TimetableAdapter.MyHolder> {
    Context context;
    public TimetableAdapter(Context context){


        this.context=context;
    }

    public  MyHolder onCreateViewHolder(ViewGroup parent,int type){
        LayoutInflater inflater = LayoutInflater.from(context);

     return  new MyHolder(inflater.inflate(R.layout.timetablestrip,parent,false));
    }

    public void onBindViewHolder(MyHolder holder,int position){


    }

    public int getItemCount(){

        return 20;
    }



    class MyHolder extends RecyclerView.ViewHolder{

        public MyHolder(View view){
            super(view);



        }

    }
}
