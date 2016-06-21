package Helpers;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.et.fivekilled.R;
import com.et.fivekilled.SinglePlayerActivity;

import Helpers.fonts.DefaultTextView;

/**
 * Created by AbdulGafar on 6/15/2016.
 */
public class FiveKilledDialog extends DialogFragment {
    FragmentManager fm = getFragmentManager();
    FiveKilledHelper fk = new FiveKilledHelper();
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
                break;

        }
        return ListDailog;
    }


    public AlertDialog prepareListedDialog(){


        String[] mSelected = {"Easy","Mediim","Hard"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setItems(mSelected,  new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(getActivity(), SinglePlayerActivity.class);
                switch (which){
                    case 0:
                        i.putExtra("Select difficulty",1);
                        startActivity(i);
                        break;
                    case 1:
                        i.putExtra("difficulty",2);
                        startActivity(i);
                        break;
                    case 2:
                        i.putExtra("difficulty",3);
                        startActivity(i);

                        break;
                }
            }
        });
        builder.setTitle("Difficulty");
        AlertDialog dialog = builder.create();
        return dialog;
    }
    public AlertDialog prepareInGameDialog(String msg){
        AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View inGameDialogView =inflater.inflate(R.layout.dialog,null);
        DefaultTextView dialog_textview = (DefaultTextView) inGameDialogView.findViewById(R.id.dialog_text) ;
        ImageButton action_button = (ImageButton)  inGameDialogView.findViewById(R.id.btnAction);
        ImageButton cancel_button = (ImageButton)  inGameDialogView.findViewById(R.id.btnCancel);
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

        builder.setTitle("CUSTOMIZABLE IN GAME DIALOG");
//        builder.sets
        return builder.create();
    }
}


