package com.et.fivekilled;

import android.content.Intent;
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
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Games;
import com.google.example.games.basegameutils.BaseGameActivity;

import Helpers.FiveKilledDialog;

import Helpers.fonts.MenuTextView;


public class HomeMenu extends BaseGameActivity {
    FloatingActionButton spbtn;
    FloatingActionButton mpbtn;
    FloatingActionButton hbtn,hscore_btn;
    FloatingActionButton showLeaderBoardButton;
    FloatingActionButton showAchievementButton;
    FloatingActionButton soutButton;
    FragmentManager fm;
    Bundle Dialogargs;
    EtTextView FiveKilled;
    Animation anim_bounce, anim_flash,anim_sway;
    GoogleApiClient mClient;

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
        mClient = getApiClient();
        FiveKilled = (EtTextView) findViewById(R.id.fk);
        FiveKilled.setAnimation(anim_bounce);
        spbtn = (FloatingActionButton) findViewById(R.id.singlePlayerButton);
        hbtn = (FloatingActionButton) findViewById(R.id.helpButton);
        showLeaderBoardButton = (FloatingActionButton) findViewById(R.id.lboardButton);
        soutButton = (FloatingActionButton) findViewById(R.id.soutButton);
        hscore_btn = (FloatingActionButton) findViewById(R.id.high_score_button);
        showAchievementButton = (FloatingActionButton) findViewById(R.id.show_achievement_button);

        showAchievementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mClient.isConnected()){
                    startActivityForResult(Games.Achievements.getAchievementsIntent(getApiClient()),3);
                }
            }
        });


        soutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mClient.isConnected()){
                    signOut();
                    Toast.makeText(HomeMenu.this,"Sign out done",Toast.LENGTH_SHORT).show();

                }
                else{
                    beginUserInitiatedSignIn();

                }


            }
        });

        showLeaderBoardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mClient.isConnected()){
                    Toast.makeText(HomeMenu.this,"Displaying leaderBoard",Toast.LENGTH_SHORT).show();
                    startActivityForResult(Games.Leaderboards.getAllLeaderboardsIntent(getApiClient()),2);
                }




            }
        });

       // mpbtn = (FloatingActionButton) findViewById(R.id.multiPlayerButton);
        hbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeMenu.this,HelpActivity.class);
                startActivity(i);
            }
        });
        spbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spbtn.setAnimation(anim_flash);
                FiveKilledDialog fk = new FiveKilledDialog();
                Dialogargs  = new Bundle();
                Dialogargs.putInt("DialogType",1);
                fk.setArguments(Dialogargs);
                fk.show(fm,"");

            }
        });

        hscore_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FiveKilledDialog fk = new FiveKilledDialog();
                Dialogargs  = new Bundle();
                Dialogargs.putInt("DialogType",5);
                fk.setArguments(Dialogargs);
                fk.show(fm,"");

            }
        });

        AlphaApplication.setSignedUser(mClient);
    }



    @Override
    public void onSignInFailed() {
        Toast.makeText(this,"sign in failed: unable to sign in",Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onSignInSucceeded() {
        Toast.makeText(this,"Sign in succesful",Toast.LENGTH_SHORT).show();


    }
}
