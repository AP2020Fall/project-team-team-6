package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import server.model.database.MySqlDataBase;

import java.io.File;
import java.net.URL;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) {
        try {
            MySqlDataBase mySqlDataBase = MySqlDataBase.getMySqlDataBase();
            URL url = new File("risk\\src\\Client\\graphic\\Login.fxml").toURI().toURL();

            AnchorPane root = FXMLLoader.load(url);

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setOnCloseRequest(e->{
                System.exit(1);
            });
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
