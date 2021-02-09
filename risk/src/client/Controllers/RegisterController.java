package client.Controllers;

import client.ClientMethods;
import server.controller.UserController;
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
import server.model.database.LocalDataBase;
import server.model.usersModels.Admin;
import server.model.usersModels.Player;
import server.model.usersModels.User;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterController {
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
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("check username$").append(username.getText());
        boolean isUsernameAvailable = ClientMethods.checkUsername(String.valueOf(stringBuilder));

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
            boolean haveAdmin = ClientMethods.haveAdmin("haveAdmin");
            if (haveAdmin) {
                StringBuilder stringBuilder1 = new StringBuilder();
                stringBuilder1.append("sign up as player$").append(firstname.getText()).append("$").append(lastname.getText()).append("$").append(username.getText());
                stringBuilder1.append("$").append(pass.getText()).append("$").append(email.getText()).append("$").append(phnumber.getText());
                User user = ClientMethods.getClientMethods().signUpAsPlayer(String.valueOf(stringBuilder1));
                LoginController.setUser(user);
                String playerText = "find player$"+user.getUsername();
                Player player = ClientMethods.getClientMethods().findPlayerByUsername(playerText);
                MainPlatoController.setPlayer(player);
                URL url = new File("risk\\src\\Client\\graphic\\MainPlato.fxml").toURI().toURL();
                Parent register = FXMLLoader.load(url);
                Scene message = new Scene(register);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(message);
                window.show();
            } else {
                StringBuilder stringBuilder1 = new StringBuilder();
                stringBuilder1.append("sign up as admin$").append(firstname.getText()).append("$").append(lastname.getText()).append("$").append(username.getText());
                stringBuilder1.append("$").append(pass.getText()).append("$").append(email.getText()).append("$").append(phnumber.getText());
                Admin admin1 = ClientMethods.getClientMethods().signUpAsAdmin(String.valueOf(stringBuilder1));
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
