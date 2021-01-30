package view.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class RiskGameInformationController {
    // TODO: 1/30/2021 .hamash

    @FXML
    private TextField gamename;

    @FXML
    private RadioButton N2;

    @FXML
    private RadioButton N3;

    @FXML
    private RadioButton N4;

    @FXML
    private RadioButton N5;

    @FXML
    private RadioButton N6;

    @FXML
    private RadioButton M3;

    @FXML
    private RadioButton M5;

    @FXML
    private RadioButton M7;


    public void back(ActionEvent event) throws IOException {
        URL url = new File("risk\\src\\view\\graphic\\Playonline-ofline.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }

    public void submit(ActionEvent event) throws IOException {
        URL url = new File("risk\\src\\view\\graphic\\ManualPlacement.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    } // TODO: 1/30/2021 ....porose submit
}
