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
    protected static UserController userController = new UserController();


    @FXML
    Label friends;
    @FXML
    Label wins;
    @FXML
    Label coins;
    @FXML
    Label username;

    @FXML
    private void setLabel(){
        Player player = userController.findPlayerByUserName(LoginController.getUser().getUsername());
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
    public void events(ActionEvent actionEvent) {
    }
    @FXML
    public void games(ActionEvent actionEvent) {
    }
    @FXML
    public void messages(ActionEvent actionEvent) {
    }
    @FXML
    public void scoreboard(ActionEvent actionEvent) {
    }
    @FXML
    public void gameshistory(ActionEvent actionEvent) {
    }
    @FXML
    public void requests(ActionEvent actionEvent) {
    }
    @FXML
    public void friends(ActionEvent actionEvent) {
    }
    @FXML
    public void editinformation(ActionEvent actionEvent) {
    }
    @FXML
    private void logout(ActionEvent event) throws IOException {

        URL url = new File("risk\\src\\view\\graphic\\Login.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }


}
