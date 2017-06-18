package Controller;


import Storage.ElementXpath;
import Storage.OptionsApp;
import View.MainWindows.Controller;
import javafx.scene.Node;
import netscape.javascript.JSObject;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.xpath.XPathExpressionException;
import java.util.concurrent.TimeUnit;

import static Controller.TimeOut.randomTime;

public class Attack {
    public static boolean stop = false;
    public static Controller controller;
    public static void loop() {
        if (Boolean.valueOf(OptionsApp.getSetting(OptionsApp.opt.ATTACKED))) {
            do {
                int _min_time = Integer.parseInt(OptionsApp.getSetting(OptionsApp.opt.TIME_MIN));
                int _max_time = Integer.parseInt(OptionsApp.getSetting(OptionsApp.opt.TIME_MAX));

                try {
                    JSObject element = (JSObject) controller.getNodeList(ElementXpath.x_paths.BTN_ATTACK);
                   if (element!=null) {
                        element.call("submit");
                        TimeUnit.SECONDS.sleep(randomTime(_min_time, _max_time));
                    }else{
                       stop=!stop;
                   }
                } catch (InterruptedException | XPathExpressionException e) {
                    e.printStackTrace();
                }
            } while (!stop);
            stop = !stop;
        }
    }
}