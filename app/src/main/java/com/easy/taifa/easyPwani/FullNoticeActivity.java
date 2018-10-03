package com.easy.taifa.easyPwani;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class FullNoticeActivity extends AppCompatActivity {
    ImageView fullNotice;
    AdView fullNoticeBanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_notice);
        Intent intent  = getIntent();
        fullNotice = findViewById(R.id.fullNotice);
//        fullNotice.setImageResource();
        Glide.with(this).load(intent.getStringExtra("id"))
                .placeholder(R.drawable.sam).into(fullNotice);

        fullNoticeBanner = findViewById(R.id.noLessonBanner);

        AdRequest request =new  AdRequest.Builder().build();

        fullNoticeBanner.loadAd(request);



    }
}
