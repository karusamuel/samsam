package com.example.taifa.last;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class FullNoticeActivity extends AppCompatActivity {
    ImageView fullNotice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_notice);
        Intent intent  = getIntent();
        fullNotice = findViewById(R.id.fullNotice);
        fullNotice.setImageResource(intent.getIntExtra("id",R.drawable.ic_launcher_foreground));



    }
}
