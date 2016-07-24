package Helpers;

import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by shaibujnr on 7/12/16.
 */
public class InputListener implements View.OnClickListener {
    ArrayList<Button> kpb;
    public InputListener(ArrayList<Button> keyPadBtns){
        this.kpb = keyPadBtns;
    }
    @Override
    public void onClick(View view) {
        Button clickedInput= (Button) view;
        String clickedText = clickedInput.getText().toString();
        clickedInput.setText("");

        for (Button btn : kpb) {
            if (btn.getText().toString().equals(clickedText)) {
                btn.setEnabled(true);
            }
        }

    }
}
