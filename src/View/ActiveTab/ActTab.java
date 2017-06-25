package View.ActiveTab;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;

public class ActTab {
    @FXML
    private Pane id_list_command;
    @FXML
    private Pane id_auto_in;

    @FXML
    public void initialize() throws IOException {
        URL url_auto_in= getClass().getResource("./Authorised/AutoIn.fxml");
        URL url_list_command = getClass().getResource("./ListCommand/ListCommand.fxml");

        FXMLLoader loader_auto_in = new FXMLLoader(url_auto_in);
        FXMLLoader loader_list_command = new FXMLLoader(url_list_command);

        id_auto_in.getChildren().add(loader_auto_in.load());
        id_list_command.getChildren().add(loader_list_command.load());
    }
}
