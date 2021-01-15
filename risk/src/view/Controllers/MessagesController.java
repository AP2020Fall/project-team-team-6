package view.Controllers;

import controller.UserController;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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

public class MessagesController implements Initializable{
    private Player player = MainPlatoController.getPlayer();
    @FXML
    TextField search; //todo............
    @FXML
    ListView list; //todo..........
    @FXML
    public void back(ActionEvent event) throws IOException {
        URL url = new File("risk\\src\\view\\graphic\\Mainplato.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();

    }

    public void search(InputMethodEvent inputMethodEvent) {

    }

    public ArrayList<HBox> getInbox(){
        ArrayList<HBox> hBoxes = new ArrayList<>();
        HashSet<Player> inbox= UserController.getUserController().getAllPlayersThatHadMessageWith(player);
        for(Player player1 : inbox){
            hBoxes.add(makeVBox(player1));
        }
        return hBoxes;
    }
    private Label makeNameLabel(String text){
        Label label = new Label(text);
        label.setStyle("-fx-font-size: 20 , bold ; -fx-text-fill: white");
        return label;
    }
    private HBox makeVBox(Player secondPlayer){
        HBox hBox = new HBox(5);
        hBox.setPadding(new Insets(5 , 5 , 5, 5));
        hBox.setStyle("-fx-background-color: #ffc6ff ; -fx-border-radius: 10px ; -fx-background-radius: 10px");
        Image image = new Image("@../../NotResoures/purple player.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(80);
        imageView.setFitWidth(40);
        VBox vBox = new VBox(5);
        Label usernameLabel = makeNameLabel(secondPlayer.getUsername());
        usernameLabel.setPadding(new Insets(10 ,0 ,0 , 5 ));
        vBox.getChildren().add(usernameLabel);
        ArrayList<Massage> secondPlayersMessages = UserController.getUserController().getPlayersMassage(player , secondPlayer);
        if(!secondPlayersMessages.isEmpty()){
            int lastMessageIndex = secondPlayersMessages.size() - 1;
            Label lastMessage = makeNameLabel(secondPlayersMessages.get(lastMessageIndex).getMassage());
            lastMessage.setStyle("-fx-font-size:15px");
            vBox.getChildren().add(lastMessage);
        }
        hBox.getChildren().addAll(imageView , vBox);
        return hBox;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        list.getItems().addAll(getInbox());
    }
}
