package Helpers;

import android.os.CountDownTimer;
import android.widget.TextView;

/**
 * Created by shaibujnr on 6/19/16.
 */
public class MyCountDownTimer extends CountDownTimer {
    TextView timeLabel;
    int duration;
    int interval;
    public MyCountDownTimer(TextView tlabel,int dur, int inter){
        super(dur,inter);
        this.timeLabel = tlabel;
        this.duration = dur;
        this.interval = inter;

    }
    @Override
    public void onTick(long l) {
        duration-=1000;
        timeLabel.setText(String.valueOf(duration));

    }

    @Override
    public void onFinish() {
        timeLabel.setText("Finished");

    }
}
