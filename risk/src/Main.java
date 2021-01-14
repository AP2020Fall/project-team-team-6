import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.database.MySqlDataBase;
import view.terminalMenu.MainMenu;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) {
        try {
            MySqlDataBase mySqlDataBase = MySqlDataBase.getMySqlDataBase();
            URL url = new File("risk\\src\\view\\graphic\\Login.fxml").toURI().toURL();

            AnchorPane root = FXMLLoader.load(url);

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
