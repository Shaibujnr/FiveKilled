package com.et.fivekilled;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import Helpers.fonts.AerosolTextView;
import Helpers.fonts.EtTextView;
import Helpers.fonts.MenuTextView;

public class SplashScreen extends AppCompatActivity implements  Runnable{
    MenuTextView FiveKilled;
    EtTextView ET;
    Animation anim_flash,anim_bounce;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        anim_flash = AnimationUtils.loadAnimation(this, R.anim.anim_flash);
        anim_bounce = AnimationUtils.loadAnimation(this, R.anim.anim_bounce);
        FiveKilled = (MenuTextView) findViewById(R.id.five_killed);
        ET = (EtTextView) findViewById(R.id.et);
        anim_bounce.setDuration(3000);
        anim_bounce.setRepeatMode(Animation.REVERSE);
        FiveKilled.setAnimation(anim_bounce);



        new Handler().postDelayed(this,2000);
    }

    @Override
    public void run() {
        Intent i = new Intent(SplashScreen.this, HomeMenu.class);
        startActivity(i);
        finish();
    }
}
