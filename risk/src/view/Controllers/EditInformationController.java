package view.Controllers;

import controller.UserController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.usersModels.Player;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class EditInformationController implements Initializable {
    private static Player player = null;
    protected static UserController userController = UserController.getUserController();

    public static Player getPlayer() {
        return player;
    }
    public static void setPlayer(Player player) {
        EditInformationController.player = player;
    }
    @FXML
    TextField user;
    @FXML
    TextField first;
    @FXML
    TextField last;
    @FXML
    TextField email;
    @FXML
    TextField phone;
    @FXML
    TextField pass;
    @FXML
    TextField repass;

    @FXML
    public void submit(ActionEvent event) {
        // TODO: 1/15/2021  
    }
    @FXML
    public void back(ActionEvent event) throws IOException {
        URL url = new File("risk\\src\\view\\graphic\\Mainplato.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }

    @FXML
    private void setLabel() {
        phone.setText(String.valueOf(player.getTelephoneNumber()));
        first.setText(LoginController.getUser().getFirstName());
        last.setText(LoginController.getUser().getLastName());
        email.setText(LoginController.getUser().getEmailAddress());
        user.setText(LoginController.getUser().getUsername());
        pass.setText(LoginController.getUser().getPassword());
        repass.setText(LoginController.getUser().getPassword());
    }
    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setLabel();
    }
}

