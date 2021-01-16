package model.usersModels;

import controller.UserController;
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

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
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

    public static String changeGameLogToString(GameLog gameLog){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(gameLog.getRiskGameName()).append("$");//This is for game name
        stringBuilder.append(gameLog.getPoints()).append("$"); // This is for points
        stringBuilder.append(gameLog.getWinner().getUsername()).append("$");//This is for winner
        stringBuilder.append(gameLog.getLocalDateTime()); // this is for date
        return stringBuilder.toString();
    }
    public static GameLog changeStringToGameLog(String text){
        String[] gameLogInArray = text.split("\\$");
        if(gameLogInArray.length > 3){
            String gameName = gameLogInArray[0];
            double point = Double.parseDouble(gameLogInArray[1]);
            Player winner = UserController.getUserController().findPlayerByUserName(gameLogInArray[2]);
            LocalDateTime date = LocalDateTime.parse(gameLogInArray[3]);
            GameLog gameLog = new GameLog(gameName , point , winner);
            gameLog.setLocalDateTime(date);
            return gameLog;
        }
        return null;
    }

}

