import model.database.MySqlDataBase;
import view.terminalMenu.MainMenu;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        MySqlDataBase mySqlDataBase = MySqlDataBase.getMySqlDataBase();
        MainMenu mainMenu = new MainMenu(null);
        mainMenu.show();
        mainMenu.execute();

    }
}
