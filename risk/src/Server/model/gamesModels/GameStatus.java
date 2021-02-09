package Server.model.gamesModels;

public class GameStatus {
   private static Boolean pass = true;
    private static Boolean update = true;
    private static Boolean uninstall = true;

    public static Boolean getUninstall() {
        return uninstall;
    }

    public static void setUninstall(Boolean uninstall) {
        GameStatus.uninstall = uninstall;
    }

    public static Boolean getUpdate() {
        return update;
    }

    public static void setUpdate(Boolean update) {
        GameStatus.update = update;
    }

    public static Boolean getPass() {
        return pass;
    }

    public static void setPass(Boolean pass) {
        GameStatus.pass = pass;
    }
}
