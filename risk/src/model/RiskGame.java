package model;

import controller.GameController;

import java.util.ArrayList;
import java.util.HashMap;

public class RiskGame {
    private String name;
    private int gameID;


    private HashMap<Integer , Country> allCountriesWithNumber;
    private HashMap<Integer , Country> allNorthAmericasCountries;
    private HashMap<Integer , Country> allSouthAmericasCountries;
    private HashMap<Integer , Country> allAfricaCountries;
    private HashMap<Integer , Country> allEuropeCountries;
    private HashMap<Integer , Country> allAsiaCountries;
    private HashMap<Integer , Country> allAustraliaCountries;


    private ArrayList<Card> allCards;


    private Player creator;
    private ArrayList<Player> players;



    public RiskGame(String name ,Player creator) {
        this.name = name;
        this.allAfricaCountries = new HashMap<>();
        this.allAsiaCountries   = new HashMap<>();
        this.allAustraliaCountries  = new HashMap<>();
        this.allEuropeCountries  = new HashMap<>();
        this.allNorthAmericasCountries = new HashMap<>();
        this.allSouthAmericasCountries = new HashMap<>();
        this.allCountriesWithNumber = new HashMap<>();
        GameController.makeAllCountries(this);
        this.allCards = GameController.makeAllCardsForGame();
        this.creator = creator;
        this.players = new ArrayList<>();
        players.add(creator);
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

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setCreator(Player creator) {
        this.creator = creator;
    }
}
