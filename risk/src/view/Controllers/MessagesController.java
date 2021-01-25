package view.Controllers;

import controller.UserController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.usersModels.Massage;
import model.usersModels.Player;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;

public class MessagesController implements Initializable {
    private Player player = MainPlatoController.getPlayer();


    @FXML
    TextField search;
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


    public ArrayList<HBox> getInbox() {
        ArrayList<HBox> hBoxes = new ArrayList<>();

        HashSet<Player> inbox= UserController.getUserController().getAllPlayersThatHadMessageWith(player);
        for(Player player1 : inbox){
            hBoxes.add(makeHBoxFromMessages(player1));
        }
        return hBoxes;
    }

    private Label makeNameLabel(String text) {
        Label label = new Label(text);
        label.setStyle("-fx-font-size: 20 , bold ; -fx-text-fill: white");
        return label;
    }

    private HBox makeHBoxFromMessages(Player secondPlayer){
        HBox hBox = new HBox(5);
        hBox.setPadding(new Insets(5, 5, 5, 5));
        hBox.setStyle("-fx-background-color: #ffc6ff ; -fx-border-radius: 10px ; -fx-background-radius: 10px");
        Image image = new Image("@../../NotResoures/purple player.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(80);
        imageView.setFitWidth(40);
        VBox vBox = new VBox(5);
        Label usernameLabel = makeNameLabel(secondPlayer.getUsername());
        usernameLabel.setPadding(new Insets(10, 0, 0, 5));
        vBox.getChildren().add(usernameLabel);
        ArrayList<Massage> secondPlayersMessages = UserController.getUserController().getPlayersMassage(player, secondPlayer);
        if (!secondPlayersMessages.isEmpty()) {
            int lastMessageIndex = secondPlayersMessages.size() - 1;
            Label lastMessage = makeNameLabel(secondPlayersMessages.get(lastMessageIndex).getMassage());
            lastMessage.setStyle("-fx-font-size:15px");
            vBox.getChildren().add(lastMessage);
        }
        hBox.getChildren().addAll(imageView, vBox);
        return hBox;
    }
    public HBox makeHBoxForSearchedPlayer(Player player){
        HBox hBox = new HBox(5);
        hBox.setPadding(new Insets(5 , 5, 5, 5));
        hBox.setStyle("-fx-background-color: #c6def1 ; -fx-background-radius: 10px ; -fx-border-radius: 10px");
        VBox vBox = new VBox(5);
        vBox.setPadding(new Insets(5 , 5,  5, 5));
        Image image = new Image("@../../NotResoures/blue player.png");
        ImageView imageView =  new ImageView(image);
        imageView.setFitHeight(80);
        imageView.setFitWidth(40);
        Label label = new Label("Username :");
        label.setStyle("-fx-text-fill: #0b132b ; -fx-font-size: 18px, bold");

        Label usernameLabel = new Label(player.getUsername());
        usernameLabel.setStyle("-fx-text-fill: #22577a ; -fx-font-size: 15px");

        Label label1 = new Label("Number of games :");
        label1.setStyle("-fx-text-fill: #0b132b ; -fx-font-size: 18px, bold");

        String numberOfGames = String.valueOf(player.getNumbersOfGames());
        Label numberOfGameLabel = new Label(numberOfGames);
        numberOfGameLabel.setStyle("-fx-text-fill: #22577a ; -fx-font-size: 15px");

        Label label2 = new Label("Number of wins :");
        label2.setStyle("-fx-text-fill: #0b132b ; -fx-font-size: 18px, bold");

        String numberOfWin = String.valueOf(player.getNumbersOfWin());
        Label numberOfWinLabel = new Label(numberOfWin);
        numberOfWinLabel.setStyle("-fx-text-fill: #22577a ; -fx-font-size: 15px");
        vBox.getChildren().addAll(label , usernameLabel , label1 , numberOfGameLabel , label2 , numberOfWinLabel);
        hBox.getChildren().addAll(imageView , vBox);
        return hBox;
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
        list.getItems().addAll(getInbox());
        search.setOnKeyReleased(new EventHandler<KeyEvent>() {
            //TODO chat menu
            @Override
            public void handle(KeyEvent event) {
                if(search.getText().isEmpty()){
                    list.getItems().clear();
                    list.getItems().addAll(getInbox());
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
                        if(search.getText().isEmpty()){
                            HBox hBox = (HBox) list.getSelectionModel().getSelectedItem();
                            VBox vBox = (VBox) hBox.getChildren().get(1);
                            String username = ((Label) vBox.getChildren().get(0)).getText();
                            Player player = UserController.getUserController().findPlayerByUserName(username);
                            ChatController.setSecondPlayer(player);
                        }else{
                            HBox hBox = (HBox) list.getSelectionModel().getSelectedItem();
                            VBox vBox = (VBox) hBox.getChildren().get(1);
                            String username = ((Label) vBox.getChildren().get(1)).getText();
                            Player player = UserController.getUserController().findPlayerByUserName(username);
                            ChatController.setSecondPlayer(player);
                        }
                        URL url = null;
                        ChatController.setIsFromPlayerInfoPage(false);
                        try {
                            url = new File("risk\\src\\view\\graphic\\Chat.fxml").toURI().toURL();
                            Parent register = FXMLLoader.load(url);
                            Scene message = new Scene(register);
                            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            window.setScene(message);
                            window.show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }
}
