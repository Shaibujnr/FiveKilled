package Helpers;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.v4.app.FragmentManager;
import android.widget.TextView;

/**
 * Created by shaibujnr on 6/23/16.
 */
public class CountDownHelper extends CountDownTimer {
    TextView timeLabel;
    long timeInMinutes;
    long interval;
    FiveKilledHelper fk;
    Context callingContext;
    android.app.FragmentManager fm;
    public CountDownHelper(long millisInFuture, long countDownInterval, TextView tlabel, Context context) {
        super(millisInFuture, countDownInterval);
        this.timeInMinutes = millisInFuture;
        this.interval = countDownInterval;
        this.timeLabel = tlabel;
        this.callingContext = context;
        this.fm = fm;
    }

    @Override
    public void onTick(long l) {
        this.timeLabel.setText(StaticHelpers.getTimeFormat(l));

    }

    @Override
    public void onFinish() {
        this.timeLabel.setText("00:00");
//        fk.createInGsmeDialog(callingContext,fm,"Time Up");


    }
}


