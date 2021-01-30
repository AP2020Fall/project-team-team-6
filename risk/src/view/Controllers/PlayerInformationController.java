package view.Controllers;

import javafx.collections.transformation.TransformationList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class PlayerInformationController {
    // TODO: 1/30/2021 ...........kolan todo...... 

    @FXML
    private Label username;

    @FXML
    private Label playernumber;

    @FXML
    private RadioButton red;

    @FXML
    private RadioButton green;

    @FXML
    private RadioButton purple;

    @FXML
    private RadioButton orange;

    @FXML
    private RadioButton yellow;

    @FXML
    private RadioButton blue;


    public void back(MouseEvent mouseEvent) throws IOException {
        URL url = new File("risk\\src\\view\\graphic\\ManualPlacement.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        TransformationList<Object, Object> event = null;
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }

    public void next(MouseEvent mouseEvent) throws IOException {
        URL url = new File("risk\\src\\view\\graphic\\PlayerInformation.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        TransformationList<Object, Object> event = null;
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }// TODO: 1/30/2021 .........kolofttttttttttttttttttttttt 
}
