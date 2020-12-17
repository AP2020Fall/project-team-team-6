package model;

public enum GameStages {
    ATTACK, FORTIFY, DRAFT, STARTING;

    private static GameStages[] defaultGameStages = {DRAFT, ATTACK, FORTIFY};
}
