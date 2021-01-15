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
import javafx.stage.Stage;
import model.usersModels.Player;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainPlatoController implements Initializable {
    private static Player player = null;
    protected static UserController userController = UserController.getUserController();

    public static Player getPlayer() {
        return player;
    }

    public static void setPlayer(Player player) {
        MainPlatoController.player = player;
    }

    @FXML
    Label friends;
    @FXML
    Label wins;
    @FXML
    Label coins;
    @FXML
    Label username;

    @FXML
    private void setLabel() {
        friends.setText(String.valueOf(player.getFriends().size()));
        wins.setText(String.valueOf(player.getNumbersOfWin()));
        coins.setText(String.valueOf(player.getRate()));
        username.setText(LoginController.getUser().getUsername());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setLabel();
    }

    @FXML
    public void events(ActionEvent actionEvent) throws IOException {
        URL url = new File("risk\\src\\view\\graphic\\ShowEvent.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }

    @FXML
    public void games(ActionEvent actionEvent) {
        //todo.............
    }

    @FXML
    public void messages(ActionEvent actionEvent) throws IOException {
        URL url = new File("risk\\src\\view\\graphic\\messages.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        Stage stage = (Stage) username.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void scoreboard(ActionEvent actionEvent) {
        //todo..............
    }

    @FXML
    public void gameshistory(ActionEvent actionEvent) {
        //todo..........
    }

    @FXML
    public void requests(ActionEvent actionEvent) throws IOException {
        URL url = new File("risk\\src\\view\\graphic\\FriendsRequest.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }

    @FXML
    public void friends(ActionEvent actionEvent) throws IOException {
        URL url = new File("risk\\src\\view\\graphic\\Friends.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }

    @FXML
    public void editinformation(ActionEvent actionEvent) throws IOException {
        URL url = new File("risk\\src\\view\\graphic\\EditInformation.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        MainPlatoController.setPlayer(null);
        URL url = new File("risk\\src\\view\\graphic\\Login.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }


}
