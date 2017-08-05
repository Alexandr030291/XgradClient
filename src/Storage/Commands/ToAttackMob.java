package Storage.Commands;

import Controllers.JSBuild;
import Storage.Command;
import Storage.ElementXpath;
import View.MainWindows.Controller;

public class ToAttackMob implements Command {
    private int id;
    private int timeout;

    public ToAttackMob(int id, int timeout){
        this.id=id;
        this.timeout = timeout;
    }

    private String getName(int id){
        switch (id){
            case 0: return "Слабый моб";
            case 1: return "Средний моб";
            case 2: return "Сильный моб";
            default: return "";
        }
    }

    @Override
    public void run(Controller controller) {
        String command ="";
        int _timeout = getRandomTimeout();
        command+= JSBuild.clickElement(ElementXpath.getXPath(ElementXpath.x_paths.BTN_BATTLE),0,_timeout);
        _timeout += getRandomTimeout();
        command+= JSBuild.clickElement(ElementXpath.getXPath(ElementXpath.x_paths.BTN_TO_ATTACK),id,_timeout);
    }

    @Override
    public String getName() {
        return "Атаковать: " + getName(id);
    }

    @Override
    public int getTimeout() {
        return timeout;
    }
}
