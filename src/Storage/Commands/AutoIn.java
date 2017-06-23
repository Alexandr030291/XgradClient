package Storage.Commands;

import Controllers.JSBuild;
import Storage.Command;
import Storage.ElementXpath;
import Storage.OptionsApp;

public class AutoIn extends Command {
    public AutoIn(){
        String login  = "\""+ OptionsApp.getSetting(OptionsApp.opt.LOGIN)+"\"";
        String password ="\""+OptionsApp.getSetting(OptionsApp.opt.PASSWORD)+"\"";
        int timeout = getRandomTimeout();
        command+= JSBuild.setValueElement(ElementXpath.getXPath(ElementXpath.x_paths.INPUT_LOGIN),login,0,timeout);
        timeout += getRandomTimeout();
        command+= JSBuild.setValueElement(ElementXpath.getXPath(ElementXpath.x_paths.INPUT_PASSWORD),password,0,timeout);
        timeout += getRandomTimeout();
        command+= JSBuild.clickElement(ElementXpath.getXPath(ElementXpath.x_paths.BTN_SIGN_IN),0,timeout);
        timeout += getRandomTimeout();
        command+= JSBuild.clickElement(ElementXpath.getXPath(ElementXpath.x_paths.BTN_PLAY),0,timeout);
        name = "Авторизовать: "+login;
    }
}
