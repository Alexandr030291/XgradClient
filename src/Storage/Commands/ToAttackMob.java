package Storage.Commands;

import Controllers.JSBuild;
import Storage.Command;
import Storage.ElementXpath;

public class ToAttackMob extends Command{
    public ToAttackMob(int id, int timeout){
        this.timeout = timeout;
        name = "Атаковать: " + getName(id);
        int _timeout = getRandomTimeout();
        command+= JSBuild.clickElement(ElementXpath.getXPath(ElementXpath.x_paths.BTN_BATTLE),0,_timeout);
        _timeout += getRandomTimeout();
        command+= JSBuild.clickElement(ElementXpath.getXPath(ElementXpath.x_paths.BTN_TO_ATTACK),id,_timeout);
    }

    private String getName(int id){
        switch (id){
            case 0: return "Слабый моб";
            case 1: return "Средний моб";
            case 2: return "Сильный моб";
            default: return "";
        }
    }

}
