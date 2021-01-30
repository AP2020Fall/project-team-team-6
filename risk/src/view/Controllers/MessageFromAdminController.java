package view.Controllers;

import controller.UserController;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.usersModels.Admin;
import model.usersModels.Player;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MessageFromAdminController implements Initializable {
    public ComboBox userNames;
    public ImageView sendButton;
    public TextField textField;
    public ListView textLists;
    private static  ArrayList<Player> players = new ArrayList<>();
    public Button backButton;
    private Admin admin = UserController.getUserController().getAdmin();

    public static ArrayList<Player> getPlayers() {
        return players;
    }

    public static void setPlayers(ArrayList<Player> players) {
        MessageFromAdminController.players = players;
    }

    public void sendMessage(MouseEvent event) {

    }

    public void back(ActionEvent event) {

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
       for(Player player : players){
           userNames.getItems().add(player.getUsername());
       }
    }
}
