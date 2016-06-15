package com.et.fivekilled;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity implements  Runnable{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(this,5000);
    }

    @Override
    public void run() {
        Intent i = new Intent(SplashScreen.this, HomeMenu.class);
        startActivity(i);
        finish();
    }
}
