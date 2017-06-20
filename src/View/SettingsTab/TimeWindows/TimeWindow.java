package View.SettingsTab.TimeWindows;

import Controllers.ActiveHandler;
import Storage.OptionsApp;
import View.SplitterText.SPText;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;

public class TimeWindow extends ActiveHandler {
    @FXML
    private Pane id_sp_text_max;
    @FXML
    private Pane id_sp_text_min;

    private SPText _text_min_controller;
    private SPText _text_max_controller;

    @FXML
    public void initialize() throws IOException {
        URL url_sp_text = getClass().getResource("../../SplitterText/SPText.fxml");

        FXMLLoader sp_text_min = new FXMLLoader(url_sp_text);
        FXMLLoader sp_text_max = new FXMLLoader(url_sp_text);

        id_sp_text_min.getChildren().add(sp_text_min.load());
        id_sp_text_max.getChildren().add(sp_text_max.load());

        _text_max_controller = sp_text_max.getController();
        _text_min_controller = sp_text_min.getController();

        _text_min_controller.setValue(Integer.parseInt(OptionsApp.getSetting(OptionsApp.opt.TIME_MIN)));
        _text_max_controller.setValue(Integer.parseInt(OptionsApp.getSetting(OptionsApp.opt.TIME_MAX)));
        _text_min_controller.setActiveHandler(this);
        _text_max_controller.setActiveHandler(this);
    }

    @FXML
    public void onClicked() {
        int min = _text_min_controller.getValue();
        int max = _text_max_controller.getValue();
        if (min<0) min=0;
        if (min < max) max=min+1;
        OptionsApp.setSetting(OptionsApp.opt.TIME_MIN, String.valueOf(min));
        OptionsApp.setSetting(OptionsApp.opt.TIME_MAX, String.valueOf(max));
        _text_max_controller.setMin(min);
        _text_min_controller.setMax(max);
    }
}
