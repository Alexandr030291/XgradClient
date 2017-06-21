package Controllers;

import Storage.ElementXpath;
import Storage.OptionsApp;
import View.MainWindows.Controller;
import java.util.Scanner;

import static java.lang.System.exit;

public class Console{
    private Scanner sc = new Scanner(System.in);
    enum com{
        ATTACKED_TRUE,
        OPEN_MAP,
        BTN_SIGN_IN,
        AUTO_IN,
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
            case AUTO_IN: return "AUTO_IN";
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
        if (str.equals(comToString(com.AUTO_IN))){
            return com.AUTO_IN;
        }
        return com.CONTINUE;
    }

    public String getCommand(){
        return sc.nextLine();
    }


    public void Select(String buffer ) {
        if (buffer==null)
            return;
        switch (stringToCom(buffer)) {
            case CONTINUE:
                break;
            case EXIT:
                exit(0);
                break;
            case ATTACKED_TRUE:
                Attack.loop();
                break;
            case OPEN_MAP:
            /*/    NodeList list = controller.getNodeList(ElementXpath.x_paths.OPEN_MAP);
                JSObject element = (JSObject) list.item(0);
                if (element != null) {
                    element.call("submit");
                    //TimeUnit.SECONDS.sleep(randomTime(_min_time, _max_time));
                }*/
                break;
            case BTN_SIGN_IN:
                System.out.print("\t"+controller.runScript(JSBuild.clickElement(ElementXpath.getXPath(ElementXpath.x_paths.BTN_SIGN_IN), 0))+ "\n\n");
                break;
            case AUTO_IN:
                String login  = "\""+OptionsApp.getSetting(OptionsApp.opt.LOGIN)+"\"";
                String password ="\""+OptionsApp.getSetting(OptionsApp.opt.PASSWORD)+"\"";
                controller.runScript(JSBuild.setValueElement(ElementXpath.getXPath(ElementXpath.x_paths.INPUT_LOGIN),login,0));
                controller.runScript(JSBuild.setValueElement(ElementXpath.getXPath(ElementXpath.x_paths.INPUT_PASSWORD),password,0));
                controller.runScript(JSBuild.clickElement(ElementXpath.getXPath(ElementXpath.x_paths.BTN_SIGN_IN), 0));
                controller.runScript(JSBuild.clickElement(ElementXpath.getXPath(ElementXpath.x_paths.BTN_PLAY), 0));
                break;

        }
    }
}
