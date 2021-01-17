package view.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuAdminController implements Initializable {
    @FXML
    Label name;

    @FXML
    public void players(ActionEvent actionEvent) throws IOException {
        URL url = new File("risk\\src\\view\\graphic\\ShowAllPlayers.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }
    @FXML
    public void events(ActionEvent actionEvent) throws IOException {
        URL url = new File("risk\\src\\view\\graphic\\CreateEvents.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }
    public void edit(ActionEvent event) throws IOException {
        URL url = new File("risk\\src\\view\\graphic\\EditInformationAdmin.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }

    private void setLabel() {

        name.setText(LoginController.getUser().getUsername());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        setLabel();
    }

    private void logout(ActionEvent event) throws IOException {

        URL url = new File("risk\\src\\view\\graphic\\Login.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }


}
