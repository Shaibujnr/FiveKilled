package com.et.fivekilled;

import android.content.Intent;
<<<<<<< HEAD
=======
import android.graphics.Typeface;
>>>>>>> 7eb67770e255301c98cb71bae9455f9b7fec48b0
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

<<<<<<< HEAD
public class SplashScreen extends AppCompatActivity implements  Runnable{

=======
import Helpers.fonts.AerosolTextView;

public class SplashScreen extends AppCompatActivity implements  Runnable{
AerosolTextView FiveKilled,ET;
>>>>>>> 7eb67770e255301c98cb71bae9455f9b7fec48b0
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
<<<<<<< HEAD

        new Handler().postDelayed(this,5000);
=======
        FiveKilled = (AerosolTextView) findViewById(R.id.five_killed);
        ET = (AerosolTextView) findViewById(R.id.et);

        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/GreatPoints.ttf");
        ET.setTypeface(tf);
        FiveKilled.setTypeface(tf);
        new Handler().postDelayed(this,2000);
>>>>>>> 7eb67770e255301c98cb71bae9455f9b7fec48b0
    }

    @Override
    public void run() {
        Intent i = new Intent(SplashScreen.this, HomeMenu.class);
        startActivity(i);
        finish();
    }
}
