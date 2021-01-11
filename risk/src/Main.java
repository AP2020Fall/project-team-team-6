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
//        MySqlDataBase mySqlDataBase = MySqlDataBase.getMySqlDataBase();
        launch(args);

//        MainMenu mainMenu = new MainMenu(null);
//        mainMenu.show();
//        mainMenu.execute();

    }

    @Override
    public void start(Stage primaryStage) {
        try {
            URL url = new File("risk\\src\\view\\graphic\\Register.fxml").toURI().toURL();

            AnchorPane root = FXMLLoader.load(url);

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
