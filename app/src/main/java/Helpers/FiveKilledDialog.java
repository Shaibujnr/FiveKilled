package Helpers;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.widget.ListAdapter;

import android.app.FragmentManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.et.fivekilled.FiveKilledActivity;
import com.et.fivekilled.FourKilledActivity;
import com.et.fivekilled.HomeMenu;
import com.et.fivekilled.R;
import com.et.fivekilled.SixKilledActivity;

import Helpers.fonts.DefaultTextView;
import Helpers.fonts.InGameTextView;


/**
 * Created by AbdulGafar on 6/15/2016.
 */
public class FiveKilledDialog extends DialogFragment {
    FragmentManager fm = getFragmentManager();
    FiveKilledHelper fk = new FiveKilledHelper();


    Bundle DialogArguments;
    ListAdapter listAdapter;



    public void setDaialogType(){

    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int whichDialog = getArguments().getInt("DialogType",0);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        AlertDialog ListDailog = builder.create();
        switch (whichDialog){
            case 0:
                builder.setTitle("Nothing passed bro");
                ListDailog = builder.create();
                break;
            case 1:
                ListDailog = prepareListedDialog();
                break;
            case 2:
                String msg= getArguments().getString("DialogMessage");
                ListDailog = prepareInGameDialog(msg);
                break;
            case 3:
                String No_of_calls = getArguments().getString("noc");
                String time_taken = getArguments().getString("tt");
                String score = getArguments().getString("score");
                ListDailog = prepareWinDialog(No_of_calls,time_taken,score);
                break;
            case 4:
                ListDailog = prepareBackButtonDialog();
                break;

        }

        return ListDailog;
    }


    public AlertDialog prepareListedDialog(){


        String[] mSelected = {"4Killed(Beginners)","5Killed(Intermediate)","6Killed(Pro!)"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),R.style.DialogTheme);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setItems(mSelected,  new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                switch (which){
                    case 0:
                        Intent i = new Intent(getActivity(), FourKilledActivity.class);
                        i.putExtra("difficulty",1);
                        startActivity(i);
                        break;
                    case 1:
                        Intent in = new Intent(getActivity(), FiveKilledActivity.class);
                        in.putExtra("difficulty",2);
                        startActivity(in);
                        break;
                    case 2:
                        Intent iin = new Intent(getActivity(), SixKilledActivity.class);
                        startActivity(iin);
                        break;
                }
            }
        });

        builder.setTitle("Select Difficulty");


        builder.setTitle("Difficulty");
        AlertDialog dialog = builder.create();
        return dialog;
    }
    public AlertDialog prepareInGameDialog(String msg){
        AlertDialog.Builder builder= new AlertDialog.Builder(getActivity(),R.style.DialogTheme);
        LayoutInflater inflater = getActivity().getLayoutInflater();


        View inGameDialogView =inflater.inflate(R.layout.game_dialog,null);
        DefaultTextView dialog_textview = (DefaultTextView) inGameDialogView.findViewById(R.id.dialog_text) ;
        Button action_button = (Button)  inGameDialogView.findViewById(R.id.btnAction);
        Button cancel_button = (Button)  inGameDialogView.findViewById(R.id.btnCancel);
        Button home_button   =  (Button) inGameDialogView.findViewById(R.id.btnHome);
        home_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =  new Intent(getActivity().getApplicationContext(),HomeMenu.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(i);
            }
        });
        dialog_textview.setText(msg);
        action_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fk.tst("Action yet to set",getActivity().getApplicationContext());
            }
        });
        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        builder.setView(inGameDialogView);
        builder.setTitle("5KILLED");

        return builder.create();
    }
    public AlertDialog prepareWinDialog(String No_of_calls, String time_taken, String time_in_sec){
        AlertDialog.Builder builder= new AlertDialog.Builder(getActivity(),R.style.DialogTheme);
        LayoutInflater inflater = getActivity().getLayoutInflater();


        View inGameDialogView =inflater.inflate(R.layout.win_dialog,null);
        InGameTextView NOC_textview = (InGameTextView) inGameDialogView.findViewById(R.id.noc_text) ;
        InGameTextView tt_textview = (InGameTextView) inGameDialogView.findViewById(R.id.tt_text) ;
        InGameTextView score_textview = (InGameTextView) inGameDialogView.findViewById(R.id.score_text) ;
        Button action_button = (Button)  inGameDialogView.findViewById(R.id.btnAction);
        Button home_button   =  (Button) inGameDialogView.findViewById(R.id.btnHome);
        Button review_button = (Button) inGameDialogView.findViewById(R.id.btnReview);

        action_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().recreate();
                dismiss();

            }
        });
        home_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =  new Intent(getActivity().getApplicationContext(),HomeMenu.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });

        review_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(getActivity().getLocalClassName()){
                    case "FourKilledActivity":
                        FourKilledActivity fka = (FourKilledActivity) getActivity();
                        fka.setInReviewMode();
                        dismiss();
                        break;
                    case "FiveKilledActivity":
                        FiveKilledActivity fvk = (FiveKilledActivity) getActivity();
                        fvk.setInReviewMode();
                        dismiss();
                        break;
                    case "SixKilledActivity":
                        SixKilledActivity ska = (SixKilledActivity) getActivity();
                        ska.setInReviewMode();
                        dismiss();
                        break;

                }



            }
        });
        NOC_textview.append(No_of_calls);
        tt_textview.append(time_taken);
        score_textview.append(time_in_sec);
        builder.setView(inGameDialogView);
        builder.setTitle("   YOU WIN!!!   ");

        return builder.create();
    }
    public AlertDialog prepareBackButtonDialog(){
        AlertDialog.Builder builder= new AlertDialog.Builder(getActivity(),R.style.DialogTheme);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View inGameDialogView =inflater.inflate(R.layout.back_button_dialog,null);
        InGameTextView dptxt = (InGameTextView) inGameDialogView.findViewById(R.id.bbd_dtxt);
        Button btnYes = (Button) inGameDialogView.findViewById(R.id.btn_bbd_yes);
        Button btnNo = (Button) inGameDialogView.findViewById(R.id.btn_bbd_no);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(getActivity().getApplicationContext(),HomeMenu.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);

            }
        });
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        builder.setView(inGameDialogView);
        builder.setTitle("Leave Game");
        return builder.create();

    }
}


