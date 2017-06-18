import Storage.ElementXpath;
import Storage.OptionsApp;
import View.MainWindows.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;


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
    }

    public static void main(String[] args) {
        OptionsApp.main(args);
        ElementXpath.main(args);
        Application.launch(args);
    }


}
