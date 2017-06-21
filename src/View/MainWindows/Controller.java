package View.MainWindows;

import Controllers.Attack;
import Controllers.Console;
import Controllers.JSBuild;
import Storage.ElementXpath;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebView;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;

import static java.lang.System.exit;

public class Controller {
    private  Console console = new Console();
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
        id_browser.getEngine().load("http://app01.x-grad.com/start/");
        console.controller = this;
        Task<Void> com = new Task<Void>() {
            @Override
            protected Void call() {
                while (true) {
                    String buffer = console.getCommand();
                    Platform.runLater(() -> doing(buffer));
                }
            }
        };
        Thread thread = new Thread(com);
        thread.start();
        runScript(JSBuild.alert());
    }
/*
    public NodeList getNodeList(ElementXpath.x_paths path) {
        String x_path = ElementXpath.getXPath(path);
        Document document = id_browser.getEngine().getDocument();
        if (document==null) return null;
        System.out.print(document.toString());
        try {
            XPathExpression expression = XPathFactory.newInstance().newXPath().compile(x_path);
            return (NodeList) expression.evaluate(document.getXmlEncoding(), XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
            exit(e.hashCode());
        }
        return null;
    }
*/
    public Object runScript(String script){
       // id_browser.getEngine().reload();
       /* if (id_browser.getEngine().getDocument()!=null) {
            System.out.print(id_browser.getEngine().getDocument().getXmlEncoding());
        }/**/
        return id_browser.getEngine().executeScript(script);
    }

    public WebView getId_browser() {
        return id_browser;
    }

    private void doing(String buffer){
        console.Select(buffer);
    }
}