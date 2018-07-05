package com.example.taifa.last;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyHolder> {
    Context context;
    int[] images = new int[]{};
    String[] headlines = new String[]{};
    String[] shortStory = new String[] {};
    String[] time = new String[]{};

    public NewsAdapter(Context context){


        this.context=context;
    }

    public  MyHolder onCreateViewHolder(ViewGroup parent,int type){
        LayoutInflater inflater = LayoutInflater.from(context);

     return  new MyHolder(inflater.inflate(R.layout.newsstrip,parent,false));
    }

    public void onBindViewHolder(MyHolder holder,int position){


    }

    public int getItemCount(){

        return 20;
    }



    class MyHolder extends RecyclerView.ViewHolder{
        TextView headline;
        TextView story;
        TextView date;
        ImageView newHealdlineImage;

        public MyHolder(View view){
            super(view);
            headline = view.findViewById(R.id.newsHeadlineTextview);
            story =view.findViewById(R.id.shortNewsTextView);
            date = view.findViewById(R.id.newsTimeTextView);
            newHealdlineImage = view.findViewById(R.id.newsimageView);



        }

    }
}
