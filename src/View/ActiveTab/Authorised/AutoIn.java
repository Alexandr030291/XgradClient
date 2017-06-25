package View.ActiveTab.Authorised;

import Storage.ListCommands;
import Storage.OptionsApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import static Storage.OptionsApp.opt.*;

public class AutoIn {
    @FXML
    private Button id_btn_auto_in;
    @FXML
    private Button id_btn_auto_out;
    @FXML
    private Button id_btn_select;
    @FXML
    private TextField id_login;
    @FXML
    private PasswordField id_password;
    @FXML
    private Button id_btn_add;
    @FXML
    private Button id_btn_del;
    @FXML
    private ListView id_login_list;

    @FXML
    public void initialize(){
        id_btn_auto_in.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> onBtnAuotoInClick());
    }

    @FXML
    public void onBtnAddClick(){

    }

    @FXML
    public void onBtnDelClick(){

    }

    @FXML
    public  void  onBtnAuotoInClick(){
        OptionsApp.setSetting(LOGIN,id_login.getText());
        OptionsApp.setSetting(PASSWORD,id_password.getText());
        ListCommands.autoIN();

    }
}
