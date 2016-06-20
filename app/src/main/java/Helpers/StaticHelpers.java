package Helpers;

import java.util.concurrent.TimeUnit;

/**
 * Created by shaibujnr on 6/19/16.
 */
public class StaticHelpers {
    public static String getTimeFormat(long difficultyTime) {
        return String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(difficultyTime),
                TimeUnit.MILLISECONDS.toSeconds(difficultyTime)-
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(difficultyTime)));

    }
}
