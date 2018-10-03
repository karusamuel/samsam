package com.easy.taifa.easyPwani;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyHolder> {

    Context context;
    String[] headlines ;
    String[] shortStory;
    String[] time;
    String[] url;
    List<News> list;

    public NewsAdapter(Context context, List<News> list){
        this.context=context;
        this.list = list;

    }

    public  MyHolder onCreateViewHolder(ViewGroup parent,int type){
        LayoutInflater inflater = LayoutInflater.from(context);

     return  new MyHolder(inflater.inflate(R.layout.newsstrip,parent,false));
    }

    public void onBindViewHolder(MyHolder holder, final int position){
       Glide.with(context).load(list.get(position).imgUrl).placeholder(R.drawable.abstract_image).into(holder.newHealdlineImage);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,WebViewActivity.class);
                intent.putExtra("url",list.get(position).storyUrl);
                context.startActivity(intent);
            }
        });
        holder.headline.setText(list.get(position).heading);
        holder.story.setText(list.get(position).story);
        holder.date.setText(list.get(position).date);



    }




    public int getItemCount(){

        return list.size();
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
