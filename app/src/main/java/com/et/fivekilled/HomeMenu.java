package com.et.fivekilled;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import Helpers.FiveKilledDialog;

public class HomeMenu extends AppCompatActivity {
    FloatingActionButton spbtn;
    FloatingActionButton mpbtn;
    FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_menu);
        fm = getFragmentManager();

        spbtn = (FloatingActionButton) findViewById(R.id.singlePlayerButton);
       // mpbtn = (FloatingActionButton) findViewById(R.id.multiPlayerButton);
        spbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FiveKilledDialog fk = new FiveKilledDialog();
                fk.show(fm,"");
//                Intent i = new Intent(HomeMenu.this,SinglePlayerActivity.class);
//                startActivity(i);
            }
        });
    }
}
