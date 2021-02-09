package Client.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class EditEventAdminController {
    @FXML
    TextField name;
    @FXML
    TextField start;
    @FXML
    TextField end;
    @FXML
    TextField winners;
    @FXML
    TextField invited;
    public void back(ActionEvent event) throws IOException {
        URL url = new File("risk\\src\\Client\\graphic\\ShowEventsAdmin.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }

    public void delete(ActionEvent event) {
        // TODO: 1/16/2021  
    }

    public void submit(ActionEvent event) {
        // TODO: 1/16/2021  
    }
}
