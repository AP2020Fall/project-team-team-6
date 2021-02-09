package Client;

import java.io.*;
import java.net.Socket;

public class Client {
    Socket socket;
    DataOutputStream dataOutputStream;
    DataInputStream dataInputStream;
    public static void main(String[] args) {
        new Client().connection();
    }

    private void connection() {
        try {
            socket = new Socket("127.0.0.1" , 8585);
            System.out.println("Successfully connected!");
            dataOutputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            dataInputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            handleConnection();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private void handleConnection() throws IOException {
        String answer = dataInputStream.readUTF();
        System.out.println(answer);
    }
}
