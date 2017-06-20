package View.MainWindows;

import Controller.Attack;
import Storage.ElementXpath;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.swing.plaf.nimbus.State;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;

public class Controller {
    @FXML
    private Pane id_control_panel;

    @FXML
    private WebView id_browser;

    @FXML
    public void initialize() throws IOException {
        id_control_panel.getChildren().add(FXMLLoader.
                load(getClass().
                        getResource("../ControlPanel/ControlPanel.fxml")));
        Attack.controller = this;

    }

    public void start() {
        id_browser.getEngine().setJavaScriptEnabled(true);
        id_browser.getEngine().load("http://www.x-grad.com");
    }

    public NodeList getNodeList(ElementXpath.x_paths path) {
        String x_path = ElementXpath.getXPath(path);
        Document document = id_browser.getEngine().getDocument();
        if (document==null) return null;
        try {
            XPathExpression expression = XPathFactory.newInstance().newXPath().compile(x_path);
            return (NodeList) expression.evaluate(document.getXmlEncoding(), XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        //System.out.print(document.toString());
        return null;
    }

    public Object runScript(String script){
        return id_browser.getEngine().executeScript(script);
    }

    public WebView getId_browser() {
        return id_browser;
    }
}