package com.et.fivekilled;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import Helpers.FiveKilled;

public class SinglePlayerActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_player);

        Intent i = getIntent();
        int difficulty = i.getIntExtra("difficulty",0);
        Toast.makeText(this,""+difficulty,Toast.LENGTH_SHORT).show();

    }
}
