package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class splash_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splach);
        getSupportActionBar().hide();

        final Intent i = new Intent(splash_activity.this,MainActivity.class);
        new Handler().postAtTime(new Runnable() {
            @Override
            public void run() {
                startActivity(i);
                finish();
            }
        },1000);
    }
}