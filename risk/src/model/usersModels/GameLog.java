package model.usersModels;

import model.gamesModels.RiskGame;

import java.time.LocalDateTime;

public class GameLog {
    private int logID;
    private String riskGameName;
    private double points;
    private Player winner;
    private LocalDateTime localDateTime;

    public GameLog(String riskGameName, double points, Player winner) {
        this.riskGameName = riskGameName;
        this.points = points;
        this.winner = winner;
        this.localDateTime = LocalDateTime.now();
    }

    public int getLogID() {
        return logID;
    }

    public void setLogID() {
    }

    public String getRiskGameName() {
        return riskGameName;
    }

    public double getPoints() {
        return points;
    }

    public Player getWinner() {
        return winner;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

}

