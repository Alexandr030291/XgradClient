package View.ActiveTab.ListCommand;

import Controllers.Bot;
import Storage.ListCommands;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class ListCommand {
    @FXML
    private Button id_btn_run;
    @FXML
    private Button id_btn_stop;
    @FXML
    private ListView<String> id_list_view;

    @FXML
    public void initialize() throws IOException {
        id_list_view.setItems(ListCommands.getNameList());
        id_btn_run.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> btnRunClick());
        id_btn_stop.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> btnStopClick());
    }

    public void updateList(){

    }

    @FXML
    private void btnRunClick() {
        Bot.run();

    }

    @FXML
    private void btnStopClick(){
        Bot.stop();
    }

}
