import Storage.ElementXpath;
import Storage.OptionsApp;
import View.MainWindows.Controller;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import Controller.Console;
import netscape.javascript.JSObject;
import org.w3c.dom.Node;

import javax.swing.plaf.nimbus.State;

public class Main extends Application {

    @Override
    public void start(final Stage stage) throws Exception {
          final int window_width_min = 960;
        final int window_height_min = 640;
        URL recourse = getClass().getResource("View/MainWindows/Layout.fxml");

        stage.setTitle("Клиент для онлайн-игры Тайный город");
        FXMLLoader fxmlLoader = new FXMLLoader(recourse);
        Parent root = fxmlLoader.load();
        Controller сontroller = fxmlLoader.getController();
        сontroller.start();
        Scene scene = new Scene(root, window_width_min, window_height_min);
        stage.setScene(scene);
        stage.setMinWidth(window_width_min);
        stage.setMinHeight(window_height_min);
        stage.show();
        Console console = new Console();
        console.controller = сontroller;
        Thread thread = new Thread(console);
        thread.start();
        stage.setOnCloseRequest(we -> console.stop());
    }

    public static void main(String[] args) {
        OptionsApp.main(args);
        ElementXpath.main(args);
        Application.launch(args);
    }


}
