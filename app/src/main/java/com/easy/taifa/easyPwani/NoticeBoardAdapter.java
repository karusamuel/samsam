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

import java.util.ArrayList;
import java.util.List;

public class NoticeBoardAdapter extends RecyclerView.Adapter<NoticeBoardAdapter.MyHolder> {
    Context context;
    int[] board = new int[]{R.drawable.boardone,R.drawable.board2,R.drawable.boardtree,R.drawable.boardfour,R.drawable.boardfive,R.drawable.boardsix,R.drawable.boardsven};
    String[] boardTime;
    List<Notice> list = new ArrayList<>();

    public NoticeBoardAdapter(Context context,List<Notice> list){



        this.list = list;
        this.context=context;
    }

    public  MyHolder onCreateViewHolder(ViewGroup parent,int type){
        LayoutInflater inflater = LayoutInflater.from(context);

     return  new MyHolder(inflater.inflate(R.layout.noticeboardstrip,parent,false));
    }

    public void onBindViewHolder(MyHolder holder, final int position){
        Glide.with(context).load(list.get(position).url).placeholder(R.drawable.sam).into(holder.noticeBoardImageView);
        holder.noticeTime.setText(list.get(position).date);
        holder.noticeBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context,FullNoticeActivity.class);
                intent.putExtra("id",list.get(position).url);
                context.startActivity(intent);
            }
        });


    }

    public int getItemCount(){

        return list.size();
    }



    class MyHolder extends RecyclerView.ViewHolder{
        CardView noticeBoard;
        ImageView noticeBoardImageView;
        TextView noticeTime;
        TextView description;

        public MyHolder(View view){
            super(view);
            noticeBoard=view.findViewById(R.id.noticeBoard);
            noticeBoardImageView = view.findViewById(R.id.noticeBoardImageView);
            noticeTime = view.findViewById(R.id.boardTime);
            description = view.findViewById(R.id.description);



        }

    }
}
