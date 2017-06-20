package Controller;

import Storage.ElementXpath;
import View.MainWindows.Controller;
import netscape.javascript.JSObject;
import org.w3c.dom.NodeList;
import java.util.Scanner;

import static java.lang.System.exit;

public class Console implements Runnable {
    enum com{
        ATTACKED_TRUE,
        OPEN_MAP,
        BTN_SIGN_IN,
        EXIT,
        CONTINUE
    }

    public Controller controller;

    private String comToString(com _com){
        switch (_com){
            case EXIT: return "EXIT";
            case ATTACKED_TRUE: return "ATTACKED_TRUE";
            case OPEN_MAP: return "OPEN_MAP";
            case BTN_SIGN_IN: return "BTN_SIGN_IN";
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
        if (str.equals(comToString(com.OPEN_MAP))){
            return com.OPEN_MAP;
        }
        if (str.equals(comToString(com.BTN_SIGN_IN))){
            return com.BTN_SIGN_IN;
        }
        return com.CONTINUE;
    }

    public void run(){
        loop();
    }


    private void loop(){
        String buffer;
        Scanner sc = new Scanner(System.in);
        boolean stop = false;
        while (!stop){
            buffer = sc.nextLine();
            switch (stringToCom(buffer)){
                case CONTINUE: break;
                case EXIT:
                    stop= true;
                    break;
                case ATTACKED_TRUE:
                    Attack.loop();
                    break;
                case OPEN_MAP:
                    NodeList list = controller.getNodeList(ElementXpath.x_paths.OPEN_MAP);
                    JSObject element = (JSObject) list.item(0);
                    if (element!=null) {
                        element.call("submit");
                        //TimeUnit.SECONDS.sleep(randomTime(_min_time, _max_time));
                    }
                    break;
                case BTN_SIGN_IN:/*
                    list = controller.getNodeList(ElementXpath.x_paths.BTN_SIGN_IN);
                    element = (JSObject) list.item(0);
                    if (element!=null) {
                        element.call("submit");
                        //TimeUnit.SECONDS.sleep(randomTime(_min_time, _max_time));
                    }*/
                    controller.runScript(JSBuild.clickElement(ElementXpath.getXPath(ElementXpath.x_paths.BTN_SIGN_IN),0));
                    break;
            }
        }
        stop();
    }

    public void stop(){
        exit(0);
    }
}
