package view.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.usersModels.Player;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class FriendsRequestController implements Initializable {
    @FXML
    ListView list; //todo..............

    @FXML
    public void back(ActionEvent event) throws IOException {
        URL url = new File("risk\\src\\view\\graphic\\Mainplato.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }
    public HBox makeFriendRequestHBox(Player player){
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10 , 10 , 10 , 10));
        Image image = new Image("@../../NotResoures/19.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(80);
        imageView.setFitWidth(40);
        Label usernameLabel = new Label(player.getUsername());
        usernameLabel.setStyle("-fx-font-size: 20px ;");
        usernameLabel.setMinWidth(99);
        Label addFriendLabel = new Label("Add friend");
        addFriendLabel.setStyle("-fx-underline: true ; -fx-text-fill: darkgreen ; -fx-font-size: 10px ");
        Label rejectLabel = new Label("Reject");
        rejectLabel.setStyle("-fx-underline: true ; -fx-text-fill: red ; -fx-font-size: 10px ");
        Label showInformationLabel = new Label("Show information");
        showInformationLabel.setStyle("-fx-underline: true ; -fx-text-fill: yellow ; -fx-font-size: 10px ");

        return hBox;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
