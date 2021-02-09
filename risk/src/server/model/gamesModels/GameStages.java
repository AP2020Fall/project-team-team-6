package server.model.gamesModels;

public enum GameStages {
    ATTACK, FORTIFY, DRAFT, STARTING;

    private static GameStages[] defaultGameStages = {DRAFT, ATTACK, FORTIFY};
}
