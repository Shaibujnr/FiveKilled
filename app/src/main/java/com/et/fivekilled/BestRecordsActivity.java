package com.et.fivekilled;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import Helpers.fonts.EtTextView;

public class BestRecordsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.best_record_dialog);


        EtTextView four_time = (EtTextView) findViewById(R.id.br_four_time);
        EtTextView four_trials = (EtTextView) findViewById(R.id.br_four_trials);
        EtTextView five_time = (EtTextView) findViewById(R.id.br_five_time);
        EtTextView five_trials = (EtTextView) findViewById(R.id.br_five_trials);
        EtTextView six_time = (EtTextView) findViewById(R.id.br_six_time);
        EtTextView six_trials = (EtTextView) findViewById(R.id.br_six_trials);
        Button btnOk = (Button) findViewById(R.id.br_button);

        int ft = AlphaApplication.getFourKilledTimeHs(this);
        int ftr = AlphaApplication.getFourKilledTrialsHs(this);
        int fit = AlphaApplication.getFiveKilledTimeHs(this);
        int fitr = AlphaApplication.getFiveKilledTrialsHs(this);
        int st = AlphaApplication.getSixKilledTimeHs(this);
        int str = AlphaApplication.getSixKilledTrialsHs(this);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlphaApplication.playPopDiaogButtonSound(BestRecordsActivity.this);
                finish();

            }
        });
        if(ft==-1){
            four_time.append("Nil");
        }else{
            four_time.append(String.valueOf(ft)+" seconds");
        }if(ftr==-1){
            four_trials.append("Nil");
        }else{
            four_trials.append(String.valueOf(ftr));
        }
        if(fit==-1){
            five_time.append("Nil");
        }else{
            five_time.append(String.valueOf(fit)+" seconds");
        }
        if(fitr==-1){
            five_trials.append("Nil");
        }else{
            five_trials.append(String.valueOf(fitr));
        }
        if(st==-1){
            six_time.append("Nil");
        }else{
            six_time.append(String.valueOf(st)+" seconds");
        }
        if(str==-1){
            six_trials.append("Nil");
        }else{
            six_trials.append(String.valueOf(str));
        }

    }
}
