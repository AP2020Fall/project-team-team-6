package client.Controllers;

import javafx.collections.transformation.TransformationList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class TerritoryCardsController {

    @FXML
    private Label cardsnumber;


    public void back(MouseEvent mouseEvent) throws IOException {
        URL url = new File("risk\\src\\Client\\graphic\\MapGames.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        TransformationList<Object, Object> event = null;
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }

    public void next(MouseEvent mouseEvent) throws IOException {
        URL url = new File("risk\\src\\Client\\graphic\\MapGames.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        TransformationList<Object, Object> event = null;
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }
}
