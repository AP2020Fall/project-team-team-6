package client.Controllers;

import javafx.collections.transformation.TransformationList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class NumberOfDiceController {
    // TODO: 1/30/2021 .............baghiash

    @FXML
    private RadioButton D1;

    @FXML
    private RadioButton D2;

    @FXML
    private RadioButton D3;

    @FXML
    private RadioButton Blitz;

    @FXML
    private ToggleGroup dice;
    RadioButton selectedRadioButtonDice = (RadioButton) dice.getSelectedToggle();
    String dicegroup = selectedRadioButtonDice.getText();

    @FXML
    public void back(MouseEvent mouseEvent) throws IOException {

        URL url = new File("risk\\src\\Client\\graphic\\MapGames.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        TransformationList<Object, Object> event = null;
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }
}
