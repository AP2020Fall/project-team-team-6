package view.Controllers;

import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class MapGamesController {
    // TODO: 1/30/2021 ................................................hamash moondeh
    @FXML
    private Circle C1;

    @FXML
    private Circle C2;

    @FXML
    private Circle C9;

    @FXML
    private Circle C6;

    @FXML
    private Circle C7;

    @FXML
    private Circle C3;

    @FXML
    private Circle C5;

    @FXML
    private Circle C4;

    @FXML
    private Circle C8;

    @FXML
    private Circle C10;

    @FXML
    private Circle C11;

    @FXML
    private Circle C12;

    @FXML
    private Circle C13;

    @FXML
    private Circle C24;

    @FXML
    private Circle C22;

    @FXML
    private Circle C26;

    @FXML
    private Circle C20;

    @FXML
    private Circle C21;

    @FXML
    private Circle C25;

    @FXML
    private Circle C23;

    @FXML
    private Circle C17;

    @FXML
    private Circle C15;

    @FXML
    private Circle C16;

    @FXML
    private Circle C19;

    @FXML
    private Circle C14;

    @FXML
    private Circle C18;

    @FXML
    private Circle C41;

    @FXML
    private Circle C40;

    @FXML
    private Circle C39;

    @FXML
    private Circle C42;

    @FXML
    private Circle C27;

    @FXML
    private Circle C33;

    @FXML
    private Circle C37;

    @FXML
    private Circle C36;

    @FXML
    private Circle C28;

    @FXML
    private Circle C29;

    @FXML
    private Circle C35;

    @FXML
    private Circle C34;

    @FXML
    private Circle C30;

    @FXML
    private Circle C38;

    @FXML
    private Circle C31;

    @FXML
    private Circle C32;

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
}



