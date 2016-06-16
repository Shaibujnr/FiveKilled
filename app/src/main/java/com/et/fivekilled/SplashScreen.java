package com.et.fivekilled;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import Helpers.fonts.AerosolTextView;

public class SplashScreen extends AppCompatActivity implements  Runnable{
AerosolTextView FiveKilled,ET;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        FiveKilled = (AerosolTextView) findViewById(R.id.five_killed);
        ET = (AerosolTextView) findViewById(R.id.et);

        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/GreatPoints.ttf");
        ET.setTypeface(tf);
        FiveKilled.setTypeface(tf);
        new Handler().postDelayed(this,2000);
    }

    @Override
    public void run() {
        Intent i = new Intent(SplashScreen.this, HomeMenu.class);
        startActivity(i);
        finish();
    }
}
