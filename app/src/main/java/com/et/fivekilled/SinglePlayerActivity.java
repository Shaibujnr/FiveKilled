package com.et.fivekilled;


import android.content.Intent;

import android.app.FragmentManager;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


import Helpers.Constants;
import Helpers.FiveKilledHelper;
import Helpers.FiveKilledDialog;
import Helpers.StaticHelpers;

public class SinglePlayerActivity extends AppCompatActivity {


    int difficultySelected;


    DisplayMetrics dm = new DisplayMetrics();
    int ScreenHeight;
    int ScreenWidth;


    TextView timeLabel;
    EditText gone, gtwo, gthree, gfour, gfive;

    GridLayout display;
    GridLayout keyPad;
    RelativeLayout root;
    ScrollView sview;


    Button btnSubmit;
    FragmentManager fm;
    FiveKilledDialog fkDialog;
    Bundle dialogArgs;
    FiveKilledHelper fk;
    ArrayList<EditText> inputs;
    ArrayList<Button> keyPadButtons;

    View.OnClickListener inputListener;
    TextWatcher inputTextWatcher;

    String keyAlphs;
    String ComSpecialNumbers;

    MediaPlayer mPlayer;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_single_player);
        fkDialog = new FiveKilledDialog();
        dialogArgs = new Bundle();
        fk = new FiveKilledHelper();
        fm = getFragmentManager();
        inputs = new ArrayList<EditText>();
        keyPadButtons = new ArrayList<Button>();

        keyAlphs = fk.generateKeyAlphs(12);
        ComSpecialNumbers = fk.generateSpecialAlphs(5,keyAlphs);
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        ScreenHeight = dm.heightPixels;
        ScreenWidth = dm.widthPixels;

        gone = (EditText) findViewById(R.id.gone);
        inputs.add(gone);
        gtwo = (EditText) findViewById(R.id.gtwo);
        inputs.add(gtwo);
        gthree = (EditText) findViewById(R.id.gthree);
        inputs.add(gthree);
        gfour = (EditText) findViewById(R.id.gfour);
        inputs.add(gfour);
        gfive = (EditText) findViewById(R.id.gfive);
        inputs.add(gfive);


        display = (GridLayout) findViewById(R.id.display);
        keyPad = (GridLayout) findViewById(R.id.keyPad);
        timeLabel = (TextView) findViewById(R.id.timer);
        root = (RelativeLayout) findViewById(R.id.root);
        sview = (ScrollView) findViewById(R.id.sv);


        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                fk.createInGsmeDialog(SinglePlayerActivity.this, fm, "This is a test String");
                handleSubmit();

            }


        });

        inputListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText clickedEditText = (EditText) view;
                String clickedText = clickedEditText.getText().toString();
                clickedEditText.setText("");

                for (Button btn : keyPadButtons) {
                    if (btn.getText().toString().equals(clickedText)) {
                        btn.setEnabled(true);
                    }
                }


            }
        };

        inputTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(inputFull()){
                    btnSubmit.setEnabled(true);
                }else{
                    btnSubmit.setEnabled(false);
                }

            }
        };

        setTimeFromDifficulty();

        setDisplay();
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

        lpg.height= (ScreenHeight-keyPad.getHeight()-gone.getHeight()-timeLabel.getHeight())/8;
        lpg.width = (ScreenWidth-(displayMag*4))/2;
        lpg.columnSpec = GridLayout.spec(0);
        lpg.setMargins(displayMag,displayMag,displayMag,displayMag);

        lpr.height= (ScreenHeight-keyPad.getHeight()-gone.getHeight()-timeLabel.getHeight())/8;
        lpr.width = (ScreenWidth-(displayMag*4))/2;
        lpr.columnSpec = GridLayout.spec(1);
        lpr.setMargins(displayMag,displayMag,displayMag,displayMag);

        String guess = "";
        String result = "";
        for(EditText edt: inputs){
            guess+=edt.getText().toString();
        }
        result = fk.getResult(guess,ComSpecialNumbers);
        TextView guessdt = new TextView(this);
        guessdt.setText(guess);
        guessdt.setLayoutParams(lpg);
        guessdt.setBackgroundResource(R.drawable.brown6);
        guessdt.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);


        TextView resultdt = new TextView(this);
        resultdt.setText(result);
        resultdt.setLayoutParams(lpr);
        resultdt.setBackgroundResource(R.drawable.brown6);
        resultdt.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);


        display.addView(guessdt);
        display.addView(resultdt);
        for(EditText edt: inputs){
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



    }

    private void setTimeFromDifficulty() {
        Intent hmIntent = getIntent();
        String txt;
        long duration;
        difficultySelected = hmIntent.getIntExtra("difficulty",0);
        switch (difficultySelected){
            case 1:
                duration = convertDifficultyToMillis(Constants.DIFFICULTY_EASY_TIME);
                txt = StaticHelpers.getTimeFormat(duration);
                timeLabel.setText(txt);
                new CountDownTimer(duration,1000){

                    @Override
                    public void onTick(long l) {
                        timeLabel.setText(StaticHelpers.getTimeFormat(l));

                    }

                    @Override
                    public void onFinish() {
                        timeLabel.setText("Finished");

                    }
                }.start();
                break;
            case 2:
                duration = convertDifficultyToMillis(Constants.DIFFICULTY_MEDIUM_TIME);
                txt = StaticHelpers.getTimeFormat(duration);
                timeLabel.setText(txt);
                new CountDownTimer(duration,1000){

                    @Override
                    public void onTick(long l) {
                        timeLabel.setText(StaticHelpers.getTimeFormat(l));

                    }

                    @Override
                    public void onFinish() {
                        timeLabel.setText("Finished");

                    }
                }.start();
                break;
            case 3:
                duration = convertDifficultyToMillis(Constants.DIFFICULTY_HARD_TIME);
                txt = StaticHelpers.getTimeFormat(duration);
                timeLabel.setText(txt);
                new CountDownTimer(duration,1000){

                    @Override
                    public void onTick(long l) {
                        timeLabel.setText(StaticHelpers.getTimeFormat(l));

                    }

                    @Override
                    public void onFinish() {
                        timeLabel.setText("Finished");

                    }
                }.start();
                break;
        }
    }

    private long convertDifficultyToMillis(long difficulty){
        return difficulty * 60000;

    }




    public void setKeyAlphs(String alphs) {
        int mag = 2;
        int row = 2;
        int col = (alphs.length() / row);


        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                GridLayout.LayoutParams lp = new GridLayout.LayoutParams();

                lp.setMargins(mag, mag, mag, mag);
                lp.height = ScreenHeight / 10;
                lp.width = (ScreenWidth - (mag * (col + 3))) / col;
                lp.rowSpec = GridLayout.spec(i);
                lp.columnSpec = GridLayout.spec(j);
                final Button btn = new Button(this);
                btn.setLayoutParams(lp);
                btn.setBackgroundResource(R.drawable.keypad_button);
                btn.setText(String.valueOf(alphs.charAt(((6 * i) + j))));
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String clickedText = btn.getText().toString();
                        EditText availableEdit = getEmptyEdit();
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

    public void setDisplay() {
//        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams();
//        lp.ScreenHeight-keyPad.getHeight()-submit.getHeight()-timeLabel.getHeight());
//        display.setMinimumWidth(ScreenWidth);
//        display.setLayoutParams();
    }

    public void setInput() {
        int mag = 2;
        int textWidth = (ScreenWidth - (7 * mag)) / 7;
        int textHeight = (ScreenHeight) / 12;
        gone.setInputType(InputType.TYPE_NULL);
        gtwo.setInputType(InputType.TYPE_NULL);
        gthree.setInputType(InputType.TYPE_NULL);
        gfour.setInputType(InputType.TYPE_NULL);
        gfive.setInputType(InputType.TYPE_NULL);

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



    public EditText getEmptyEdit() {
        for (EditText edt : inputs) {
            if (edt.getText().toString().isEmpty()) {
                return edt;
            }
        }
        return null;

    }
    private boolean inputFull(){
        for(EditText edt:inputs){
            if(edt.getText().toString().isEmpty()){
                return false;
            }
        }
        return true;
    }
}

