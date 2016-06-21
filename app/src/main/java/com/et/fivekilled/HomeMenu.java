package com.et.fivekilled;

import android.animation.Animator;
import android.support.design.widget.FloatingActionButton;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import Helpers.FiveKilledDialog;
import Helpers.fonts.EtTextView;
import Helpers.fonts.MenuTextView;

public class HomeMenu extends AppCompatActivity {
    FloatingActionButton spbtn;
    FloatingActionButton mpbtn;
    FragmentManager fm;
    Bundle Dialogargs;
    EtTextView FiveKilled;
    Animation anim_bounce, anim_flash,anim_sway;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_menu);
        anim_flash = AnimationUtils.loadAnimation(this, R.anim.anim_flash);
        anim_bounce = AnimationUtils.loadAnimation(this, R.anim.anim_bounce);
        anim_sway = AnimationUtils.loadAnimation(this, R.anim.anim_sway);
        anim_sway.setDuration(3000);
        anim_bounce.setDuration(2000);
        anim_bounce.setDuration(3000);
        anim_bounce.setRepeatMode(Animation.REVERSE);
        fm = getFragmentManager();
        FiveKilled = (EtTextView) findViewById(R.id.fk);
        FiveKilled.setAnimation(anim_bounce);
       spbtn = (FloatingActionButton) findViewById(R.id.singlePlayerButton);

       // mpbtn = (FloatingActionButton) findViewById(R.id.multiPlayerButton);
        spbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spbtn.setAnimation(anim_flash);
                FiveKilledDialog fk = new FiveKilledDialog();
                Dialogargs  = new Bundle();
                Dialogargs.putInt("DialogType",1);
                fk.setArguments(Dialogargs);
                fk.show(fm,"");
//                Intent i = new Intent(HomeMenu.this,SinglePlayerActivity.class);
//                startActivity(i);
            }
        });
    }
}
