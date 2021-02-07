package view.Controllers;

import controller.UserController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.AccessibleRole;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.usersModels.Player;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FriendsRequestController implements Initializable {

    private  Player currentPlayer = MainPlatoController.getPlayer();
    @FXML
    ListView list;

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
        HBox hBox = new HBox(15);
        hBox.setPadding(new Insets(10 , 10 , 10 , 10));
        Image image = new Image("@../../NotResoures/images/19.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(80);
        imageView.setFitWidth(40);
        Label usernameLabel = new Label(player.getUsername());
        usernameLabel.setStyle("-fx-font-size: 20px ;");
        usernameLabel.setMinWidth(99);
        Label addFriendLabel = new Label("Add friend");
        addFriendLabel.setStyle("-fx-underline: true ; -fx-text-fill: darkgreen ; -fx-font-size: 20px ");
        Label rejectLabel = new Label("Reject");
        rejectLabel.setStyle("-fx-underline: true ; -fx-text-fill: red ; -fx-font-size: 20px ");
        Label showInformationLabel = new Label("Show information");
        showInformationLabel.setStyle("-fx-underline: true ; -fx-text-fill: yellow ; -fx-font-size: 20px ");
        rejectLabel.setAccessibleRole(AccessibleRole.BUTTON);
        addFriendLabel.setAccessibleRole(AccessibleRole.BUTTON);
        showInformationLabel.setAccessibleRole(AccessibleRole.BUTTON);
        rejectLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton().equals(MouseButton.PRIMARY)){
                    try {
                        UserController.getUserController().rejectFriend(currentPlayer , player);
                        list.getItems().clear();
                        list.getItems().addAll(getAllRequests());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        addFriendLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton().equals(MouseButton.PRIMARY)){
                    try {
                        UserController.getUserController().acceptFriendShip(currentPlayer , player);
                        list.getItems().clear();
                        list.getItems().addAll(getAllRequests());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        showInformationLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton().equals(MouseButton.PRIMARY)){
                    URL url = null;
                    try {
                        FriendInfoController.setSecondPlayer(player);
                        FriendInfoController.setIsFromRequestPage(true);
                        url = new File("risk\\src\\view\\graphic\\FriendInfo.fxml").toURI().toURL();
                        Parent register = FXMLLoader.load(url);
                        Scene message = new Scene(register);
                        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        window.setScene(message);
                        window.show();
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        hBox.setStyle("-fx-background-color: #f3c4fb ; -fx-border-radius: 20px ; -fx-background-radius: 20px ");
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.getChildren().addAll(imageView , usernameLabel , addFriendLabel , rejectLabel , showInformationLabel);
        return hBox;
    }

    public ArrayList<HBox> getAllRequests(){
        ArrayList<HBox> hBoxes = new ArrayList<>();
        ArrayList<Player> requests = currentPlayer.getRequestsForFriendShips();
        for(Player player : requests){
            hBoxes.add(makeFriendRequestHBox(player));
        }
        return hBoxes;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
            list.getItems().addAll(getAllRequests());
    }
}
