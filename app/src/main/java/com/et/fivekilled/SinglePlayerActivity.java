package com.et.fivekilled;


import android.app.Activity;
import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.content.Intent;
import android.renderscript.ScriptGroup;

import android.content.Intent;
import android.app.FragmentManager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


import Helpers.FiveKilledHelper;
import Helpers.FiveKilledDialog;

public class SinglePlayerActivity extends AppCompatActivity {

//    Keyboard mKeyboard;
//    KeyboardView mKeyboardView;

    DisplayMetrics dm = new DisplayMetrics();
    int ScreenHeight;
    int ScreenWidth;


    TextView timeLabel;
    EditText gone, gtwo, gthree, gfour, gfive;
    Button submit;
    TextView errorLabel;
    GridLayout display;
    GridLayout keyPad;
    RelativeLayout root;


    private Button btnSubmit;
    FragmentManager fm;
    FiveKilledDialog fkDialog;
    Bundle dialogArgs;
    FiveKilledHelper fk;
    ArrayList<EditText> inputs;
    ArrayList<Button> keyPadButtons;

    View.OnClickListener inputListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_player);
        fkDialog = new FiveKilledDialog();
        dialogArgs = new Bundle();
        fk = new FiveKilledHelper();
        fm = getFragmentManager();
        inputs = new ArrayList<EditText>();
        keyPadButtons = new ArrayList<Button>();


        getWindowManager().getDefaultDisplay().getMetrics(dm);
        ScreenHeight = dm.heightPixels;
        ScreenWidth = dm.widthPixels;

        gone = (EditText) findViewById(R.id.gone);
        inputs.add(gone);
        gtwo = (EditText) findViewById(R.id.gtwo);
        inputs.add(gtwo);
        gthree = (EditText) findViewById(R.id.gthree);
        inputs.add(gthree);
        gfour = (EditText) findViewById(R.id.gfour);
        inputs.add(gfour);
        gfive = (EditText) findViewById(R.id.gfive);
        inputs.add(gfive);


        display = (GridLayout) findViewById(R.id.display);
        keyPad = (GridLayout) findViewById(R.id.keyPad);
        timeLabel = (TextView) findViewById(R.id.timer);
        root = (RelativeLayout) findViewById(R.id.root);


        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fk.createInGsmeDialog(SinglePlayerActivity.this, fm, "This is a test String");
            }


        });

        inputListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText clickedEditText = (EditText) view;
                String clickedText = clickedEditText.getText().toString();
                clickedEditText.setText("");

                for (Button btn : keyPadButtons) {
                    if (btn.getText().toString().equals(clickedText)) {
                        btn.setEnabled(true);
                    }
                }


            }
        };

        setDisplay();
        setKeyAlphs(fk.generateKeyAlphs(12));
        setInput();


    }

    public void setKeyAlphs(String alphs) {
        int mag = 2;
        int row = 2;
        int col = (alphs.length() / row);


        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                GridLayout.LayoutParams lp = new GridLayout.LayoutParams();

                lp.setMargins(mag, mag, mag, mag);
                lp.height = ScreenHeight / 10;
                lp.width = (ScreenWidth - (mag * (col + 3))) / col;
                lp.rowSpec = GridLayout.spec(i);
                lp.columnSpec = GridLayout.spec(j);
                final Button btn = new Button(this);
                btn.setLayoutParams(lp);
                btn.setBackgroundResource(R.drawable.keypad_button);
                btn.setText(String.valueOf(alphs.charAt(((6 * i) + j))));
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String clickedText = btn.getText().toString();
                        EditText availableEdit = getEmptyEdit();
                        if (availableEdit != null) {
                            availableEdit.setText(clickedText);
                            btn.setEnabled(false);
                        }


                    }
                });
                keyPadButtons.add(btn);
                keyPad.addView(btn);
            }


        }


    }

    public void setDisplay() {
//        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams();
//        lp.ScreenHeight-keyPad.getHeight()-submit.getHeight()-timeLabel.getHeight());
//        display.setMinimumWidth(ScreenWidth);
//        display.setLayoutParams();
    }

    public void setInput() {
        int mag = 2;
        int textWidth = (ScreenWidth - (7 * mag)) / 7;
        int textHeight = (ScreenHeight) / 12;
        gone.setInputType(InputType.TYPE_NULL);
        gtwo.setInputType(InputType.TYPE_NULL);
        gthree.setInputType(InputType.TYPE_NULL);
        gfour.setInputType(InputType.TYPE_NULL);
        gfive.setInputType(InputType.TYPE_NULL);

        gone.setHeight(textHeight);
        gone.setWidth(textWidth);
        gone.setOnClickListener(inputListener);

        gtwo.setHeight(textHeight);
        gtwo.setWidth(textWidth);
        gtwo.setOnClickListener(inputListener);

        gthree.setHeight(textHeight);
        gthree.setWidth(textWidth);
        gthree.setOnClickListener(inputListener);

        gfour.setHeight(textHeight);
        gfour.setWidth(textWidth);
        gfour.setOnClickListener(inputListener);

        gfive.setHeight(textHeight);
        gfive.setWidth(textWidth);
        gfive.setOnClickListener(inputListener);


    }

    public EditText getEmptyEdit() {
        for (EditText edt : inputs) {
            if (edt.getText().toString().isEmpty()) {
                return edt;
            }
        }
        return null;

    }
}

