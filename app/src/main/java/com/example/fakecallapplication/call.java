package com.example.fakecallapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class call extends AppCompatActivity {
    @SuppressLint({ "WrongThread"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_call);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        Intent i= new Intent(call.this,NewService.class);
        startService(i);

        ImageView ic = findViewById(R.id.pick);
        ImageView re = findViewById(R.id.rej);

        TextView name = findViewById(R.id.tf1);
        name.setText(""+MainActivity.s1);
        TextView no = findViewById(R.id.tf2);
        no.setText("+91 "+MainActivity.s2);

        BitmapDrawable drawable = (BitmapDrawable) MainActivity.IVPreviewImage.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();

        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        CircleImageView image2 =  findViewById(R.id.cont);
        image2.setImageBitmap(bmp);



        ic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent cl = new Intent(call.this , NewService.class);
                stopService(cl);
                Intent i = new Intent(call.this, Recieve.class);
                startActivity(i);
            }
        });
        re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(call.this , NewService.class);
                stopService(i);
finish();

            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent i = new Intent(call.this , NewService.class);
        stopService(i);
        super.onBackPressed();

    }
}