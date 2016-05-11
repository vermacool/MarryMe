package com.example.ukstechno.marryme;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class ViewDetails extends AppCompatActivity {
    private ImageView networkImageView;
    private TextView firstnameD;
    private TextView lastnameD;
    private TextView emailD;
    private TextView cityD;
    private TextView contactD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        findId();
        setDetails();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    private void findId(){
        firstnameD= (TextView) findViewById(R.id.firstnamedetails);
        lastnameD= (TextView) findViewById(R.id.lastnamedetals);
        networkImageView= (ImageView) findViewById(R.id.imagedetails);
        emailD= (TextView) findViewById(R.id.emaildetails);
        cityD= (TextView) findViewById(R.id.citydetails);
        contactD= (TextView) findViewById(R.id.contactdetails);
    }
    private void setDetails(){
        Intent intent=getIntent();
        String image=intent.getStringExtra("image");
        Bitmap bitmap=StringToBitMap(image);
       networkImageView.setImageBitmap(bitmap);


       // networkImageView.setImageResource(getIntent().getIntExtra("image", 00));
        String firstname=intent.getStringExtra("name");
        firstnameD.setText(firstname);
        Log.d("view",image);
    }
    public Bitmap StringToBitMap(String encodedString){
        try{
            byte [] encodeByte=Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap=BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        }catch(Exception e){
            e.getMessage();
            return null;
        }
    }
    public  int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }
}



