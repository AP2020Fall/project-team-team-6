package controller;

import model.*;

import java.util.ArrayList;
import java.util.HashMap;

public class UserController {
    private static UserController userController = new UserController();
    private static EventController eventController = EventController.getEventController();
    private static GameController gameController = GameController.getGameController();
    private DataBase dataBase;

    private UserController() {
        dataBase = DataBase.getDataBase();
    }

    public static UserController getUserController() {
        return userController;
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
    public User signUp(String firstName , String lastName , String username ,
                       String password , String emailAddress ,String telephoneNumber){
        Player player = new Player(firstName , lastName , username , password , emailAddress , telephoneNumber);
        dataBase.getAllPlayersWithID().put(player.getPlayerID(),player);
        dataBase.getAllUsers().add(player);
        return player;
    }
    public User findUserByUsername(String userName) {
        ArrayList<User> allUsers = dataBase.getAllUsers();
        for(User user : allUsers) {
            if (user.getUsername().equals(userName))
                return user;
        }
        return null;
    }
    public void changeFirstName(String username , String newFirstName) {
        User user = findUserByUsername(username);
        user.setFirstName(newFirstName);
    }
    public void changeLastName(String username , String newLastName) {
        User user = findUserByUsername(username);
        user.setLastName(newLastName);
    }
    public void changeTelephoneNumber(String username , String newTelephoneNumber) {
        User user = findUserByUsername(username);
        user.setTelephoneNumber(newTelephoneNumber);
    }
    public void changeEmailAddress(String username , String newEmailAddress) {
        User user = findUserByUsername(username);
        user.setEmailAddress(newEmailAddress);
    }
    public void changePassword(String username , String newPassword) {
        User user = findUserByUsername(username);
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
        boolean isPasswordCorrect = checkPassword(username , password);
        if(isPasswordCorrect){
            return findUserByUsername(username);
        }else{
            throw new Exception("Username or password are incorrect ! ");
        }
    }

    public void logout(){
        //TODO .....
    }

    public boolean checkUsername(String username){
        ArrayList<User> allUsers = dataBase.getAllUsers();
        for(User user : allUsers) {
            if (user.getUsername().equals(username))
                return false;
        }
        return true;
    }

    public boolean checkPassword(String userName,String password){
        User user = findUserByUsername(userName);
        if(user == null)
            return false;
        else{
            return password.equals(user.getPassword());
        }
    }

    //End Users Methods
    //-----------------------------------------------------------------------------------------------------------------


    //Players Methods
    //-----------------------------------------------------------------------------------------------------------------
    public void sendFriendRequest(Player firstPlayer , String secondPlayer){
        //TODO .....
    }
    public HashMap<Integer, Player> getPlayersFriends(Player player){
        return player.getFriends();
    }
    public ArrayList<Player> getFriendsRequestsList(Player player){
        return player.getRequestsForFriendShips();
    }
    public void rejectFriend(Player player , Player secondPlayer){
        //TODO
    }

    public void acceptFriend(Player player , Player secondPlayer){
        //TODO ....
    }

    public void removePlayerFromFriendsList(Player firstPlayer , Player secondPlayer){
        //TODO ......
    }

    public void inviteToPlay(Player player , String username , RiskGame riskGame){
        //TODO ....
    }

    public void acceptInvitation(Player player , RequestForPlaying requestForPlaying){
        //TODO ....
    }
    public void rejectInvitation(Player player , RequestForPlaying requestForPlaying){
        //TODO ....
    }
    public HashMap<Integer , Player> getFriendsList(Player player){
        HashMap<Integer, Player> friendsList = new HashMap<>();
        ArrayList<Player> friends = player.getRequestsForFriendShips();
        for( int i = 2; i <= friends.size()+1; i++){
            friendsList.put(i ,friends.get(i-2));
        }
        return friendsList;
    }

    public HashMap<Integer,GameLog> getAllGamesLogForPlayer(Player player){
        return player.getGameLogs();
    }

    public double getPlayersCredit(Player player){
        return player.getCredit();
    }
    public double getPlayersRate(Player player){
        return player.getRate();
    }
    public void addCreditForPlayer(Player player , double credit){
        //TODO ....
    }
    public void reduceCreditForPlayer(Player player , double credit){
        //TODO .....
    }

    //new player methods
//----------------------------------------------------------------------------------------------------------------

    public void acceptEvent(Event event) {
        //ToDo
    }

    public void rejectEvent(Event event) {
        //ToDo
    }

    public void sendMessage(Player player1, Player player2) {
        //ToDo
    }

    public ArrayList<String> showAllMessages(Player player) {
        return null;
    }

    public ArrayList<String> showSentMessages(Player player) {
        return null;
    }

    public ArrayList<String> showReceivedMessages(Player player) {
        return null;
    }

    public int getNumbersOfWin(Player player){
        //TODO

        return 0;
    }

    public int getNumbersOfPlayer(Player player){
        //TODO


        return 0;
    }

    public void sendRequestForFriendShipInGame(Player firstPlayer , Player secondPlayer , RiskGame riskGame){
        //TODO .....
    }
    public void acceptFriendShipInGame(Player firstPlayer , Player secondPlayer){
        //TODO.....
    }
    public void rejectFriendShipInGame(Player firstPlayer , Player secondPlayer){

    }
    public void endFriendShipInGame(Player player){

    }


    //Admin methods
//------------------------------------------------------------------------------------------------------------------
    public void sendMessageToAllPlayers() {
        //ToDo
    }

    public void sendMessageToChosenPlayers(ArrayList<Player> chosenPlayers) {
        //ToDo
    }
}
