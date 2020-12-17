package view;

import model.RiskGame;

import java.util.HashMap;

public class GamesMenu extends Menu {
    private HashMap<Integer, RiskGame> allGames;

    public GamesMenu(Menu parentMenu) {
        super("Games Menu", parentMenu);
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
