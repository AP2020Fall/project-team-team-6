package client.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PlayOnlineOfflineController implements Initializable {

    @FXML
    public void online(ActionEvent event) throws IOException {
        URL url = new File("risk\\src\\Client\\graphic\\RiskGameInformation.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }
    @FXML
    public void offline(ActionEvent event) throws IOException {
        URL url = new File("risk\\src\\Client\\graphic\\RiskGameInformation.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }
    @FXML
    public void back(ActionEvent event) throws IOException {
        URL url = new File("risk\\src\\Client\\graphic\\Mainplato.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }
    private static final File file = new File("risk/src/NotResoures/sounds/Sina-30kas-Jen3-320.mp3");
    protected static Media media = new Media(file.toURI().toString());
    protected static MediaPlayer mediaPlayer = new MediaPlayer(media);


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoginController.mediaPlayer.pause();
//        mediaPlayer.play();
    }
}
