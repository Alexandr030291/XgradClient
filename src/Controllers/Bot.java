package Controllers;

import Storage.Command;

import java.util.concurrent.TimeUnit;

public class Bot {
    private static Libs.Queue<Command> queue_command;
    private static int value=0;
    private static int last=0;
    private static Command command;
    public static boolean Stop = false;

    public static void run() throws InterruptedException {
        while (!Stop){
            command=queue_command.pop();
            select();
            TimeUnit.SECONDS.sleep(command.getTimeout());
            Stop=!(queue_command.size()==0);
        }
    }

    private static void select(){
        switch (command.getCommand()){
            case ATTACKED:
                Attack.loop();
                break;

        }
    }

}
