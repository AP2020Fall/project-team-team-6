package view.Controllers;

import controller.UserController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.usersModels.GameLog;
import model.usersModels.Player;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainPlatoController implements Initializable {
    private static Player player =null;
    protected static UserController userController = UserController.getUserController();;
    public Pane gameHistoryPane;
    public ListView gameHistoryList;

    public static Player getPlayer() {
        return player;
    }

    public static void setPlayer(Player player) {
        MainPlatoController.player = player;
    }

    @FXML
    Label friends;
    @FXML
    Label wins;
    @FXML
    Label coins;
    @FXML
    Label username;

    @FXML
    private void setLabel() {
        friends.setText(String.valueOf(player.getFriends().size()));
        wins.setText(String.valueOf(player.getNumbersOfWin()));
        coins.setText(String.valueOf(player.getRate()));
        username.setText(LoginController.getUser().getUsername());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setLabel();
    }

    @FXML
    public void events(ActionEvent actionEvent) throws IOException {
        URL url = new File("risk\\src\\view\\graphic\\ShowEvent.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }

    @FXML
    public void games(ActionEvent actionEvent) {
        //todo.............
    }

    @FXML
    public void messages(ActionEvent actionEvent) throws IOException {
        URL url = new File("risk\\src\\view\\graphic\\messages.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        Stage stage = (Stage) username.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void scoreboard(ActionEvent actionEvent) {
        //todo..............
    }

    @FXML
    public void gameshistory(ActionEvent actionEvent) {
        gameHistoryPane.setVisible(true);
        gameHistoryPane.setDisable(false);
//        scoreBoardPane.setVisible(false);
//        scoreBoardPane.setDisable(true);
        gameHistoryList.getItems().clear();
        gameHistoryList.getItems().addAll(getAllGameLogs());

    }

    @FXML
    public void requests(ActionEvent actionEvent) throws IOException {
        URL url = new File("risk\\src\\view\\graphic\\FriendsRequest.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }

    @FXML
    public void friends(ActionEvent actionEvent) throws IOException {
        URL url = new File("risk\\src\\view\\graphic\\Friends.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }

    @FXML
    public void editinformation(ActionEvent actionEvent) throws IOException {
        URL url = new File("risk\\src\\view\\graphic\\EditInformation.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        MainPlatoController.setPlayer(null);
        URL url = new File("risk\\src\\view\\graphic\\Login.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }

    public HBox makeGameHistoryInHBox(GameLog gameLog){
        Label gameName= makeLabel(gameLog.getRiskGameName() );
        gameName.setPadding(new Insets(0 , 0 , 0 ,30));
        gameName.setMinWidth(100);
        Label point = makeLabel(String.valueOf(gameLog.getPoints()));
        point.setMinWidth(50);
        point.setPadding(new Insets(0 , 0, 0, 30));
//        point.setPadding(new Insets(0 ,0 , 0, 60));
        Label winner = makeLabel(gameLog.getWinner().getUsername());
        winner.setMinWidth(100);
        winner.setPadding(new Insets(0 , 0 , 0, 50));
        Label date = makeLabel(changeDateToString(gameLog.getLocalDateTime()));
        date.setMinWidth(100);
//        date.setPadding(new Insets(0 , 0 , 0 , 5));
        HBox hBox = new HBox(20);
        hBox.setStyle("-fx-border-radius: 20px ; -fx-background-radius: 20px ; -fx-background-color: #ccdbfd");
        hBox.setPadding(new Insets(12 , 10 , 12 , 10));
        hBox.getChildren().addAll(gameName , point , winner , date);
        return hBox;
    }
    public ArrayList<HBox> getAllGameLogs(){
        ArrayList<HBox> allGameLogs = new ArrayList<>();
        ArrayList<GameLog> gameLogs = player.getGameLogs();
        HBox firstLine = new HBox(50);
        firstLine.setPadding(new Insets(12 , 10 , 12 , 10));
        firstLine.setStyle(" -fx-background-color: #f72585 ; -fx-border-radius: 20px ; -fx-background-radius: 20px ;");
        Label gameNameLabel = new Label("Game name");
        gameNameLabel.setStyle(" -fx-background-radius: 10px ; -fx-border-radius: 10px ; -fx-text-fill: white ; -fx-font-size: 20px , bold ; -fx-background-size: 10px");
        Label pointLabel = new Label("Point");
        pointLabel.setStyle(" -fx-background-radius: 10px ; -fx-border-radius: 10px ; -fx-text-fill: white ; -fx-font-size: 20px , bold ");
        Label winnerLabel = new Label("Winner");
        winnerLabel.setStyle("-fx-background-radius: 10px ; -fx-border-radius: 10px ; -fx-text-fill: white ; -fx-font-size: 20px , bold ");
        Label dataLabel = new Label("Date");
        dataLabel.setStyle("-fx-background-radius: 10px ; -fx-border-radius: 10px ; -fx-text-fill: white ; -fx-font-size: 20px , bold ");
        firstLine.getChildren().addAll(gameNameLabel , pointLabel , winnerLabel , dataLabel);
        allGameLogs.add(firstLine);
        for(GameLog gameLog : gameLogs){
            allGameLogs.add(makeGameHistoryInHBox(gameLog));
        }
        return allGameLogs;
    }
    private String changeDateToString(LocalDateTime localDateTime) {
        String dateInString = String.valueOf(localDateTime);
        String[] year = dateInString.split("T");
        String[] date = year[0].split("-");
        String result = date[0] + "/" + date[1] + "/" + date[2];
        return result;
    }
    private Label makeLabel(String text){
        Label label = new Label(text +"\t");
        label.setStyle("-fx-font-size: 15px ; -fx-text-fill: black ; -fx-text-alignment: center; ");
        return label;
    }



}
