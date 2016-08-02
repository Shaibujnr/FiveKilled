package com.et.fivekilled;

import android.content.Intent;

import android.app.FragmentManager;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.Layout;
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

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Games;
import com.google.example.games.basegameutils.BaseGameActivity;
import com.google.example.games.basegameutils.GameHelper;

import java.util.ArrayList;


import Helpers.Constants;
import Helpers.CountDownHelper;
import Helpers.FiveKilledHelper;
import Helpers.FiveKilledDialog;
import Helpers.InputListener;
import Helpers.InputTextWatcher;
import Helpers.StaticHelpers;
import Helpers.InputTextWatcher;

public class FourKilledActivity extends BaseGameActivity {


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
        setContentView(R.layout.activity_four_killed);

        getGameHelper().setMaxAutoSignInAttempts(0);
        fkDialog = new FiveKilledDialog();
        dialogArgs = new Bundle();
        fk = new FiveKilledHelper();
        fm = getFragmentManager();
        inputs = new ArrayList<Button>();
        keyPadButtons = new ArrayList<Button>();

        keyAlphs = fk.generateKeyAlphs(Constants.FOURKILLED_POOL_NUMBERS);
        ComSpecialNumbers = fk.generateSpecialAlphs(Constants.FOURKILLED_SPECIAL_NUMBERS,keyAlphs);

        gone = (Button) findViewById(R.id.four_gone);
        inputs.add(gone);
        gtwo = (Button) findViewById(R.id.four_gtwo);
        inputs.add(gtwo);
        gthree = (Button) findViewById(R.id.four_gthree);
        inputs.add(gthree);
        gfour = (Button) findViewById(



                R.id.four_gfour);
        inputs.add(gfour);



        display = (GridLayout) findViewById(R.id.four_display);
        keyPad = (GridLayout) findViewById(R.id.four_keyPad);
        root = (RelativeLayout) findViewById(R.id.four_root);
        sview = (ScrollView) findViewById(R.id.four_sv);
        trialLabel = (TextView) findViewById(R.id.four_trials_label);


        timeLabel = (Chronometer) findViewById(R.id.four_timer);
        timeLabel.start();
        btnSubmit = (Button) findViewById(R.id.four_btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleSubmit();
//                fk.tst(ComSpecialNumbers,getApplicationContext());
            }
        });






        inputListener = new InputListener(keyPadButtons);

        inputTextWatcher = new InputTextWatcher(btnSubmit,inputs);

