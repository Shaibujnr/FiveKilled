package com.et.fivekilled;

import android.content.Intent;

import android.app.FragmentManager;

import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
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

import com.google.example.games.basegameutils.BaseGameActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


import Helpers.Constants;
import Helpers.CountDownHelper;
import Helpers.FiveKilledHelper;
import Helpers.FiveKilledDialog;
import Helpers.InputListener;
import Helpers.InputTextWatcher;
import Helpers.StaticHelpers;
import Helpers.InputTextWatcher;

public class SixKilledActivity extends BaseGameActivity {


    int difficultySelected;
    int number_of_calls = 0;





    Chronometer timeLabel;
    //    EditText gone, gtwo, gthree, gfour, gfive;
    Button gone, gtwo, gthree, gfour, gfive,gsix;
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
        setContentView(R.layout.activity_six_killed);
        fkDialog = new FiveKilledDialog();
        dialogArgs = new Bundle();
        fk = new FiveKilledHelper();
        fm = getFragmentManager();
        inputs = new ArrayList<Button>();
        keyPadButtons = new ArrayList<Button>();

        keyAlphs = fk.generateKeyAlphs(Constants.SIXKILLED_POOL_NUMBERS);
        ComSpecialNumbers = fk.generateSpecialAlphs(Constants.SIXKILLED_SPECIAL_NUMBERS,keyAlphs);

        gone = (Button) findViewById(R.id.six_gone);
        inputs.add(gone);
        gtwo = (Button) findViewById(R.id.six_gtwo);
        inputs.add(gtwo);
        gthree = (Button) findViewById(R.id.six_gthree);
        inputs.add(gthree);
        gfour = (Button) findViewById(R.id.six_gfour);
        inputs.add(gfour);
        gfive = (Button) findViewById(R.id.six_gfive);
        inputs.add(gfive);
        gsix = (Button) findViewById(R.id.six_gsix);
        inputs.add(gsix);



        display = (GridLayout) findViewById(R.id.six_display);
        keyPad = (GridLayout) findViewById(R.id.six_keyPad);
        root = (RelativeLayout) findViewById(R.id.six_root);
        sview = (ScrollView) findViewById(R.id.six_sv);
        trialLabel = (TextView) findViewById(R.id.six_trials_label);


        timeLabel = (Chronometer) findViewById(R.id.six_timer);
        timeLabel.start();
        btnSubmit = (Button) findViewById(R.id.six_btnSubmit);
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
        setTrialLabel();

        mPlayer = MediaPlayer.create(getApplicationContext(),R.raw.clockbg);
        mPlayer.start();
        mPlayer.setLooping(true);


    }

    private void setTrialLabel() {
        switch(difficultySelected){
            case 1:
                trialLabel.setText("0"+"/"+Constants.DIFFICULTY_EASY_TRIALS);
                break;
            case 2:
                trialLabel.setText("0"+"/"+Constants.DIFFICULTY_MEDIUM_TRIALS);
                break;
            case 3:
                trialLabel.setText("0"+"/"+Constants.DIFFICULTY_HARD_TRIALS);
                break;
        }
    }

    private void handleSubmit() {
        int displayMag = 2;
        GridLayout.LayoutParams lpg = new GridLayout.LayoutParams();
        GridLayout.LayoutParams lpr = new GridLayout.LayoutParams();

        lpg.height= (BaseGameActivity.ScreenHeight-keyPad.getHeight()-
                gone.getHeight()-timeLabel.getHeight())/8;
        lpg.width = (BaseGameActivity.ScreenWidth-(displayMag*4))/2;
        lpg.columnSpec = GridLayout.spec(0);
        lpg.setMargins(0,0,0,0);


        lpr.height= (ScreenHeight-keyPad.getHeight()-gone.getHeight()-timeLabel.getHeight())/8;
        lpr.width = (ScreenWidth-(displayMag*4))/2;
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

        if(fk.isWin(result,Constants.SIXKILLED_SPECIAL_NUMBERS)){
            timeLabel.stop();
            hideAllButtons();
            String tis = convertToSeconds(getTimeUsed());
            fk.createWinDialog(fm,String.valueOf(number_of_calls),getTimeUsed(),tis+" seconds");



        }



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
                lp.height = BaseGameActivity.KeyPadHeight;
                lp.width = (BaseGameActivity.ScreenWidth - (mag * (col + 3))) / col;
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
        int textWidth = (BaseGameActivity.ScreenWidth - (7 * mag)) /14;
        int textHeight = (BaseGameActivity.ScreenHeight) / 12;






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

        gsix.setHeight(textHeight);
        gsix.setWidth(textWidth);
        gsix.setOnClickListener(inputListener);
        gsix.addTextChangedListener(inputTextWatcher);

    }



    public Button getEmptyEdit() {
        for (Button edt : inputs) {
            if (edt.getText().toString().isEmpty()) {
                return edt;
            }
        }
        return null;

    }


    private String getTimeUsed(){
       return timeLabel.getText().toString();

    }

    public void setInReviewMode(){
        hideAllButtons();
        final Button doneFab = new Button(this);
        doneFab.setText(R.string.review_done_buton);
        doneFab.setBackgroundResource(R.drawable.dialog_edge);
        doneFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fk.createWinDialog(fm,String.valueOf(number_of_calls),getTimeUsed(),"400");
                doneFab.setVisibility(View.INVISIBLE);
            }
        });
        RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        rlp.addRule(RelativeLayout.ABOVE,R.id.six_keyPad);
        rlp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        rlp.setMargins(0,0,40,0);
        rlp.width = root.getWidth()/4;
        rlp.height = root.getHeight()/10;
        root.addView(doneFab,rlp);

    }
    private void hideAllButtons(){
        for(Button btn : inputs){
            btn.setVisibility(View.INVISIBLE);
        }
        for(Button btn : keyPadButtons){
            btn.setVisibility(View.INVISIBLE);
        }
        btnSubmit.setVisibility(View.INVISIBLE);

    }

    public String convertToSeconds(String time){
        String[] parts = time.split(":");
        String result = "";
        switch(parts.length){
            case 3:
                int hour = Integer.parseInt(parts[0]);
                int minute = Integer.parseInt(parts[1]);
                int sec = Integer.parseInt(parts[2]);
                result = String.valueOf((hour*60*60)+(minute*60)+sec);
                break;
            case 2:
                int min = Integer.parseInt(parts[0]);
                int secs = Integer.parseInt(parts[1]);
                result = String.valueOf((min*60)+secs);
                break;
            case 1:
                result = String.valueOf(parts[0]);
                break;
        }
        return result;
    }

    @Override
    public void onBackPressed() {
        fk.createBackButtonDialog(getFragmentManager());
    }

    @Override
    public void onSignInFailed() {

    }

    @Override
    public void onSignInSucceeded() {

    }
}

