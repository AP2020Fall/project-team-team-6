package view.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.gamesModels.GameStatus;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuAdminController implements Initializable {
    @FXML
    Label name;
    @FXML
    Button togglegamestatus;
    @FXML
    Button toggleupdate;
    @FXML
    Button unistall;


    @FXML
    public void players(ActionEvent actionEvent) throws IOException {
        URL url = new File("risk\\src\\view\\graphic\\ShowAllPlayers.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }
    @FXML
    public void events(ActionEvent actionEvent) throws IOException {
        URL url = new File("risk\\src\\view\\graphic\\ShowEventsAdmin.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }
    public void edit(ActionEvent event) throws IOException {
        URL url = new File("risk\\src\\view\\graphic\\EditInformationAdmin.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }

    private void setLabel() {

        name.setText(LoginController.getUser().getUsername());
    }

    @FXML
    private void togglegamestatus(ActionEvent event) throws IOException {

        GameStatus.setPass(!GameStatus.getPass());

        URL url = new File("risk/src/view/graphic/MainMenuAdmin.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }
    @FXML
    private void toggleupdate (ActionEvent event) throws IOException {
        GameStatus.setUpdate(!GameStatus.getUpdate());

        URL url = new File("risk/src/view/graphic/MainMenuAdmin.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }
    @FXML
    private void unistallTouggle (ActionEvent event) throws IOException {
        GameStatus.setUninstall(!GameStatus.getUninstall());

        URL url = new File("risk/src/view/graphic/MainMenuAdmin.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (GameStatus.getPass()){
            togglegamestatus.setText("make game not accessible");
        }else {
            togglegamestatus.setText("make game accessible");
        }
        setLabel();
        if (GameStatus.getUpdate()){
            toggleupdate.setText("make game not accessible for updating");
        }else {
            toggleupdate.setText("make game accessible");
        }

        if (GameStatus.getUninstall()){
            unistall.setText("Uninstall Game");
        }else {
            unistall.setText("Install Game");
        }
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
