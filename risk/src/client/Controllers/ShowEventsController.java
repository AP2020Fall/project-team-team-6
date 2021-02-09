package client.Controllers;

import server.controller.EventController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import server.model.gamesModels.Event;
import server.model.usersModels.Player;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ShowEventsController implements Initializable {
    Player currentPlayer = MainPlatoController.getPlayer();
    @FXML
    ListView eventslist;

    //todo connect kardan listview
    @FXML
    public void back(ActionEvent event) throws IOException {
        URL url = new File("risk\\src\\Client\\graphic\\MainPlato.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }

    private ArrayList<HBox> makeEventList() {
        ArrayList<Event> allEventsForPlayer = new ArrayList<>(EventController.getEventController().getEventForPlayer(currentPlayer).values());
        ArrayList<HBox> hBoxes = new ArrayList<>();
        for (Event event : allEventsForPlayer) {
            String eventId = String.valueOf(event.getEventID());
            String eventName = event.getGameName();
            String eventPoint = String.valueOf(event.getEventPoint());
            String startDate = changeDateToString(event.getStartDate());
            String endDate = changeDateToString(event.getStartDate());
            Label firstLabel = makeLabel(eventId);
            Label secondLabel = makeLabel(eventPoint);
            secondLabel.setPadding(new Insets(0, 0, 0, 30));
            Label thirdLabel = makeLabel(startDate);
            thirdLabel.setPadding(new Insets(0, 0, 0, 10));
            Label fourthLabel = makeLabel(endDate);
            Label fifthLabel = makeLabel(eventName);
            HBox hBox = new HBox(25);
            hBox.setPadding(new Insets(5, 5, 5, 30));
            hBox.setStyle("-fx-background-color: #90e0ef ; -fx-background-radius: 20px; -fx-border-radius: 20px");
            hBox.getChildren().addAll(firstLabel, secondLabel, thirdLabel, fourthLabel, fifthLabel);
            hBoxes.add(hBox);
        }
        return hBoxes;
    }

    private Label makeLabel(String text) {
        Label label = new Label(text);
        label.setStyle("-fx-font-size: 15px ; -fx-text-fill:black ");
        return label;
    }

    private String changeDateToString(LocalDate localDate) {
        String dateInString = String.valueOf(localDate);
        String[] year = dateInString.split("T");
        String[] date = year[0].split("-");
        String result = date[0] + "/" + date[1] + "/" + date[2];
        return result;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        eventslist.getItems().addAll(makeEventList());
        eventslist.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton().equals(MouseButton.PRIMARY)) {
                    if (event.getClickCount() == 2) {
                        ObservableList<HBox> list = eventslist.getSelectionModel().getSelectedItems();
                        for (HBox hBox : list) {
                            Label label = (Label) hBox.getChildren().get(0);
                            int eventId = Integer.parseInt(label.getText());
                            Event event1 = EventController.getEventController().findEventByEventId(eventId);
                            try {
                                URL url = new File("risk\\src\\Client\\graphic\\JoinEvent.fxml").toURI().toURL();
                                JoinEventController.setEvent(event1);
                                Stage stage = (Stage) eventslist.getScene().getWindow();
                                try {
                                    Parent root = FXMLLoader.load(url);
                                    Scene scene = new Scene(root);
                                    stage.setScene(scene);
                                    stage.show();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } catch (MalformedURLException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });

    }
}
