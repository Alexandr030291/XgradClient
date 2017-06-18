package View.SettingsTab;

import Storage.OptionsApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;

public class SetTab {
    @FXML
    private Button id_btn_save;
    @FXML
    private Pane id_time_win;

    @FXML
    public void initialize() throws IOException {
        URL url_time_window= getClass().getResource("./TimeWindows/TimeWindow.fxml");

        FXMLLoader loader_set_tab = new FXMLLoader(url_time_window);
        id_time_win.getChildren().add(loader_set_tab.load());
        id_btn_save.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> onClicked());
    }

    @FXML
    private void onClicked(){
        OptionsApp.update();
    }
}
