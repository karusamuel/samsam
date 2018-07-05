package com.example.taifa.last;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyHolder> {
    Context context;

    int[] notificationImages = new int[]{};

    String[] notificationText = new String[]{};

    public NotificationAdapter(Context context){


        this.context=context;
    }

    public  MyHolder onCreateViewHolder(ViewGroup parent,int type){
        LayoutInflater inflater = LayoutInflater.from(context);

     return  new MyHolder(inflater.inflate(R.layout.notificationstrip,parent,false));
    }

    public void onBindViewHolder(MyHolder holder,int position){


    }

    public int getItemCount(){

        return 20;
    }



    class MyHolder extends RecyclerView.ViewHolder{
        ImageView notificationImageview;
        TextView notificationTextView;

        public MyHolder(View view){
            super(view);

            notificationImageview = view.findViewById(R.id.notificationImage);
            notificationTextView = view.findViewById(R.id.notificationTextview);




        }

    }
}
