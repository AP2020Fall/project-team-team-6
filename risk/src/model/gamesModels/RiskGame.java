package model.gamesModels;

import controller.GameController;
import controller.UserController;
import model.database.LocalDataBase;
import model.usersModels.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RiskGame {
    private String name;
    private int gameID;
    private RiskGameType riskGameType;
    private int gamePoint;
    private static RiskGame offlineGame;
    private boolean isGameStarted;
    private GameStages gameStages;
    private boolean hasGotSoldiersForDraft = false;
    private boolean hasOneSuccessAttack = false;
    private boolean hasDoneFortify = false;
    private long timer;
    private boolean isMapManually;
    private Country[][] allCountriesInArray = new Country[8][23];

    private static final String defaultMap = "1  6  6  5  5  5  21  23  23 23  23  --  --  --  --  --  --  --  --  38  38  32  32\n" +
            "2  2  7  7  8  -  20  20  22 22  25  25  25  25  37  37  37  36  36  36  30  30  31\n" +
            "9  9  9  4  4  -  26  26  26 24  24  33  33  25  27  27  28  28  28  34  34  34  34\n" +
            "3  3  3  3  3  -  18  18  18 18  18  16  33  33  33  29  29  29  35  --  --  --  --\n" +
            "3  -  -  -  -  -  18  18  18 18  18  16  33  33  33  29  29  29  35  --  --  --  --\n" +
            "13 11 11 11 11 11 18  18  18 18  18  16  33  33  33  29  29  29  35  --  --  --  --\n" +
            "12 12 10 -- -- -- 14  14  14 14  15  15  33  --  --  --  --  --  40  41  41  --  --\n" +
            "-- -- -- -- -- -- --  --  -- 19  19  17  --  --  --  --  --  --  42  42  39  --  --";

    private String[][] riskMap = makeRiskMap();


    private HashMap<Integer, Country> allCountriesWithNumber = new HashMap<>();
    private HashMap<Integer, Country> allNorthAmericasCountries = new HashMap<>();
    private HashMap<Integer, Country> allSouthAmericasCountries = new HashMap<>();
    private HashMap<Integer, Country> allAfricaCountries = new HashMap<>();
    private HashMap<Integer, Country> allEuropeCountries = new HashMap<>();
    private HashMap<Integer, Country> allAsiaCountries = new HashMap<>();
    private HashMap<Integer, Country> allAustraliaCountries = new HashMap<>();


    private int numberOfPlayers;
    private Player winner;
    private Player currentPlayer;

    private ArrayList<Card> allCards;


    private ArrayList<Integer> attackersDices = new ArrayList<>();
    private ArrayList<Integer> defendersDices = new ArrayList<>();

    private Player creator;
    private Player[] players;


    //This constructor is for online
    public RiskGame(String name, Player creator, RiskGameType riskGameType,
                    int numberOfPlayers, int gamePoint, long timer, boolean isMapManually) {
        this.name = name;
        this.numberOfPlayers = numberOfPlayers;
        GameController.makeAllCountries(this);
        this.allCards = GameController.makeAllCardsForGame();
        this.creator = creator;
        this.players = new Player[numberOfPlayers];
        players[0] = creator;
        this.winner = null;
        this.riskGameType = riskGameType;
        this.isGameStarted = false;
        this.currentPlayer = null;
        this.gamePoint = gamePoint;
        this.gameStages = GameStages.STARTING;
        this.hasOneSuccessAttack = false;
        this.timer = timer;
        this.isMapManually = isMapManually;
        makeAllCountriesArray();
        setGameID();
    }

    //This constructor is for offline games
    public RiskGame(String name, Player[] players, int numberOfPlayers, long timer, boolean isMapManually) {
        this.name = name;
        this.numberOfPlayers = numberOfPlayers;

        //Make card + Countries
        GameController.makeAllCountries(this);
        this.allCards = GameController.makeAllCardsForGame();
        this.timer = timer;
        this.isMapManually = isMapManually;
        this.riskGameType = RiskGameType.OFFLINE;

        //Set Players
        this.players = players;
        this.winner = null;
        this.currentPlayer = players[0];
        this.isGameStarted = true;
        this.gameStages = GameStages.STARTING;
        this.gamePoint = 0;
        this.hasOneSuccessAttack = false;
        GameController.getGameController().addSoldiersForStartingGame(this);
        makeAllCountriesArray();

        setGameID();
    }


    public void setGameID() {
        this.gameID = LocalDataBase.getLocalDataBase().getAllRiskGames().size() + 1;
    }

    public String getName() {
        return name;
    }

    public int getGameID() {
        return gameID;
    }

    public HashMap<Integer, Country> getAllCountriesWithNumber() {
        return allCountriesWithNumber;
    }

    public HashMap<Integer, Country> getAllNorthAmericasCountries() {
        return allNorthAmericasCountries;
    }

    public HashMap<Integer, Country> getAllSouthAmericasCountries() {
        return allSouthAmericasCountries;
    }

    public HashMap<Integer, Country> getAllAfricaCountries() {
        return allAfricaCountries;
    }

    public HashMap<Integer, Country> getAllEuropeCountries() {
        return allEuropeCountries;
    }

    public HashMap<Integer, Country> getAllAsiaCountries() {
        return allAsiaCountries;
    }

    public HashMap<Integer, Country> getAllAustraliaCountries() {
        return allAustraliaCountries;
    }

    public ArrayList<Card> getAllCards() {
        return allCards;
    }

    public Player getCreator() {
        return creator;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setCreator(Player creator) {
        this.creator = creator;
    }

    public RiskGameType getRiskGameType() {
        return riskGameType;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public static RiskGame getOfflineGame() {
        return offlineGame;
    }

    public static void setOfflineGame(RiskGame offlineGame) {
        RiskGame.offlineGame = offlineGame;
    }

    public boolean isGameStarted() {
        return isGameStarted;
    }

    public void setGameStarted(boolean gameStarted) {
        isGameStarted = gameStarted;
    }

    public int getGamePoint() {
        return gamePoint;
    }

    public ArrayList<Integer> getAttackersDices() {
        return attackersDices;
    }

    public ArrayList<Integer> getDefendersDices() {
        return defendersDices;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public boolean isHasOneSuccessAttack() {
        return hasOneSuccessAttack;
    }

    public void setHasOneSuccessAttack(boolean hasOneSuccessAttack) {
        this.hasOneSuccessAttack = hasOneSuccessAttack;
    }

    public GameStages getGameStages() {
        return gameStages;
    }

    public long getTimer() {
        return timer;
    }

    public boolean isMapManually() {
        return isMapManually;
    }

    private String[][] makeRiskMap() {
        String map[][] = new String[8][23];
        String[] defaultMap = RiskGame.defaultMap.split("\\s+");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 23; j++) {
                map[i][j] = defaultMap[i * 23 + j];
            }
        }
        return map;
    }

    public static String getDefaultMap() {
        return defaultMap;
    }

    public String[][] getRiskMap() {
        return riskMap;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }


    public void setGameStages(GameStages gameStages) {
        this.gameStages = gameStages;
    }

    public void makeAllCountriesArray() {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 23; j++) {
                matcher = pattern.matcher(riskMap[i][j]);
                if (matcher.find()) {
                    int countryCoordinate = Integer.parseInt(riskMap[i][j]);
                    Country country = allCountriesWithNumber.get(countryCoordinate);
                    allCountriesInArray[i][j] = country;
                }
            }
        }
    }

    public Country[][] getAllCountriesInArray() {
        return allCountriesInArray;
    }

    public boolean isHasGotSoldiersForDraft() {
        return hasGotSoldiersForDraft;
    }

    public void setHasGotSoldiersForDraft(boolean hasGotSoldiersForDraft) {
        this.hasGotSoldiersForDraft = hasGotSoldiersForDraft;
    }

    public boolean isHasDoneFortify() {
        return hasDoneFortify;
    }

    public void setHasDoneFortify(boolean hasDoneFortify) {
        this.hasDoneFortify = hasDoneFortify;
    }

    public static  String changeOnlineRiskInformationToString(RiskGame riskGame){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(riskGame.getName()).append(":");  //risk game name
        if(riskGame.getCreator() != null)
        stringBuilder.append(riskGame.getCreator().getUsername()).append(":"); // risk game creator[
        else{
            stringBuilder.append("null:");
        }
        stringBuilder.append(riskGame.getRiskGameType()).append(":"); //risk game type
        stringBuilder.append(riskGame.getNumberOfPlayers()).append(":"); // number of players
        stringBuilder.append(riskGame.getGamePoint()).append(":");//game point
        stringBuilder.append(riskGame.getTimer()).append(":"); // get timer
        stringBuilder.append(riskGame.isMapManually).append(":");// is map manually ?
        return stringBuilder.toString();
    }
    public static RiskGame getOnlineRiskFromString(String riskInString){
        String[] riskInArray = riskInString.split(":");
        Player creator = null;
        String riskName = riskInArray[0];
        if(!riskInArray[1].equalsIgnoreCase("null")) {
             creator = UserController.getUserController().findPlayerByUserName(riskInArray[1]);  //This may return null
        }
        RiskGameType riskGameType =  getRiskGameTypeFromString(riskInArray[2]);
        int numberOfPlayer = Integer.parseInt(riskInArray[3]);
        int gamePoint = Integer.parseInt(riskInArray[4]);
        long timer = Long.parseLong(riskInArray[5]);
        boolean isMapManually = Boolean.parseBoolean(riskInArray[6]);
        RiskGame riskGame = new RiskGame(riskName , creator , riskGameType , numberOfPlayer , gamePoint , timer , isMapManually);
        return riskGame;
    }

    public static RiskGameType getRiskGameTypeFromString(String string){
        if(string.equalsIgnoreCase("Public"))
            return RiskGameType.PUBLIC;
        else if(string.equalsIgnoreCase("Private"))
            return RiskGameType.PRIVATE;
        else if(string.equalsIgnoreCase("Offline"))
            return RiskGameType.OFFLINE;
        else
            return null;
    }
}
