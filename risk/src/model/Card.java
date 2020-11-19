package model;

import java.util.ArrayList;

public class Card {
    private Country country;
    private Color color;
    private CardDesigns cardDesigns;
    private  Player player;

    private Card(Country country, Color color, CardDesigns cardsDesigns) {
        this.country = country;
        this.color = color;
        this.cardDesigns = cardsDesigns;
        this.player = null;
    }

    public Country getCountry() {
        return country;
    }

    public Color getColor() {
        return color;
    }

    public CardDesigns getCardsDesigns() {
        return cardDesigns;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public static void makeAllCards(ArrayList<Color> colors) {}
}
