package com.et.fivekilled;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

public class NoStatusBarActivity extends AppCompatActivity {
    public static final DisplayMetrics  dm = new DisplayMetrics();
    public static  int ScreenHeight;
    public static  int ScreenWidth;
    public static int KeyPadHeight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        ScreenHeight = dm.heightPixels;
        ScreenWidth = dm.widthPixels;
        KeyPadHeight = ScreenHeight/10;
    }


}
