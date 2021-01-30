package view.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ManualPlacementController {
    // TODO: 1/30/2021 ..........baghie kara

    @FXML
    private RadioButton mpoff;

    @FXML
    private RadioButton mpon;

    @FXML
    private RadioButton fogon;

    @FXML
    private RadioButton fogoff;

    @FXML
    private RadioButton blizzardoff;

    @FXML
    private RadioButton blizzardon;


    public void back(ActionEvent event) throws IOException {
        URL url = new File("risk\\src\\view\\graphic\\RiskGameInformation.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }

    public void create(ActionEvent event) throws IOException {
        URL url = new File("risk\\src\\view\\graphic\\MapGames.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    } // TODO: 1/30/2021 ..........baghie create
}
