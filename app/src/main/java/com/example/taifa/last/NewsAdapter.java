package com.example.taifa.last;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyHolder> {
    Context context;
    int[] images;
    String[] headlines ;
    String[] shortStory;
    String[] time;
    String[] url;

    public NewsAdapter(Context context){
        this.context=context;
        headlines = context.getResources().getStringArray(R.array.HeadLines);
        shortStory = context.getResources().getStringArray(R.array.ShortContent);
        time = context.getResources().getStringArray(R.array.Dates);
        url = context.getResources().getStringArray(R.array.url);
    }

    public  MyHolder onCreateViewHolder(ViewGroup parent,int type){
        LayoutInflater inflater = LayoutInflater.from(context);

     return  new MyHolder(inflater.inflate(R.layout.newsstrip,parent,false));
    }

    public void onBindViewHolder(MyHolder holder, final int position){

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,WebViewActivity.class);
                intent.putExtra("url",url[position]);
                context.startActivity(intent);
            }
        });
        holder.headline.setText(headlines[position]);
        holder.story.setText(shortStory[position]);
        holder.date.setText(time[position]);



    }




    public int getItemCount(){

        return headlines.length;
    }



    class MyHolder extends RecyclerView.ViewHolder{
        TextView headline;
        TextView story;
        TextView date;
        ImageView newHealdlineImage;
        CardView cardView;

        public MyHolder(View view){
            super(view);
            headline = view.findViewById(R.id.newsHeadlineTextview);
            story =view.findViewById(R.id.shortNewsTextView);
            date = view.findViewById(R.id.newsTimeTextView);
            newHealdlineImage = view.findViewById(R.id.newsimageView);
            cardView = view.findViewById(R.id.newsView);


    }
}


}
