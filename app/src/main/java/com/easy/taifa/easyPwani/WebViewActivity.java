package com.easy.taifa.easyPwani;

import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

public class WebViewActivity extends AppCompatActivity{
    WebView storyView;
    String url;
    NetworkInfo info;
    ProgressBar hideProgressBar;
    TextView hideTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        hideProgressBar = findViewById(R.id.webViewHide);
        hideTextView = findViewById(R.id.textViewHide);
        url = getIntent().getStringExtra("url");
        storyView = findViewById(R.id.storyView);





        storyView.setWebViewClient(new WebViewClient(){

            public void onPageFinished(WebView webView,String url){
               hideProgressBar.setVisibility(View.GONE);
               hideTextView.setVisibility(View.GONE);

            }

        });

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
                    finish();

                }
            });
            builder.create().show();

        }
    }
    public void load(){
        storyView.loadUrl(url);




    }
}
