package Controllers;

import Storage.Command;
import Storage.ListCommands;
import javafx.application.Platform;
import javafx.concurrent.Task;

import java.util.concurrent.TimeUnit;

public class Bot {
    private static Libs.Queue<Command> queue_command;
    private static boolean stop = false;
    private static boolean run =false;

    public static void run() {
        if (!Bot.run) {
            Task<Void> com = new Task<Void>() {
                @Override
                protected Void call() {
                    while (!stop&& !ListCommands.isEmpty()){
                        int timeout = ListCommands.getTimeFistCommand();
                        Platform.runLater(ListCommands::run);
                        try {
                            TimeUnit.SECONDS.sleep(timeout);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    stop = false;
                    run = false;
                    return null;
                }
            };
            Thread thread = new Thread(com);
            thread.start();
            run = true;
        }
    }

    public static void stop(){
        stop =true;
    }

}
