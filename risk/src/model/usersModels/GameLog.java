package model.usersModels;

import model.gamesModels.RiskGame;

public class GameLog {
    private int logID;
    private int pointsEarned;
    private RiskGame game;

    public GameLog(RiskGame game) {
        this.game = game;
        this.pointsEarned = 0;
        //TODO pointsEarned
    }

    public int getLogID() {
        return logID;
    }

    public int getPointsEarned() {
        return pointsEarned;
    }

    public RiskGame getGame() {
        return game;
    }

    public void setLogID() {
        //TODO .....
    }
}

