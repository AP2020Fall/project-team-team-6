package model;

import java.util.ArrayList;
import java.util.HashMap;

public class DataBase {
    private static DataBase dataBase = new DataBase();
    private ArrayList<Player> allPlayers;
    private HashMap<Integer, RiskGame> allRiskGames;
    private ArrayList<User> allUsers;
    private ArrayList<Event> allEvents;
    private Admin admin;

    private DataBase() {
        this.allPlayers = new ArrayList<>();
        this.allRiskGames = new HashMap<>();
        this.allEvents = new ArrayList<>();
        this.allUsers = new ArrayList<>();
        this.admin = new Admin("1", "1", "1", "1", "1", "1 ");
        Player player1 = new Player("Amin ", "Ghodosiyan", "A", "1", "1@1.com", "11111111111");
        Player player2 = new Player("Shayan ", "Shabanzadeh", "S", "1", "1@1.com", "11111111111");
        allPlayers.add(player1);
        allPlayers.add(player2);
        allUsers.add(player1);
        allUsers.add(player2);
        allUsers.add(admin);
    }


    public static DataBase getDataBase() {
        return dataBase;
    }

    public ArrayList<Player> getAllPlayers() {
        return allPlayers;
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
