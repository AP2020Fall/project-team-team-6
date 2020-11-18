package view;

import model.RiskGame;


public class RiskGameMenu extends Menu {
    private RiskGame riskGame;
    public RiskGameMenu( Menu parentMenu , RiskGame riskGame) {
        super("Play Risk ", parentMenu);
        this.riskGame = riskGame;
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void execute() {
        super.execute();
    }
}
