package Helpers;

/**
 * Created by shaibujnr on 6/19/16.
 */
import java.util.concurrent.TimeUnit;
public class TimeHelper {
    public static void main(String[] x){
        int timeInMilli = 500000;
        System.out.println(TimeUnit.MILLISECONDS.toMinutes(timeInMilli));
        System.out.println(TimeUnit.MILLISECONDS.toSeconds(timeInMilli));
    }
}
