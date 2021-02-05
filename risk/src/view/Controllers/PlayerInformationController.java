package view.Controllers;

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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.usersModels.Player;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PlayerInformationController implements Initializable {
    // TODO: 1/30/2021 ...........kolan todo......
    private static ArrayList<Player> players = new ArrayList<>();

    public static ArrayList<Player> getPlayers() {
        return players;
    }

    @FXML
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
        URL url = new File("risk\\src\\view\\graphic\\MapGames.fxml").toURI().toURL();
        Parent register = FXMLLoader.load(url);
        Scene message = new Scene(register);
        TransformationList<Object, Object> event = null;
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(message);
        window.show();
    }


    public void submit(MouseEvent mouseEvent) throws Exception {
        RadioButton selectedRadioButtonColor = (RadioButton) color.getSelectedToggle() ;
        String colorgroup = selectedRadioButtonColor.getText();
        String usernametext = username.getText();
        if (!usernametext.isEmpty()){
            if (!UserController.getUserController().checkUsername(usernametext)){
                Player player = UserController.getUserController().getPlayerByUsername(usernametext);
                if (player != null){
                    players.add(player);
                }
            } else {
                notfounderror.setVisible(true);
            }

        }
        else {
            emptierror.setVisible(true);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        players.add(MainPlatoController.getPlayer());
    }
}

