package Client.terminalMenu;

import Server.model.gamesModels.Color;
import Server.model.gamesModels.RiskGame;
import Server.model.usersModels.Admin;
import Server.model.usersModels.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class MainMenu extends Menu {
    private RiskGame offlineRiskGame;

    public MainMenu(Menu parentMenu) {
        super("Client.Main Menu", parentMenu);
        offlineRiskGame = null;
        calculateSubMenusForMainMenu();
    }

    @Override
    public void show() {
        calculateSubMenusForMainMenu();
        //This super shows 1.Back and 2.Online game
        super.show();
        //This shows 3.Play Offline game
        System.out.println("3.Play Offline Game");
    }

    @Override
    public void execute() {
        Menu nextMenu = this;
        String inputString = getInputFormatWithHelpText("^1|2|3$", "Enter a number : ");
        int input = Integer.parseInt(inputString);
        if (input == 1) {
            System.exit(1);
        } else if (input == 2) {
            nextMenu = subMenus.get(2);
        } else if (input == 3) {
            nextMenu = this;
            offlineRiskGame = gameController.getOfflineGame();
            if (offlineRiskGame == null) {
                if (makeNewOfflineGame()) {
                    nextMenu = new OfflineGame(this);
                }
            } else {
                nextMenu = new Menu("Do you want to continue your last game ? ", this) {
                    @Override
                    public void show() {
                        System.out.println("1.Back");
                        System.out.println("2.Continue playing");
                        System.out.println("3.Start new Game");
                    }

                    @Override
                    public void execute() {
                        Menu nextMenu = this;
                        String inputInString = getInputFormatWithHelpText("^1|2|3$", "Enter a number:");
                        int input = Integer.parseInt(inputInString);
                        if (input == 1) {
                            nextMenu = parentMenu;
                        } else if (input == 2) {
                            nextMenu = new OfflineGame(this);
                        } else if (input == 3) {
                            makeNewOfflineGame();
                            nextMenu = new OfflineGame(this);
                        }
                        nextMenu.show();
                        nextMenu.execute();
                    }
                };
            }
        }
        nextMenu.show();
        nextMenu.execute();
    }

    private String getRiskGameName() {
        String riskGameName = getInputFormatWithHelpText("^(.+$)|(?i)back", "Enter your risk game name or write back to return");
        if (riskGameName.equalsIgnoreCase("Back")) {
            return null;
        }
        return riskGameName;
    }

    private int getPlayersNumber() {
        String playersNumberString = getInputFormatWithHelpText("^(\\d+)|(?i)back$", "Enter number of players or write back to return");
        if (playersNumberString.equalsIgnoreCase("Back")) {
            return 0;
        }
        int playersNumber = Integer.parseInt(playersNumberString);
        while (true) {
            try {
                if (gameController.checkNumberOfPlayers(playersNumber))
                    break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            playersNumberString = getInputFormatWithHelpText("^\\d+$", "Enter number of players or write back to return");
            playersNumber = Integer.parseInt(playersNumberString);
        }
        return playersNumber;
    }

    private long setTimer() {
        System.out.println("1.Back");
        System.out.println("2.3 min");
        System.out.println("3.5 min");
        System.out.println("4.7 min");
        String inputInString = getInputFormatWithHelpText("^1|2|3|4$", "Enter a number");
        int input = Integer.parseInt(inputInString);
        if (input == 1) {
            return 0;
        } else if (input == 2) {
            return 180;
        } else if (input == 3) {
            return 300;
        } else {
            return 420;
        }
    }

    //TODO Manually is not complete
    public boolean makeNewOfflineGame() {
        String riskGameName = getRiskGameName();
        if (riskGameName == null)
            return false;
        int numberOfPlayers = getPlayersNumber();
        if (numberOfPlayers == 0)
            return false;
        Player[] players = makeOfflinePlayers(numberOfPlayers);
        if (players == null)
            return false;
        long timer = setTimer();
        if (timer == 0) {
            return false;
        }
        System.out.println("Do U wanna Play Manually for Starting or Unmanually ?");
        System.out.println("1.Back      2.Manually      3.Unmanually");
        String input = getInputWithFormat("^1|2|3$");
        if (input.equalsIgnoreCase("1"))
            return false;
        else if (input.equalsIgnoreCase("2"))
            gameController.makeOfflineGame(riskGameName, players, numberOfPlayers, timer, true);
        else if (input.equalsIgnoreCase("3"))
            gameController.makeOfflineGame(riskGameName, players, numberOfPlayers, timer, false);
        return true;
    }

    private Player[] makeOfflinePlayers(int numberOfPlayers) {
        String inputInString;
        String userName;
        ArrayList<Color> colors = gameController.getDefaultColors();
        HashMap<Integer, Color> colorsToChose;
        Player[] players = new Player[numberOfPlayers];
        System.out.println("Enter the names of the players or write back to return");
        for (int i = 0; i < numberOfPlayers; i++) {
            userName = getInputFormatWithHelpText("^.+$|^(?i)back$", "Please Enter a Username : ");
            if (userName.equalsIgnoreCase("back"))
                return null;
            colorsToChose = gameController.getColorsToChose(colors);
            showColorsToChose(colorsToChose);
            while (true) {
                inputInString = getInputWithFormat("^\\d+$|^(?i)back$");
                if (inputInString.equalsIgnoreCase("back"))
                    return null;
                int input = Integer.parseInt(inputInString);
                if (input <= colorsToChose.size()) {
                    colors.remove(colorsToChose.get(input));
                    players[i] = gameController.makeOfflinePlayerWithColor(userName, colorsToChose.get(input));
                    break;
                }
            }
        }
        return players;
    }

    private void showColorsToChose(HashMap<Integer, Color> colors) {
        for (Integer i : colors.keySet()) {
            System.out.print(i + "." + colors.get(i).toString() + "\t");
        }
    }

    private void calculateSubMenusForMainMenu() {
        Admin admin = userController.getAdmin();
        if (admin == null) {
            subMenus.put(2, new RegisterMenu(this));
        } else {
            subMenus.put(2, new OnlineGameMenu(this));
        }
    }
}

