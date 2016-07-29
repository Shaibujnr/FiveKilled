package com.et.fivekilled;


import android.content.Intent;

import android.app.FragmentManager;

import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


import Helpers.Constants;
import Helpers.CountDownHelper;
import Helpers.FiveKilledHelper;
import Helpers.FiveKilledDialog;
import Helpers.InputListener;
import Helpers.InputTextWatcher;
import Helpers.StaticHelpers;

public class FiveKilledActivity extends NoStatusBarActivity {


    int difficultySelected;
    int number_of_calls = 0;





    Chronometer timeLabel;
//    EditText gone, gtwo, gthree, gfour, gfive;
    Button gone, gtwo, gthree, gfour, gfive;
    TextView trialLabel;

    GridLayout display;
    GridLayout keyPad;
    RelativeLayout root;
    ScrollView sview;


    Button btnSubmit;
    FragmentManager fm;
    FiveKilledDialog fkDialog;
    Bundle dialogArgs;
    FiveKilledHelper fk;
//    ArrayList<EditText> inputs;
    ArrayList<Button> inputs;
    ArrayList<Button> keyPadButtons;

    View.OnClickListener inputListener;
    TextWatcher inputTextWatcher;

    String keyAlphs;
    String ComSpecialNumbers;

    MediaPlayer mPlayer;
    CountDownHelper cdHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_player);
        fkDialog = new FiveKilledDialog();
        dialogArgs = new Bundle();
        fk = new FiveKilledHelper();
        fm = getFragmentManager();
        inputs = new ArrayList<Button>();
        keyPadButtons = new ArrayList<Button>();

        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        keyAlphs = fk.generateKeyAlphs(12);
        ComSpecialNumbers = fk.generateSpecialAlphs(5,keyAlphs);

        gone = (Button) findViewById(R.id.gone);
        inputs.add(gone);
        gtwo = (Button) findViewById(R.id.gtwo);
        inputs.add(gtwo);
        gthree = (Button) findViewById(R.id.gthree);
        inputs.add(gthree);
        gfour = (Button) findViewById(R.id.gfour);
        inputs.add(gfour);
        gfive = (Button) findViewById(R.id.gfive);
        inputs.add(gfive);


        display = (GridLayout) findViewById(R.id.display);
        keyPad = (GridLayout) findViewById(R.id.keyPad);
        root = (RelativeLayout) findViewById(R.id.root);
        sview = (ScrollView) findViewById(R.id.sv);
        trialLabel = (TextView) findViewById(R.id.trials_label);


        timeLabel = (Chronometer) findViewById(R.id.timer);
        timeLabel.start();
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleSubmit();
                fk.tst(ComSpecialNumbers,getApplicationContext());
            }
        });






        inputListener = new InputListener(keyPadButtons);

        inputTextWatcher = new InputTextWatcher(btnSubmit,inputs);

        setKeyAlphs(keyAlphs);
        setInput();

        mPlayer = MediaPlayer.create(getApplicationContext(),R.raw.clockbg);
        mPlayer.start();
        mPlayer.setLooping(true);


    }



    private void handleSubmit() {
        int displayMag = 2;
        GridLayout.LayoutParams lpg = new GridLayout.LayoutParams();
        GridLayout.LayoutParams lpr = new GridLayout.LayoutParams();

        lpg.height= (NoStatusBarActivity.ScreenHeight-keyPad.getHeight()-gone.getHeight()
                -timeLabel.getHeight())/8;
        lpg.width = (NoStatusBarActivity.ScreenWidth-(displayMag*4))/2;
        lpg.columnSpec = GridLayout.spec(0);
        lpg.setMargins(0,0,0,0);


        lpr.height= (NoStatusBarActivity.ScreenHeight-keyPad.getHeight()-gone.getHeight()
                -timeLabel.getHeight())/8;
        lpr.width = (NoStatusBarActivity.ScreenWidth-(displayMag*4))/2;
        lpr.columnSpec = GridLayout.spec(1);
        lpr.setMargins(0,0,0,0);


        String guess = "";
        String result = "";
        for(Button edt: inputs){
            guess+=edt.getText().toString();
        }
        result = fk.getResult(guess,ComSpecialNumbers);
        TextView guessdt = new TextView(this);
        guessdt.setText(guess);
        guessdt.setLayoutParams(lpg);
        guessdt.setBackgroundResource(R.drawable.guess_display_label);
        guessdt.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        guessdt.setGravity(Gravity.CENTER);
        guessdt.setTypeface(null, Typeface.BOLD);


        TextView resultdt = new TextView(this);
        resultdt.setText(result);
        resultdt.setLayoutParams(lpr);
        resultdt.setBackgroundResource(R.drawable.resuslt_display_label);
        resultdt.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        resultdt.setGravity(Gravity.CENTER);
        resultdt.setTypeface(null, Typeface.BOLD);



        display.addView(guessdt);
        display.addView(resultdt);
        updateTrials();
        for(Button edt: inputs){
            edt.setText("");
        }
        for(Button btn: keyPadButtons){
            btn.setEnabled(true);
        }
        sview.post(new Runnable() {
            @Override
            public void run() {
                sview.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });
        String resultString="";

        if(fk.isWin(result)){
            fk.createWinDialog(fm,String.valueOf(number_of_calls),getTimeUsed(difficultySelected),calculateScore());
        }



    }

    private String calculateScore() {
        long secondsLeft = getTimeFromDifficultyInSeconds(difficultySelected)-Long.parseLong(getTimeUsed(difficultySelected));
        long trialsLeft = getTrailsFromDifficulty(difficultySelected);
        long score = secondsLeft+trialsLeft;
        return String.valueOf(score);
    }

    private void updateTrials() {
        number_of_calls++;
        trialLabel.setText("Guesses: "+number_of_calls);
    }

    private void EndGame() {
        Toast.makeText(this,"Game has ended trials used up",Toast.LENGTH_SHORT).show();
    }






    public void setKeyAlphs(String alphs) {
        int mag = Constants.KEYPAD_MARGIN_SIZE;
        int row = Constants.KEYPAD_BUTTON_ROWS;
        int col = (alphs.length() / row);


        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                GridLayout.LayoutParams lp = new GridLayout.LayoutParams();

                lp.setMargins(mag, mag, mag, mag);
                lp.height = NoStatusBarActivity.KeyPadHeight;
                lp.width = (NoStatusBarActivity.ScreenWidth - (mag * (col + 3))) / col;
                lp.rowSpec = GridLayout.spec(i);
                lp.columnSpec = GridLayout.spec(j);
                final Button btn = new Button(this);
                btn.setLayoutParams(lp);
                btn.setBackgroundResource(R.drawable.keypad_button);
                btn.setText(String.valueOf(alphs.charAt(((col * i) + j))));
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String clickedText = btn.getText().toString();
                        Button availableEdit = getEmptyEdit();
                        if (availableEdit != null) {
                            availableEdit.setText(clickedText);
                            btn.setEnabled(false);
                        }


                    }
                });
                keyPadButtons.add(btn);
                keyPad.addView(btn);
            }


        }


    }

    public void setInput() {
        int mag = 2;
        int textWidth = (NoStatusBarActivity.ScreenWidth - (7 * mag)) /14;
        int textHeight = (NoStatusBarActivity.ScreenHeight) / 12;






        gone.setHeight(textHeight);
        gone.setWidth(textWidth);
        gone.setOnClickListener(inputListener);
        gone.addTextChangedListener(inputTextWatcher);

        gtwo.setHeight(textHeight);
        gtwo.setWidth(textWidth);
        gtwo.setOnClickListener(inputListener);
        gtwo.addTextChangedListener(inputTextWatcher);

        gthree.setHeight(textHeight);
        gthree.setWidth(textWidth);
        gthree.setOnClickListener(inputListener);
        gthree.addTextChangedListener(inputTextWatcher);

        gfour.setHeight(textHeight);
        gfour.setWidth(textWidth);
        gfour.setOnClickListener(inputListener);
        gfour.addTextChangedListener(inputTextWatcher);

        gfive.setHeight(textHeight);
        gfive.setWidth(textWidth);
        gfive.setOnClickListener(inputListener);
        gfive.addTextChangedListener(inputTextWatcher);
    }



    public Button getEmptyEdit() {
        for (Button edt : inputs) {
            if (edt.getText().toString().isEmpty()) {
                return edt;
            }
        }
        return null;

    }

    private String getTimeUsed(int dif){
        String remainingTime = timeLabel.getText().toString();
        String[] rtminSec = remainingTime.split(":");
        long rMinute = Long.parseLong(rtminSec[0]);
        long rSec = Long.parseLong(rtminSec[1]);
        long rTotalSec = (rMinute*60)+rSec;
        long initialTime=0;
        switch(dif){
            case 1:
                initialTime = Constants.DIFFICULTY_EASY_TIME;
                break;
            case 2:
                initialTime = Constants.DIFFICULTY_MEDIUM_TIME;
                break;
            case 3:
                initialTime = Constants.DIFFICULTY_HARD_TIME;
                break;
        }
        long initialTimeInSeconds = initialTime*60;
        long totalSecondsUsed = initialTimeInSeconds - rTotalSec;
        long mUsed = totalSecondsUsed/60;
        long sUsed = totalSecondsUsed%60;

        String MinutesUsed = String.valueOf(mUsed)+" : "+String.valueOf(sUsed);
        return MinutesUsed;


    }
    private long getTimeFromDifficultyInSeconds(int dif){
        long time = 0;
        switch(dif){
            case 1:
                time= Constants.DIFFICULTY_EASY_TIME*60;
            case 2:
                time= Constants.DIFFICULTY_MEDIUM_TIME*60;
            case 3:
                time= Constants.DIFFICULTY_HARD_TIME*60;
        }
        return time;
    }
    private long getTrailsFromDifficulty(int dif){
       long trial = 0;
        switch(dif) {
            case 1:
                trial =  Constants.DIFFICULTY_EASY_TRIALS;
            case 2:
                trial =  Constants.DIFFICULTY_MEDIUM_TRIALS;
            case 3:
                trial =  Constants.DIFFICULTY_HARD_TRIALS;
        }
        return trial;

    }

}

