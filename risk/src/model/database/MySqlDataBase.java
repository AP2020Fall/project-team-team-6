package model.database;

import model.usersModels.Admin;
import model.usersModels.Player;
import model.usersModels.User;

import java.sql.*;
import java.time.LocalDate;

public class MySqlDataBase {
    private Connection connection;
    private static MySqlDataBase mySqlDataBase = new MySqlDataBase();
    private LocalDataBase dataBase = LocalDataBase.getLocalDataBase();

    private MySqlDataBase() {
        this.connection = creatConnection();
        getUsersInfo();
    }

    public  Connection getConnection() {
        return connection;
    }

    public static MySqlDataBase getMySqlDataBase() {
        return mySqlDataBase;
    }

    private  Connection creatConnection(){
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/plato" , "root" , "root");
            System.out.println("Database is connected successfully");
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
                    //TODO implement all other players infos
                    dataBase.getAllPlayers().add(player);
                }
            }
            statement.close();
            System.out.println("All users has added from database");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void addNewUserToDataBase(String firstName , String lastName , String username , String password , String emailAddress , String telephoneNumber , boolean isAdmin){
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
                    int id = resultSet.getInt(1);
                    LocalDate localDate = LocalDate.now();
                    addNewPlayerToDataBae(id , localDate.toString());
                }
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserFromDataBase(int id){
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM `users` WHERE `users`.`user_id` = ?");
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



}
