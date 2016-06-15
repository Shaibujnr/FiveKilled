package Helpers;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import com.et.fivekilled.R;
import com.et.fivekilled.SinglePlayerActivity;

import java.util.ArrayList;

/**
 * Created by AbdulGafar on 6/15/2016.
 */
public class FiveKilledDialog extends DialogFragment {
    Bundle DialogArguments;
    ListAdapter listAdapter;


    public void setDaialogType(){

    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
       AlertDialog ListDailog = prepareListedDialog();
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
                        i.putExtra("difficulty",1);
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
        builder.setTitle("Select Difficulty");
//        builder.setView(inflater.inflate(R.layout.dialog,null));
        return builder.create();
    }
}


