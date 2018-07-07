package com.example.taifa.last;

import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Toast;

public class WebViewActivity extends AppCompatActivity {
    WebView storyView;
    String url;
    NetworkInfo info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        url = getIntent().getStringExtra("url");
        storyView = findViewById(R.id.storyView);
        storyView.loadUrl(url);
        Toast.makeText(this,url,Toast.LENGTH_LONG).show();
        ConnectivityManager manager = (ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);
        info = manager.getActiveNetworkInfo();
        checkConnection();




    }
    public void checkConnection(){
        if(info!=null && info.isConnectedOrConnecting()){
            load();

        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setIcon(R.drawable.about);
            builder.setMessage("You need an Active Internet Connection");
            builder.setTitle("No Connection");
            builder.setPositiveButton("Try again", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    checkConnection();

                }
            });
            builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            builder.create().show();

        }
    }
    public void load(){
        storyView.loadUrl(url);

    }
}
