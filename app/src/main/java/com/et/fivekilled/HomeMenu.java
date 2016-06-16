package com.et.fivekilled;

import android.support.design.widget.FloatingActionButton;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import Helpers.FiveKilledDialog;
<<<<<<< HEAD
=======
import Helpers.fonts.MenuTextView;
>>>>>>> 7eb67770e255301c98cb71bae9455f9b7fec48b0

public class HomeMenu extends AppCompatActivity {
    FloatingActionButton spbtn;
//    FloatingActionButton mpbtn;
    FloatingActionButton mpbtn;
    FragmentManager fm;
<<<<<<< HEAD
=======
    Bundle Dialogargs;
    MenuTextView FiveKilled;
>>>>>>> 7eb67770e255301c98cb71bae9455f9b7fec48b0

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_menu);
        fm = getFragmentManager();
<<<<<<< HEAD

        spbtn = (FloatingActionButton) findViewById(R.id.singlePlayerButton);
//        mpbtn = (FloatingActionButton) findViewById(R.id.multiPlayerButton);
=======
        FiveKilled = (MenuTextView) findViewById(R.id.fk);

        spbtn = (FloatingActionButton) findViewById(R.id.singlePlayerButton);
>>>>>>> 7eb67770e255301c98cb71bae9455f9b7fec48b0
       // mpbtn = (FloatingActionButton) findViewById(R.id.multiPlayerButton);
        spbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FiveKilledDialog fk = new FiveKilledDialog();
<<<<<<< HEAD
=======
                Dialogargs  = new Bundle();
                Dialogargs.putInt("DialogType",1);
                fk.setArguments(Dialogargs);
>>>>>>> 7eb67770e255301c98cb71bae9455f9b7fec48b0
                fk.show(fm,"");
//                Intent i = new Intent(HomeMenu.this,SinglePlayerActivity.class);
//                startActivity(i);
            }
        });
    }
}
