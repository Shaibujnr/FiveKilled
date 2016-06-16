package com.et.fivekilled;


import android.app.Activity;
import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.content.Intent;
import android.renderscript.ScriptGroup;

import android.content.Intent;
import android.app.FragmentManager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


import Helpers.FiveKilledHelper;
import Helpers.FiveKilledDialog;

public class SinglePlayerActivity extends AppCompatActivity {

//    Keyboard mKeyboard;
//    KeyboardView mKeyboardView;

    DisplayMetrics dm = new DisplayMetrics();
    int ScreenHeight;
    int ScreenWidth;


    TextView timeLabel;
    EditText gone,gtwo,gthree,gfour,gfive;
    Button submit;
    TextView errorLabel;
    GridLayout display;
    GridLayout keyPad;
    RelativeLayout root;


    private Button btnSubmit;
    FragmentManager fm;
    FiveKilledDialog fkDialog;
    Bundle dialogArgs;
    FiveKilledHelper fk;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_player);
        fkDialog = new FiveKilledDialog();
        dialogArgs = new Bundle();
        fk = new FiveKilledHelper();
        fm = getFragmentManager();


        getWindowManager().getDefaultDisplay().getMetrics(dm);
        ScreenHeight = dm.heightPixels;
        ScreenWidth = dm.widthPixels;

        gone = (EditText) findViewById(R.id.gone);
        gtwo = (EditText) findViewById(R.id.gtwo);
        gthree = (EditText) findViewById(R.id.gthree);
        gfour = (EditText) findViewById(R.id.gfour);
        gfive = (EditText) findViewById(R.id.gfive);



        keyPad = (GridLayout) findViewById(R.id.keyPad);
        display= (GridLayout) findViewById(R.id.display);
        submit = (Button) findViewById(R.id.btnSubmit);
        timeLabel = (TextView) findViewById(R.id.timer);
        root = (RelativeLayout) findViewById(R.id.root);

//        mKeyboard = new Keyboard(this,R.xml.kboard);
//        mKeyboardView = (KeyboardView) findViewById(R.id.keyboardview);
//        mKeyboardView.setKeyboard(mKeyboard);
//        mKeyboardView.setPreviewEnabled(false);
        submit = (Button) findViewById(R.id.btnSubmit);
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setDisplay();
        setKeyAlphs(fk.generateKeyAlphs(12));
        setInput();

//        input.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View view, boolean b) {
//                if(b){
//                    showCustomKeyboard(view);
//                }else{
//                    hideCustomKeyboard();
//                }
//            }
//        });



    }
//    public void hideCustomKeyboard() {
//        mKeyboardView.setVisibility(View.GONE);
//        mKeyboardView.setEnabled(false);
//    }
//
//    public void showCustomKeyboard( View v ) {
//        mKeyboardView.setVisibility(View.VISIBLE);
//        mKeyboardView.setEnabled(true);
//        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
//    }
//
//    public boolean isCustomKeyboardVisible() {
//        return mKeyboardView.getVisibility() == View.VISIBLE;
//    }
    public void setKeyAlphs(String alphs){
        int mag = 2;
        int row = 2;
        int col = (alphs.length()/row)+1;


        for(int i=0;i<2;i++){
            for(int j=0;j<7;j++){
                GridLayout.LayoutParams lp = new GridLayout.LayoutParams();

                lp.setMargins(mag,mag,mag,mag);
                lp.height = ScreenHeight/10;
                lp.width = (ScreenWidth-(mag*(col+3)))/col;
                lp.rowSpec = GridLayout.spec(i);
                lp.columnSpec = GridLayout.spec(j);
                Button btn = new Button(this);
                btn.setLayoutParams(lp);
                btn.setBackgroundResource(android.R.color.holo_orange_dark);
                btn.setText(""+i+""+j);
                keyPad.addView(btn);
            }


        }


    }
    public void setDisplay(){
//        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams();
//        lp.ScreenHeight-keyPad.getHeight()-submit.getHeight()-timeLabel.getHeight());
//        display.setMinimumWidth(ScreenWidth);
//        display.setLayoutParams();
    }
    public void setInput(){
        int mag = 2;
        int textWidth = (ScreenWidth-(7*mag))/6;
        int textHeight = (ScreenHeight)/12;
        gone.setInputType(InputType.TYPE_NULL);
        gtwo.setInputType(InputType.TYPE_NULL);
        gthree.setInputType(InputType.TYPE_NULL);
        gfour.setInputType(InputType.TYPE_NULL);
        gfive.setInputType(InputType.TYPE_NULL);

        gone.setHeight(textHeight);
        gone.setWidth(textWidth);

        gtwo.setHeight(textHeight);
        gtwo.setWidth(textWidth);

        gthree.setHeight(textHeight);
        gthree.setWidth(textWidth);

        gfour.setHeight(textHeight);
        gfour.setWidth(textWidth);

        gfive.setHeight(textHeight);
        gfive.setWidth(textWidth);



        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fk.createInGsmeDialog(SinglePlayerActivity.this,fm,"This is a test String");
            }



    });
        Intent i = getIntent();
        int difficulty = i.getIntExtra("difficulty",0);
        fk.tst(""+difficulty,getApplicationContext());
}}
