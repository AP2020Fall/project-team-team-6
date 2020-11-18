package controller;

import model.Admin;
import model.DataBase;
import model.Player;
import model.User;

public class UserController {
    private static UserController userController = new UserController();
    private DataBase dataBase;

    private UserController() {
        dataBase = DataBase.getDataBase();
    }



    //Users Methods
    //-----------------------------------------------------------------------------------------------------------------
    public Admin getAdmin(){
        return dataBase.getAdmin();
    }
    public void setAdmin(String firstName , String lastName , String username ,
                         String password , String emailAddress ,String telephoneNumber){
        Admin admin = new Admin(firstName , lastName , username , password , emailAddress , telephoneNumber);
        dataBase.setAdmin(admin);
    }
    public void signUp(String firstName , String lastName , String username ,
                       String password , String emailAddress ,String telephoneNumber){
        Player player = new Player(firstName , lastName , username , password , emailAddress , telephoneNumber);
        dataBase.getAllPlayersWithID().put(player.getPlayerID(),player);
    }
    public User findUserByUsername(String userName) throws Exception{
        return null;
        //TODO
    }
    public void changeFirstName(String username , String newFirstName) throws Exception {
        User user = findUserByUsername(username);
        user.setFirstName(newFirstName);
    }
    public void changeLastName(String username , String newLastName) throws Exception {
        User user = findUserByUsername(username);
        user.setLastName(newLastName);
    }
    public void changeTelephoneNumber(String username , String newTelephoneNumber) throws Exception {
        User user = findUserByUsername(username);
        user.setTelephoneNumber(newTelephoneNumber);
    }
    public void changeEmailAddress(String username , String newEmailAddress) throws Exception {
        User user = findUserByUsername(username);
        user.setEmailAddress(newEmailAddress);
    }
    public void changePassword(String username , String newPassword , String confirmPassword) throws Exception {
        User user = findUserByUsername(username);
        if(checkPassword(username , newPassword)){

        }else{
            throw  new Exception("Password is incorrect.");
        }
        if (newPassword.equals(confirmPassword)){

        }else{
            throw new Exception("Passwords didn't match.") ;
        }
        user.setPassword(newPassword);
    }
    public void changeUsername(String username , String newUsername) throws Exception {
        User user = findUserByUsername(username);
        if(checkUsername(username)){
            user.setUsername(newUsername);
        }else{
            throw new Exception("This username has already taken.");
        }
    }
    public User login(String username , String password) throws Exception {
        if(!checkUsername(username)){
            if(checkPassword(username , password)){
                return findUserByUsername(username);
            }else{
                throw new Exception("Username or Password are incorrect.");
            }
        }else{
            throw new Exception("Username or Password are incorrect.");
        }
        //TODO
    }

    public void logout(){
        //TODO .....
    }

    public boolean checkUsername(String username){
        //Todo this will check if username exist or not if exits then return false;
        return true;
    }

    public boolean checkPassword(String userName,String password){
        //TODO
        return true;
    }



    //Players Methods
    //-----------------------------------------------------------------------------------------------------------------


    //Method for Event

    



}
