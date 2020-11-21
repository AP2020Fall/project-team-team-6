package model;

import controller.GameController;

import java.util.ArrayList;
import java.util.HashMap;

public class RiskGame {
    private String name;
    private int gameID;
    private RiskGameType riskGameType;
    private int gamePoint;
    private static RiskGame offlineGame;
    private boolean isGameStarted;
    private GameStages gameStages = GameStages.DRAFT;
    private boolean hasOneSuccessAttack;
    private long timer;
    private boolean isMapManually;


    private HashMap<Integer , Country> allCountriesWithNumber = new HashMap<>();
    private HashMap<Integer , Country> allNorthAmericasCountries = new HashMap<>();
    private HashMap<Integer , Country> allSouthAmericasCountries = new HashMap<>();
    private HashMap<Integer , Country> allAfricaCountries = new HashMap<>();
    private HashMap<Integer , Country> allEuropeCountries = new HashMap<>();
    private HashMap<Integer , Country> allAsiaCountries = new HashMap<>();
    private HashMap<Integer , Country> allAustraliaCountries = new HashMap<>();


    private int numberOfPlayers;
    private Player winner ;
    private Player currentPlayer;

    private ArrayList<Card> allCards;


    private ArrayList<Integer> attackersDices = new ArrayList<>();
    private ArrayList<Integer> defendersDices = new ArrayList<>();

    private Player creator;
    private Player[] players;


    //This constructor is for online
    public RiskGame(String name ,Player creator , RiskGameType riskGameType ,
                    int numberOfPlayers , int gamePoint , long timer , boolean isMapManually) {
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
        this.hasOneSuccessAttack = false;
        this.timer = timer;
        this.isMapManually = isMapManually;
        setGameID();
    }
    //This constructor is for offline games
    public RiskGame(String name , String[] playersName , int numberOfPlayers , long timer , boolean isMapManually) {
        this.name = name;
        this.numberOfPlayers = numberOfPlayers;

        //Make card + Countries
        GameController.makeAllCountries(this);
        this.allCards = GameController.makeAllCardsForGame();
        this.timer = timer;
        this.isMapManually = isMapManually;
        this.riskGameType = RiskGameType.OFFLINE;

        //Set Players
        this.players = new Player[numberOfPlayers];
        this.players = GameController.makeOfflinePlayers(playersName);
        this.winner = null;
        this.currentPlayer = null;
        this.isGameStarted = true;
        this.gamePoint =0 ;
        this.riskGameType = RiskGameType.OFFLINE;
        this.hasOneSuccessAttack = false;


        setGameID();
    }



    public void setGameID(){
        this.gameID = DataBase.getDataBase().getAllRiskGames().size()+1;
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
}
