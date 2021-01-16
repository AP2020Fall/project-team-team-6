package view.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ShowAllPlayersController {
    @FXML
    TextField search; // TODO: 1/15/2021  
    @FXML
    ListView list; // TODO: 1/15/2021  
    @FXML
    Button remove; // TODO: 1/15/2021

    public void back(ActionEvent event) throws IOException {
        URL url = new File("risk\\src\\view\\graphic\\MainMenuAdmin.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }

    public void info(ActionEvent event) throws IOException {
        URL url = new File("risk\\src\\view\\graphic\\PlayersInfo.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }

    public void messages(ActionEvent event) {
        // TODO: 1/15/2021  
    }
}
