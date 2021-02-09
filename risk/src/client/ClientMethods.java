package client;

import server.model.usersModels.Admin;
import server.model.usersModels.Player;
import server.model.usersModels.User;

import java.io.IOException;
import java.util.ArrayList;

public class ClientMethods {
    private static ClientMethods clientMethods;

    public static ClientMethods getClientMethods() {
        if(clientMethods == null){
            clientMethods = new ClientMethods();
        }
        return clientMethods;
    }

    private ClientMethods() {

    }

    public static boolean checkUsername(String input) throws IOException {
        Client.getDataOutputStream().writeUTF(input);
        Client.getDataOutputStream().flush();
        String answer = Client.getDataInputStream().readUTF();
        return Boolean.parseBoolean(answer);
    }

    public static boolean haveAdmin(String input) throws IOException {
        Client.getDataOutputStream().writeUTF(input);
        Client.getDataOutputStream().flush();
        String answer = Client.getDataInputStream().readUTF();
        return Boolean.parseBoolean(answer);
    }

    public User login(String input) throws IOException {
        Client.getDataOutputStream().writeUTF(input);
        Client.getDataOutputStream().flush();
        String answer = Client.getDataInputStream().readUTF();
        if(answer.startsWith("user$")){
            return  User.changeUserFromString(answer);
        }else{
            return null;
        }
    }
    public void updateUser(User user) throws IOException {
        String userInString = user.changeUserToString();
        Client.getDataOutputStream().writeUTF(userInString);
        Client.getDataOutputStream().flush();
        String answer = Client.getDataInputStream().readUTF();
        //TODO
    }

    public Player findPlayerByUsername(String input) throws IOException {
        Client.getDataOutputStream().writeUTF(input);
        Client.getDataOutputStream().flush();
        String answer = Client.getDataInputStream().readUTF();
        if(answer.startsWith("player$")) {
            Player player = Player.changePlayerFromString(answer);
            return player;
        }
        return null;
    }


    public User signUpAsPlayer(String input) throws IOException {
        Client.getDataOutputStream().writeUTF(input);
        Client.getDataOutputStream().flush();
        String answer = Client.getDataInputStream().readUTF();
        return User.changeUserFromString(answer);
    }
    public Admin signUpAsAdmin(String input) throws IOException {
        Client.getDataOutputStream().writeUTF(input);
        Client.getDataOutputStream().flush();
        String answer = Client.getDataInputStream().readUTF();
        User user = User.changeUserFromString(answer);
        Admin admin = new Admin(user.getFirstName() , user.getLastName() , user.getUsername() , user.getPassword() , user.getEmailAddress() , user.getTelephoneNumber());
        admin.setID(user.getID());
        return admin;
    }
}
