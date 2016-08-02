package com.et.fivekilled;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by shaibujnr on 7/30/16.
 */
public class AlphaApplication extends Application {
    private static GoogleApiClient signedUser;





    public static GoogleApiClient getSignedUser() {
        return signedUser;
    }

    public static void setSignedUser(GoogleApiClient signedUser) {
        AlphaApplication.signedUser = signedUser;
    }

    @Override
    public void onCreate() {
        super.onCreate();


    }
    public static void setFourKilledTimeHs(Context act,int time){
        SharedPreferences sp= act.getSharedPreferences(act.getString(R.string.shared_pref),MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(act.getString(R.string.four_time),time);
        editor.commit();
    }public static void setFourKilledTrialsHs(Context act,int trials){
        SharedPreferences sp= act.getSharedPreferences(act.getString(R.string.shared_pref),MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(act.getString(R.string.four_trial),trials);
        editor.commit();
    }public static void setFiveKilledTimeHs(Context act,int time){
        SharedPreferences sp= act.getSharedPreferences(act.getString(R.string.shared_pref),MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(act.getString(R.string.five_time),time);
        editor.commit();
    }public static void setFiveKilledTrialsHs(Context act,int trials){
        SharedPreferences sp= act.getSharedPreferences(act.getString(R.string.shared_pref),MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(act.getString(R.string.five_trial),trials);
        editor.commit();
    }
    public static void setSixKilledTimeHs(Context act,int time){
        SharedPreferences sp= act.getSharedPreferences(act.getString(R.string.shared_pref),MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(act.getString(R.string.six_time),time);
        editor.commit();
    }
    public static void setSixKilledTrialsHs(Context act,int trials){
        SharedPreferences sp= act.getSharedPreferences(act.getString(R.string.shared_pref),MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(act.getString(R.string.six_trial),trials);
        editor.commit();
    }

    public static int getFourKilledTimeHs(Context act){
        SharedPreferences sp= act.getSharedPreferences(act.getString(R.string.shared_pref),MODE_PRIVATE);
        return sp.getInt(act.getString(R.string.four_time),-1);
    }
    public static int getFourKilledTrialsHs(Context act){
        SharedPreferences sp= act.getSharedPreferences(act.getString(R.string.shared_pref),MODE_PRIVATE);
        return sp.getInt(act.getString(R.string.four_trial),-1);
    }
    public static int getFiveKilledTimeHs(Context act){
        SharedPreferences sp= act.getSharedPreferences(act.getString(R.string.shared_pref),MODE_PRIVATE);
        return sp.getInt(act.getString(R.string.five_time),-1);
    }
    public static int getFiveKilledTrialsHs(Context act){
        SharedPreferences sp= act.getSharedPreferences(act.getString(R.string.shared_pref),MODE_PRIVATE);
        return sp.getInt(act.getString(R.string.five_trial),-1);
    }
    public static int getSixKilledTimeHs(Context act){
        SharedPreferences sp= act.getSharedPreferences(act.getString(R.string.shared_pref),MODE_PRIVATE);
        return sp.getInt(act.getString(R.string.six_time),-1);
    }
    public static int getSixKilledTrialsHs(Context act){
        SharedPreferences sp= act.getSharedPreferences(act.getString(R.string.shared_pref),MODE_PRIVATE);
        return sp.getInt(act.getString(R.string.six_trial),-1);
    }
}
