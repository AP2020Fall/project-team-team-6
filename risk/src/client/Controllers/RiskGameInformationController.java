package client.Controllers;

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
    @FXML
    public void back(ActionEvent event) throws IOException {
        URL url = new File("risk\\src\\Client\\graphic\\Playonline-ofline.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }
    @FXML
    public void submit(ActionEvent event) throws IOException {
        int numberOfPlayers = 0 ;
        double timeValue = 0;
        RadioButton selectedRadioButtonTime = (RadioButton) time.getSelectedToggle();
        String timegroup = selectedRadioButtonTime.getText();
        if (timegroup.equals("3")) {
            timeValue = (Double.parseDouble(selectedRadioButtonTime.getText())) * 60;
        } else if (timegroup.equals("5")) {
            timeValue = (Double.parseDouble(selectedRadioButtonTime.getText())) * 60;
        } else if (timegroup.equals("7")) {
            timeValue = (Double.parseDouble(selectedRadioButtonTime.getText())) * 60;
        }

            RadioButton selectedRadioButtonNumber = (RadioButton) numberofplayers.getSelectedToggle();
            String numbergroup = selectedRadioButtonNumber.getText();
            if (numbergroup.equals("2")) {
                 numberOfPlayers = Integer.parseInt(selectedRadioButtonNumber.getText());
            } else if (numbergroup.equals("3")) {
                 numberOfPlayers = Integer.parseInt(selectedRadioButtonNumber.getText());
            } else if (numbergroup.equals("4")) {
                 numberOfPlayers = Integer.parseInt(selectedRadioButtonNumber.getText());
            } else if (numbergroup.equals("5")) {
                 numberOfPlayers = Integer.parseInt(selectedRadioButtonNumber.getText());
            } else if (numbergroup.equals("6")) {
                 numberOfPlayers = Integer.parseInt(selectedRadioButtonNumber.getText());
            }
            String gamenametext = gamename.getText();
            if (!gamenametext.isEmpty() && numberOfPlayers != 0 && timeValue != 0 ) {
                ManualPlacementController.setGameName(gamenametext);
                ManualPlacementController.setNumberOfPlayers(numberOfPlayers);
                ManualPlacementController.setTime(timeValue);
                URL url = new File("risk\\src\\Client\\graphic\\ManualPlacement.fxml").toURI().toURL();
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