//        setTimeFromDifficulty();
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

        lpg.height= (BaseGameActivity.ScreenHeight-keyPad.getHeight()-
                gone.getHeight()-timeLabel.getHeight())/8;
        lpg.width = (BaseGameActivity.ScreenWidth-(displayMag*4))/2;
        lpg.columnSpec = GridLayout.spec(0);
        lpg.setMargins(0,0,0,0);


        lpr.height= (BaseGameActivity.ScreenHeight-keyPad.getHeight()-gone.getHeight()
                -timeLabel.getHeight())/8;
        lpr.width = (BaseGameActivity.ScreenWidth-(displayMag*4))/2;
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

        if(fk.isWin(result,Constants.FOURKILLED_SPECIAL_NUMBERS)){
            timeLabel.stop();
            hideAllButtons();
            String tis = convertToSeconds(getTimeUsed());
            boolean is_record_set = isHighScore(number_of_calls,tis);
            implementHs(number_of_calls,tis);
            fk.createWinDialog(fm,String.valueOf(number_of_calls),getTimeUsed(),tis+" seconds",is_record_set);
            if(getApiClient().isConnected()) {
                submitScore(number_of_calls,Integer.parseInt(tis)*1000);
            }
            if(getApiClient().isConnected() && number_of_calls>1 && number_of_calls<=6){
                unlockAchivement(getString(R.string.newton));
            }
            if(getApiClient().isConnected() && number_of_calls==1){
                unlockAchivement(getString(R.string.casper));
            }
            if(getApiClient().isConnected() && Integer.parseInt(tis)<60){
                unlockAchivement(getString(R.string.fast_finger));
            }
        }



    }

    private void unlockAchivement(String string) {
        Games.Achievements.unlock(getApiClient(),string);
    }


    private void updateTrials() {
        number_of_calls++;
        trialLabel.setText("Guesses: "+number_of_calls);

    }


    private boolean isHighScore(int number_of_calls, String tis) {
        if(AlphaApplication.getFourKilledTrialsHs(this)>=-1 &&
                number_of_calls<AlphaApplication.getFourKilledTrialsHs(this)){

            return true;
        }
        else if(AlphaApplication.getFourKilledTimeHs(this)>=-1 &&
                Integer.parseInt(tis)<AlphaApplication.getFourKilledTimeHs(this)){
//            SharedPreferences sp = getSharedPreferences("high_score_pref",MODE_PRIVATE);
//            SharedPreferences.Editor editor = sp.edit();
//            editor.putInt("four_trials_hs",number_of_calls);
            return true;
        }
//        else if(AlphaApplication.getFourKilledTimeHs(this)!=-1 &&
//                Integer.parseInt(tis)<AlphaApplication.getFourKilledTimeHs(this) &&
//                number_of_calls>=AlphaApplication.getFourKilledTrialsHs(this)){
//            SharedPreferences sp = getSharedPreferences("high_score_pref",MODE_PRIVATE);
//            SharedPreferences.Editor editor = sp.edit();
//            editor.putInt("four_time_hs",Integer.parseInt(tis));
//            return true;
//
//        }
//        else if(AlphaApplication.getFourKilledTimeHs(this)==-1 &&
//                number_of_calls>=AlphaApplication.getFourKilledTrialsHs(this)){
//            SharedPreferences sp = getSharedPreferences("high_score_pref",MODE_PRIVATE);
//            SharedPreferences.Editor editor = sp.edit();
//            editor.putInt("four_time_hs",Integer.parseInt(tis));
//            return true;
//
//        }
//        else if(Integer.parseInt(tis)!=-1 &&
//                Integer.parseInt(tis)<AlphaApplication.getFourKilledTimeHs(this) &&
//                number_of_calls<AlphaApplication.getFourKilledTrialsHs(this)){
//            SharedPreferences sp = getSharedPreferences("high_score_pref",MODE_PRIVATE);
//            SharedPreferences.Editor editor = sp.edit();
//            editor.putInt("four_time_hs",Integer.parseInt(tis));
//            editor.putInt("four_trials_hs",number_of_calls);
//            return true;
//
//        }
        return false;
    }

    private void implementHs(int noc,String tis){
        int cths = AlphaApplication.getFourKilledTimeHs(this);
        int ctrhs = AlphaApplication.getFourKilledTrialsHs(this);
        if(ctrhs!=-1 && noc<ctrhs){
            AlphaApplication.setFourKilledTrialsHs(this,noc);
        }
        if(ctrhs==-1){
            AlphaApplication.setFourKilledTrialsHs(this,noc);
        }
        if(cths!=-1 && Integer.parseInt(tis)<cths){
            AlphaApplication.setFourKilledTimeHs(this,Integer.parseInt(tis));

        }
        if(cths==-1){
            AlphaApplication.setFourKilledTimeHs(this,Integer.parseInt(tis));

        }
    }





    private void setKeyAlphs(String alphs) {
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

    private void setInput() {
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

    }



    private Button getEmptyEdit() {
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
    private void hideAllButtons(){
        for(Button btn : inputs){
            btn.setVisibility(View.INVISIBLE);
        }
        for(Button btn : keyPadButtons){
            btn.setVisibility(View.INVISIBLE);
        }
        btnSubmit.setVisibility(View.INVISIBLE);

    }
    public void setInReviewMode(){
        hideAllButtons();
        final Button doneFab = new Button(this);
        doneFab.setText(R.string.review_done_buton);
        doneFab.setBackgroundResource(R.drawable.dialog_edge);
        doneFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tis = convertToSeconds(getTimeUsed());
                boolean is_record_set = isHighScore(number_of_calls,tis);
                fk.createWinDialog(fm,String.valueOf(number_of_calls),getTimeUsed(),tis+" seconds",is_record_set);
                doneFab.setVisibility(View.INVISIBLE);
            }
        });
        RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        rlp.addRule(RelativeLayout.BELOW,R.id.four_sv);
        rlp.addRule(RelativeLayout.CENTER_HORIZONTAL);
        rlp.width = root.getWidth();
        rlp.height = root.getHeight()/10;
        root.addView(doneFab,rlp);

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
    private void submitScore(int number_of_calls, int i) {
        Games.Leaderboards.submitScore(getApiClient(),getString(R.string.four_guess_lbid),number_of_calls);
        Games.Leaderboards.submitScore(getApiClient(),getString(R.string.four_time_lbid),i);
    }


    @Override
    public void onSignInFailed() {

    }

    @Override
    public void onSignInSucceeded() {

    }
}
