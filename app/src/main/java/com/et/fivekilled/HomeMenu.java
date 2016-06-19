package com.et.fivekilled;

import android.support.design.widget.FloatingActionButton;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import Helpers.FiveKilledDialog;

import Helpers.fonts.MenuTextView;


public class HomeMenu extends AppCompatActivity {
    FloatingActionButton spbtn;
    FragmentManager fm;

    Bundle Dialogargs;
    MenuTextView FiveKilled;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_menu);
        fm = getFragmentManager();


        spbtn = (FloatingActionButton) findViewById(R.id.singlePlayerButton);

        FiveKilled = (MenuTextView) findViewById(R.id.fk);

        spbtn = (FloatingActionButton) findViewById(R.id.singlePlayerButton);

        spbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FiveKilledDialog fk = new FiveKilledDialog();

                Dialogargs  = new Bundle();
                Dialogargs.putInt("DialogType",1);
                fk.setArguments(Dialogargs);

                fk.show(fm,"");

            }
        });
    }
}
