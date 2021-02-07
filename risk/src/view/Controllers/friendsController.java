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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.usersModels.Player;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class friendsController implements Initializable {


    public TextField search;
    public ListView list;

    public void back(ActionEvent event) throws IOException {
        URL url = new File("risk\\src\\view\\graphic\\Mainplato.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }

    public HBox makeHBoxForSearchedPlayer(Player player){
        HBox hBox = new HBox(5);
        hBox.setPadding(new javafx.geometry.Insets(5 , 5, 5, 5));
        hBox.setStyle("-fx-background-color: #c6def1 ; -fx-background-radius: 10px ; -fx-border-radius: 10px");
        VBox vBox = new VBox(5);
        vBox.setPadding(new Insets(5 , 5,  5, 5));
        javafx.scene.image.Image image = new Image("@../../NotResoures/images/blue player.png");
        ImageView imageView =  new ImageView(image);
        imageView.setFitHeight(80);
        imageView.setFitWidth(40);
        javafx.scene.control.Label label = new javafx.scene.control.Label("Username :");
        label.setStyle("-fx-text-fill: #0b132b ; -fx-font-size: 18px, bold");

        javafx.scene.control.Label usernameLabel = new javafx.scene.control.Label(player.getUsername());
        usernameLabel.setStyle("-fx-text-fill: #22577a ; -fx-font-size: 15px");

        javafx.scene.control.Label label1 = new javafx.scene.control.Label("Number of games :");
        label1.setStyle("-fx-text-fill: #0b132b ; -fx-font-size: 18px, bold");

        String numberOfGames = String.valueOf(player.getNumbersOfGames());
        javafx.scene.control.Label numberOfGameLabel = new javafx.scene.control.Label(numberOfGames);
        numberOfGameLabel.setStyle("-fx-text-fill: #22577a ; -fx-font-size: 15px");

        javafx.scene.control.Label label2 = new javafx.scene.control.Label("Number of wins :");
        label2.setStyle("-fx-text-fill: #0b132b ; -fx-font-size: 18px, bold");

        String numberOfWin = String.valueOf(player.getNumbersOfWin());
        javafx.scene.control.Label numberOfWinLabel = new javafx.scene.control.Label(numberOfWin);
        numberOfWinLabel.setStyle("-fx-text-fill: #22577a ; -fx-font-size: 15px");
        vBox.getChildren().addAll(label , usernameLabel , label1 , numberOfGameLabel , label2 , numberOfWinLabel);
        hBox.getChildren().addAll(imageView , vBox);
        return hBox;
    }

    public HBox makeHBoxForFriend(int number , Player player){
        HBox hBox = new HBox(20);
        hBox.setPadding(new Insets(5 , 5, 5,5 ));
        if(number % 2 == 0 ){
            hBox.setStyle("-fx-background-color: #7e7f9a ; -fx-background-radius: 10px ; -fx-border-radius: 10px");

        }else{
            hBox.setStyle("-fx-background-color: #97a7b3 ; -fx-background-radius: 10px ; -fx-border-radius: 10px");
        }
        javafx.scene.image.Image image = new Image("@../../NotResoures/images/purple player.png");
        ImageView imageView =  new ImageView(image);
        imageView.setFitHeight(80);
        imageView.setFitWidth(40);
        Label username = makeLabel(player.getUsername()+"\n"+player.getFirstName() + " " + player.getLastName() , Color.WHITE);
        username.setStyle("-fx-font-size: 20 ; -fx-font: bold");
        hBox.getChildren().addAll(imageView , username);
        hBox.setAlignment(Pos.CENTER_LEFT);
        return hBox;
    }

    public ArrayList<HBox> makeHBoxesForFriends(){
        Player currentPlayer = MainPlatoController.getPlayer();
        ArrayList<Player> friends = currentPlayer.getFriends();
        ArrayList<HBox> allFriendsInHBox = new ArrayList<>();
        for(Player player : friends){
            int number = friends.indexOf(player);
            allFriendsInHBox.add(makeHBoxForFriend(number , player));
        }
        return allFriendsInHBox;
    }

    public Label makeLabel(String text , Color color){
        Label label = new Label(text);
        label.setTextFill(color);
        return label;
    }

    public ArrayList<HBox> makeHboxesForSearching(ArrayList<Player> players){
        ArrayList<HBox> hBoxes = new ArrayList<>();
        for(Player player : players){
            hBoxes.add(makeHBoxForSearchedPlayer(player));
        }
        return hBoxes;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        list.getItems().addAll(makeHBoxesForFriends());
        search.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(search.getText().isEmpty()){
                    list.getItems().clear();
                    list.getItems().addAll(makeHBoxesForFriends());
                }else{
                    String searchText = search.getText();
                    ArrayList<Player> players = UserController.getUserController().search(searchText);
                    list.getItems().clear();
                    list.getItems().addAll(makeHboxesForSearching(players));
                }
            }
        });
        list.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton().equals(MouseButton.PRIMARY)){
                    if(event.getClickCount() == 2){
                        HBox hBox = (HBox) list.getSelectionModel().getSelectedItem();
                        if(search.getText().isEmpty()){
                            Label label =(Label) hBox.getChildren().get(1);
                            String[] s = label.getText().split("\n");
                            Player secondPlayer = UserController.getUserController().findPlayerByUserName(s[0]);
                            FriendInfoController.setSecondPlayer(secondPlayer);
                        }else{
                            VBox vBox = (VBox) hBox.getChildren().get(1);
                            Label label = (Label) vBox.getChildren().get(1);
                            Player secondPlayer = UserController.getUserController().findPlayerByUserName(label.getText());
                            FriendInfoController.setSecondPlayer(secondPlayer);
                        }
                        URL url = null;
                        try {
                            FriendInfoController.setIsFromRequestPage(false);
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
            }
        });
    }
}
