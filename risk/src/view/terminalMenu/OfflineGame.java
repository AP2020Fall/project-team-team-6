package view.terminalMenu;

import model.usersModels.Player;
import model.gamesModels.RiskGame;

public class OfflineGame extends Menu {
    private RiskGame riskGame;
    private Player[] players;

    public OfflineGame(Menu parentMenu) {
        super("Play Offline Game", parentMenu);
        this.riskGame = gameController.getOfflineGame();
        this.players = riskGame.getPlayers();
    }

    @Override
    public void show() {
        showRiskGameInformation();
        System.out.println("1.Back");
        System.out.println("2.Continue this game");
    }

    @Override
    public void execute() {
        String inputInString = getInputFormatWithHelpText("^1|2$", "Enter a number : ");
        int input = Integer.parseInt(inputInString);
        Menu nextMenu = this;
        if (input == 1) {
            nextMenu = parentMenu;
        } else if (input == 2) {
            nextMenu = new RiskGameMenu(this, riskGame);
        }
        nextMenu.show();
        nextMenu.execute();
    }

    private void showRiskGameInformation() {
        System.out.println("Name : " + riskGame.getName());
        System.out.print("Players : ");
        for (int i = 1; i <= players.length; i++) {
            System.out.print(i + "." + players[i - 1].getUsername() + "\t");
        }
        System.out.println();
        System.out.println("Timer : " + riskGame.getTimer() + " s");
        System.out.println(riskGame.getRiskGameType());
        if (riskGame.isMapManually())
            System.out.println("Manually");
        else {
            System.out.println("Unmanually");
        }
        System.out.println("------------------------------------------------");
    }


}
