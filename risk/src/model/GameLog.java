package model;

public class GameLog {
    private int numberOfTimes;
    private int numberOfWins;
    private int logID;
    private int pointsEarned;

    public GameLog(int numberOfTimes, int numberOfWins, int pointsEarned) {
        this.numberOfTimes = numberOfTimes;
        this.numberOfWins = numberOfWins;
        this.pointsEarned = pointsEarned;
    }

    public int showNumberOfWins(){return numberOfWins;}

    public int showNumberOfTimes(){return numberOfTimes;}

}

