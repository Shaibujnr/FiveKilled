package Helpers;

/**
 * Created by shaibujnr on 6/13/16.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.FragmentManager;
import android.view.Gravity;
import android.widget.Toast;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.Random;

public class FiveKilledHelper {


    private static final String alphs = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private Random rand = new Random();
    private String generateAlphs(int howMany){
        //generate howMany number of Alphabets
        int nons = howMany;
        String result= "";
        for(int i=0;i<nons;i++){
            result+= alphs.charAt(rand.nextInt(alphs.length()));
        }

        return result;
    }

    public boolean is_valid(String number,int length){
        //returns true if number is unique i.e no alphabet appears more than
        //once in the number and returns false if otherwise
        if(number.length()!=length){
            return false;
        }
        int counter = 0;
        for(int i=0;i<number.length();i++){
            char current = number.charAt(i);
            for(int j=0;j<number.length();j++){
                if(number.charAt(j)==current){
                    counter++;
                }
            }
            if(counter==1){
                counter =0;
            }else{
                return false;
            }
        }
        return true;
    }

    public String generateKeyAlphs(int numberOfKeyAlphs){
        //generates 12 alphabets to be used through out the session of the game
        String result = generateAlphs(numberOfKeyAlphs);
        while(!is_valid(result,numberOfKeyAlphs)){
            result = generateAlphs(numberOfKeyAlphs);
        }

        return result;
    }
    private String generateAlphsFrom(int howMany,String source){
        //generate random string of length howMany from source
        String result = "";
        for(int i=0;i<howMany;i++){
            result+= source.charAt(rand.nextInt(source.length()));
        }
        return result;
    }

    public String generateSpecialAlphs(int numberOfSpecialAlphs,String keyAlphs){
        //generates computers 5 special numbers
        String result = "";
        String from = keyAlphs;
        if(keyAlphs.isEmpty()){
            result =  "";
        }else{
            result = generateAlphsFrom(numberOfSpecialAlphs,from);
            while(!is_valid(result,numberOfSpecialAlphs)){
                result = generateAlphsFrom(numberOfSpecialAlphs,from);
            }
        }
        return result;
    }

    public int killed(String guess,String value){
        int killedCounter = 0;
        if(value.isEmpty() || guess.isEmpty()){
            return -1;
        }else{
            for(int i=0;i<value.length();i++){
                for(int j=0;j<guess.length();j++){
                    if(i==j && value.charAt(i)==guess.charAt(j)){
                        killedCounter++;
                    }
                }
            }
            return killedCounter;
        }

    }

    public int injured(String guess,String value){
        int injuredCounter = 0;
        if(value.isEmpty() || guess.isEmpty()){
            return -1;
        }else{
            for(int i=0;i<value.length();i++){
                for(int j=0;j<guess.length();j++){
                    if(i!=j && value.charAt(i)== guess.charAt(j)){
                        injuredCounter++;
                    }
                }
            }
            return injuredCounter;
        }


    }

    public int none(String guess, String value){
        int noneCounter = 0;
        if(value.isEmpty() || guess.isEmpty()){
            return -1;
        }else{
            for(int j=0;j<guess.length();j++){
                for(int i=0;i<value.length();i++){
                    if(guess.charAt(j) != value.charAt(i)){
                        noneCounter++;
                    }
                }
            }
            if(noneCounter == ( value.length()*guess.length())){
                return value.length();
            }
            return (noneCounter%value.length());
        }

    }
    public void tst(String message, Context context){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();

    }
    public void createInGameDialog(FragmentManager fm, String message){
        FiveKilledDialog fkDialog = new FiveKilledDialog();
        Bundle dialogArgs = new Bundle();
        dialogArgs.putInt("DialogType",2);
        dialogArgs.putString("DialogMessage",message);
        fkDialog.setArguments(dialogArgs);
        fkDialog.show(fm,"");
    } public void createWinDialog( FragmentManager fm, String No_of_calls, String time_taken,String score,boolean irs){
        FiveKilledDialog fkDialog = new FiveKilledDialog();
        fkDialog.setCancelable(false);
        Bundle dialogArgs = new Bundle();
        dialogArgs.putInt("DialogType",3);
        dialogArgs.putString("noc",No_of_calls);
        dialogArgs.putString("tt",time_taken);
        dialogArgs.putString("score",score);
        dialogArgs.putBoolean("is_record_set",irs);
        fkDialog.setArguments(dialogArgs);
        fkDialog.show(fm,"");
    }
    public void createBackButtonDialog(FragmentManager fm){
        FiveKilledDialog fkDialog = new FiveKilledDialog();
        Bundle dialogArgs = new Bundle();
        dialogArgs.putInt("DialogType",4);
        fkDialog.setArguments(dialogArgs);
        fkDialog.show(fm,"");
    }

    public String getResult(String guess,String special){
        int killed = killed(guess,special);
        int injured = injured(guess,special);
        int none = none(guess,special);
        return String.format("%dk %di %dn",killed,injured,none);
    }

    public boolean isWin(String result,int type){
        if(result.equals(""+type+"k 0i 0n")){
            return true;
        }
        else{
            return false;
        }
    }
    public void createBestRecordDialog(FragmentManager fm){
        FiveKilledDialog fkDialog = new FiveKilledDialog();
        Bundle dialogArgs = new Bundle();
        dialogArgs.putInt("DialogType",5);
        fkDialog.setArguments(dialogArgs);
        fkDialog.show(fm,"");
    }






}

