package server.model.gamesModels;


import server.model.usersModels.Player;

import java.util.ArrayList;

public class Card {
    private static ArrayList<Card> allCards = makeAllCards();
    private int countryID;
    private CardDesigns cardDesigns;
    private Player player;

    public Card(int countryID, CardDesigns cardsDesigns) {
        this.countryID = countryID;
        this.cardDesigns = cardsDesigns;
        this.player = null;
    }

    public int getCountryID() {
        return countryID;
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

    private static ArrayList<Card> makeAllCards() {
        ArrayList<Card> allCards = new ArrayList<>();
        for (int i = 1; i <= 42; i++) {
            Card card = new Card(i, CardDesigns.INFANTRY);
            allCards.add(card);
            card = new Card(i, CardDesigns.ARTILLERY);
            allCards.add(card);
            card = new Card(i, CardDesigns.CAVALRY);
            allCards.add(card);
        }
        return allCards;
    }

    public static ArrayList<Card> getAllCards() {
        return allCards;
    }

    public String changeCardInformationToString() {
        String cardInformation = countryID + "," + cardDesigns + "\r\n";
        return cardInformation;
    }

    public static Card getCardFromString(String string) {
        String[] cardInformation = string.split(",");
        if (cardInformation.length > 1) {
            int countryID = Integer.parseInt(cardInformation[0]);
            String cardDesignInString = cardInformation[1];
            CardDesigns cardDesign;
            if (cardDesignInString.equals("Infantry")) {
                cardDesign = CardDesigns.INFANTRY;
            } else if (cardDesignInString.equals("Cavalry")) {
                cardDesign = CardDesigns.CAVALRY;
            } else {
                cardDesign = CardDesigns.ARTILLERY;
            }
            return new Card(countryID, cardDesign);
        }
        return null;
    }
}
