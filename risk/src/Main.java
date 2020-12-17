import model.Player;
import view.MainMenu;

public class Main {

    public static void main(String[] args) {
        //   Player player = new Player("amin","gh","amin","123","a@g.com","09876543212");
        MainMenu mainMenu = new MainMenu(null);
        mainMenu.show();
        mainMenu.execute();

    }
}
