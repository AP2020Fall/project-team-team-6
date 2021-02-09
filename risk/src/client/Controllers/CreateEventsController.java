package client.Controllers;

import server.controller.EventController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import server.model.gamesModels.RiskGame;
import server.model.gamesModels.RiskGameType;
import server.model.usersModels.Player;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateEventsController implements Initializable {
    private static ArrayList<Player> invitedPlayers = new ArrayList<>();
    public Label errorName;
    public Label errorDate;
    public Label errorDate1;
    public Label errorCoin;
    public Label errorInvite;

    private  static String nameString = "" ;
    private  static String startString = "" ;
    private  static String endString = "";
    private  static String coinString = "";

    @FXML
    TextField name;
    @FXML
    TextField start;
    @FXML
    TextField end;
    @FXML
    TextField winners;

    public static ArrayList<Player> getInvitedPlayers() {
        return invitedPlayers;
    }

    @FXML
    public void back(ActionEvent event) throws IOException {
        URL url = new File("risk\\src\\Client\\graphic\\ShowEventsAdmin.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }

    @FXML
    public void invite(ActionEvent event) throws IOException {
        nameString = name.getText();
        startString = start.getText();
        endString = end.getText();
        coinString = winners.getText();

        URL url = new File("risk\\src\\Client\\graphic\\SendEventInvitation.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }

    @FXML
    public void create(ActionEvent event) throws Exception {
        LocalDate startDate = null;
        LocalDate endDate = null;
        Pattern pattern = Pattern.compile("^20[2-9][0-9]\\/([1-9]|1[0-2])\\/([1-9]|1[0-9]|2[0-9]|30)$");
        if(name.getText().isEmpty()){
            errorName.setVisible(true);
        }
        if(start.getText().isEmpty()){
            errorDate.setVisible(true);
        }
        if(!start.getText().isEmpty()){
            Matcher matcher = pattern.matcher(start.getText());
            if(!matcher.find()){
                errorDate.setVisible(true);
            }else{
                String[] dateInString = start.getText().split("\\/");
                int year = Integer.parseInt(dateInString[0]);
                int month = Integer.parseInt(dateInString[1]);
                int day = Integer.parseInt(dateInString[2]);
                LocalDate startDateTime =LocalDate.of(year , month , day);
                startDate = EventController.getEventController().checkStartDate(startDateTime);
                if(startDate == null){
                    errorDate.setVisible(true);
                }
            }
        }
        if(end.getText().isEmpty()){
            errorDate1.setVisible(true);
        }
        if(!end.getText().isEmpty()){
            Matcher matcher = pattern.matcher(end.getText());
            if(!matcher.find()){
                errorDate1.setVisible(true);
            }else{
                String[] dateInString = end.getText().split("\\/");
                int year = Integer.parseInt(dateInString[0]);
                int month = Integer.parseInt(dateInString[1]);
                int day = Integer.parseInt(dateInString[2]);
                LocalDate endDateTime = LocalDate.of(year , month , day);
                endDate = EventController.getEventController().checkEndDate(endDateTime , startDate);
                if(endDate == null){
                    errorDate.setVisible(true);
                }
            }
        }
        if(winners.getText().isEmpty()){
            errorCoin.setVisible(true);
        }
        if(invitedPlayers.size() == 0){
            errorInvite.setVisible(true);
        }
        if(!winners.getText().isEmpty() && !name.getText().isEmpty() && !start.getText().isEmpty() && !end.getText().isEmpty() && startDate != null && endDate != null && invitedPlayers.size() != 0) {
            double eventPoint = Double.parseDouble(winners.getText());
            RiskGame riskGame = new RiskGame(name.getText() , null, RiskGameType.PRIVATE, 6,(int)eventPoint , 180, true);
            EventController.getEventController().createNewPrivateEvent(startDate, endDate, riskGame, invitedPlayers);
            URL url = new File("risk\\src\\Client\\graphic\\ShowEventsAdmin.fxml").toURI().toURL();
            Parent register = FXMLLoader.load(url);
            Scene message = new Scene(register);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(message);
            window.show();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.name.setText(nameString);
        this.start.setText(startString);
        this.end.setText(endString);
        this.winners.setText(coinString);
    }
}
