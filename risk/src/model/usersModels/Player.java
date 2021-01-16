package model.usersModels;

import controller.UserController;
import model.database.LocalDataBase;
import model.gamesModels.Card;
import model.gamesModels.CardDesigns;
import model.gamesModels.Color;
import model.gamesModels.Country;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Player extends User {
    private LocalDate localDate;
    private int numbersOfDaysSinceRegistration;
    private double rate;
    private ArrayList<GameLog> gameLogs;
    private ArrayList<Player> friends;
    private ArrayList<Player> requestsForFriendShips;
    private ArrayList<RequestForPlaying> requestForPlaysList;
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
        this.localDate = LocalDate.now();
        this.rate = 0.0;
        this.gameLogs = new ArrayList<>();
        this.friends = new ArrayList<>();
        this.requestsForFriendShips = new ArrayList<>();
        this.requestForPlaysList = new ArrayList<>();
        this.playersCard = new ArrayList<>();
        this.playersCountry = new HashMap<>();
        this.massages = new ArrayList<>();
        this.requestForFriendShipInGame = new ArrayList<>();
        this.friendInGame = null;
        this.currentColor = null;
        this.adminMassages = new ArrayList<>();
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

    public ArrayList<GameLog> getGameLogs() {
        return gameLogs;
    }

    public ArrayList<Player> getFriends() {
        return friends;
    }


    public ArrayList<Player> getRequestsForFriendShips() {
        return requestsForFriendShips;
    }

    public ArrayList<RequestForPlaying> getRequestForPlaysList() {
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

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void calculateRegisterDate(String date) {
        String[] dates = date.split("-");
        int year = Integer.parseInt(dates[0]);
        int month = Integer.parseInt(dates[1]);
        int days = Integer.parseInt(dates[2]);
        localDate = LocalDate.of(year, month, days);
        numbersOfDaysSinceRegistration = LocalDate.now().compareTo(localDate);
    }

    public String changeFriendsToString() {
        String friendsInString = "";
        for (Player player : friends) {
            friendsInString = player.getUsername() + "\r\n";
        }
        return friendsInString;
    }

    public void getPlayersFriendsFromString(String friendsInString) {
        String[] friendsInArray = friendsInString.split("\r\n");
        for (String p : friendsInArray) {
            Player player = UserController.getUserController().findPlayerByUserName(p);
            if (player != null)
                friends.add(player);
        }
    }

    public String changeFriendsRequestToString() {
        String friendsInString = "";
        for (Player player : requestsForFriendShips) {
            friendsInString = player.getUsername() + "\r\n";
        }
        return friendsInString;
    }

    public void getPlayersFriendsRequestsFromString(String friendsInString) {
        String[] friendsInArray = friendsInString.split("\r\n");
        for (String p : friendsInArray) {
            Player player = UserController.getUserController().findPlayerByUserName(p);
            if (player != null)
                getRequestsForFriendShips().add(player);
        }
    }

    public String changePlayerCardsIntoString() {
        StringBuilder cardsInString = new StringBuilder();
        for (Card card : playersCard) {
            cardsInString.append(card.changeCardInformationToString());
        }
        return cardsInString.toString();
    }

    public void getPlayersCardsFromString(String cards) {
        String[] cardsInArray = cards.split("\r\n");
        for (String p : cardsInArray) {
            Card card = Card.getCardFromString(p);
            if (card != null)
                playersCard.add(card);
        }
    }

    public String getAllPlayerMessagesInString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Massage massage : massages) {
            stringBuilder.append(Massage.changeMessageToString(massage)).append("\n");
        }
        return stringBuilder.toString();
    }

    public void getAllMessageFromString(String text) {
        String[] allMessagesInArray = text.split("\n");
        for (String s : allMessagesInArray) {
            Massage massage = Massage.changeMessageFromStringToMessage(s);
            if (massage != null)
                this.massages.add(massage);
        }
    }
    public String changeGameLogsToString(){
        StringBuilder stringBuilder = new StringBuilder();
        for (GameLog gameLog : gameLogs) {
            stringBuilder.append(gameLog.changeGameLogToString(gameLog)).append("\n");
        }
        return stringBuilder.toString();
    }
    public void getAllGameLogsFromString(String text){
        String[] allGameLogsInArray = text.split("\n");
        for(String s: allGameLogsInArray){
            GameLog gameLog = GameLog.changeStringToGameLog(s);
            if(gameLog != null){
                gameLogs.add(gameLog);
            }
        }
    }

}
