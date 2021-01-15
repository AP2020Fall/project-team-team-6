package view.Controllers;

import controller.UserController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.usersModels.Player;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PlayersInfoController implements Initializable {
    private static Player player = null;
    protected static UserController userController = UserController.getUserController();

    public static Player getPlayer() {
        return player;
    }

    public static void setPlayer(Player player) {
        PlayersInfoController.player = player;
    }
    @FXML
    Label user;
    @FXML
    Label first;
    @FXML
    Label last;
    @FXML
    Label email;
    @FXML
    Label phone;
    @FXML
    Label wins;
    @FXML
    Label records;
    @FXML
    Label coins;
    @FXML
    public void back(ActionEvent event) throws IOException {
        URL url = new File("risk\\src\\view\\graphic\\ShowAllPlayers.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }
    @FXML
    private void setLabel() {
        wins.setText(String.valueOf(player.getNumbersOfWin()));
        coins.setText(String.valueOf(player.getRate()));
        phone.setText(String.valueOf(player.getTelephoneNumber()));
       // records.setText(String.valueOf(player.get()));
        first.setText(LoginController.getUser().getFirstName());
        last.setText(LoginController.getUser().getLastName());
        email.setText(LoginController.getUser().getEmailAddress());
        user.setText(LoginController.getUser().getUsername());
    }
    @FXML
        @Override
    public void initialize(URL location, ResourceBundle resources) {
        setLabel();
    }
}
