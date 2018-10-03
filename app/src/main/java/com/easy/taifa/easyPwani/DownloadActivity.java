package com.easy.taifa.easyPwani;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class DownloadActivity extends AppCompatActivity {
    FirebaseStorage firebaseStorage;
    Button download;
    StorageReference storageReference;
    ProgressBar progressBar;
    AdView adview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dowload);
        firebaseStorage = FirebaseStorage.getInstance();
        download = findViewById(R.id.download);
        progressBar = findViewById(R.id.loader);

        adview = findViewById(R.id.noLessonBanner);

        AdRequest request = new AdRequest.Builder().build();
        adview.loadAd(request);




        storageReference =firebaseStorage.getReferenceFromUrl(getIntent().getStringExtra("link"));
        Log.e("TAG", "onCreate: started here" );


        
    }
    public void download(View view){

        ConnectivityManager conn =(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = conn.getActiveNetworkInfo();
        if(info==null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setIcon(R.drawable.about);
            builder.setTitle("No Internet");
            builder.setMessage("An internet connection is Required For firstTime setUp");
            builder.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    onStart();


                }
            });

            AlertDialog alertDialog = builder.create();
            alertDialog.setCancelable(false);
            alertDialog.show();
        }else {


            download.setClickable(false);
            download.setText("Downloading...");
            download.setBackgroundColor(Color.GRAY);
            progressBar.setVisibility(View.VISIBLE);

            try {
                final File localFile = File.createTempFile("timetable", "xml");

                storageReference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {

                        Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_LONG).show();
                        try {


                            InputStream is = new FileInputStream(localFile);


                            Toast.makeText(getApplicationContext(), "Loading please Wait", Toast.LENGTH_LONG).show();
                            @SuppressLint("StaticFieldLeak") AsyncTask<InputStream, Void, Void> task = new AsyncTask<InputStream, Void, Void>() {
                                @Override
                                protected void onPreExecute() {
                                    super.onPreExecute();


                                }

                                @Override
                                protected Void doInBackground(InputStream... stream) {


                                    new Parser(stream[0], getApplicationContext());

                                    return null;
                                }

                                @Override
                                protected void onPostExecute(Void aVoid) {
                                    super.onPostExecute(aVoid);
                                    Toast.makeText(getApplicationContext(), "Db updated", Toast.LENGTH_LONG).show();
                                    SharedPreferences myPref = getSharedPreferences("version", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = myPref.edit();

                                    editor.putInt("version", getIntent().getIntExtra("version", 0));

                                    editor.commit();
                                    Log.e("Tag", "onPostExecute: " + getIntent().getIntExtra("version", 0));
//                                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
//                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                                    getApplicationContext().startActivity(intent);
                                    finish();


                                }

                            };


                            task.execute(is);


                        } catch (FileNotFoundException e) {
                            Log.e("Tag", "onSuccess: File not Found");

                        }

                    }
                });


            } catch (IOException e) {


            }


        }
    }

    }

