package com.easy.taifa.easyPwani;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyHolder> {
    Context context;

    int[] notificationImages = new int[]{};

    String[] notificationText ;
    List<Not> list;

    public NotificationAdapter(Context context, List<Not> list){
        this.list = list;


        this.context=context;
    }

    public  MyHolder onCreateViewHolder(ViewGroup parent,int type){
        LayoutInflater inflater = LayoutInflater.from(context);

     return  new MyHolder(inflater.inflate(R.layout.notificationstrip,parent,false));
    }

    public void onBindViewHolder(MyHolder holder,int position){
        holder.notificationTextView.setText(list.get(position).notification);


    }

    public int getItemCount(){

      return list.size();
    }



    class MyHolder extends RecyclerView.ViewHolder{
        ImageView notificationImageview;
        TextView notificationTextView;

        public MyHolder(View view){
            super(view);

            notificationImageview = view.findViewById(R.id.notificationImage);
            notificationImageview.setImageResource(R.drawable.notifications);
            notificationTextView = view.findViewById(R.id.notificationTextview);




        }

    }
}
