package server.model.usersModels;

import server.controller.UserController;
import server.model.gamesModels.Card;
import server.model.gamesModels.Color;
import server.model.gamesModels.Country;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Player extends User implements Comparable{
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
        this.requestForFriendShipInGame = new ArrayList<>();
        this.friendInGame = null;
        this.currentColor = null;
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
        StringBuilder stringBuilder = new StringBuilder();
        for (Player player : friends) {
            stringBuilder.append(player.getUsername()).append("%");
        }
        return stringBuilder.toString();
    }

    public void getPlayersFriendsFromString(String friendsInString) {
        String[] friendsInArray = friendsInString.split("%");
        for (String p : friendsInArray) {
            Player player = UserController.getUserController().findPlayerByUserName(p);
            if (player != null)
                friends.add(player);
        }
    }

    public String changeFriendsRequestToString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Player player : requestsForFriendShips) {
            stringBuilder.append(player.getUsername()).append("%");
        }
        return stringBuilder.toString();
    }

    public void getPlayersFriendsRequestsFromString(String friendsInString) {
        String[] friendsInArray = friendsInString.split("%");
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
        String[] cardsInArray = cards.split("%");
        for (String p : cardsInArray) {
            Card card = Card.getCardFromString(p);
            if (card != null)
                playersCard.add(card);
        }
    }


    public String changeGameLogsToString(){
        StringBuilder stringBuilder = new StringBuilder();
        for (GameLog gameLog : gameLogs) {
            stringBuilder.append(gameLog.changeGameLogToString(gameLog)).append("#");
        }
        return stringBuilder.toString();
    }
    public void getAllGameLogsFromString(String text){
        String[] allGameLogsInArray = text.split("#");
        for(String s: allGameLogsInArray){
            GameLog gameLog = GameLog.changeStringToGameLog(s);
            if(gameLog != null){
                gameLogs.add(gameLog);
            }
        }
    }

    @Override
    public int compareTo(Object o) {
       Player player = (Player) o;
       return (int) (this.rate - player.getRate());
    }

    public String changePlayerToString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("player$").append(ID).append("$").append(firstName).append("$").append(lastName).append("$");
        stringBuilder.append(username).append("$").append(password).append("$").append(emailAddress).append("$");
        stringBuilder.append(telephoneNumber).append("$").append(isAdmin).append("$");
        //This is for user information
        String friends = changeFriendsToString();
        stringBuilder.append(friends).append("$");
        //This is for friends
        String friendRequests = changeFriendsRequestToString();
        stringBuilder.append(friendRequests).append("$");
        //This is for game logs
        String gameLogs = changeGameLogsToString();
        stringBuilder.append(gameLogs).append("$");
        return String.valueOf(stringBuilder);
    }
    public static Player changePlayerFromString(String input){
        String[] inputs = input.split("\\$");
        int length = inputs.length - 1 ;
        int ID = Integer.parseInt(inputs[1]);
        String firstName = inputs[2];
        String lastName = inputs[3];
        String username = inputs[4];
        String password = inputs[5];
        String email = inputs[6];
        String telephoneNumber = inputs[7];
        boolean isAdmin = Boolean.parseBoolean(inputs[8]);
        Player player = new Player(firstName , lastName , username , password , email , telephoneNumber);
        if(length > 8) {
            String friendsInString = inputs[9];
            player.getPlayersFriendsFromString(friendsInString);
        }
        if(length > 9) {
            String friendRequestsInString = inputs[10];
            player.getPlayersFriendsRequestsFromString(friendRequestsInString);
        }
        if(length > 10) {
            String gameLogsInString = inputs[11];
            player.getAllGameLogsFromString(gameLogsInString);
        }
        player.setID(ID);

        return player;
    }
}
