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
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.usersModels.Player;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class EditInformationController implements Initializable {
    private static Player player = null;
    protected static UserController userController = UserController.getUserController();
    public Label usernameError;
    public Label emailError;
    public Label phoneError;
    public Label blankError;
    public Label passError;

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
        Pattern pattern = Pattern.compile("^\\d{11}$");
        Matcher matcher = pattern.matcher(phone.getText());
        Matcher emailMatcher = Pattern.compile("^.+\\@.+\\.com$").matcher(email.getText());
        boolean isAnyFieldEmpty = isTextFieldEmpty();
        boolean isEmailCorrect = emailMatcher.find();
        boolean isPhoneNumberCorrect = matcher.find();
        boolean arePasswordsEqual = pass.getText().equals(repass.getText());
        boolean isUsernameAvailable = userController.checkUsername(user.getText());
        if(isAnyFieldEmpty){
            blankError.setVisible(true);
        }
        if(!isEmailCorrect){
            emailError.setVisible(true);
        }
        if(!isPhoneNumberCorrect){
            phoneError.setVisible(true);
        }
        if (!arePasswordsEqual){
            passError.setVisible(true);
        } else if(!isAnyFieldEmpty && isEmailCorrect && isPhoneNumberCorrect && arePasswordsEqual){
            String firstName = first.getText();
            String lastName = last.getText();
            String userName = user.getText();
            String password = pass.getText();
            String emailAddress = email.getText();
            String telephoneNumber = phone.getText();
            UserController userController = UserController.getUserController();
            Player player = MainPlatoController.getPlayer();
            userController.changeFirstName(player.getUsername() , firstName);
            userController.changeLastName(player.getUsername() , lastName);
            userController.changeTelephoneNumber(player.getUsername() , telephoneNumber);
            userController.changeEmailAddress(player.getUsername() , emailAddress);
            try {
                userController.changeUsername(player.getUsername() , userName);
            } catch (Exception e) {
                usernameError.setVisible(true);
            }
            userController.changePassword(player.getUsername() , password);
            Stage stage = new Stage();
            stage.setTitle("Change information");
            Label label = new Label("Your information have successfully changed");
            Pane pane = new Pane();
            pane.getChildren().addAll(label);
            stage.setScene(new Scene(pane));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        }
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
        phone.setText(LoginController.getUser().getTelephoneNumber());
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

    private boolean isTextFieldEmpty(){
        if(user.getText().isEmpty() || first.getText().isEmpty() ||
                last.getText().isEmpty() || phone.getText().isEmpty()  ||
                email.getText().isEmpty() || pass.getText().isEmpty() || repass.getText().isEmpty())
            return true;
        return false;
    }
}

