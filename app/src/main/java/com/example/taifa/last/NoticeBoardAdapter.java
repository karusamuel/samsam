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

public class NoticeBoardAdapter extends RecyclerView.Adapter<NoticeBoardAdapter.MyHolder> {
    Context context;
    int[] board = new int[]{};
    String[] boardTime = new String[]{};
    String[] boardDescription = new String[]{};
    public NoticeBoardAdapter(Context context){


        this.context=context;
    }

    public  MyHolder onCreateViewHolder(ViewGroup parent,int type){
        LayoutInflater inflater = LayoutInflater.from(context);

     return  new MyHolder(inflater.inflate(R.layout.noticeboardstrip,parent,false));
    }

    public void onBindViewHolder(MyHolder holder, final int position){
        holder.noticeBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,FullNoticeActivity.class);
                intent.putExtra("id", board[position]);
            }
        });


    }

    public int getItemCount(){

        return board.length;
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
