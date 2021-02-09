package client.Controllers;

import client.Client;
import client.ClientMethods;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import server.model.usersModels.Player;
import server.model.usersModels.User;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    private static User user = null;
    public Label errorLabel;

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        LoginController.user = user;
    }

    @FXML
    Button login;
    @FXML
    Button noaccount;
    @FXML
    TextField username;
    @FXML
    PasswordField pass;

    @FXML
    private void LoginController() throws IOException {
        playMouseSound();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("login$").append(username.getText()).append("$").append(pass.getText());
            User currentUser  = ClientMethods.getClientMethods().login(String.valueOf(stringBuilder));
            if(currentUser != null)
            setUser(currentUser);
            else errorLabel.setVisible(true);
            URL url;
            if (user.isAdmin()) {
                url = new File("risk\\src\\Client\\graphic\\MainMenuAdmin.fxml").toURI().toURL();
            } else {
                url = new File("risk\\src\\Client\\graphic\\MainPlato.fxml").toURI().toURL();
                StringBuilder stringBuilder1 = new StringBuilder();
                stringBuilder1.append("find player$").append(user.getUsername());
                Player player = ClientMethods.getClientMethods().findPlayerByUsername(String.valueOf(stringBuilder1));
                MainPlatoController.setPlayer(player);
            }
            Parent register = FXMLLoader.load(url);
            Scene message = new Scene(register);
            Stage window = (Stage) login.getScene().getWindow();
            window.setScene(message);
            window.show();
    }

    @FXML
    private void signup(ActionEvent event) throws IOException {
        playMouseSound();
        URL url = new File("risk\\src\\Client\\graphic\\Register.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }
    private static final File file = new File("risk/src/NotResoures/sounds/20401330.mp3");
    protected static Media media = new Media(file.toURI().toString());
    protected static MediaPlayer mediaPlayer = new MediaPlayer(media);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mediaPlayer.setVolume(0.1);
//        mediaPlayer.play();
    }
    public void playMouseSound(){
        File file = new File("risk/src/NotResoures/sounds/Click.mp3");
        Media media = new Media(file.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
//        mediaPlayer.play();
    }
}
