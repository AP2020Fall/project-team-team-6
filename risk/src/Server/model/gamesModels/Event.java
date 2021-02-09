package Server.model.gamesModels;

import Server.controller.UserController;
import Server.model.database.LocalDataBase;
import Server.model.usersModels.Player;

import java.time.LocalDate;
import java.util.ArrayList;

public class Event {
    private String gameName;
    private RiskGame game;
    private LocalDate startDate;
    private LocalDate endDate;
    private double eventPoint;
    private int eventID;
    private ArrayList<Player> invitedPlayers;
    private boolean isPublic;

    public Event(LocalDate startDate, LocalDate endDate, RiskGame riskGame) {
        this.gameName = riskGame.getName();
        this.startDate = startDate;
        this.endDate = endDate;
        this.eventPoint = riskGame.getGamePoint();
        this.game = riskGame;
        this.isPublic = true;
        this.invitedPlayers = LocalDataBase.getLocalDataBase().getAllPlayers();
        setEventID();
    }

    public Event(LocalDate startDate, LocalDate endDate, RiskGame riskGame, ArrayList<Player> invitedList) {
        this.gameName = riskGame.getName();
        this.startDate = startDate;
        this.endDate = endDate;
        this.eventPoint = riskGame.getGamePoint();
        this.game = riskGame;
        this.invitedPlayers = invitedList;
        this.isPublic = false;
        setEventID();
    }

    public String getGameName() {
        return gameName;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
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
