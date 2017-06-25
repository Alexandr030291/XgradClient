package View.MainWindows;

import Controllers.Attack;
import Controllers.Console;
import Storage.ListCommands;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebView;

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

    public Object runScript(String script){
        return id_browser.getEngine().executeScript(script);
    }

    private void doing(String buffer){
        console.Select(buffer);
    }
}