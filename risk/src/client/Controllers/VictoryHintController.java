package client.Controllers;

import javafx.collections.transformation.TransformationList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class VictoryHintController implements Initializable {
    @FXML
    private ImageView image;
    private static Image winnerImage;

    public static void setWinnerImage(Image winnerImage) {
        VictoryHintController.winnerImage = winnerImage;
    }

    public void home(MouseEvent mouseEvent) throws IOException {
        URL url = new File("risk\\src\\Client\\graphic\\Mainplato.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        TransformationList<Object, Object> event = null;
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        image.setImage(winnerImage);
    }
}
