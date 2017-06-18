package View.ControlPanel;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class ControlPanel {
    @FXML
    private Pane id_act_tab;
    @FXML
    private Pane id_set_tab;

    @FXML
    public void initialize() throws IOException {
        FXMLLoader loader_set_tab = new FXMLLoader(getClass().getResource("../SettingsTab/SetTab.fxml"));
        FXMLLoader loader_act_tab = new FXMLLoader(getClass().getResource("../ActiveTab/ActTab.fxml"));
        id_set_tab.getChildren().add(loader_set_tab.load());
        id_act_tab.getChildren().add(loader_act_tab.load());
    }


}
