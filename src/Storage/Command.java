package Storage;

import static Controllers.TimeOut.randomTimeMileSec;

public class Command {
    protected String name;
    protected int timeout;
    protected String command;

    public Command(){
        timeout = getRandomTimeout();
        command="";
    }

    public String getName() {
        return name;
    }

    public int getTimeout() {
        return timeout;
    }

    public String getCommand() {
        return command;
    }

    public int getRandomTimeout(){
        int _min_time = Integer.parseInt(OptionsApp.getSetting(OptionsApp.opt.TIME_MIN));
        int _max_time = Integer.parseInt(OptionsApp.getSetting(OptionsApp.opt.TIME_MAX));
        return randomTimeMileSec(_min_time, _max_time);
    }
}

