package com.et.fivekilled;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HomeMenu extends AppCompatActivity {
    FloatingActionButton spbtn;
    FloatingActionButton mpbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_menu);

        spbtn = (FloatingActionButton) findViewById(R.id.singlePlayerButton);
        mpbtn = (FloatingActionButton) findViewById(R.id.multiPlayerButton);
        spbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeMenu.this,SinglePlayerActivity.class);
                startActivity(i);
            }
        });
    }
}
