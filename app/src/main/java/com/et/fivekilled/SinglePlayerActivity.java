package com.et.fivekilled;

import android.content.Intent;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import Helpers.FiveKilledHelper;
import Helpers.FiveKilledDialog;

public class SinglePlayerActivity extends AppCompatActivity {
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
    }

}
