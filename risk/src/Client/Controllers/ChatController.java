package Client.Controllers;

import Server.controller.UserController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import Server.model.database.LocalDataBase;
import Server.model.usersModels.Admin;
import Server.model.usersModels.Massage;
import Server.model.usersModels.Player;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ChatController implements Initializable {
    private static Player currentUser = null;
    private Admin admin = LocalDataBase.getLocalDataBase().getAdmin();
    private static  Player secondPlayer = null;
    private static boolean isFromPlayerInfoPage = false;
    private static  boolean isFromAdmin = false;

    public static boolean isIsFromAdmin() {
        return isFromAdmin;
    }

    public static void setCurrentUser(Player currentUser) {
        ChatController.currentUser = currentUser;
    }

    public static void setIsFromAdmin(boolean isFromAdmin) {
        ChatController.isFromAdmin = isFromAdmin;
    }

    public static boolean isIsFromPlayerInfoPage() {
        return isFromPlayerInfoPage;
    }

    public static void setIsFromPlayerInfoPage(boolean isFromPlayerInfoPage) {
        ChatController.isFromPlayerInfoPage = isFromPlayerInfoPage;
    }

    @FXML
    private ListView<HBox> textLists;

    @FXML
    private TextField textField;

    @FXML
    private ImageView sendButton;

    @FXML
    private Label usernameLabel;

    @FXML
    private Button backButton;

    public Player getCurrentUser() {
        return currentUser;
    }

    public static Player getSecondPlayer() {
        return secondPlayer;
    }


    public static void setSecondPlayer(Player secondPlayer) {
        ChatController.secondPlayer = secondPlayer;
    }
    public ArrayList<HBox> loadInBox(){
        ArrayList<Massage> getPlayerMessagesWhitSecondPlayer = null;
        if(currentUser == null){
            getPlayerMessagesWhitSecondPlayer = UserController.getUserController().getAdminMessages(secondPlayer);
        }else if(secondPlayer == null){
            getPlayerMessagesWhitSecondPlayer = UserController.getUserController().getAdminMessages(currentUser);
        }else{
            getPlayerMessagesWhitSecondPlayer= UserController.getUserController().getPlayersMassage(currentUser , secondPlayer );
        }
        ArrayList<HBox> hBoxes = new ArrayList<>();
        for(Massage message : getPlayerMessagesWhitSecondPlayer){
            HBox hBox = new HBox();
            hBox.setPadding(new Insets(5 , 5 , 5 , 5));
            String messageText = message.getMassage();
            Label text = new Label(messageText);
            text.setPadding(new Insets(5 , 5, 5, 5));
            if(currentUser == null){
                if(message.getSender().getID() == admin.getID()){
                    hBox.setAlignment(Pos.TOP_RIGHT);
                    text.setStyle("-fx-background-color: #dee2e6 ; -fx-text-fill: #4d194d ; -fx-background-radius: 10px ; -fx-border-radius: 10px ;-fx-font-size: 20px ; -fx-text-alignment: right");
                }else{
                    hBox.setAlignment(Pos.TOP_LEFT);
                    text.setStyle("-fx-background-color: #4d194d ; -fx-text-fill: #dee2e6 ; -fx-background-radius: 10px ; -fx-border-radius: 10px ;-fx-font-size: 20px ; -fx-text-alignment: left");
                }
                hBox.getChildren().add(text);
            }else if(secondPlayer == null){
                if(message.getSender().getID() == currentUser.getID()){
                    hBox.setAlignment(Pos.TOP_RIGHT);
                    text.setStyle("-fx-background-color: #dee2e6 ; -fx-text-fill: #4d194d ; -fx-background-radius: 10px ; -fx-border-radius: 10px ;-fx-font-size: 20px ; -fx-text-alignment: right");
                }else{
                    hBox.setAlignment(Pos.TOP_LEFT);
                    text.setStyle("-fx-background-color: #4d194d ; -fx-text-fill: #dee2e6 ; -fx-background-radius: 10px ; -fx-border-radius: 10px ;-fx-font-size: 20px ; -fx-text-alignment: left");
                }
                hBox.getChildren().add(text);
            }else{
                if(message.getSender().getID() == currentUser.getID()){
                    hBox.setAlignment(Pos.TOP_RIGHT);
                    text.setStyle("-fx-background-color: #dee2e6 ; -fx-text-fill: #4d194d ; -fx-background-radius: 10px ; -fx-border-radius: 10px ;-fx-font-size: 20px ; -fx-text-alignment: right");
                }else{
                    hBox.setAlignment(Pos.TOP_LEFT);
                    text.setStyle("-fx-background-color: #4d194d ; -fx-text-fill: #dee2e6 ; -fx-background-radius: 10px ; -fx-border-radius: 10px ;-fx-font-size: 20px ; -fx-text-alignment: left");
                }
                hBox.getChildren().add(text);
            }
            hBox.setStyle("-fx-background-color: transparent");
            hBoxes.add(hBox);
        }
        return hBoxes;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(secondPlayer != null)
        usernameLabel.setText(secondPlayer.getUsername());
        else{
            usernameLabel.setText("Admin");
        }
        textLists.getItems().addAll(loadInBox());
        int size = textLists.getItems().size();
        textLists.scrollTo(size - 1);
    }

    public void back(ActionEvent event) {
        URL url = null;
        secondPlayer = null;
        currentUser = null;
        try {
            if(!isIsFromPlayerInfoPage() && !isFromAdmin) {
                isFromAdmin = false;
                isFromPlayerInfoPage =false;
                url = new File("risk\\src\\Client\\graphic\\messages.fxml").toURI().toURL();
            }
            else if(isFromAdmin){
                isFromAdmin = false;
                isFromPlayerInfoPage =false;
                url = new File("risk\\src\\Client\\graphic\\ShowAllPlayers.fxml").toURI().toURL();
            }
            else {
                isFromAdmin = false;
                isFromPlayerInfoPage =false;
                url = new File("risk\\src\\Client\\graphic\\FriendInfo.fxml").toURI().toURL();
            }
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

    public void sendMessage(MouseEvent event) {
        String message = textField.getText();
        if(!message.isEmpty()){
            if(secondPlayer != null && currentUser != null)
            UserController.getUserController().sendMessage(currentUser , secondPlayer , message );
            else if(currentUser == null){
                UserController.getUserController().sendMessageFromAdmin(secondPlayer , message);
            }else{
                UserController.getUserController().sendMessageToAdmin(currentUser , message);
            }
            textField.setText("");
            textLists.getItems().clear();
            textLists.getItems().addAll(loadInBox());
            int size = textLists.getItems().size();
            textLists.scrollTo(size - 1);
        }
    }
}
