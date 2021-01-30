package view.Controllers;

import controller.UserController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.usersModels.Admin;
import model.usersModels.Massage;
import model.usersModels.Player;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MessageFromAdminController implements Initializable {
    public ComboBox userNames;
    public ImageView sendButton;
    public TextField textField;
    public ListView textLists;
    private static ArrayList<Player> players = new ArrayList<>();
    public Button backButton;
    private Admin admin = UserController.getUserController().getAdmin();
    private ArrayList<HBox> hBoxes = new ArrayList<>();


    public static ArrayList<Player> getPlayers() {
        return players;
    }

    public static void setPlayers(ArrayList<Player> players) {
        MessageFromAdminController.players = players;
    }

    public void sendMessage(MouseEvent event) {
        String messageText = textField.getText();
        if(!messageText.isEmpty()){
            for(Player player : players){
                UserController.getUserController().sendMessageFromAdmin(player , messageText);
            }
        }
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(5 , 5 , 5 , 5));
        Label text = new Label(messageText);
        text.setPadding(new Insets(5 , 5, 5, 5));
        hBox.setAlignment(Pos.TOP_RIGHT);
        text.setStyle("-fx-background-color: #dee2e6 ; -fx-text-fill: #4d194d ; -fx-background-radius: 10px ; -fx-border-radius: 10px ;-fx-font-size: 20px ; -fx-text-alignment: right");
        textField.setText("");
        this.hBoxes.add(hBox);
    }

    public void back(ActionEvent event) throws IOException {
        URL url = new File("risk\\src\\view\\graphic\\ShowAllPlayers.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (Player player : players) {
            userNames.getItems().add(player.getUsername());
        }
        userNames.setPromptText(userNames.getItems().get(0).toString());
        textLists.getItems().addAll(hBoxes);
        int size = textLists.getItems().size();
        textLists.scrollTo(size - 1);
    }

}
