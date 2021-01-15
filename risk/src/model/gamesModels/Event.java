package model.gamesModels;

import controller.UserController;
import model.database.LocalDataBase;
import model.usersModels.Player;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Event {
    private String gameName;
    private RiskGame game;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private double eventPoint;
    private int eventID;
    private ArrayList<Player> invitedPlayers;
    private boolean isPublic;

    public Event(LocalDateTime startDate, LocalDateTime endDate, RiskGame riskGame, double eventPoint) {
        this.gameName = riskGame.getName();
        this.startDate = startDate;
        this.endDate = endDate;
        this.eventPoint = eventPoint;
        this.game = riskGame;
        this.isPublic = true;
        this.invitedPlayers = LocalDataBase.getLocalDataBase().getAllPlayers();
        setEventID();
    }

    public Event(LocalDateTime startDate, LocalDateTime endDate, RiskGame riskGame, double eventPoint, ArrayList<Player> invitedList) {
        this.gameName = riskGame.getName();
        this.startDate = startDate;
        this.endDate = endDate;
        this.eventPoint = eventPoint;
        this.game = riskGame;
        this.invitedPlayers = invitedList;
        this.isPublic = false;
        setEventID();
    }

    public String getGameName() {
        return gameName;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public double getEventPoint() {
        return eventPoint;
    }

    public int getEventID() {
        return eventID;
    }

    public RiskGame getGame() {
        return game;
    }

    public ArrayList<Player> getInvitedPlayers() {
        return invitedPlayers;
    }

    public void setGame(RiskGame game) {
        this.game = game;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public void setEventPoint(double eventPoint) {
        this.eventPoint = eventPoint;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public void setEventID() {
        eventID = LocalDataBase.getLocalDataBase().getAllEvents().size() + 1;
    }

    public void setInvitedPlayers(ArrayList<Player> invitedPlayers) {
        this.invitedPlayers = invitedPlayers;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public static ArrayList<Player> getPlayersFromString(String playersInString) {
        String[] playersInArray = playersInString.split(",");
        ArrayList<Player> players = new ArrayList<>();
        for (String username : playersInArray) {
            Player player = UserController.getUserController().findPlayerByUserName(username);
            if (player != null) {
                players.add(player);
            }
        }
        return players;
    }


}
