package model;

import java.util.ArrayList;
import java.util.HashMap;

public class DataBase {
    private static  DataBase dataBase = new DataBase();
    private HashMap<Integer,Player> allPlayersWithID;
    private HashMap<Integer,RiskGame> allRiskGames;
    private ArrayList<User> allUsers;
    private ArrayList<Event> allEvents;
    private Admin admin;

    private DataBase(){
       this.allPlayersWithID = new HashMap<>();
       this.allRiskGames = new HashMap<>();
       this.allEvents = new ArrayList<>();
       this.allUsers = new ArrayList<>();
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

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public ArrayList<Event> getAllEvents() {
        return allEvents;
    }

    public ArrayList<User> getAllUsers() {
        return allUsers;
    }


}
