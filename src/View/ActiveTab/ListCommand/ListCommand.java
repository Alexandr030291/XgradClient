package View.ActiveTab.ListCommand;

import Storage.ListCommands;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;

public class ListCommand {
    @FXML
    private ListView<String> id_list_view;

    @FXML
    public void initialize() throws IOException {
        id_list_view.setItems(ListCommands.getNameList());
    }

    public void updateList(){

    }
}
