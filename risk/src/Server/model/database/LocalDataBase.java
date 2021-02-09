package Server.model.database;

import Server.model.gamesModels.Event;
import Server.model.gamesModels.RiskGame;
import Server.model.usersModels.Admin;
import Server.model.usersModels.Player;
import Server.model.usersModels.User;

import java.util.ArrayList;

public class LocalDataBase {
    private static LocalDataBase localDataBase = new LocalDataBase();
    private ArrayList<Player> allPlayers;
    private ArrayList<RiskGame> allRiskGames;
    private ArrayList<User> allUsers;
    private ArrayList<Event> allEvents;
    private Admin admin;

    private LocalDataBase() {
        this.allPlayers = new ArrayList<>();
        this.allRiskGames = new ArrayList<>();
        this.allEvents = new ArrayList<>();
        this.allUsers = new ArrayList<>();
    }


    public static LocalDataBase getLocalDataBase() {
        return localDataBase;
    }

    public ArrayList<Player> getAllPlayers() {
        return allPlayers;
    }

    public ArrayList<RiskGame> getAllRiskGames() {
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
