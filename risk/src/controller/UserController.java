
package controller;
import model.database.LocalDataBase;
import model.database.MySqlDataBase;
import model.gamesModels.Event;
import model.gamesModels.RiskGame;
import model.usersModels.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class UserController {
    private static UserController userController = new UserController();
    private static EventController eventController = EventController.getEventController();
    private static GameController gameController = GameController.getGameController();
    private  LocalDataBase localDataBase;

    private UserController() {
        this.localDataBase = LocalDataBase.getLocalDataBase();
    }

    public static UserController getUserController() {
        return userController;
    }

    //Users Methods
    //-----------------------------------------------------------------------------------------------------------------
    //TODO is admin is not complete
    public Admin getAdmin() {
        return localDataBase.getAdmin();
    }

    public Admin signUpAsAdmin(String firstName, String lastName, String username,
                               String password, String emailAddress, String telephoneNumber) {
        Admin admin = new Admin(firstName, lastName, username, password, emailAddress, telephoneNumber);
        localDataBase.getAllUsers().add(admin);
        localDataBase.setAdmin(admin);
        int id = MySqlDataBase.getMySqlDataBase().addNewUserToDataBase(firstName , lastName , username , password , emailAddress , telephoneNumber , true);
        admin.setID(id);
        return admin;
    }

    //TODO is admin is not complete
    public User signUpAsPlayer(String firstName, String lastName, String username,
                               String password, String emailAddress, String telephoneNumber) {
        Player player = new Player(firstName, lastName, username, password, emailAddress, telephoneNumber);
        localDataBase.getAllPlayers().add(player);
        localDataBase.getAllUsers().add(player);
        int id = MySqlDataBase.getMySqlDataBase().addNewUserToDataBase(firstName , lastName , username , password , emailAddress , telephoneNumber , false);
        player.setID(id);
        return player;
    }

    public Player getPlayerByUsername(String username) throws Exception {
        ArrayList<Player> allPlayers = localDataBase.getAllPlayers();
        for (Player player : allPlayers) {
            if (player.getUsername().equals(username))
                return player;
        }
        throw new Exception("There is no user by that username");
    }

    public User findUserByUsername(String userName) {
        ArrayList<User> allUsers = localDataBase.getAllUsers();
        for (User user : allUsers) {
            if (user.getUsername().equals(userName))
                return user;
        }
        return null;
    }

    public Player findPlayerByUserName(String userName) {
        ArrayList<Player> allPlayers = localDataBase.getAllPlayers();
        for (Player player : allPlayers) {
            if (player.getUsername().equals(userName))
                return player;
        }
        return null;
    }

    public void acceptFriendShip(Player player, Player secondPlayer) throws Exception {
        ArrayList<Player> requestedPlayers = player.getRequestsForFriendShips();
        if (requestedPlayers.contains(secondPlayer)) {
            player.getFriends().add(secondPlayer);
            secondPlayer.getFriends().add(player);
            player.getRequestsForFriendShips().remove(secondPlayer);
            MySqlDataBase.getMySqlDataBase().updatePlayer(player);
            MySqlDataBase.getMySqlDataBase().updatePlayer(secondPlayer);
        } else {
            throw new Exception("Error player request");
        }
    }

    public void changeFirstName(String username, String newFirstName) {
        User user = findUserByUsername(username);
        user.setFirstName(newFirstName);
        MySqlDataBase.getMySqlDataBase().changeInfo(user);
    }

    public void changeLastName(String username, String newLastName) {
        User user = findUserByUsername(username);
        user.setLastName(newLastName);
        MySqlDataBase.getMySqlDataBase().changeInfo(user);
    }

    public void changeTelephoneNumber(String username, String newTelephoneNumber) {
        User user = findUserByUsername(username);
        user.setTelephoneNumber(newTelephoneNumber);
        MySqlDataBase.getMySqlDataBase().changeInfo(user);
    }

    public void changeEmailAddress(String username, String newEmailAddress) {
        User user = findUserByUsername(username);
        user.setEmailAddress(newEmailAddress);
        MySqlDataBase.getMySqlDataBase().changeInfo(user);
    }

    public void changePassword(String username, String newPassword) {
        User user = findUserByUsername(username);
        user.setPassword(newPassword);
        MySqlDataBase.getMySqlDataBase().changeInfo(user);
    }

    public void changeUsername(String username, String newUsername) throws Exception {
        User user = findUserByUsername(username);
        if (checkUsername(newUsername)) {
            user.setUsername(newUsername);
            MySqlDataBase.getMySqlDataBase().changeInfo(user);
        } else {
            throw new Exception("This username has already taken.");
        }
    }

    public User login(String username, String password) throws Exception {
        boolean isPasswordCorrect = checkPassword(username, password);
        if (isPasswordCorrect) {
            return findUserByUsername(username);
        } else {
            throw new Exception("Username or password are incorrect ! ");
        }
    }

    public boolean checkUsername(String username) {
        ArrayList<User> allUsers = localDataBase.getAllUsers();
        for (User user : allUsers) {
            if (user.getUsername().equals(username))
                return false;
        }
        return true;
    }

    public boolean checkPassword(String userName, String password) {
        User user = findUserByUsername(userName);
        if (user == null)
            return false;
        else {
            return password.equals(user.getPassword());
        }
    }


    public void deletePlayer(Player player) {
        localDataBase.getAllPlayers().remove(player);
        User user = findUserByUsername(player.getUsername());
        localDataBase.getAllUsers().remove(user);
        MySqlDataBase.getMySqlDataBase().removeUserFromDataBase(player.getID());
    }

    public Player findPlayerById(int Id){
        ArrayList<Player> allPlayers = localDataBase.getAllPlayers();
        for(Player player : allPlayers){
            if(player.getID() == Id)
                return player;
        }
        return null;
    }

    //End Users Methods
    //-----------------------------------------------------------------------------------------------------------------


    //Players Methods
    //-----------------------------------------------------------------------------------------------------------------
    public void sendFriendRequest(Player firstPlayer, String secondPlayer) {
        //TODO .....
    }

    public HashMap<Integer, Player> getPlayersFriends(Player player) {
        ArrayList<Player> friendsInArray = player.getFriends();
        HashMap<Integer, Player> friendsInHashMap = new HashMap<>();
        for (int i = 2; i <= friendsInArray.size() + 1; i++) {
            friendsInHashMap.put(i, friendsInArray.get(i - 2));
        }
        return friendsInHashMap;
    }

    public ArrayList<Player> getFriendsRequestsList(Player player) {
        return player.getRequestsForFriendShips();
    }

    public void rejectFriend(Player player, Player secondPlayer) throws Exception {
        ArrayList<Player> requestedPlayer = player.getRequestsForFriendShips();
        if (requestedPlayer.contains(secondPlayer)) {
            player.getRequestsForFriendShips().remove(secondPlayer);
        } else {
            throw new Exception("Error friend Request");
        }
    }


    public void inviteToPlay(Player player, String username, RiskGame riskGame) {
        //TODO ....
    }

    public void acceptInvitation(Player player, RequestForPlaying requestForPlaying) {
        //TODO ....
    }

    public void rejectInvitation(Player player, RequestForPlaying requestForPlaying) {
        //TODO ....
    }

    public HashMap<Integer, Player> getFriendsList(Player player) {
        HashMap<Integer, Player> friendsList = new HashMap<>();
        ArrayList<Player> friends = player.getRequestsForFriendShips();
        for (int i = 2; i <= friends.size() + 1; i++) {
            friendsList.put(i, friends.get(i - 2));
        }
        return friendsList;
    }

    public ArrayList<GameLog> getAllGamesLogForPlayer(Player player) {
        return player.getGameLogs();
    }


    public double getPlayersRate(Player player) {
        return player.getRate();
    }

    public void addCreditForPlayer(Player player, double credit) {
        //TODO ....
    }

    public void reduceCreditForPlayer(Player player, double credit) {
        //TODO .....
    }

    public ArrayList<Player> getAllPlayers() {
        return localDataBase.getAllPlayers();
    }

    //new player methods
//----------------------------------------------------------------------------------------------------------------

    public void acceptEvent(Event event) {
        //ToDo
    }

    public void rejectEvent(Event event) {
        //ToDo
    }

    public void sendMessage(Player sender, Player receiver, String massageText) {
        Massage massage = new Massage(massageText, sender, receiver);
        sender.getMassages().add(massage);
        receiver.getMassages().add(massage);
    }

    public void sendMessageFromAdmin(Admin sender, Player receiver, String massageText) {
        Massage massage = new Massage(massageText, sender, receiver);
        receiver.getAdminMassages().add(massage);
    }

    public ArrayList<Massage> getPlayersMassage(Player currentPlayer, Player sender) {
        ArrayList<Massage> massages = new ArrayList<>();
        ArrayList<Massage> playersMassages = currentPlayer.getMassages();
        for (Massage massage : playersMassages) {
            if (massage.getReceiver().equals(sender) || massage.getSender().equals(sender)) {
                massages.add(massage);
            }
        }
        return massages;
    }

    public HashSet<Player> getAllPlayersThatHadMessageWith(Player currentPlayer) {
        ArrayList<Massage> allMassages = currentPlayer.getMassages();
        HashSet<Player> players = new HashSet<>();
        for (Massage massage : allMassages) {
            Player sender = massage.getSender();
            Player receiver = massage.getReceiver();
            if (!sender.equals(currentPlayer))
                players.add(sender);
            else {
                players.add(receiver);
            }
        }
        return players;
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

    public int getNumbersOfWin(Player player) {
        //TODO

        return 0;
    }

    public int getNumbersOfPlayer(Player player) {
        //TODO


        return 0;
    }

    public void sendRequestForFriendShipInGame(Player firstPlayer, Player secondPlayer, RiskGame riskGame) {
        //TODO .....
    }

    public void acceptFriendShipInGame(Player firstPlayer, Player secondPlayer) {
        //TODO.....
    }

    public void rejectFriendShipInGame(Player firstPlayer, Player secondPlayer) {

    }

    public void endFriendShipInGame(Player player) {

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

