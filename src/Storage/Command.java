package Storage;

import View.MainWindows.Controller;

import static Controllers.TimeOut.randomTimeMileSec;

public interface Command {
    default int getRandomTimeout(){
        int _min_time = Integer.parseInt(OptionsApp.getSetting(OptionsApp.opt.TIME_MIN));
        int _max_time = Integer.parseInt(OptionsApp.getSetting(OptionsApp.opt.TIME_MAX));
        return randomTimeMileSec(_min_time, _max_time);
    }
    void run(Controller controller);
    String getName();
    int getTimeout();
}

