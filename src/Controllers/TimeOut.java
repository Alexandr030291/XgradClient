package Controllers;

import java.util.Random;

public class TimeOut {
    static private final Random random = new Random();

    public static int randomTimeMileSec(int min_time_sec, int max_time_sec){
        final int mile = 1000;
        final int pol_mile = mile/2;
        return random.nextInt((max_time_sec-min_time_sec)*mile+pol_mile) + min_time_sec*mile - pol_mile;
    }
}
