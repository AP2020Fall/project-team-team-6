package model.database;

import controller.UserController;
import model.usersModels.Admin;
import model.usersModels.Player;
import model.usersModels.User;

import java.sql.*;
import java.time.LocalDate;

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
    }

    public  Connection getConnection() {
        return connection;
    }


    private  Connection creatConnection(){
//        try {
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/plato" , "root" , "root");
//            System.out.println("Database is connected successfully");
//            return connection;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        return null;
    }
    public void getUsersInfo(){

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("user_id");
                String firstName = resultSet.getString("first name");
                String lastName = resultSet.getString("last name");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String emailAddress = resultSet.getString("email address");
                String telephoneNumber = resultSet.getString("telephone number");
                boolean isAdmin = resultSet.getBoolean("isAdmin");
                User user = new User(firstName , lastName , username ,  password , emailAddress , telephoneNumber , isAdmin);
                user.setID(id);
                dataBase.getAllUsers().add(user);
                if(isAdmin)
                    dataBase.setAdmin(new Admin(firstName , lastName , username , password , emailAddress , telephoneNumber ));
                else{
                    Player player = new Player(firstName , lastName , username , password , emailAddress , telephoneNumber);
                    player.setID(id);
                    //TODO implement all other players info
                    dataBase.getAllPlayers().add(player);
                }
            }
            statement.close();
            System.out.println("All users has added from database");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public int addNewUserToDataBase(String firstName , String lastName , String username , String password , String emailAddress , String telephoneNumber , boolean isAdmin){
        int id = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO `users` (`user_id`, `first name`, `last name`, `username`, `password`, `email address`, `telephone number`, `isAdmin`) VALUES (NULL, ? ,?, ?, ?, ?, ?, ?)" , Statement.RETURN_GENERATED_KEYS);
            statement.setString(1 , firstName);
            statement.setString(2, lastName);
            statement.setString(3 , username);
            statement.setString(4, password);
            statement.setString(5 , emailAddress);
            statement.setString(6, telephoneNumber);
            statement.setBoolean(7 , isAdmin);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if(!isAdmin) {
                while (resultSet.next()) {
                    id = resultSet.getInt(1);
                    LocalDate localDate = LocalDate.now();
                    addNewPlayerToDataBae(id , localDate.toString());
                }
            }else{
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

    public void removeUserFromDataBase(int id){
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM `users` WHERE `users`.`user_id` = ?");
            statement.setInt(1 , id);
            statement.execute();
            statement = connection.prepareStatement("DELETE FROM `players` WHERE `players`.`player_id` = ?");
            statement.setInt(1 , id);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    private void addNewPlayerToDataBae(int id , String date){
       try {
           PreparedStatement statement = connection.prepareStatement("INSERT INTO `players` (`player_id`, `register date`, `rate`, `game logs`, `friends`, `friend requests`, `play requests`, `cards`, `countires`, `number of soldiers`, `messages`, `admin messages`, `current color`, `number of win`, `number of game`, `ally in game`, `ally requests`) VALUES (?, ?, '0', '', '', '', '', '', '', '0', '', '', '', '0', '0', '', '')");
           statement.setInt(1 , id);
           statement.setString(2, date);
           statement.execute();
           statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void changeInfo(User user){
      String firstName = user.getFirstName();
      String lastName = user.getLastName();
      String username = user.getUsername();
      String password = user.getPassword();
      String emailAddress = user.getEmailAddress();
      String telephoneNumber = user.getTelephoneNumber();
      int id = user.getID();
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE `users` SET `first name` = ?, `last name` = ? ,`username` = ? , `password` = ? , `email address` = ? , `telephone number` = ?  WHERE `users`.`user_id` =?;");
            statement.setString(1 , firstName);
            statement.setString(2 , lastName);
            statement.setString(3 , username);
            statement.setString(4 , password);
            statement.setString(5 , emailAddress);
            statement.setString(6 , telephoneNumber);
            statement.setInt(7 , id);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void getPlayerInfo(){
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT  * FROM players");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int playerId = resultSet.getInt("player_id");
                Player player = UserController.getUserController().findPlayerById(playerId);
                if(player != null) {
                    String registerDate = resultSet.getString("register date");
                    player.calculateRegisterDate(registerDate);
                    String friendsInString = resultSet.getString("friends");
                    player.getPlayersFriendsFromString(friendsInString);
                    String requestForFriendShip = resultSet.getString("friend requests");
                    player.getPlayersFriendsRequestsFromString(requestForFriendShip);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void updatePlayer(Player player){
        int id = player.getID();
        String playersFriendInString = player.changeFriendsToString();
        String friendsRequests = player.changeFriendsRequestToString();
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE `players` SET  `friends` = ? , `friend requests` = ?  WHERE `players`.`player_id` =?;");
            statement.setString(1 ,playersFriendInString );
            statement.setString(2 , friendsRequests);
            statement.setInt(3 , id);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
