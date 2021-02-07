package view.Controllers;

import controller.GameController;
import javafx.collections.transformation.TransformationList;
import javafx.embed.swing.JFXPanel;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.scene.Group;
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
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import model.gamesModels.Country;
import model.gamesModels.GameStages;
import model.gamesModels.RiskGame;
import model.usersModels.Player;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MapGamesController implements Initializable {
    private static RiskGame riskGame = null;
    public ImageView playerImage;
    public ImageView nextPhase;
    public Label currentPlayerLabel;
    private boolean hasClickedOnce = false;
    private boolean hasHBox = false;
    private GameController gameController = GameController.getGameController();

    public static RiskGame getRiskGame() {
        return riskGame;
    }

    public static void setRiskGame(RiskGame riskGame) {
        MapGamesController.riskGame = riskGame;
    }

    // TODO: 1/30/2021 ................................................hamash moondeh
    private static Country firstCountry = null;
    private static Country secondCountry = null;

    @FXML
    private Pane pane;

    @FXML
    private Label gameStageLabel;


    @FXML
    public void back(MouseEvent mouseEvent) throws IOException {
        URL url = new File("risk\\src\\view\\graphic\\Mainplato.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        Stage stage = (Stage) pane.getScene().getWindow();
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
        pane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton().equals(MouseButton.SECONDARY)){
                    firstCountry = null;
                    secondCountry = null;
                    for(Country country : riskGame.getAllCountriesWithNumber().values()){
                        Circle circle = findShapeByCountryCoordinate(country.getCountryCoordinate());
                        circle.setStroke(null);
                    }

                }
            }
        });
        try {
            checkIfMapIsManually(riskGame);
        } catch (Exception e) {
            e.printStackTrace();
        }
        checkIfGameIsInDraftStage();
        loadGameMap();
        loadCurrentPlayer();
    }

    private void loadCurrentPlayer() {
        String color = riskGame.getCurrentPlayer().getCurrentColor().toString();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("@../../NotResoures/images/").append(color).append("_player.png");
        String url = String.valueOf(stringBuilder);
        playerImage.setImage(new Image(url));
        currentPlayerLabel.setText(riskGame.getCurrentPlayer().getUsername());
        findGameStageLabel();
    }

    private void loadGameMap() {
        for (Country country : riskGame.getAllCountriesWithNumber().values()) {
            Label countryLabel = findLabelByCountryCoordinate(country.getCountryCoordinate());
            Circle countryShape = findShapeByCountryCoordinate(country.getCountryCoordinate());
            int numberOfsoldiers = country.getNumberOfSoldiers();
            countryLabel.setText(String.valueOf(numberOfsoldiers));
            if (country.getColor() != null) {
                String colorString = country.getColor().toString();
                countryShape.setFill(findColor(colorString));
            }
        }
    }
    private Color findColor(String colorString){
        if (colorString.equals("red")) {
            return Color.RED;
        } else if (colorString.equals("yellow")) {
            return Color.YELLOW;
        } else if (colorString.equals("purple")) {
            return Color.PURPLE;
        } else if (colorString.equals("orange")) {
            return Color.ORANGE;
        } else if (colorString.equals("blue")) {
            return Color.BLUE;
        } else if (colorString.equals("green")) {
            return Color.GREEN;
        }
        return null;
    }

    private void findGameStageLabel() {
        if (!riskGame.getGameStages().equals(GameStages.DRAFT))
            gameStageLabel.setText(riskGame.getGameStages().toString());
        else {
            StringBuilder stringBuilder = new StringBuilder();
            String gameStage = riskGame.getGameStages().toString();
            stringBuilder.append(gameStage).append("\t").append(riskGame.getCurrentPlayer().getNumberOfSoldiers());
            gameStageLabel.setText(String.valueOf(stringBuilder));
        }
    }

    private void checkIfMapIsManually(RiskGame riskGame) throws Exception {
        GameStages gameStages = riskGame.getGameStages();
        if (gameStages.equals(GameStages.STARTING)) {
            if (!riskGame.isMapManually()) {
                if (!riskGame.isHasGotSoldiersForDraft()) {
                    gameController.putSoldiersForStartingStageUnManually(riskGame);
                    gameController.goNextStage(riskGame);
                }
            }
        }
    }

    private void checkIfGameIsInDraftStage() {
        if (riskGame.getGameStages().equals(GameStages.DRAFT)) {
            if (!riskGame.isHasGotSoldiersForDraft()) {
                gameController.calculateNumberOfSoldiersToAddInDraft(riskGame.getCurrentPlayer());
                gameController.changeHasGotSoldiersForDraft(riskGame);
            }
        }
    }


    public void countryClick(MouseEvent event) throws Exception {
        GameStages gameStages = riskGame.getGameStages();
        String id = ((Node) event.getSource()).getId();
        if (id != null){
            if (gameStages.equals(GameStages.STARTING)) {
                startGameStartingStage(id);
            } else if (gameStages.equals(GameStages.DRAFT)) {
                Country country = getCountryCoordination(id);
                if (!hasClickedOnce && country.getColor().equals(riskGame.getCurrentPlayer().getCurrentColor()) && riskGame.getCurrentPlayer().getNumberOfSoldiers() > 0) {
                    hasClickedOnce = true;
                    HBox hBox = new HBox(10);
                    Image image = new Image("@../../NotResoures/images/exit.png");
                    ImageView imageView = new ImageView(image);
                    imageView.setFitHeight(40);
                    imageView.setFitWidth(20);
                    Node node = (Node) event.getSource();
                    Bounds boundsInScene = node.localToScene(node.getBoundsInLocal());
                    double x = boundsInScene.getMinX();
                    double y = boundsInScene.getMinY();
                    Button addButton = new Button("+");
                    addButton.setStyle("-fx-text-fill: white ; -fx-background-color: #90be6d ; -fx-font-size: 15px ; -fx-font:bold; -fx-background-radius: 50px ; -fx-border-radius: 50px ");
                    Button subButton = new Button("-");
                    subButton.setStyle("-fx-text-fill: white ; -fx-background-color: #ff006e ; -fx-font-size: 15px ; -fx-font:bold; -fx-background-radius: 50px ; -fx-border-radius: 50px ");
                    Button okButton = new Button("Add Soldiers");
                    okButton.setStyle("-fx-text-fill: white ; -fx-background-color: #0077b6 ; -fx-font-size: 15px ; -fx-font:bold; -fx-background-radius: 50px ; -fx-border-radius: 50px ");
                    hBox.setLayoutX(x - 30);
                    hBox.setLayoutY(y - 30);
                    hBox.getChildren().addAll(imageView, subButton, addButton, okButton);
                    pane.getChildren().add(hBox);
                    //Exit button
                    imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            if (event.getButton().equals(MouseButton.PRIMARY)) {
                                hasClickedOnce = false;
                                pane.getChildren().remove(hBox);
                            }
                        }
                    });
                    //Add button
                    addButton.setOnAction(e -> {
                        Player currentPlayer = riskGame.getCurrentPlayer();
                        int countryId = getCountryCoordination(id).getCountryCoordinate();
                        Label countryLabel = findLabelByCountryCoordinate(countryId);
                        int numberOfSoldiers = Integer.parseInt(countryLabel.getText());
                        if (numberOfSoldiers < currentPlayer.getNumberOfSoldiers() + country.getNumberOfSoldiers()) {
                            numberOfSoldiers++;
                            countryLabel.setText(String.valueOf(numberOfSoldiers));
                            loadCurrentPlayer();
                        }
                    });
                    //Sub button
                    subButton.setOnAction(e -> {
                        int countryId = country.getCountryCoordinate();
                        Label countryLabel = findLabelByCountryCoordinate(countryId);
                        int numberOfSoldiers = Integer.parseInt(countryLabel.getText());
                        int minimumSoldiers = country.getNumberOfSoldiers();
                        if (numberOfSoldiers > minimumSoldiers) {
                            numberOfSoldiers--;
                            countryLabel.setText(String.valueOf(numberOfSoldiers));
                            loadCurrentPlayer();
                        }
                    });
                    //Submit button
                    okButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            if (event.getButton().equals(MouseButton.PRIMARY)) {
                                Country country = getCountryCoordination(id);
                                Label countryLabel = findLabelByCountryCoordinate(country.getCountryCoordinate());
                                int numberOfSolider = Integer.parseInt(countryLabel.getText());
                                numberOfSolider -= country.getNumberOfSoldiers();
                                try {
                                    //TODO check if number of soldiers has already changed
                                    GameController.getGameController().placeSoldiers(country, numberOfSolider, riskGame.getCurrentPlayer());
                                    loadCurrentPlayer();
                                    hasClickedOnce = false;
                                    pane.getChildren().remove(hBox);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                }
            } else if (gameStages.equals(GameStages.ATTACK)) {

                if (firstCountry == null) {
                    Country country = getCountryCoordination(id);
                    boolean isCountryForPlayer = GameController.getGameController().isCountryForPlayer(country, riskGame.getCurrentPlayer());
                    if (isCountryForPlayer) {
                        if (country.getNumberOfSoldiers() != 1) {
                            firstCountry = country;
                            ArrayList<Country> neighbourCountries = new ArrayList<>();
                            for (Country country1 : country.getNeighboringCountries()) {
                                if (!GameController.getGameController().isCountryForPlayer(country1, riskGame.getCurrentPlayer())) {
                                    neighbourCountries.add(country1);
                                }
                            }
                            for (Country country1 : neighbourCountries) {
                                Circle circle = findShapeByCountryCoordinate(country1.getCountryCoordinate());
                                circle.setStroke(Color.AQUA);
                            }
                        }
                    }
                } else if (secondCountry == null) {
                    ArrayList<Country> neighbourCountries = new ArrayList<>();
                    for (Country country1 : firstCountry.getNeighboringCountries()) {
                        if (!GameController.getGameController().isCountryForPlayer(country1, riskGame.getCurrentPlayer())) {
                            neighbourCountries.add(country1);
                        }
                    }
                    Country country = getCountryCoordination(id);
                    if (neighbourCountries.contains(country)) {
                        secondCountry = country;
                        for (Country country1 : neighbourCountries) {
                            if (!country1.equals(secondCountry)) {
                                Circle circle = findShapeByCountryCoordinate(country1.getCountryCoordinate());
                                assert circle != null;
                                circle.setStroke(null);
                            }
                        }
                                int numberOfDice = GameController.getGameController().getNumberOfDiceForAttacker(firstCountry);
                                HBox hBox = new HBox();
                                Button cancelButton = new Button("Cancel");
                                cancelButton.setStyle("-fx-text-fill: white ; -fx-background-color: #0077b6 ; -fx-font-size: 15px ; -fx-font:bold; -fx-background-radius: 50px ; -fx-border-radius: 50px ");
                                hBox.getChildren().add(cancelButton);
                                Button oneDice = new Button("One dice");
                                oneDice.setStyle("-fx-text-fill: white ; -fx-background-color: #0077b6 ; -fx-font-size: 15px ; -fx-font:bold; -fx-background-radius: 50px ; -fx-border-radius: 50px ");
                                oneDice.setOnAction(e-> {
                                    pane.getChildren().remove(hBox);
                                    attack(1 , hBox);
                                });
                                hBox.getChildren().add(oneDice);
                                if(numberOfDice == 2 ){
                                    Button twoDice = new Button("Two dice");
                                    twoDice.setStyle("-fx-text-fill: white ; -fx-background-color: #0077b6 ; -fx-font-size: 15px ; -fx-font:bold; -fx-background-radius: 50px ; -fx-border-radius: 50px ");
                                    twoDice.setOnAction(e->{
                                        pane.getChildren().remove(hBox);
                                        attack(2 , hBox);
                                    });
                                    hBox.getChildren().add(twoDice);
                                }else if(numberOfDice == 3){
                                    Button twoDice = new Button("Two dice");
                                    twoDice.setStyle("-fx-text-fill: white ; -fx-background-color: #0077b6 ; -fx-font-size: 15px ; -fx-font:bold; -fx-background-radius: 50px ; -fx-border-radius: 50px ");
                                    twoDice.setOnAction(e->{
                                        pane.getChildren().remove(hBox);
                                        attack(2 , hBox);
                                    });
                                    hBox.getChildren().add(twoDice);
                                    Button threeDice = new Button("Three dice");
                                    threeDice.setStyle("-fx-text-fill: white ; -fx-background-color: #0077b6 ; -fx-font-size: 15px ; -fx-font:bold; -fx-background-radius: 50px ; -fx-border-radius: 50px ");
                                    threeDice.setOnAction(e->{
                                        pane.getChildren().remove(hBox);
                                        attack(3 , hBox);
                                    });
                                    hBox.getChildren().add(threeDice);
                                }
                                Button blitzDice = new Button("Blitz");
                                blitzDice.setStyle("-fx-text-fill: white ; -fx-background-color: #0077b6 ; -fx-font-size: 15px ; -fx-font:bold; -fx-background-radius: 50px ; -fx-border-radius: 50px ");
                                //TODO
                                Node node = (Node) event.getSource();
                                Bounds boundsInScene = node.localToScene(node.getBoundsInLocal());
                                double x = boundsInScene.getMinX();
                                double y = boundsInScene.getMinY();
                                hBox.setLayoutX(x-30);
                                hBox.setLayoutY(y - 30);
                                pane.getChildren().add(hBox);
                                cancelButton.setOnMouseClicked(e->{
                                    if(e.getButton().equals(MouseButton.PRIMARY)) {
                                        Circle firstCountryCircle = findShapeByCountryCoordinate(firstCountry.getCountryCoordinate());
                                        Circle secondCountryCircle = findShapeByCountryCoordinate(secondCountry.getCountryCoordinate());
                                        firstCountryCircle.setStroke(null);
                                        secondCountryCircle.setStroke(null);
                                        firstCountry = null;
                                        secondCountry = null;
                                        pane.getChildren().remove(hBox);
                                    }
                                });
                            }
                        Circle circle = findShapeByCountryCoordinate(firstCountry.getCountryCoordinate());
                        circle.setStroke(Color.AQUA);
                }
            } else if (gameStages.equals(GameStages.FORTIFY)) {
                Country country = getCountryCoordination(id);
                if (!gameController.hasDoneFortify(riskGame)) {
                    if (gameController.isCountryForPlayer(country, riskGame.getCurrentPlayer())) {
                        if (firstCountry == null) {
                            firstCountry = country;
                            Circle circle = findShapeByCountryCoordinate(firstCountry.getCountryCoordinate());
                            circle.setStroke(Color.AQUA);
                        }
                        else {
                            if (gameController.isPathAvailableForFortifying(riskGame, riskGame.getCurrentPlayer(), firstCountry, country)) {
                                secondCountry = country;
                                Circle circle = findShapeByCountryCoordinate(secondCountry.getCountryCoordinate());
                                circle.setStroke(Color.AQUA);
                                HBox hBox = new HBox(10);
                                Image image = new Image("@../../NotResoures/images/exit.png");
                                ImageView imageView = new ImageView(image);
                                imageView.setFitHeight(40);
                                imageView.setFitWidth(20);
                                Node node = (Node) event.getSource();
                                Bounds boundsInScene = node.localToScene(node.getBoundsInLocal());
                                double x = boundsInScene.getMinX();
                                double y = boundsInScene.getMinY();
                                Button addButton = new Button("+");
                                addButton.setStyle("-fx-text-fill: white ; -fx-background-color: #90be6d ; -fx-font-size: 15px ; -fx-font:bold; -fx-background-radius: 50px ; -fx-border-radius: 50px ");
                                Button subButton = new Button("-");
                                subButton.setStyle("-fx-text-fill: white ; -fx-background-color: #ff006e ; -fx-font-size: 15px ; -fx-font:bold; -fx-background-radius: 50px ; -fx-border-radius: 50px ");
                                Image secondImage = new Image("@../../NotResoures/images/send button.png");
                                ImageView secondImageView = new ImageView(secondImage);
                                secondImageView.setFitHeight(40);
                                secondImageView.setFitWidth(20);
                                hBox.setLayoutX(x - 30);
                                hBox.setLayoutY(y - 30);
                                hBox.getChildren().addAll(imageView, subButton, addButton, secondImageView);
                                pane.getChildren().add(hBox);
                                //Exit button
                                imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent event) {
                                        if (event.getButton().equals(MouseButton.PRIMARY)) {
                                            pane.getChildren().remove(hBox);
                                        }
                                    }
                                });
                                //Add button
                                addButton.setOnAction(e -> {
                                    Label firstCountryLabel = findLabelByCountryCoordinate(firstCountry.getCountryCoordinate());
                                    int numberOfSoldiersInFirstCountry = Integer.parseInt(firstCountryLabel.getText());
                                    Label secondCountryLabel = findLabelByCountryCoordinate(secondCountry.getCountryCoordinate());
                                    int numberOfSoldiersInSecondCountry = Integer.parseInt(secondCountryLabel.getText());
                                    if (numberOfSoldiersInFirstCountry > 1) {
                                        numberOfSoldiersInFirstCountry--;
                                        numberOfSoldiersInSecondCountry++;
                                        firstCountryLabel.setText(String.valueOf(numberOfSoldiersInFirstCountry));
                                        secondCountryLabel.setText(String.valueOf(numberOfSoldiersInSecondCountry));
                                        //This will change the value in the labels
                                    }
                                });
                                //Sub button
                                subButton.setOnAction(e -> {
                                    Label firstCountryLabel = findLabelByCountryCoordinate(firstCountry.getCountryCoordinate());
                                    int numberOfSoldiersInFirstCountry = Integer.parseInt(firstCountryLabel.getText());
                                    Label secondCountryLabel = findLabelByCountryCoordinate(secondCountry.getCountryCoordinate());
                                    int numberOfSoldiersInSecondCountry = Integer.parseInt(secondCountryLabel.getText());
                                    if (numberOfSoldiersInSecondCountry > 1) {
                                        numberOfSoldiersInFirstCountry++;
                                        numberOfSoldiersInSecondCountry--;
                                        firstCountryLabel.setText(String.valueOf(numberOfSoldiersInFirstCountry));
                                        secondCountryLabel.setText(String.valueOf(numberOfSoldiersInSecondCountry));
                                        //This will change the value in the labels
                                    }
                                });
                                //Submit button
                                secondImageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent event) {
                                        if (event.getButton().equals(MouseButton.PRIMARY)) {
                                            Label secondCountryLabel = findLabelByCountryCoordinate(secondCountry.getCountryCoordinate());
                                            int numberOfSoldiersInLabel = Integer.parseInt(secondCountryLabel.getText());
                                            int numberOfSoldier = secondCountry.getNumberOfSoldiers() - numberOfSoldiersInLabel;
                                            if (numberOfSoldier < 0)
                                                numberOfSoldier *= -1;
                                            try {
                                                gameController.moveSoldiersFromACountryToAnotherCountry(firstCountry, secondCountry, numberOfSoldier);
                                                gameController.setHashDoneFortify(riskGame, true);
                                                pane.getChildren().remove(hBox);
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                                });
                            }
                        }
                    }
                }
            }
          }
        }

    private void startGameStartingStage(String countryCoordinate) throws Exception {
        if (!gameController.isStartingStageFinished(riskGame)) {
            Player currentPlayer = riskGame.getCurrentPlayer();
            if (riskGame.isMapManually()) {
                Country country = getCountryCoordination(countryCoordinate);
                try {
                    gameController.placeSoldiersForStartingStage(riskGame, country, currentPlayer);
                    gameController.nextPlayer(riskGame);
                    loadGameMap();
                    loadCurrentPlayer();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }else {
            gameController.goNextStage(riskGame);
            gameController.calculateNumberOfSoldiersToAddInDraft(riskGame.getCurrentPlayer());
        }
    }

    private Country getCountryCoordination(String countryId){
        String[] countryArray = countryId.split("C");
        int countryIdInInt = Integer.parseInt(countryArray[1]);
        return riskGame.getAllCountriesWithNumber().get(countryIdInInt);
    }

    private Label findLabelByCountryCoordinate(int countryCoordinate){
        StringBuilder stringBuilder = new StringBuilder();
        String countryId = String.valueOf(countryCoordinate);
        stringBuilder.append("L").append(countryId);
        String labelId = String.valueOf(stringBuilder);
       for(Node node : pane.getChildren()){
           if(node instanceof Group) {
               for(Node node1 : ((Group) node).getChildren()) {
                   String id = node1.getId();
                   if (id != null) {
                       if (node1.getId().equals(labelId)) {
                           return (Label) node1;
                       }
                   }
               }
           }
       }
       return null;
    }
    private Circle findShapeByCountryCoordinate(int countryCoordinate){
        StringBuilder stringBuilder = new StringBuilder();
        String countryId = String.valueOf(countryCoordinate);
        stringBuilder.append("C").append(countryId);
        String labelId = String.valueOf(stringBuilder);
        for(Node node : pane.getChildren()){
            if(node instanceof Group) {
                for(Node node1 : ((Group) node).getChildren()) {
                    String id = node1.getId();
                    if (id != null) {
                        if (node1.getId().equals(labelId)) {
                            return (Circle) node1;
                        }
                    }
                }
            }
        }
        return null;
    }


    public void goNextPhase(MouseEvent event) {
       if(riskGame.getGameStages().equals(GameStages.STARTING)){
           gameController.goNextStage(riskGame);
           gameController.calculateNumberOfSoldiersToAddInDraft(riskGame.getCurrentPlayer());
       }else if(riskGame.getGameStages().equals(GameStages.DRAFT)){
           if (riskGame.getCurrentPlayer().getNumberOfSoldiers() == 0)
               gameController.goNextStage(riskGame);
       }else if(riskGame.getGameStages().equals(GameStages.ATTACK)){
        //todo darim faghat kart
           boolean hasGotAnyCountry = riskGame.isHasOneSuccessAttack();
           if (hasGotAnyCountry) {
               gameController.giveCardToPlayer(riskGame);
           }
           gameController.setHashGotOneCountryInAttack(riskGame, false);
           gameController.goNextStage(riskGame);
       }else if(riskGame.getGameStages().equals(GameStages.FORTIFY)){
           gameController.setHashDoneFortify(riskGame, false);
           gameController.goNextStage(riskGame);
           gameController.nextPlayer(riskGame);
           Player currentPlayer = riskGame.getCurrentPlayer();
           while (true) {
               if (gameController.checkIfPlayerHasAnyCountries(currentPlayer))
                   break;
               gameController.nextPlayer(riskGame);
               currentPlayer = riskGame.getCurrentPlayer();
           }
           gameController.calculateNumberOfSoldiersToAddInDraft(riskGame.getCurrentPlayer());
       }
        loadGameMap();
        loadCurrentPlayer();
    }
    private void attack(int numberOfDice , HBox hBox){
        pane.getChildren().remove(hBox);
        int numberOfDiceForDefend = gameController.getNumberOfDiceForDefender(secondCountry);
        //With out blitz
        ArrayList<Integer> attackerDice = gameController.rollDice(numberOfDice);
        ArrayList<Integer> defendersDice = gameController.rollDice(numberOfDiceForDefend);
        try {
            boolean hasGotTheCountry = gameController.attack(riskGame, riskGame.getCurrentPlayer(), firstCountry, secondCountry, attackerDice, defendersDice);
            pane.getChildren().remove(hBox);
            if (hasGotTheCountry) {
                gameController.setHashGotOneCountryInAttack(riskGame, true);
                moveSoldiersAfterAttack(numberOfDice);
                if (gameController.isGameFinished(riskGame)) {
                    gameController.endGame(riskGame);
                    String color = riskGame.getCurrentPlayer().getCurrentColor().toString();
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("@../../NotResoures/images").append(color).append("_player.png");
                    String urlInString = String.valueOf(stringBuilder);
                    Image image = new Image(urlInString);
                    VictoryHintController.setWinnerImage(image);
                    URL url = new File("risk\\src\\view\\graphic\\VictoryHint.fxml").toURI().toURL();
                    Parent register = FXMLLoader.load(url);
                    Scene message = new Scene(register);
                    TransformationList<Object, Object> event = null;
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setScene(message);
                    window.show();
                }
            }else{
                Circle firstCountryCircle = findShapeByCountryCoordinate(firstCountry.getCountryCoordinate());
                Circle secondCountryCircle = findShapeByCountryCoordinate(secondCountry.getCountryCoordinate());
                firstCountryCircle.setStroke(null);
                secondCountryCircle.setStroke(null);
                firstCountry = null;
                secondCountry = null;
                loadGameMap();
            }
            //TODO cards load
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private void moveSoldiersAfterAttack(int numberOfDice){
        Label firstCountryLabel = findLabelByCountryCoordinate(firstCountry.getCountryCoordinate());
        Label secondCountryLabel = findLabelByCountryCoordinate(secondCountry.getCountryCoordinate());
        Circle secondCountryCircle = findShapeByCountryCoordinate(secondCountry.getCountryCoordinate());
        secondCountryCircle.setFill(findColor(riskGame.getCurrentPlayer().getCurrentColor().toString()));
        secondCountryLabel.setText(String.valueOf(numberOfDice));
        int maximumSoldierToMove = firstCountry.getNumberOfSoldiers() - 1;
        int numberOfSoldiersInFirstCountry =firstCountry.getNumberOfSoldiers() - numberOfDice;
        firstCountryLabel.setText(String.valueOf(numberOfSoldiersInFirstCountry));
        HBox hBox = new HBox(10);
        Node node = findShapeByCountryCoordinate(secondCountry.getCountryCoordinate());
        Bounds boundsInScene = node.localToScene(node.getBoundsInLocal());
        double x = boundsInScene.getMinX();
        double y = boundsInScene.getMinY();
        Button addButton = new Button("+");
        addButton.setStyle("-fx-text-fill: white ; -fx-background-color: #90be6d ; -fx-font-size: 15px ; -fx-font:bold; -fx-background-radius: 50px ; -fx-border-radius: 50px ");
        Button subButton = new Button("-");
        subButton.setStyle("-fx-text-fill: white ; -fx-background-color: #ff006e ; -fx-font-size: 15px ; -fx-font:bold; -fx-background-radius: 50px ; -fx-border-radius: 50px ");
        Button okButton = new Button("Add Soldiers");
        okButton.setStyle("-fx-text-fill: white ; -fx-background-color: #0077b6 ; -fx-font-size: 15px ; -fx-font:bold; -fx-background-radius: 50px ; -fx-border-radius: 50px ");
        hBox.setLayoutX(x - 30);
        hBox.setLayoutY(y - 30);
        hBox.getChildren().addAll(subButton, addButton, okButton);
        pane.getChildren().add(hBox);
        addButton.setOnAction(e->{
            int numberOfSoldiers = Integer.parseInt(secondCountryLabel.getText());
            int numberOfSoldiers2 = Integer.parseInt(firstCountryLabel.getText());
            if(numberOfSoldiers < maximumSoldierToMove){
                numberOfSoldiers++;
                numberOfSoldiers2--;
                firstCountryLabel.setText(String.valueOf(numberOfSoldiers2));
                secondCountryLabel.setText(String.valueOf(numberOfSoldiers));
            }
        });
        subButton.setOnAction(e->{
            int numberOfSoldiers = Integer.parseInt(secondCountryLabel.getText());
            int numberOfSoldiers2 = Integer.parseInt(firstCountryLabel.getText());
            if(numberOfSoldiers  > numberOfDice){
                numberOfSoldiers--;
                numberOfSoldiers2++;
                firstCountryLabel.setText(String.valueOf(numberOfSoldiers2));
                secondCountryLabel.setText(String.valueOf(numberOfSoldiers));
            }
        });
        okButton.setOnMouseClicked(e->{
            if(e.getButton().equals(MouseButton.PRIMARY)) {
                int numberOfSoldier = Integer.parseInt(secondCountryLabel.getText());
                try {
                    gameController.moveSoldiersFromACountryToAnotherCountry(firstCountry, secondCountry, numberOfSoldier);
                    gameController.occupyingACountry(riskGame, riskGame.getCurrentPlayer(), secondCountry);
                    pane.getChildren().remove(hBox);
                    secondCountryCircle.setStroke(null);
                    Circle firstCountryCircle = findShapeByCountryCoordinate(firstCountry.getCountryCoordinate());
                    firstCountryCircle.setStroke(null);
                    firstCountry = null;
                    secondCountry = null;
                    loadGameMap();
                } catch (Exception e1) {
                    System.out.println(e1.getMessage());
                }
            }
        });

    }
}



