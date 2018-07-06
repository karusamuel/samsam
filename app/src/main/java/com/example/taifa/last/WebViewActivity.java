package com.example.taifa.last;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class WebViewActivity extends AppCompatActivity {
    WebView storyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        String url = getIntent().getStringExtra("url");
        storyView = findViewById(R.id.newsView);
        storyView.loadUrl(url);

    }
}
