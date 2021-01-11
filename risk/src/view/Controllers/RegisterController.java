package view.Controllers;

import controller.UserController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.usersModels.Admin;
import model.usersModels.Player;
import model.usersModels.User;
import view.terminalMenu.AdminMenu;
import view.terminalMenu.OnlineGameMenu;
import view.terminalMenu.PlayerMenu;
import model.usersModels.Admin;
import model.usersModels.Player;
import model.usersModels.User;

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
    TextField pass;
    @FXML
    TextField repass;
    @FXML
    Button signup;
    @FXML
    Button back;

    @FXML
    private  void signUp( ){
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
        } else {
            Admin admin1 = userController.signUpAsAdmin(firstname.getText(), lastname.getText(), username.getText(), pass.getText(), email.getText(), phnumber.getText());
            OnlineGameMenu.setCurrentUser(admin1);
           //todo nextMenu = new AdminMenu(this, admin1);
        }

    }




}
