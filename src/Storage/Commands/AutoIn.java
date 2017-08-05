package Storage.Commands;

import Controllers.JSBuild;
import Storage.Command;
import Storage.ElementXpath;
import Storage.OptionsApp;
import View.MainWindows.Controller;
import netscape.javascript.JSObject;

public class AutoIn implements Command {
    private String login;
    private  String password;
    private int timeout;
    public AutoIn(){
        login  = "\""+ OptionsApp.getSetting(OptionsApp.opt.LOGIN)+"\"";
        password ="\""+OptionsApp.getSetting(OptionsApp.opt.PASSWORD)+"\"";
        timeout = 2*getRandomTimeout();
    }

    @Override
    public void run(Controller controller){
        JSObject[] objects;
        String command="";
        objects = controller.getElements(ElementXpath.getXPath(ElementXpath.x_paths.INPUT_LOGIN));
        JSObject login_fild = objects[0];
        objects = controller.getElements(ElementXpath.getXPath(ElementXpath.x_paths.INPUT_PASSWORD));
        JSObject password_fild = objects[0];
        objects = controller.getElements(ElementXpath.getXPath(ElementXpath.x_paths.BTN_SIGN_IN));
        JSObject btn_sign_in=objects[0];

        login_fild.setMember("value",login);
        password_fild.setMember("value",password);
        btn_sign_in.call("click", (Object) null);
/*
        do{
            objects = controller.getElements(ElementXpath.getXPath(ElementXpath.x_paths.BTN_PLAY));
        }while (objects.length>0);

        objects[0].call("click", (Object) null);*/
    }

    @Override
    public String getName() {
        return "Авторизовать: "+login;
    }

    @Override
    public int getTimeout() {
        return timeout;
    }

}
