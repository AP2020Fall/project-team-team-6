package client;


import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {
    public static ArrayList<Client> clients = new ArrayList<>();
    static Socket socket;
    Scanner scanner;
    static DataOutputStream dataOutputStream;
    static DataInputStream dataInputStream;
    public static void main(String[] args) {
        new Client().connection();
    }

    public static Socket getSocket() {
        return socket;
    }

    public static void setSocket(Socket socket) {
        Client.socket = socket;
    }

    public static DataOutputStream getDataOutputStream() {
        return dataOutputStream;
    }

    public static void setDataOutputStream(DataOutputStream dataOutputStream) {
        Client.dataOutputStream = dataOutputStream;
    }

    public static DataInputStream getDataInputStream() {
        return dataInputStream;
    }

    public static void setDataInputStream(DataInputStream dataInputStream) {
        Client.dataInputStream = dataInputStream;
    }

    private void connection() {
        try {
            socket = new Socket("127.0.0.1" , 8585);
            System.out.println("Successfully connected!");
            handleConnection();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void handleConnection() {
        try {
            scanner = new Scanner(System.in);
            dataInputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            dataOutputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            String userInput = "";
            while (!userInput.equalsIgnoreCase("end")) {
                Main.main(new String[1]);
                if (userInput.equals("end"))
                    return;
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void addNewClient(Client client){
        clients.add(client);
    }
}
