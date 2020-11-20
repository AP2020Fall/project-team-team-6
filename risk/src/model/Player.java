package model;

import java.util.ArrayList;
import java.util.HashMap;

public class  Player extends User{
    private int playerID;
    private int numbersOfDaysSinceRegistration;
    private double credit;
    private double rate;
    private HashMap<Integer , GameLog> gameLogs;
    private HashMap<Integer , Player> friends;
    private HashMap<Integer , Player> requestsForFriendShips;
    private HashMap<Integer , RequestForPlaying> requestForPlaysList;
    private ArrayList<Card> playersCard;
    private ArrayList<Country> playersCountry;
    private int numberOfSoldiers = 0;
    private ArrayList<String> sentMessages;
    private ArrayList<String> receivedMessages;
    private ArrayList<String> allMessages;
    private int numbersOfWin;
    private int numbersOfGames;

    public Player(String firstName, String lastName,
                  String username, String password,
                  String emailAddress, String telephoneNumber) {
        super(firstName, lastName, username, password, emailAddress, telephoneNumber);

        this.numbersOfDaysSinceRegistration = 0;
        this.credit = 0.0;
        this.numbersOfGames= 0;
        this.numbersOfWin = 0;
        this.rate = 0.0;
        this.gameLogs = new HashMap<>();
        this.friends = new HashMap<>();
        this.requestsForFriendShips = new HashMap<>();
        this.requestForPlaysList = new HashMap<>();
        this.playersCard = new ArrayList<>();
        this.playersCountry = new ArrayList<>();
        this.sentMessages = new ArrayList<>();
        this.receivedMessages = new ArrayList<>();
        this.allMessages = new ArrayList<>();
        setId();
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

    public double getCredit() {
        return credit;
    }

    public double getRate() {
        return rate;
    }

    public ArrayList<Card> getPlayersCard() {
        return playersCard;
    }

    public ArrayList<Country> getPlayersCountry() {
        return playersCountry;
    }

    public int getNumberOfSoldiers() {
        return numberOfSoldiers;
    }

    public ArrayList<String> getSentMessages() {
        return sentMessages;
    }

    public ArrayList<String> getReceivedMessages() {
        return receivedMessages;
    }

    public ArrayList<String> getAllMessages() {
        return allMessages;
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

    public void setCredit(double credit) {
        this.credit = credit;
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

    public HashMap<Integer, Player> getFriends() {
        return friends;
    }

    public int getPlayerID() {
        return playerID;
    }

    public HashMap<Integer, Player> getRequestsForFriendShips() {
        return requestsForFriendShips;
    }

    public HashMap<Integer, RequestForPlaying> getRequestForPlaysList() {
        return requestForPlaysList;
    }

    public void setId(){
        this.playerID = DataBase.getDataBase().getAllPlayersWithID().size()+1;
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


}
