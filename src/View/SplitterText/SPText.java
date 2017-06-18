package View.SplitterText;

import Controller.ActiveHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class SPText {
    @FXML
    private TextField id_text;
    @FXML
    private Button id_btn_p;
    @FXML
    private Button id_btn_m;

    private int value =0;
    private int min =0;
    private int max =100;

    private ActiveHandler activeHandler;

    @FXML
    public void initialize() throws IOException {
        id_btn_p.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> onClickedPlus());
        id_btn_m.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> onClickedMinus());
    }

    @FXML
    private void onClickedPlus(){
        value++;
        if (value > max) value=max;
        id_text.setText(String.valueOf(value));
        if(activeHandler!=null)activeHandler.onClicked();
    }

    @FXML
    private void onClickedMinus(){
        value--;
        if (value < min) value=min;
        id_text.setText(String.valueOf(value));
        if(activeHandler!=null)activeHandler.onClicked();
    }

    public int getValue() {
        value = Integer.parseInt(id_text.getText());
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        id_text.setText(String.valueOf(value));
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void setActiveHandler(ActiveHandler activeHandler) {
        this.activeHandler = activeHandler;
    }
}
