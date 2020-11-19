package model;

import java.time.LocalDateTime;

public class Event {
    private String gameName;
    private RiskGame game;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private double eventPoint;
    private int eventID;

    public Event(LocalDateTime startDate, LocalDateTime endDate, RiskGame riskGame,double eventPoint) {
        this.gameName = riskGame.getName();
        this.startDate = startDate;
        this.endDate = endDate;
        this.eventPoint = eventPoint;
        this.game = riskGame;
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

    public void setEventID(){
        eventID = DataBase.getDataBase().getAllEvents().size()+1;
    }

}
