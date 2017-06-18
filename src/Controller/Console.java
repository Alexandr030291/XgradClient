package Controller;

import java.util.Scanner;

import static java.lang.System.exit;

public class Console implements Runnable {
    enum com{
        ATTACKED_TRUE,
        EXIT,
        CONTINUE
    }

    private boolean Stop = false;
    private Scanner sc;
    private String comToString(com _com){
        switch (_com){
            case EXIT: return "EXIT";
            case ATTACKED_TRUE: return "ATTACKED_TRUE";
        }
        return "CONTINUE";
    }

    private com stringToCom(String str){
        if (str.equals(comToString(com.EXIT))){
            return com.EXIT;
        }
        if (str.equals(comToString(com.ATTACKED_TRUE))){
            return com.ATTACKED_TRUE;
        }
        return com.CONTINUE;
    }

    public void run(){
        loop();
    }


    private void loop(){
        String buffer;
        sc=new Scanner(System.in);
        while (!Stop){
            buffer = sc.nextLine();
            switch (stringToCom(buffer)){
                case CONTINUE: break;
                case EXIT:
                    Stop=!Stop;
                    break;
                case ATTACKED_TRUE:
                    Attack.loop();
                    break;
            }
        }
        stop();
    }

    public void stop(){
        exit(0);
    }
}
