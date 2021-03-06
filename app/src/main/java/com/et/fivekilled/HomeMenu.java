package com.et.fivekilled;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import Helpers.CurvedTextView;
import Helpers.FiveKilledDialog;
import Helpers.FiveKilledHelper;
import Helpers.fonts.EtTextView;
import Helpers.fonts.MenuTextView;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Games;
import com.google.example.games.basegameutils.BaseGameActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Helpers.FiveKilledDialog;

import Helpers.fonts.MenuTextView;


public class HomeMenu extends BaseGameActivity {
    FloatingActionButton spbtn;
    FloatingActionButton mpbtn;
    FloatingActionButton hbtn,hscore_btn;
    FloatingActionButton showLeaderBoardButton;
    FloatingActionButton showAchievementButton;
    FragmentManager fm;
    Bundle Dialogargs;
    EtTextView FiveKilled;
    GoogleApiClient mClient;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_menu);
        fm = getFragmentManager();
        mClient = getApiClient();
        FiveKilled = (EtTextView) findViewById(R.id.fk);
        //FiveKilled.setAnimation(anim_flash );
        spbtn = (FloatingActionButton) findViewById(R.id.singlePlayerButton);
        hbtn = (FloatingActionButton) findViewById(R.id.helpButton);
        showLeaderBoardButton = (FloatingActionButton) findViewById(R.id.lboardButton);
        hscore_btn = (FloatingActionButton) findViewById(R.id.high_score_button);
        showAchievementButton = (FloatingActionButton) findViewById(R.id.show_achievement_button);





        showAchievementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlphaApplication.playMenuButtonClickSound(HomeMenu.this);
                if(mClient.isConnected()){
                    startActivityForResult(Games.Achievements.getAchievementsIntent(getApiClient()),3);
                }
            }
        });



        showLeaderBoardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlphaApplication.playMenuButtonClickSound(HomeMenu.this);
                if(mClient.isConnected()){
                    startActivityForResult(Games.Leaderboards.getAllLeaderboardsIntent(getApiClient()),2);
                }




            }
        });

        hbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlphaApplication.playMenuButtonClickSound(HomeMenu.this);
                Intent i = new Intent(HomeMenu.this,TutorialActivity.class);
                startActivity(i);
            }
        });
        spbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlphaApplication.playMenuButtonClickSound(HomeMenu.this);
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
                AlphaApplication.playMenuButtonClickSound(HomeMenu.this);
                FiveKilledHelper fk = new FiveKilledHelper();
                fk.createBestRecordDialog(getFragmentManager());

            }
        });

        AlphaApplication.setSignedUser(mClient);
        AdView bannerAdview = (AdView) findViewById(R.id.home_admob_banner);
        AdRequest adrec = new AdRequest.Builder().addTestDevice(getString(R.string.shaibu_did)).build();
        bannerAdview.loadAd(adrec);



    }






    @Override
    public void onSignInFailed() {



    }

    @Override
    public void onSignInSucceeded() {



    }
}
