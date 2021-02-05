package view.Controllers;

import com.sun.org.apache.bcel.internal.generic.LADD;
import controller.GameController;
import javafx.embed.swing.JFXPanel;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import model.gamesModels.Country;
import model.gamesModels.GameStages;
import model.gamesModels.RiskGame;
import model.usersModels.Player;
import view.terminalMenu.Menu;
import view.terminalMenu.RiskGameMenu;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MapGamesController implements Initializable {
    private static RiskGame riskGame = null;
    private GameController gameController = GameController.getGameController();

    public static RiskGame getRiskGame() {
        return riskGame;
    }

    public static void setRiskGame(RiskGame riskGame) {
        MapGamesController.riskGame = riskGame;
    }

    // TODO: 1/30/2021 ................................................hamash moondeh
    @FXML
    private AnchorPane pane;

    @FXML
    private Circle C1;

    @FXML
    private Label L1;

    @FXML
    private Circle C2;

    @FXML
    private Label L2;

    @FXML
    private Circle C9;

    @FXML
    private Label L9;

    @FXML
    private Circle C6;

    @FXML
    private Label L6;

    @FXML
    private Circle C7;

    @FXML
    private Label L7;

    @FXML
    private Circle C3;

    @FXML
    private Label L3;

    @FXML
    private Circle C5;

    @FXML
    private Label L5;

    @FXML
    private Circle C4;

    @FXML
    private Label L4;

    @FXML
    private Circle C8;

    @FXML
    private Label L8;

    @FXML
    private Circle C10;

    @FXML
    private Label L10;

    @FXML
    private Circle C11;

    @FXML
    private Label L11;

    @FXML
    private Circle C12;

    @FXML
    private Label L12;

    @FXML
    private Circle C13;

    @FXML
    private Label L13;

    @FXML
    private Circle C24;

    @FXML
    private Label L24;

    @FXML
    private Circle C22;

    @FXML
    private Label L22;

    @FXML
    private Circle C26;

    @FXML
    private Label L26;

    @FXML
    private Circle C20;

    @FXML
    private Label L20;

    @FXML
    private Circle C21;

    @FXML
    private Label L21;

    @FXML
    private Circle C25;

    @FXML
    private Label L25;

    @FXML
    private Circle C23;

    @FXML
    private Label L23;

    @FXML
    private Circle C17;

    @FXML
    private Label L17;

    @FXML
    private Circle C15;

    @FXML
    private Label L15;

    @FXML
    private Circle C16;

    @FXML
    private Label L16;

    @FXML
    private Circle C19;

    @FXML
    private Label L19;

    @FXML
    private Circle C14;

    @FXML
    private Label L14;

    @FXML
    private Circle C18;

    @FXML
    private Label L18;

    @FXML
    private Circle C41;

    @FXML
    private Label L41;

    @FXML
    private Circle C40;

    @FXML
    private Label L40;

    @FXML
    private Circle C39;

    @FXML
    private Label L39;

    @FXML
    private Circle C42;

    @FXML
    private Label L42;

    @FXML
    private Circle C27;

    @FXML
    private Label L27;

    @FXML
    private Circle C33;

    @FXML
    private Label L33;

    @FXML
    private Circle C37;

    @FXML
    private Label L37;

    @FXML
    private Circle C36;

    @FXML
    private Label L36;

    @FXML
    private Circle C28;

    @FXML
    private Label L28;

    @FXML
    private Circle C29;

    @FXML
    private Label L29;

    @FXML
    private Circle C35;

    @FXML
    private Label L35;

    @FXML
    private Circle C34;

    @FXML
    private Label L34;

    @FXML
    private Circle C30;

    @FXML
    private Label L30;

    @FXML
    private Circle C38;

    @FXML
    private Label L38;

    @FXML
    private Circle C31;

    @FXML
    private Label L31;

    @FXML
    private Circle C32;

    @FXML
    private Label L32;

    @FXML
    public void back(MouseEvent mouseEvent) throws IOException {
        URL url = new File("risk\\src\\view\\graphic\\Mainplato.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        JFXPanel username = null;
        Stage stage = (Stage) username.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void dice(MouseEvent mouseEvent) throws IOException {
        URL url = new File("risk\\src\\view\\graphic\\NumberOfDice.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        JFXPanel username = null;
        Stage stage = (Stage) username.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void cards(MouseEvent mouseEvent) throws IOException {
        URL url = new File("risk\\src\\view\\graphic\\TerritoryCards.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        JFXPanel username = null;
        Stage stage = (Stage) username.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            checkIfMapIsManually(riskGame);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void checkIfMapIsManually(RiskGame riskGame) throws Exception {
        GameStages gameStages = riskGame.getGameStages();
        if(gameStages.equals(GameStages.STARTING)){
            if(!riskGame.isMapManually()){
                if (!riskGame.isHasGotSoldiersForDraft()) {
                    gameController.putSoldiersForStartingStageUnManually(riskGame);
                    gameController.goNextStage(riskGame);
                }
            }
        }
    }


    public void countryClick(MouseEvent event) throws Exception {
        GameStages gameStages = riskGame.getGameStages();
        String id = ((Control) event.getSource()).getId();
        if(gameStages.equals(GameStages.STARTING)){
            startGameStartingStage(id);
        }else if(gameStages.equals(GameStages.DRAFT)){
            HBox hBox = new HBox(10);
            Image image = new Image("@../../NotResoures/exit.png");
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(40);
            imageView.setFitWidth(20);
            double x = ((Control) event.getSource()).getLayoutX();
            double y = ((Control) event.getSource()).getLayoutY();
            Button addButton = new Button("+");
            addButton.setStyle("-fx-text-fill: white ; -fx-background-color: #90be6d ; -fx-font-size: 15px ; -fx-font:bold; -fx-background-radius: 50px ; -fx-border-radius: 50px ");
            Button subButton = new Button("-");
            subButton.setStyle("-fx-text-fill: white ; -fx-background-color: #ff006e ; -fx-font-size: 15px ; -fx-font:bold; -fx-background-radius: 50px ; -fx-border-radius: 50px ");
            Image secondImage = new Image("@../../NotResoures/send button.png");
            ImageView secondImageView = new ImageView(secondImage);
            secondImageView.setFitHeight(40);
            secondImageView.setFitWidth(20);
            //TODO ....
            hBox.setLayoutX(x-20);
            hBox.setLayoutY(y-20);
            hBox.getChildren().addAll(imageView , subButton , addButton , secondImageView);
            pane.getChildren().add(hBox);
            //Exit button
            imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if(event.getButton().equals(MouseButton.PRIMARY)){
                        pane.getChildren().remove(hBox);
                    }
                }
            });
            //Add button
            addButton.setOnAction(e ->{
                Player currentPlayer =riskGame.getCurrentPlayer();
                int countryId = getCountryCoordination(id).getCountryCoordinate();
                Label countryLabel = findLabelByCountryCoordinate(countryId);
                int numberOfSoldiers = Integer.parseInt(countryLabel.getText());
                if(numberOfSoldiers < currentPlayer.getNumberOfSoldiers()) {
                    numberOfSoldiers++;
                    countryLabel.setText(String.valueOf(numberOfSoldiers));
                }
            });
            //Sub button
            subButton.setOnAction(e->{
                Country country = getCountryCoordination(id);
                int countryId = country.getCountryCoordinate();
                Label countryLabel = findLabelByCountryCoordinate(countryId);
                int numberOfSoldiers = Integer.parseInt(countryLabel.getText());
                if(numberOfSoldiers >  0 ) {
                    numberOfSoldiers--;
                    countryLabel.setText(String.valueOf(numberOfSoldiers));
                }
            });
            //Submit button
            secondImageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if(event.getButton().equals(MouseButton.PRIMARY)){
                        Country country = getCountryCoordination(id);
                        Label countryLabel = findLabelByCountryCoordinate(country.getCountryCoordinate());
                        int numberOfSolider = Integer.parseInt(countryLabel.getText());
                        try {
                            //TODO check if number of soldiers has already changed
                            GameController.getGameController().placeSoldiers(country , numberOfSolider , riskGame.getCurrentPlayer());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }else if(gameStages.equals(GameStages.ATTACK)){

        }else if(gameStages.equals(GameStages.FORTIFY)){

        }
    }

    private void startGameStartingStage(String countryCoordinate) throws Exception {
        Player currentPlayer = riskGame.getCurrentPlayer();
        if (riskGame.isMapManually()) {
            while (true) {
                if (gameController.isStartingStageFinished(riskGame))
                    break;
                    Country country = getCountryCoordination(countryCoordinate);
                    try {
                        gameController.placeSoldiersForStartingStage(riskGame, country, currentPlayer);
                        gameController.nextPlayer(riskGame);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
            }
        }
        gameController.goNextStage(riskGame);
        gameController.calculateNumberOfSoldiersToAddInDraft(currentPlayer);
    }
    private void startDraftStage(String id){
        Player currentPlayer = riskGame.getCurrentPlayer();
        Country country = getCountryCoordination(id);

    }

    private Country getCountryCoordination(String countryId){
        String[] countryArray = countryId.split("C");
        int countryIdInInt = Integer.parseInt(countryArray[0]);
        return riskGame.getAllCountriesWithNumber().get(countryIdInInt);
    }

    private Label findLabelByCountryCoordinate(int countryCoordinate){
        StringBuilder stringBuilder = new StringBuilder();
        String countryId = String.valueOf(countryCoordinate);
        stringBuilder.append("L").append(countryId);
       for(Node node : pane.getChildren()){
           if(node.getId().equals(stringBuilder.toString())){
               return (Label) node;
           }
       }
       return null;
    }
}



