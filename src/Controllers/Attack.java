package Controllers;


import Storage.OptionsApp;
import View.MainWindows.Controller;

public class Attack {
    public static boolean stop = false;
    public static Controller controller;
    public static void loop() {
        if (Boolean.valueOf(OptionsApp.getSetting(OptionsApp.opt.ATTACKED))) {
            do {
                int _min_time = Integer.parseInt(OptionsApp.getSetting(OptionsApp.opt.TIME_MIN));
                int _max_time = Integer.parseInt(OptionsApp.getSetting(OptionsApp.opt.TIME_MAX));
               /* try {
                    NodeList list = controller.getNodeList(ElementXpath.x_paths.BTN_A_ATTACK);
                    JSObject element = (JSObject) list.item(0);
                   if (element!=null) {
                        element.call("submit");
                        TimeUnit.SECONDS.sleep(randomTimeMileSec(_min_time, _max_time));
                    }else{
                       stop=!stop;
                   }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
            } while (!stop);
            stop = !stop;
        }
    }
}