package view.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuAdminController implements Initializable {
    @FXML
    Label adminname;
    @FXML
    public void players(ActionEvent actionEvent) {
        //todo.............+ baghie farayand.......
    }
    @FXML    public void editinformation(ActionEvent actionEvent) {
        //todo.............+ baghie farayand.......
    }
    @FXML
    public void messagesandreq(ActionEvent actionEvent) {
        //todo.............+ baghie farayand.......
    }
    @FXML
    public void createevent(ActionEvent actionEvent) {
        //todo.............+ baghie farayand.......
    }
    @FXML
    public void events(ActionEvent actionEvent) {
        //todo.............+ baghie farayand.......
    }
    private void setLabel(){
        adminname.setText(LoginController.getUser().getUsername());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setLabel();
    }
    private void logout(ActionEvent event) throws IOException {

        URL url = new File("risk\\src\\view\\graphic\\Login.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }
}
