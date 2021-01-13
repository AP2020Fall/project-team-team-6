package view.Controllers;

import controller.UserController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.usersModels.User;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class LoginController {
    protected static UserController userController =UserController.getUserController();
    private static User user = null;

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
    private void LoginController() {

        try {
            setUser(userController.login(username.getText(),pass.getText()));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @FXML
    private void signup(ActionEvent event) throws IOException {
        URL url = new File("risk\\src\\view\\graphic\\Register.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }
}
