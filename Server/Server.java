package Server;

import Common.User;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Map;

public class Server {
    private static final int port = 2222;
    private static final boolean isServerUp = true;
    public static Map<String, User> users = null;
    public static LinkedList<User> Profiles = new LinkedList<>();

    public static boolean isServerUp() {
        return isServerUp;
    }

    public static void main(String[] args) {
        Database.initializeServer();

        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(port);

        } catch (IOException e) {
            System.exit(0);
        }
        //accept client and start ClientHandler
        while ( isServerUp() ) {
            Socket userSocket;
            try {
                userSocket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(userSocket);
                new Thread(clientHandler).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
