package Client.terminalMenu;

import Server.model.gamesModels.Color;
import Server.model.gamesModels.RiskGame;
import Server.model.gamesModels.RiskGameType;
import Server.model.usersModels.GameLog;
import Server.model.usersModels.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class GamesMenu extends Menu {
    private HashMap<Integer, RiskGame> allGames;
    private Player currentPlayer;

    public GamesMenu(Player currentPlayer, Menu parentMenu) {
        super("Games Menu", parentMenu);
        this.currentPlayer = currentPlayer;
    }

    @Override
    public void show() {
        super.show();
        System.out.println("2.Create new Game");
        System.out.println("3.All Available risk games");
        System.out.println("4.Game logs");
    }

    @Override
    public void execute() {
        Menu nextMenu = this;
        String inputInString = getInputFormatWithHelpText("^1|2|3|4$", "Enter a number:");
        int input = Integer.parseInt(inputInString);
        if (input == 1) {
            nextMenu = parentMenu;
        } else if (input == 2) {
            RiskGame riskGame = makeNewGame();
            if (riskGame != null) {
                Color color = chooseColor(riskGame);
                if (color != null) {
                    nextMenu = new Menu(riskGame.getName(), this) {
                        @Override
                        public void show() {
                            showRiskGameInformation(riskGame);
                            System.out.println("1.back");
                            System.out.println("2.Invite player");
                            System.out.println("3.delete the game");
                            System.out.println("4.Start");
                        }

                        @Override
                        public void execute() {
                            Menu nextMenu = this;
                            String inputInString = getInputFormatWithHelpText("^1|2|3|4$", "Enter a number :");
                            int input = Integer.parseInt(inputInString);
                            if (input == 1) {
                                nextMenu = parentMenu;
                            } else if (input == 2) {
                                try {
                                    invitePlayersToGame(riskGame);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } else if (input == 3) {
                                System.out.println("Are you sure you want to delete this game ? ");
                                String confirmString = getInputFormatWithHelpText("^(?i)yes|(?i)no$", "Answer with yes or no");
                                if (confirmString.equalsIgnoreCase("yes")) {
                                    gameController.deleteRiskGame(riskGame);
                                }
                            } else if (input == 4) {
                                boolean isGameReadyForStart = gameController.checkGame(riskGame);
                                if (isGameReadyForStart) {
                                    gameController.makeOnlineGameReadyToStart(riskGame);
                                    nextMenu = new RiskGameMenu(this, riskGame);
                                } else {
                                    System.err.println("You cant Start the game because you don't have enough players in your game ");
                                }
                            }
                            nextMenu.show();
                            nextMenu.execute();
                        }
                    };
                }
            }
        } else if (input == 3) {
            nextMenu = new Menu("", this) {
                RiskGame playerGame = gameController.getPlayersGame(currentPlayer);
                HashMap<Integer, RiskGame> requestedGames = gameController.getAllRequestedGamesForPlayers(currentPlayer, 2);
                int index = requestedGames.size() + 2;
                HashMap<Integer, RiskGame> eventGames = eventController.getEventsGameForPlayerInHashMap(currentPlayer, index);

                @Override
                public void show() {
                    System.out.println("1.back");
                    requestedGames.putAll(eventGames);
                    if (playerGame != null) {
                        showRiskGameInformation(playerGame);
                        if (!currentPlayer.equals(playerGame.getCreator()) && !playerGame.isGameStarted()) {
                            System.out.println("WAITING TO START");
                            System.out.println("2.Leave the match");
                        } else if (currentPlayer.equals(playerGame.getCreator()) && !playerGame.isGameStarted()) {
                            System.out.println("2.Invite player");
                            System.out.println("3.delete the game");
                            System.out.println("4.Start");
                        } else if (playerGame.isGameStarted()) {
                            System.out.println("2.Continue this game");
                        }
                    } else {
                        showGamesToJoin(requestedGames);
                    }
                }

                @Override
                public void execute() {
                    Menu nextMenu = this;
                    String inputString = getInputFormatWithHelpText("^\\d+$", "Enter a number :");
                    int input = Integer.parseInt(inputString);
                    if (input == 1) {
                        nextMenu = parentMenu;
                    } else if (playerGame != null) {
                        if (!currentPlayer.equals(playerGame.getCreator()) && !playerGame.isGameStarted()) {
                            if (input == 2) {
                                gameController.removePlayerFromGame(playerGame, currentPlayer);
                                System.out.println("You have successfully left the game");
                            } else
                                System.out.println("Invalid command");
                        } else if (currentPlayer.equals(playerGame.getCreator()) && !playerGame.isGameStarted()) {
                            if (input == 2) {
                                try {
                                    invitePlayersToGame(playerGame);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } else if (input == 3) {
                                System.out.println("Are you sure you want to delete this game ? ");
                                String confirmString = getInputFormatWithHelpText("^(?i)yes|(?i)no$", "Answer with yes or no");
                                if (confirmString.equalsIgnoreCase("yes")) {
                                    gameController.deleteRiskGame(playerGame);
                                }
                            } else if (input == 4) {
                                boolean isGameReadyForStart = gameController.checkGame(playerGame);
                                if (isGameReadyForStart) {
                                    gameController.makeOnlineGameReadyToStart(playerGame);
                                    nextMenu = new RiskGameMenu(this, playerGame);
                                } else {
                                    System.err.println("You cant Start the game because you don't have enough players in your game ");
                                }
                            } else {
                                System.out.println("Invalid number");
                            }
                        } else if (playerGame.isGameStarted()) {
                            if (input == 2) {
                                nextMenu = new RiskGameMenu(this, playerGame);
                            } else {
                                System.out.println("Invalid number");
                            }
                        }
                    } else {
                        if (input > requestedGames.size() + 1) {
                            System.out.println("Invalid number");
                        } else {
                            RiskGame riskGame = requestedGames.get(input);
                            Color color = chooseColor(riskGame);
                            if (color != null) {
                                try {
                                    gameController.addPlayerToGame(currentPlayer, riskGame);
                                    System.out.println("You have successfully joined the game");
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                            }

                        }
                    }
                    nextMenu.show();
                    nextMenu.execute();
                }
            };
        } else if (input == 4) {
            showGameLogs();
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


    public RiskGame makeNewGame() {
        RiskGame riskGame = null;
        String riskGameName = getRiskGameName();
        if (riskGameName == null)
            return null;
        int numberOfPlayers = getPlayersNumber();
        if (numberOfPlayers == 0)
            return null;
        Player[] players = new Player[numberOfPlayers];
        players[0] = currentPlayer;
        long timer = setTimer();
        if (timer == 0) {
            return null;
        }
        System.out.println("Do U wanna Play Manually for Starting or Unmanually ?");
        System.out.println("1.Back      2.Manually      3.Unmanually");
        String input = getInputWithFormat("^1|2|3$");
        if (input.equalsIgnoreCase("1"))
            return null;
        else if (input.equalsIgnoreCase("2")) {
            riskGame = new RiskGame(riskGameName, currentPlayer, RiskGameType.PRIVATE, numberOfPlayers, 10, timer, true);
            gameController.addGameToLocalDataBase(riskGame);
        } else if (input.equalsIgnoreCase("3")) {
            riskGame = new RiskGame(riskGameName, currentPlayer, RiskGameType.PRIVATE, numberOfPlayers, 10, timer, false);
            gameController.addGameToLocalDataBase(riskGame);
        }
        return riskGame;
    }

    private void showRiskGameInformation(RiskGame riskGame) {
        System.out.println("Name : " + riskGame.getName());
        Player[] players = riskGame.getPlayers();
        System.out.print("Players : ");
        if (players.length == 0) {
            System.out.println("There is no player in this game");
        } else {
            for (int i = 1; i <= players.length; i++) {
                if (players[i - 1] != null)
                    System.out.print(i + "." + players[i - 1].getUsername() + "\t");
            }
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

    private void invitePlayersToGame(RiskGame riskGame) throws Exception {
        int maximumOfPlayers = riskGame.getNumberOfPlayers();
        int currentNumberOfPlayers = 0;
        ArrayList<Player> currentPlayers = new ArrayList<>();
        for (Player player : riskGame.getPlayers()) {
            if (player != null) {
                currentNumberOfPlayers++;
                currentPlayers.add(player);
            }
        }
        if (currentNumberOfPlayers <= maximumOfPlayers) {
            System.out.println("Fill the blanks or type back to return");
            while (true) {
                String userInput = getInputFormatWithHelpText(".+", "Enter the player username : ");
                if (userInput.equals("back"))
                    return;
                Player player = userController.findPlayerByUserName(userInput);
                if (player == null) {
                    throw new Exception("This user doesn't exist");
                } else if (currentPlayers.contains(player)) {
                    throw new Exception("This player has already joined the game");
                } else {
                    System.out.println("Your have successfully invited " + player.getUsername());
                    gameController.invitePlayerToGame(player, riskGame);
                    break;
                }
            }
        } else {
            throw new Exception("You can't add more than " + maximumOfPlayers + " players to this game");
        }


    }

    private void showGamesToJoin(HashMap<Integer, RiskGame> riskGameHashMap) {
        for (int i : riskGameHashMap.keySet()) {
            System.out.println(i + ".");
            showRiskGameInformation(riskGameHashMap.get(i));
        }
    }

    private void showColorsToChose(HashMap<Integer, Color> colors) {
        for (Integer i : colors.keySet()) {
            System.out.print(i + "." + colors.get(i).toString() + "\t");
        }
    }

    private Color chooseColor(RiskGame riskGame) {
        ArrayList<Color> availableColors = gameController.getAvailableColors(riskGame);
        HashMap<Integer, Color> colors = gameController.getColorsToChose(availableColors);
        showColorsToChose(colors);
        String inputInString = getInputFormatWithHelpText("^\\d+|(?i)back$", "Enter a number or type back to return");
        if (inputInString.equalsIgnoreCase("back"))
            return null;
        int input = Integer.parseInt(inputInString);
        Color color = colors.get(input);
        currentPlayer.setCurrentColor(color);
        return color;
    }

    private void showGameLogs() {
        System.out.println("----------------- Game Logs -------------------");
        int index = 1;
        if (currentPlayer.getGameLogs().size() == 0) {
            System.out.println("Empty");
        } else {
            for (GameLog gameLog : currentPlayer.getGameLogs()) {
                System.out.println(index + ".\nGame name : " + gameLog.getRiskGameName() + "Point : " + gameLog.getPoints() + "Winner :" + gameLog.getWinner().getUsername() + "Date : " + gameLog.getLocalDateTime());
                index++;
            }
        }
    }


}
