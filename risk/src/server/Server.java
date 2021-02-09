package server;

import server.controller.EventController;
import server.controller.GameController;
import server.controller.UserController;
import server.model.database.MySqlDataBase;
import server.model.usersModels.Admin;
import server.model.usersModels.Player;
import server.model.usersModels.User;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;

public class Server {
    public static void main(String[] args) throws IOException {
        MySqlDataBase mySqlDataBase = MySqlDataBase.getMySqlDataBase();
        run();
    }
    static void run() throws IOException {
        ServerSocket serverSocket = new ServerSocket(8585);
        while (true){
            Socket clientSocket;
            System.out.println("Waiting for client ....");
            try {
                clientSocket = serverSocket.accept();
                System.out.println("client successfully connected to the server");
                DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(clientSocket.getOutputStream()));
                DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));
                ClientHandler clientHandler = new ClientHandler(clientSocket , dataOutputStream , dataInputStream);
                ClientHandler.addNewClientHandler(clientHandler);
                clientHandler.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    static class ClientHandler extends Thread{
        UserController userController;
        EventController eventController;
        GameController gameController;
        private static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
        private Socket clientSocket;
        private DataOutputStream dataOutputStream;
        private DataInputStream  dataInputStream;
        private Player player;
        private Admin admin;
        private String username;

        public ClientHandler(Socket clientSocket, DataOutputStream dataOutputStream, DataInputStream dataInputStream) {
            this.clientSocket = clientSocket;
            this.dataOutputStream = dataOutputStream;
            this.dataInputStream = dataInputStream;
            this.username = "";
            this.player = null;
            this.admin = null;
            this.userController = UserController.getUserController();
            this.eventController = EventController.getEventController();
            this.gameController = GameController.getGameController();
        }


        public static void addNewClientHandler(ClientHandler clientHandler) {
            clientHandlers.add(clientHandler);
        }

        public static void removeClientHandler(ClientHandler clientHandler) {
            clientHandlers.remove(clientHandler);
        }

        public static ClientHandler clientHandlerFinder(String username) {
            for (Server.ClientHandler clientHandler : clientHandlers) {
                if (clientHandler.username.equals(username)) {
                    return clientHandler;
                }
            }
            return null;
        }


        @Override
        public void run() {
            try {
                String input;
                while (true){
                    input = dataInputStream.readUTF();
                    if (player != null)
                        System.out.println("Client.Client with username " + player.getLastName() + " sent : " + input);
                    else System.out.println("A client sent : " + input);
                    String answer = answerClient(input);
                    dataOutputStream.writeUTF(answer);
                    dataOutputStream.flush();
                    System.out.println("server answered : " + answer);
                    if(input.equals("end"))
                        break;
                }
            }catch (IOException e){
                System.err.println("In ro Amin yek kari mikone :) ");
            }

        }

        private String answerClient(String input) {
            String answer;
            if(input.startsWith("login")){
                String[] inputs = input.split("\\$");
                String username = inputs[1];
                String password = inputs[2];
                try {
                   User user  = userController.login(username , password);
                    return user.changeUserToString();
                } catch (Exception e) {
                    return e.getMessage();
                }
            }else if(input.startsWith("find player$")){
                String[] inputs = input.split("\\$");
                Player player = userController.findPlayerByUserName(inputs[1]);
                if(player == null)
                    return "player didn't found";
                else
                    return player.changePlayerToString();
            }else if(input.startsWith("check username")){
                String[] inputs = input.split("\\$");
                boolean checkUsername = userController.checkUsername(inputs[1]);
                return String.valueOf(checkUsername);
            }else if(input.startsWith("haveAdmin")){
                boolean haveAdmin = false;
                Admin admin = userController.getAdmin();
                if(admin != null)
                    haveAdmin = true;
                return String.valueOf(haveAdmin);
            }else if(input.startsWith("sign up as player$")){
                String[] inputs = input.split("\\$");
                String firstName = inputs[1];
                String lastName = inputs[2];
                String username = inputs[3];
                String password = inputs[4];
                String emailAddress = inputs[5];
                String telephoneNumber = inputs[6];
                User user = userController.signUpAsPlayer(firstName , lastName , username , password , emailAddress , telephoneNumber);
                return user.changeUserToString();
            }else if(input.startsWith("sign up as admin$")){
                String[] inputs= input.split("\\$");
                String firstName = inputs[1];
                String lastName = inputs[2];
                String username = inputs[3];
                String password = inputs[4];
                String emailAddress = inputs[5];
                String telephoneNumber = inputs[6];
                Admin admin = userController.signUpAsAdmin(firstName , lastName , username , password , emailAddress , telephoneNumber);
                return admin.changeUserToString();
            }else if(input.startsWith("all players had message$")){
                String[] inputs = input.split("\\$");
                Player player = userController.findPlayerByUserName(inputs[1]);
                HashSet<Player> players = userController.getAllPlayersThatHadMessageWith(player);
            }
            return null;
        }
    }
}
