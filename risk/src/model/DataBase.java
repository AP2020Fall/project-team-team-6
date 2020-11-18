package model;

import java.util.HashMap;

public class DataBase {
    private static  DataBase dataBase = new DataBase();
    private HashMap<Integer,Player> allPlayersWithID;
    private HashMap<Integer,RiskGame> allRiskGames;
    private HashMap<Integer , Country> allCountriesWithNumber;
    private HashMap<Integer , Country> allNorthAmericasCountries;
    private HashMap<Integer , Country> allSouthAmericasCountries;
    private HashMap<Integer , Country> allAfricaCountries;
    private HashMap<Integer , Country> allEuropeCountries;
    private HashMap<Integer , Country> allAsiaCountries;
    private HashMap<Integer , Country> allAustraliaCountries;
    private HashMap<Integer , RiskGame> allGames ;
    private Admin admin;

    private DataBase(){
       this.allPlayersWithID = new HashMap<>();
       this.allRiskGames = new HashMap<>();
       this.allCountriesWithNumber = new HashMap<>();
       this.allNorthAmericasCountries = new HashMap<>();
       this.allSouthAmericasCountries = new HashMap<>();
       this.allAfricaCountries = new HashMap<>();
       this.allEuropeCountries = new HashMap<>();
       this.allAsiaCountries = new HashMap<>();
       this.allAustraliaCountries = new HashMap<>();
       this.allGames = new HashMap<>();
    }


    public static DataBase getDataBase() {
        return dataBase;
    }

    public HashMap<Integer, Player> getAllPlayersWithID() {
        return allPlayersWithID;
    }

    public HashMap<Integer, RiskGame> getAllRiskGames() {
        return allRiskGames;
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

    public HashMap<Integer, RiskGame> getAllGames() {
        return allGames;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
}
