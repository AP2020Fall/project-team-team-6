package view.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


public class EditInformationController {
    @FXML
    TextField user; // TODO: 1/14/2021
    @FXML
    TextField first; // TODO: 1/14/2021
    @FXML
    TextField last;  // TODO: 1/14/2021
    @FXML
    TextField email; // TODO: 1/14/2021
    @FXML
    TextField phone; // TODO: 1/14/2021
    @FXML
    TextField pass; // TODO: 1/14/2021
    @FXML
    TextField repass; // TODO: 1/14/2021

    @FXML
    public void submit(ActionEvent event) {
    //todo............
    }
    @FXML
    public void back(ActionEvent event) throws IOException {
        URL url = new File("risk\\src\\view\\graphic\\Mainplato.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }
}
