package server.model.database;

import server.controller.UserController;
import server.model.gamesModels.Event;
import server.model.gamesModels.RiskGame;
import server.model.usersModels.Admin;
import server.model.usersModels.Player;
import server.model.usersModels.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class MySqlDataBase {
    private Connection connection;
    private static MySqlDataBase mySqlDataBase = new MySqlDataBase();
    private LocalDataBase dataBase = LocalDataBase.getLocalDataBase();

    public static MySqlDataBase getMySqlDataBase() {
        return mySqlDataBase;
    }

    private MySqlDataBase() {
        this.connection = creatConnection();
        getUsersInfo();
        getPlayerInfo();
        loadMessages();
        getEventsInfo();
    }

    public Connection getConnection() {
        return connection;
    }


    private Connection creatConnection() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/plato", "root", "root");
            System.out.println("Database is connected successfully");
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void getUsersInfo() {

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("user_id");
                String firstName = resultSet.getString("first name");
                String lastName = resultSet.getString("last name");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String emailAddress = resultSet.getString("email address");
                String telephoneNumber = resultSet.getString("telephone number");
                boolean isAdmin = resultSet.getBoolean("isAdmin");
                User user = new User(firstName, lastName, username, password, emailAddress, telephoneNumber, isAdmin);
                user.setID(id);
                dataBase.getAllUsers().add(user);
                if (isAdmin) {
                    Admin admin = new Admin(firstName, lastName, username, password, emailAddress, telephoneNumber);
                    admin.setID(id);
                    dataBase.setAdmin(admin);
                }
                else {
                    Player player = new Player(firstName, lastName, username, password, emailAddress, telephoneNumber);
                    player.setID(id);
                    dataBase.getAllPlayers().add(player);
                }
            }
            statement.close();
            System.out.println("All users has added from database");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void loadMessages(){
            try {
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM users");
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    String username = resultSet.getString("username");
                    String message = resultSet.getString("messages");
                    User user = UserController.getUserController().findUserByUsername(username);
                    if(user.isAdmin()){
                        Admin admin = dataBase.getAdmin();
                        admin.getAllMessageFromString(message);
                    }else{
                        Player player = UserController.getUserController().findPlayerByUserName(username);
                        player.getAllMessageFromString(message);
                    }
                }
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public int addNewUserToDataBase(String firstName, String lastName, String username, String password, String emailAddress, String telephoneNumber, boolean isAdmin) {
        int id = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO `users` (`user_id`, `first name`, `last name`, `username`, `password`, `email address`, `telephone number`, `isAdmin`, `messages`) VALUES (NULL, ? ,?, ?, ?, ?, ?, ? , ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, username);
            statement.setString(4, password);
            statement.setString(5, emailAddress);
            statement.setString(6, telephoneNumber);
            statement.setBoolean(7, isAdmin);
            statement.setString(8 , "");
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (!isAdmin) {
                while (resultSet.next()) {
                    id = resultSet.getInt(1);
                    LocalDate localDate = LocalDate.now();
                    addNewPlayerToDataBae(id, localDate.toString());
                }
            } else {
                while (resultSet.next())
                    id = resultSet.getInt(1);
            }
            statement.close();
            return id;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public void removeUserFromDataBase(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM `users` WHERE `users`.`user_id` = ?");
            statement.setInt(1, id);
            statement.execute();
            statement = connection.prepareStatement("DELETE FROM `players` WHERE `players`.`player_id` = ?");
            statement.setInt(1, id);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void addNewPlayerToDataBae(int id, String date) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO `players` (`player_id`, `register date`, `rate`, `game logs`, `friends`, `friend requests`, `play requests`, `cards`, `countires`, `number of soldiers`, `admin messages`, `current color`, `number of win`, `number of game`, `ally in game`, `ally requests`) VALUES (?, ?, '0', '', '', '', '', '', '', '0', '', '', '0', '0', '', '')");
            statement.setInt(1, id);
            statement.setString(2, date);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void changeInfo(User user) {
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String username = user.getUsername();
        String password = user.getPassword();
        String emailAddress = user.getEmailAddress();
        String telephoneNumber = user.getTelephoneNumber();
        int id = user.getID();
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE `users` SET `first name` = ?, `last name` = ? ,`username` = ? , `password` = ? , `email address` = ? , `telephone number` = ?  WHERE `users`.`user_id` =?;");
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, username);
            statement.setString(4, password);
            statement.setString(5, emailAddress);
            statement.setString(6, telephoneNumber);
            statement.setInt(7, id);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getPlayerInfo() {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT  * FROM players");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int playerId = resultSet.getInt("player_id");
                Player player = UserController.getUserController().findPlayerById(playerId);
                if (player != null) {
                    String registerDate = resultSet.getString("register date");
                    player.calculateRegisterDate(registerDate);
                    String friendsInString = resultSet.getString("friends");
                    player.getPlayersFriendsFromString(friendsInString);
                    String requestForFriendShip = resultSet.getString("friend requests");
                    player.getPlayersFriendsRequestsFromString(requestForFriendShip);
                    String cardsInString = resultSet.getString("cards");
                    player.getPlayersCardsFromString(cardsInString);
//                    String messagesInString = resultSet.getString("messages");
//                    player.getAllMessageFromString(messagesInString);
                    String gameLogsInString = resultSet.getString("game logs");
                    player.getAllGameLogsFromString(gameLogsInString);
                    int numberOfGame = resultSet.getInt("number of game");
                    player.setNumbersOfGames(numberOfGame);
                    int numberOfWin = resultSet.getInt("number of win");
                    player.setNumbersOfWin(numberOfWin);
                    double rate = resultSet.getDouble("rate");
                    player.setRate(rate);
                }
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateUser(User user){
        int id = user.getID();
        String messagesInString = user.getAllPlayerMessagesInString();
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE `users` SET  `messages` = ?  WHERE `users`.`user_id` =?;");
            statement.setString(1 , messagesInString);
            statement.setInt(2 , id);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePlayer(Player player) {
        int id = player.getID();
        String playersFriendInString = player.changeFriendsToString();
        String friendsRequests = player.changeFriendsRequestToString();
        String playersCardsInString = player.changePlayerCardsIntoString();
        String gameLogs = player.changeGameLogsToString();
        int numberOfWin = player.getNumbersOfWin();
        int numberOfGames = player.getNumbersOfGames();
        double rate = player.getRate();
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE `players` SET  `friends` = ? , `friend requests` = ? , `cards` = ?  ,`game logs` = ? ,`number of win` = ? ,`number of game` = ? ,`rate` = ? WHERE `players`.`player_id` =?;");
            statement.setString(1, playersFriendInString);
            statement.setString(2, friendsRequests);
            statement.setString(3, playersCardsInString);
            statement.setString(4 , gameLogs);
            statement.setInt(5 , numberOfWin);
            statement.setInt(6 , numberOfGames);
            statement.setDouble(7 , rate);
            statement.setInt(8, id);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addNewEventToDataBase(Event event) {
        String startDate = String.valueOf(event.getStartDate());
        String endDate = String.valueOf(event.getEndDate());
        double point = event.getEventPoint();
        StringBuilder players = new StringBuilder();
        boolean isPublic = event.isPublic();
        for (Player player : event.getInvitedPlayers()) {
            players.append(player.getUsername()).append(",");
        }
        String playersInString = String.valueOf(players);
        String riskGame = RiskGame.changeOnlineRiskInformationToString(event.getGame());
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO `events` (`event_Id`, `start date`, `end date`, `event point`, `risk game`, `players` , `isPublic`) VALUES (NULL, ?, ?, ?, ?, ? , ?);", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, startDate);
            statement.setString(2, endDate);
            statement.setDouble(3, point);
            statement.setString(4, riskGame);
            statement.setString(5, playersInString);
            statement.setBoolean(6, isPublic);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                event.setEventID(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getEventsInfo() {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM events");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int eventID = resultSet.getInt("event_id");
                LocalDate startDate = LocalDate.parse(resultSet.getString("start date"));
                LocalDate endDate = LocalDate.parse(resultSet.getString("end date"));
                double eventPoint = resultSet.getDouble("event point");
                RiskGame riskGame = RiskGame.getOnlineRiskFromString(resultSet.getString("risk game"));
                boolean isPublic = resultSet.getBoolean("isPublic");
                if (!isPublic) {
                    ArrayList<Player> players = Event.getPlayersFromString(resultSet.getString("players"));
                    Event event = new Event(startDate, endDate, riskGame, players);
                    event.setEventID(eventID);
                    dataBase.getAllEvents().add(event);
                } else {
                    Event event = new Event(startDate, endDate, riskGame);
                    event.setEventID(eventID);
                    event.setInvitedPlayers(dataBase.getAllPlayers());
                    dataBase.getAllEvents().add(event);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
