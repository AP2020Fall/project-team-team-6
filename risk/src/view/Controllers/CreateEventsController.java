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
import java.net.URL;

public class CreateEventsController {
    @FXML
    TextField name; //todo................
    @FXML
    TextField start; //todo..................
    @FXML
    TextField end; //todo......................
    @FXML
    TextField winners; // TODO: 1/14/2021
    @FXML
    public void back(ActionEvent event) throws IOException {
        URL url = new File("risk\\src\\view\\graphic\\ShowEventsAdmin.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }
    @FXML
    public void invite(ActionEvent event) throws IOException {
        URL url = new File("risk\\src\\view\\graphic\\SendEventInvitation.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }
    @FXML
    public void create(ActionEvent event) throws IOException {
        URL url = new File("risk\\src\\view\\graphic\\ShowEventsAdmin.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show(); 
    }//todo................. + baghie faraiand create
}
