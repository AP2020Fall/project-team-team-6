package view.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ManualPlacementController {

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

    @FXML
    private ToggleGroup blizzard;

    @FXML
    private ToggleGroup manual;

    @FXML
    private ToggleGroup fog;



    public void back(ActionEvent event) throws IOException {
        URL url = new File("risk\\src\\view\\graphic\\RiskGameInformation.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }

    public void next(ActionEvent event) throws IOException {
        RadioButton selectedRadioButtonBlizzard = (RadioButton) blizzard.getSelectedToggle();
        String blizzardgroup = selectedRadioButtonBlizzard.getText();
        RadioButton selectedRadioButtonManual = (RadioButton) manual.getSelectedToggle();
        String manualgroup = selectedRadioButtonBlizzard.getText();
        RadioButton selectedRadioButtonFof = (RadioButton) fog.getSelectedToggle();
        String foggroup = selectedRadioButtonBlizzard.getText();
        URL url = new File("risk\\src\\view\\graphic\\MapGames.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    } // TODO: 1/30/2021 ..........baghie create
}
