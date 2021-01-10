package model.usersModels;

import model.database.LocalDataBase;
import model.gamesModels.Card;
import model.gamesModels.CardDesigns;
import model.gamesModels.Color;
import model.gamesModels.Country;

import java.util.ArrayList;
import java.util.HashMap;

public class Player extends User {
    private int numbersOfDaysSinceRegistration;
    private double rate;
    private HashMap<Integer, GameLog> gameLogs;
    private ArrayList<Player> friends;
    private ArrayList<Player> requestsForFriendShips;
    private HashMap<Integer, RequestForPlaying> requestForPlaysList;
    private ArrayList<Card> playersCard;
    private HashMap<Integer, Country> playersCountry;
    private int numberOfSoldiers = 0;
    //This should change to HashMap<User , String >
    private ArrayList<Massage> massages;
    private ArrayList<Massage> adminMassages;

    private Color currentColor;
    private int numbersOfWin;
    private int numbersOfGames;
    private Player friendInGame;
    private ArrayList<Player> requestForFriendShipInGame;

    public Player(String firstName, String lastName,
                  String username, String password,
                  String emailAddress, String telephoneNumber) {
        super(firstName, lastName, username, password, emailAddress, telephoneNumber, false);

        this.numbersOfDaysSinceRegistration = 0;
        this.numbersOfGames = 0;
        this.numbersOfWin = 0;
        this.rate = 0.0;
        this.gameLogs = new HashMap<>();
        this.friends = new ArrayList<>();
        this.requestsForFriendShips = new ArrayList<>();
        this.requestForPlaysList = new HashMap<>();
        this.playersCard = new ArrayList<>();
        this.playersCountry = new HashMap<>();
        this.massages = new ArrayList<>();
        this.requestForFriendShipInGame = new ArrayList<>();
        this.friendInGame = null;
        this.currentColor = null;
        this.adminMassages = new ArrayList<>();
        playersCard.add(new Card(1, CardDesigns.INFANTRY));
        playersCard.add(new Card(1, CardDesigns.INFANTRY));
        playersCard.add(new Card(1, CardDesigns.INFANTRY));
        playersCard.add(new Card(1, CardDesigns.ARTILLERY));
        playersCard.add(new Card(1, CardDesigns.ARTILLERY));
        playersCard.add(new Card(1, CardDesigns.ARTILLERY));
        playersCard.add(new Card(1, CardDesigns.CAVALRY));
        playersCard.add(new Card(1, CardDesigns.INFANTRY));
        playersCard.add(new Card(1, CardDesigns.ARTILLERY));

    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public int getNumbersOfDaysSinceRegistration() {
        return numbersOfDaysSinceRegistration;
    }


    public double getRate() {
        return rate;
    }

    public ArrayList<Card> getPlayersCard() {
        return playersCard;
    }

    public HashMap<Integer, Country> getPlayersCountry() {
        return playersCountry;
    }

    public int getNumberOfSoldiers() {
        return numberOfSoldiers;
    }

    public ArrayList<Massage> getMassages() {
        return massages;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public void setNumbersOfDaysSinceRegistration(int numbersOfDaysSinceRegistration) {
        this.numbersOfDaysSinceRegistration = numbersOfDaysSinceRegistration;
    }


    public void setRate(double rate) {
        this.rate = rate;
    }

    public void setNumberOfSoldiers(int numberOfSoldiers) {
        this.numberOfSoldiers = numberOfSoldiers;
    }

    public HashMap<Integer, GameLog> getGameLogs() {
        return gameLogs;
    }

    public ArrayList<Player> getFriends() {
        return friends;
    }


    public ArrayList<Player> getRequestsForFriendShips() {
        return requestsForFriendShips;
    }

    public HashMap<Integer, RequestForPlaying> getRequestForPlaysList() {
        return requestForPlaysList;
    }


    public int getNumbersOfWin() {
        return numbersOfWin;
    }

    public void setNumbersOfWin(int numbersOfWin) {
        this.numbersOfWin = numbersOfWin;
    }

    public int getNumbersOfGames() {
        return numbersOfGames;
    }

    public void setNumbersOfGames(int numbersOfGames) {
        this.numbersOfGames = numbersOfGames;
    }

    public Player getFriendInGame() {
        return friendInGame;
    }

    public void setFriendInGame(Player friendInGame) {
        this.friendInGame = friendInGame;
    }

    public ArrayList<Player> getRequestForFriendShipInGame() {
        return requestForFriendShipInGame;
    }

    public Color getCurrentColor() {
        return currentColor;
    }

    public void setCurrentColor(Color currentColor) {
        this.currentColor = currentColor;
    }

    public ArrayList<Massage> getAdminMassages() {
        return adminMassages;
    }
}
