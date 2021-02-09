package client.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import server.model.database.MySqlDataBase;
import server.model.usersModels.Player;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class FriendInfoController implements Initializable {
    private static Player secondPlayer = null;
    private Player currentPlayer = MainPlatoController.getPlayer();

    private boolean isThisPlayerAFriend = false;

    private static boolean isFromRequestPage = false;

    public static void setIsFromRequestPage(boolean isFromRequestPage) {
        FriendInfoController.isFromRequestPage = isFromRequestPage;
    }

    public void setThisPlayerAFriend(boolean thisPlayerAFriend) {
        isThisPlayerAFriend = thisPlayerAFriend;
    }

    public static Player getSecondPlayer() {
        return secondPlayer;
    }

    public static void setSecondPlayer(Player secondPlayer) {
        FriendInfoController.secondPlayer = secondPlayer;
    }

    @FXML
    private Button sendMessageButton;

    @FXML
    private Button romveOrRequestButton;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label winLabel;

    @FXML
    private Label recodeLabel;

    @FXML
    private Label coinLabel;

    @FXML
    private Button backButton;

    @FXML
    void back(ActionEvent event) {
        URL url = null;
        try {
            if(!isFromRequestPage) {
                url = new File("risk\\src\\Client\\graphic\\friends.fxml").toURI().toURL();
            }
            else {
                url = new File("risk\\src\\Client\\graphic\\FriendsRequest.fxml").toURI().toURL();
            }
            Parent register = FXMLLoader.load(url);
            Scene message = new Scene(register);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(message);
            window.show();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(currentPlayer.getFriends().contains(secondPlayer))
            this.isThisPlayerAFriend = true;
        usernameLabel.setText(secondPlayer.getUsername());
        String numberOfWin = String.valueOf(secondPlayer.getNumbersOfWin());
        winLabel.setText(numberOfWin);
        String numberOfGame  = String.valueOf(secondPlayer.getNumbersOfGames());
        recodeLabel.setText(numberOfGame);
        String numberOfCoins = String.valueOf(secondPlayer.getRate());
        coinLabel.setText(numberOfCoins);
        if(isThisPlayerAFriend){
            romveOrRequestButton.setText("Remove");
        }else{
            romveOrRequestButton.setText("Send friend request");
        }

    }

    public void romveOrRequest(ActionEvent event) {
        if (isThisPlayerAFriend) {
            currentPlayer.getFriends().remove(secondPlayer);
            MySqlDataBase.getMySqlDataBase().updatePlayer(currentPlayer);
            Stage stage = new Stage();
            AlertController.setText("You have successfully removed " + secondPlayer.getUsername() + " \t\nFrom your friends.");
            try {
                URL url = new File("risk\\src\\Client\\graphic\\alert.fxml").toURI().toURL();
                Parent root = FXMLLoader.load(url);
                Scene scene = new Scene(root);
                scene.setFill(Color.TRANSPARENT);
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setScene(scene);
                stage.setResizable(false);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            secondPlayer.getRequestsForFriendShips().add(currentPlayer);
            MySqlDataBase.getMySqlDataBase().updatePlayer(currentPlayer);
            Stage stage = new Stage();
            AlertController.setText("You have successfully send friend request \n to " + secondPlayer.getUsername());
            try {
                URL url = new File("risk\\src\\Client\\graphic\\alert.fxml").toURI().toURL();
                Parent root = FXMLLoader.load(url);
                Scene scene = new Scene(root);
                scene.setFill(Color.TRANSPARENT);
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setScene(scene);
                stage.setResizable(false);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessage(ActionEvent event) {
        URL url = null;
        ChatController.setSecondPlayer(secondPlayer);
        ChatController.setIsFromPlayerInfoPage(true);
        ChatController.setIsFromAdmin(false);
        ChatController.setCurrentUser(currentPlayer);
        try {
            url = new File("risk\\src\\Client\\graphic\\chat.fxml").toURI().toURL();
            Parent register = FXMLLoader.load(url);
            Scene message = new Scene(register);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(message);
            window.show();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}