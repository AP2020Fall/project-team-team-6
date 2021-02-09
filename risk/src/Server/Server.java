package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
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
                new ClientHandler(clientSocket , dataOutputStream , dataInputStream).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    static class ClientHandler extends Thread{
        private Socket clientSocket;
        private DataOutputStream dataOutputStream;
        private DataInputStream  dataInputStream;

        public ClientHandler(Socket clientSocket, DataOutputStream dataOutputStream, DataInputStream dataInputStream) {
            this.clientSocket = clientSocket;
            this.dataOutputStream = dataOutputStream;
            this.dataInputStream = dataInputStream;
        }

        @Override
        public void run() {
            while (true){

            }
        }
    }
}
