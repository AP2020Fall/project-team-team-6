package model;

public class RiskGame {
    private String name;
    private int gameID;

    public RiskGame(String name) {
        this.name = name;
        setGameID();
    }


    public void setGameID(){
        this.gameID = DataBase.getDataBase().getAllRiskGames().size()+1;
    }

    public String getName() {
        return name;
    }

    public int getGameID() {
        return gameID;
    }

    //TODO ......
}
