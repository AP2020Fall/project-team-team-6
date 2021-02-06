package view.Controllers;

import controller.GameController;
import controller.UserController;
import javafx.collections.transformation.TransformationList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.gamesModels.Color;
import model.gamesModels.RiskGame;
import model.gamesModels.RiskGameType;
import model.usersModels.Player;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PlayerInformationController implements Initializable {
    // TODO: 1/30/2021 ...........kolan todo......
    private static ArrayList<Player> players = new ArrayList<>();
    public ImageView submitButton;
    public Pane nextButton;

    public static ArrayList<Player> getPlayers() {
        return players;
    }

    private static boolean manual ;

    public static boolean isManual() {
        return manual;
    }

    public static void setManual(boolean manual) {
        PlayerInformationController.manual = manual;
    }

    @FXML
    private TextField username;

    @FXML
    private Label playernumber;

    @FXML
    private RadioButton red;

    @FXML
    private RadioButton green;

    @FXML
    private RadioButton purple;

    @FXML
    private RadioButton orange;

    @FXML
    private RadioButton yellow;

    @FXML
    private RadioButton blue;
    @FXML
    private ToggleGroup color;
    @FXML
    private Label emptierror;
    @FXML
    private Label notfounderror;


    public void back(MouseEvent mouseEvent) throws IOException {
        URL url = new File("risk\\src\\view\\graphic\\ManualPlacement.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        TransformationList<Object, Object> event = null;
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }

    public void next(MouseEvent mouseEvent) throws IOException {
        if(players.size() > 1) {
            String riskGameName = ManualPlacementController.getGameName();
            Player currentPlayer = MainPlatoController.getPlayer();
            long time = (long) ManualPlacementController.getTime();
            RiskGame riskGame = new RiskGame(riskGameName, currentPlayer, RiskGameType.PRIVATE, players.size(), 10, time, manual);
            for(Player player : players){
                try {
                    if(player != currentPlayer)
                    GameController.getGameController().addPlayerToGame(player , riskGame);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            GameController.getGameController().makeOnlineGameReadyToStart(riskGame);
            MapGamesController.setRiskGame(riskGame);
            URL url = new File("risk\\src\\view\\graphic\\MapGames.fxml").toURI().toURL();
            Parent register = FXMLLoader.load(url);
            Scene message = new Scene(register);
            TransformationList<Object, Object> event = null;
            Stage window = (Stage) nextButton.getScene().getWindow();
            window.setScene(message);
            window.show();
        }
    }


    public void submit(MouseEvent mouseEvent) throws Exception {
        int numberOfPlayers = ManualPlacementController.getNumberOfPlayers();
        if (players.size() < numberOfPlayers) {
            Color color1 = null;
            RadioButton selectedRadioButtonColor = (RadioButton) color.getSelectedToggle();
            String colorgroup = selectedRadioButtonColor.getId();
            if (colorgroup.equals("red")) {
                color1 = Color.RED;
            } else if (colorgroup.equals("green")) {
                color1 = Color.GREEN;
            } else if (colorgroup.equals("purple")) {
                color1 = Color.PURPLE;
            } else if (colorgroup.equals("orange")) {
                color1 = Color.ORANGE;
            } else if (colorgroup.equals("yellow")) {
                color1 = Color.YELLOW;
            } else if (colorgroup.equals("blue")) {
                color1 = Color.BLUE;
            }
            String usernametext = username.getText();
            if (!usernametext.isEmpty() && color1 != null) {
                if (!UserController.getUserController().checkUsername(usernametext)) {
                    Player player = UserController.getUserController().getPlayerByUsername(usernametext);
                    if (player != null) {
                        if(!players.contains(player)) {
                            player.setCurrentColor(color1);
                            players.add(player);
                            selectedRadioButtonColor.setDisable(true);
                            username.setText("");
                            username.setEditable(true);
                        }
                    }
                } else {
                    notfounderror.setVisible(true);
                }

            } else {
                emptierror.setVisible(true);
            }
        }else{
            submitButton.setDisable(true);
            submitButton.setStyle("opacity: 0.5%");
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        int numberOfPlayers = ManualPlacementController.getNumberOfPlayers();
        playernumber.setText(String.valueOf(numberOfPlayers));
        if(players.size() == 0 ){
            username.setText(MainPlatoController.getPlayer().getUsername());
            username.setEditable(false);
        }else{
            username.setEditable(true);
        }
    }
}

