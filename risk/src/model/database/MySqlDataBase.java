package model.database;

import model.usersModels.Admin;
import model.usersModels.User;

import java.sql.*;

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
            }
            statement.close();
            System.out.println("All users has added from database");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void addNewUserToDataBase(String firstName , String lastName , String username , String password , String emailAddress , String telephoneNumber , boolean isAdmin){
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO `users` (`user_id`, `first name`, `last name`, `username`, `password`, `email address`, `telephone number`, `isAdmin`) VALUES (NULL, ? ,?, ?, ?, ?, ?, ?);");
            statement.setString(1 , firstName);
            statement.setString(2, lastName);
            statement.setString(3 , username);
            statement.setString(4, password);
            statement.setString(5 , emailAddress);
            statement.setString(6, telephoneNumber);
            statement.setBoolean(7 , isAdmin);
            statement.execute();
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

}
