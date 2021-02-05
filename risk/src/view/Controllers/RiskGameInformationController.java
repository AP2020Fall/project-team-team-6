package view.Controllers;

import controller.GameController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class RiskGameInformationController {


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

    @FXML
    private ToggleGroup numberofplayers;

    @FXML
    private ToggleGroup time;

    @FXML
    private Label nameerror;

    public void back(ActionEvent event) throws IOException {
        URL url = new File("risk\\src\\view\\graphic\\Playonline-ofline.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }

    public void submit(ActionEvent event) throws IOException {
        RadioButton selectedRadioButtonTime = (RadioButton) time.getSelectedToggle();
        String timegroup = selectedRadioButtonTime.getText();
        if (selectedRadioButtonTime.getText().equals(3)) {
            double time = (Double.parseDouble(selectedRadioButtonTime.getText())) * 60;
        } else if (selectedRadioButtonTime.getText().equals(5)) {
            double time = (Double.parseDouble(selectedRadioButtonTime.getText())) * 60;
        } else if (selectedRadioButtonTime.getText().equals(7)) {
            double time = (Double.parseDouble(selectedRadioButtonTime.getText())) * 60;

            RadioButton selectedRadioButtonNumber = (RadioButton) numberofplayers.getSelectedToggle();
            String numbergroup = selectedRadioButtonNumber.getText();
            if (selectedRadioButtonTime.getText().equals(2)) {
                int numberOfPlayers = Integer.parseInt(selectedRadioButtonNumber.getText());

            } else if (selectedRadioButtonTime.getText().equals(3)) {
                int numberOfPlayers = Integer.parseInt(selectedRadioButtonNumber.getText());
            } else if (selectedRadioButtonTime.getText().equals(4)) {
                int numberOfPlayers = Integer.parseInt(selectedRadioButtonNumber.getText());
            } else if (selectedRadioButtonTime.getText().equals(5)) {
                int numberOfPlayers = Integer.parseInt(selectedRadioButtonNumber.getText());
            } else if (selectedRadioButtonTime.getText().equals(6)) {
                int numberOfPlayers = Integer.parseInt(selectedRadioButtonNumber.getText());
            }
            String gamenametext = gamename.getText();
            if (!gamenametext.isEmpty()) {
                URL url = new File("risk\\src\\view\\graphic\\ManualPlacement.fxml").toURI().toURL();
                Parent register = FXMLLoader.load(url);
                Scene message = new Scene(register);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(message);
                window.show();
            } else {
                nameerror.setVisible(true);
            }


        }
    }
}
