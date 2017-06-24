import Storage.OptionsApp;
import View.MainWindows.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

import static java.lang.System.exit;

public class Main extends Application {

    @Override
    public void start(final Stage stage) throws Exception {
        final int window_width_min = 960;
        final int window_height_min = 640;
        URL recourse = getClass().getResource("View/MainWindows/Layout.fxml");
        stage.setTitle("Клиент для онлайн-игры Тайный город. Создано при поддержке темного двора");
        FXMLLoader fxmlLoader = new FXMLLoader(recourse);
        Parent root = fxmlLoader.load();
        Controller controller = fxmlLoader.getController();
        controller.start();
        Scene scene = new Scene(root, window_width_min, window_height_min);
        stage.setScene(scene);
        stage.setMinWidth(window_width_min);
        stage.setMinHeight(window_height_min);
        stage.setOnCloseRequest(we -> exit(0));
        stage.show();
    }


    public static void main(String[] args) {
        OptionsApp.main(args);
        Application.launch(args);
    }


}
