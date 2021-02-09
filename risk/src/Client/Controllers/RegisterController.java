package Client.Controllers;

import Server.controller.UserController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Server.model.database.LocalDataBase;
import Server.model.usersModels.Admin;
import Server.model.usersModels.Player;
import Server.model.usersModels.User;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterController {
    protected static UserController userController = UserController.getUserController();
    public Label usernameError;
    public Label phoneNumberError;
    public Label passwordError;
    public Label emailError;
    public Label invalidTextsError;

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
    private void signUp(ActionEvent event) throws IOException {
        System.out.println(pass.getText());
        System.out.println(repass.getText());
        Pattern pattern = Pattern.compile("^\\d{11}$");
        Matcher matcher = pattern.matcher(phnumber.getText());
        Matcher emailMatcher = Pattern.compile("^.+\\@.+\\.com$").matcher(email.getText());
        boolean isAnyFieldEmpty = isTextFieldEmpty();
        boolean isEmailCorrect = emailMatcher.find();
        boolean isPhoneNumberCorrect = matcher.find();
        boolean arePasswordsEqual = pass.getText().equals(repass.getText());
        boolean isUsernameAvailable = userController.checkUsername(username.getText());

        if (isAnyFieldEmpty) {
            invalidTextsError.setVisible(true);
        }
        if (!isEmailCorrect) {
            emailError.setVisible(true);
        }
        if (!isPhoneNumberCorrect) {
            phoneNumberError.setVisible(true);
        }
        if (!arePasswordsEqual) {
            passwordError.setVisible(true);
        }
        if (!isUsernameAvailable) {
            usernameError.setVisible(true);
        } else if (!isAnyFieldEmpty && isEmailCorrect && isPhoneNumberCorrect && arePasswordsEqual && isUsernameAvailable) {
            Admin admin = LocalDataBase.getLocalDataBase().getAdmin();
            if (admin != null) {
                User user = userController.signUpAsPlayer(firstname.getText(), lastname.getText(), username.getText(), pass.getText(), email.getText(), phnumber.getText());
                LoginController.setUser(user);
                Player player = userController.findPlayerByUserName(username.getText());
                MainPlatoController.setPlayer(player);
                URL url = new File("risk\\src\\Client\\graphic\\MainPlato.fxml").toURI().toURL();
                Parent register = FXMLLoader.load(url);
                Scene message = new Scene(register);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(message);
                window.show();
            } else {
                Admin admin1 = userController.signUpAsAdmin(firstname.getText(), lastname.getText(), username.getText(), pass.getText(), email.getText(), phnumber.getText());
                LoginController.setUser(admin1);
                URL url = new File("risk\\src\\Client\\graphic\\MainMenuAdmin.fxml").toURI().toURL();
                Parent register = FXMLLoader.load(url);
                Scene message = new Scene(register);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(message);
                window.show();
            }
        }

    }

    @FXML
    private void back(ActionEvent event) throws IOException {

        URL url = new File("risk\\src\\Client\\graphic\\Login.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }

    private boolean isTextFieldEmpty() {
        if (username.getText().isEmpty() || firstname.getText().isEmpty() ||
                lastname.getText().isEmpty() || phnumber.getText().isEmpty() ||
                email.getText().isEmpty() || pass.getText().isEmpty() || repass.getText().isEmpty())
            return true;
        return false;
    }


}
