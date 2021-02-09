package Client.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import Server.model.gamesModels.Event;
import Server.model.usersModels.Player;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class JoinEventController implements Initializable {
    public Label point;
    public Button joinEventButton;
    public Label endDate;
    public Label startDate;
    public Label eventName;
    public Button backButton;
    public static Event event = null;
    private Player player = MainPlatoController.getPlayer();

    public void joinEvent(ActionEvent event) {
        //TODO complete ..... -> shayan
    }

    public void back(ActionEvent event) {
        try {
            URL url = new File("risk\\src\\Client\\graphic\\ShowEvent.fxml").toURI().toURL();
            Stage stage = (Stage) backButton.getScene().getWindow();
            try {
                Parent root = FXMLLoader.load(url);
                Scene scene = new Scene(root);
                stage.setScene(scene);
                setEvent(null);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static Event getEvent() {
        return event;
    }

    public static void setEvent(Event event) {
        JoinEventController.event = event;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        eventName.setText(event.getGameName());
        startDate.setText(changeDateToString(event.getStartDate()));
        endDate.setText(changeDateToString(event.getEndDate()));
        point.setText(String.valueOf(event.getEventPoint()));
    }

    private String changeDateToString(LocalDate localDate) {
        String dateInString = String.valueOf(localDate);
        String[] year = dateInString.split("T");
        String[] date = year[0].split("-");
        String result = date[0] + "/" + date[1] + "/" + date[2];
        return result;
    }
}
