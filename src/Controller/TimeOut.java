package Controller;

import java.util.Random;

public class TimeOut {
    static private final Random random = new Random();

    public static long randomTime(int min, int max){
        return random.nextInt(max+1) + min -1;
    }
}
