package View.MainWindows;

import Controllers.Attack;
import Controllers.Console;
import Controllers.JSBuild;
import Storage.ListCommands;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;

import java.io.IOException;

public class Controller {
    private  Console console = new Console();
    @FXML
    private Pane id_control_panel;

    @FXML
    private WebView id_browser;

    @FXML
    public void initialize() throws IOException {
        id_control_panel.getChildren().add(FXMLLoader.
                load(getClass().
                        getResource("../ControlPanel/ControlPanel.fxml")));
        Attack.controller = this;
        ListCommands.setController(this);

    }

    public void start() {
        id_browser.getEngine().setJavaScriptEnabled(true);
        id_browser.getEngine().load("http://app01.x-grad.com/start/");
        /*console.controller = this;
        Task<Void> com = new Task<Void>() {
            @Override
            protected Void call() {
                while (true) {
                    String buffer = console.getCommand();
                    Platform.runLater(() -> doing(buffer));
                }
            }
        };
        Thread thread = new Thread(com);
        thread.start();*/
    }

    public JSObject[] getElements(String xpath){
        String script = JSBuild.getElements(xpath);
        JSObject object = (JSObject)id_browser.getEngine().executeScript(script);
        int length = (int) object.getMember("length");
        JSObject[] result = new JSObject[length];
        for (int i=0; i<length;i++){
            result[i] = (JSObject) object.getSlot(i);
        }
        return result;
    }

    public Object runScript(String script){
        return id_browser.getEngine().executeScript(script);
    }

    private void doing(String buffer){
        console.Select(buffer);
    }
}