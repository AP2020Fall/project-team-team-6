package view.Controllers;

import controller.UserController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.usersModels.Player;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class ShowAllPlayersController implements Initializable {
    public Button infoButton;
    @FXML
    TextField search; // TODO: 1/15/2021  
    @FXML
    ListView list; // TODO: 1/15/2021  
    @FXML
    Button remove; // TODO: 1/15/2021

    private HashMap<Player , CheckBox> checkBoxes = new HashMap<>();
    private boolean isAllPlayersSelected = false;

    public void back(ActionEvent event) throws IOException {
        URL url = new File("risk\\src\\view\\graphic\\MainMenuAdmin.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }

    public void info(ActionEvent event) throws IOException {
        int numberOfChosenCheckBoxes = 0;
        Player chosenPlayer = null;
        for(Player player : checkBoxes.keySet()){
            CheckBox checkBox = checkBoxes.get(player);
            if(checkBox.isSelected()) {
                numberOfChosenCheckBoxes++;
                chosenPlayer = player;
            }
        }
        if(numberOfChosenCheckBoxes == 1) {
            PlayersInfoController.setPlayer(chosenPlayer);
            URL url = new File("risk\\src\\view\\graphic\\PlayersInfo.fxml").toURI().toURL();
            Parent register = FXMLLoader.load(url);
            Scene message = new Scene(register);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(message);
            window.show();
        }
    }

    public void messages(ActionEvent event) throws IOException {
        ArrayList<Player> chosenPlayers = new ArrayList<>();
        for(Player player : checkBoxes.keySet()){
            CheckBox checkBox = checkBoxes.get(player);
            if(checkBox.isSelected()){
                chosenPlayers.add(player);
            }
        }
        if(chosenPlayers.size() == 1){
            Player player = chosenPlayers.get(0);
            ChatController.setSecondPlayer(player);
            ChatController.setIsFromAdmin(true);
            URL url = new File("risk\\src\\view\\graphic\\Chat.fxml").toURI().toURL();
            Parent register = FXMLLoader.load(url);
            Scene message = new Scene(register);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(message);
            window.show();
        }else if(chosenPlayers.size() > 1){
            MessageFromAdminController.setPlayers(chosenPlayers);
            URL url = new File("risk\\src\\view\\graphic\\MessageFromAdmin.fxml").toURI().toURL();
            Parent register = FXMLLoader.load(url);
            Scene message = new Scene(register);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(message);
            window.show();
        }
    }

    public void deletePlayers(ActionEvent event) {
        ArrayList<Player> players = new ArrayList<>();
        for(Player player : checkBoxes.keySet()){
            CheckBox checkBox = checkBoxes.get(player);
            if(checkBox.isSelected()){
                players.add(player);
            }
        }
        for(Player player : players) {
            UserController.getUserController().deletePlayer(player);
            list.getItems().clear();
            list.getItems().addAll(showAllPlayers());
        }
    }

    public void selectAll(ActionEvent event) {
        if(isAllPlayersSelected){
            for(CheckBox checkBox : checkBoxes.values()){
                checkBox.setSelected(false);
            }
            isAllPlayersSelected = false;
        }else{
            for(CheckBox checkBox :checkBoxes.values()){
                checkBox.setSelected(true);
            }
            isAllPlayersSelected = true;
        }

    }
    public HBox getPlayerHBox(Player player){
        HBox hBox = new HBox(5);
        hBox.setPadding(new Insets(5 , 5, 5, 5));
        hBox.setStyle("-fx-background-color: #c6def1 ; -fx-background-radius: 10px ; -fx-border-radius: 10px");
        hBox.setMinWidth(331);
        VBox vBox = new VBox(5);
        vBox.setPadding(new Insets(5 , 5,  5, 5));
        Image image = new Image("@../../NotResoures/purple player.png");
        ImageView imageView =  new ImageView(image);
        imageView.setFitHeight(80);
        imageView.setFitWidth(40);
        Label label = new Label("Username :");
        label.setStyle("-fx-text-fill: #0b132b ; -fx-font-size: 18px, bold");

        Label usernameLabel = new Label(player.getUsername());
        usernameLabel.setStyle("-fx-text-fill: #22577a ; -fx-font-size: 15px");

        Label label1 = new Label("Player ID  :");
        label1.setStyle("-fx-text-fill: #0b132b ; -fx-font-size: 18px, bold");

        String ID = String.valueOf(player.getID());
        Label IDLabel = new Label(ID);
        IDLabel.setStyle("-fx-text-fill: #22577a ; -fx-font-size: 15px");

        Label label2 = new Label("Email :");
        label2.setStyle("-fx-text-fill: #0b132b ; -fx-font-size: 18px, bold");

        String email = String.valueOf(player.getEmailAddress());
        Label emailLabel = new Label(email);
        emailLabel.setStyle("-fx-text-fill: #22577a ; -fx-font-size: 15px");
        CheckBox checkBox = new CheckBox();
        checkBoxes.put(player , checkBox);
        checkBox.setStyle("-fx-background-radius: 20px ; -fx-border-radius: 20px ;");
        checkBox.setMinSize(20 , 20);
        vBox.getChildren().addAll(label , usernameLabel , label1 , IDLabel , label2 , emailLabel , checkBox);
        hBox.getChildren().addAll(imageView , vBox);
        return hBox;
    }
    public ArrayList<HBox> searchedPlayer(String text){
        ArrayList<Player> players = UserController.getUserController().search(text);
        ArrayList<HBox> hBoxes = new ArrayList<>();
        for(Player player : players){
            hBoxes.add(getPlayerHBox(player));
        }
        return hBoxes;
    }

    public ArrayList<HBox> showAllPlayers(){
        ArrayList<Player> allPlayers = UserController.getUserController().getAllPlayers();
        ArrayList<HBox> hBoxes = new ArrayList<>();
        for(Player p  : allPlayers){
            HBox hBox = getPlayerHBox(p);
            hBoxes.add(hBox);
        }
        return hBoxes;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        list.getItems().addAll(showAllPlayers());
        search.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                String text = search.getText();
                list.getItems().clear();
                list.getItems().addAll(searchedPlayer(text));
            }
        });
    }
}
