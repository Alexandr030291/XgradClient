package View.MainWindows;

import Controller.Attack;
import Storage.ElementXpath;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

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
        id_browser.getEngine().load("http://www.x-grad.com");
    }

    public Node getNodeList(ElementXpath.x_paths path) throws XPathExpressionException {
        String x_path = ElementXpath.getXPath(path);
        Document document = id_browser.getEngine().getDocument();
        if (document==null) return null;
        XPathExpression expression = XPathFactory.newInstance().newXPath().compile(x_path);
        return (Node) expression.evaluate(document, XPathConstants.NODESET);
    }



    public WebView getId_browser() {
        return id_browser;
    }
}