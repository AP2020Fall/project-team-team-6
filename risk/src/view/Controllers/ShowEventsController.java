package view.Controllers;

import controller.EventController;
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
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.database.LocalDataBase;
import model.gamesModels.Event;
import model.gamesModels.RiskGame;
import model.usersModels.Player;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ShowEventsController implements Initializable {
    Player currentPlayer = MainPlatoController.getPlayer();
    @FXML
    ListView eventslist;
    //todo connect kardan listview
    @FXML
    public void back(ActionEvent event) throws IOException {
        URL url = new File("risk\\src\\view\\graphic\\MainPlato.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }
    private ArrayList<HBox> makeEventList(){
        ArrayList<Event> allEventsForPlayer = new ArrayList<>(EventController.getEventController().getEventForPlayer(currentPlayer).values());
        ArrayList<HBox> hBoxes = new ArrayList<>();
        for(Event event : allEventsForPlayer){
            String eventId = String.valueOf(event.getEventID());
            String eventName = event.getGameName();
            String eventPoint = String.valueOf(event.getEventPoint());
            String startDate = changeDateToString(event.getStartDate());
            String endDate = changeDateToString(event.getStartDate());
            Label firstLabel = makeLabel(eventId);
            Label secondLabel = makeLabel(eventPoint);
            Label thirdLabel = makeLabel(startDate);
            Label fourthLabel = makeLabel(endDate);
            Label fifthLabel = makeLabel(eventName);
            HBox hBox = new HBox(20);
            hBox.setPadding(new Insets(5 , 5, 5, 5));
            hBox.setStyle("-fx-background-color: #90e0ef ; -fx-background-radius: 20px; -fx-border-radius: 20px");
            hBox.getChildren().addAll(firstLabel , secondLabel , thirdLabel , fourthLabel , fifthLabel);
            hBoxes.add(hBox);
        }
     return hBoxes;
    }
    private Label makeLabel(String text){
        Label label= new Label(text);
        label.setStyle("-fx-font-size: 10px ; -fx-text-fill:white ");
        return label;
    }
    private String changeDateToString(LocalDateTime localDateTime){
        String dateInString = String.valueOf(localDateTime);
        String[] year = dateInString.split("T");
        String[] date = year[0].split("-");
        String result = date[0]+"/"+date[1]+"/"+date[2] + " Time : " + year[1];
        return result;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        eventslist.getItems().addAll(makeEventList());
    }
}
