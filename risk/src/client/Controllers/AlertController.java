package client.Controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AlertController implements Initializable {


    public ImageView exitButton;
    public Label label;
    private static String text;

    public static String getText() {
        return text;
    }

    public static void setText(String text) {
        AlertController.text = text;
    }

    public void exit(MouseEvent event) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        label.setText(text);
    }
}
