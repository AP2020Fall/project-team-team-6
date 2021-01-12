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
import model.usersModels.Admin;
import model.usersModels.Player;
import model.usersModels.User;
import view.terminalMenu.AdminMenu;
import view.terminalMenu.OnlineGameMenu;
import view.terminalMenu.PlayerMenu;
import model.usersModels.Admin;
import model.usersModels.Player;
import model.usersModels.User;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class RegisterController {
    protected static UserController userController = new UserController();

    @FXML
    TextField username;
    @FXML
    TextField firstname;
    @FXML
    TextField lastname;
    @FXML
    TextField email;
    @FXML
    TextField phnumber;
    @FXML
    PasswordField pass;
    @FXML
    PasswordField repass;
    @FXML
    Button signup;
    @FXML
    Button back;

    @FXML
    private  void signUp(ActionEvent event) throws IOException {
        System.out.println(pass.getText());
        System.out.println(repass.getText());
        if (!pass.getText().equals(repass.getText())){
            System.err.println("fuck U");
            return;
        }
        Admin admin = userController.getAdmin();
        if (admin != null) {
            User user = userController.signUpAsPlayer(firstname.getText(), lastname.getText(), username.getText(), pass.getText(), email.getText(), phnumber.getText());
            OnlineGameMenu.setCurrentUser(user);
            Player player = userController.findPlayerByUserName(username.getText());
            //todo  nextMenu = new PlayerMenu(this.parentMenu.getParentMenu(), player);
            URL url = new File("risk\\src\\view\\graphic\\MainPlato.fxml").toURI().toURL();
            Parent register = FXMLLoader.load(url);
            Scene message = new Scene(register);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(message);
            window.show();
        } else {
            Admin admin1 = userController.signUpAsAdmin(firstname.getText(), lastname.getText(), username.getText(), pass.getText(), email.getText(), phnumber.getText());
            OnlineGameMenu.setCurrentUser(admin1);
           //todo nextMenu = new AdminMenu(this, admin1);
            URL url = new File("risk\\src\\view\\graphic\\MainPlato.fxml").toURI().toURL();
            Parent register = FXMLLoader.load(url);
            Scene message = new Scene(register);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(message);
            window.show();
        }

    }
    @FXML
    private void back(ActionEvent event) throws IOException {

        URL url = new File("risk\\src\\view\\graphic\\Login.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }





}
