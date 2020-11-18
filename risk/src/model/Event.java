package model;

import java.time.LocalDateTime;

public class Event {
    private String gameName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private double rate;
    private int eventID;

    public Event(String gameName, LocalDateTime startDate, LocalDateTime endDate, double rate) {
        this.gameName = gameName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.rate = rate;
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

    public double getRate() {
        return rate;
    }

    public int getEventID() {
        return eventID;
    }
}
