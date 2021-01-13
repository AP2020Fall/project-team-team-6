import model.database.MySqlDataBase;
import view.terminalMenu.MainMenu;

public class newMain {
    public static void main(String[] args) {
        MySqlDataBase mySqlDataBase = MySqlDataBase.getMySqlDataBase();
        MainMenu mainMenu = new MainMenu(null);
        mainMenu.show();
        mainMenu.execute();
    }
}
