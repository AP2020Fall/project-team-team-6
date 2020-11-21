package view;

import model.RiskGame;

public class OfflineGame extends Menu {
    private String[] playersName;
    private RiskGame riskGame;
    public OfflineGame( Menu parentMenu , RiskGame riskGame) {
        super( "Play Offline Game" ,parentMenu);
        //TODO players Name
        this.riskGame = riskGame;
    }

    public String[] getPlayersName() {
        return playersName;
    }
}
