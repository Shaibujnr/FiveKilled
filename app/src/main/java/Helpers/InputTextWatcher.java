package Helpers;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by shaibujnr on 7/12/16.
 */
public class InputTextWatcher implements TextWatcher {
    Button SubmitButton;
    ArrayList<Button> inputButtons;
    public InputTextWatcher(Button bsubmit, ArrayList<Button> inputBtns){
        this.SubmitButton = bsubmit;
        this.inputButtons = inputBtns;

    }
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        if(inputFull()){
            SubmitButton.setEnabled(true);
        }else{
            SubmitButton.setEnabled(false);
        }

    }
    private boolean inputFull(){
        for(Button edt:inputButtons){
            if(edt.getText().toString().isEmpty()){
                return false;
            }
        }
        return true;
    }
}
