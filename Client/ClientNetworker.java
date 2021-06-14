package Client;

import Common.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class ClientNetworker{

    // you might set this from graphic
    public static String serverAddress = "localhost";
    public static final int PORT = 2222;

    private static boolean isConnected = false;
    public static Socket socket;
    public static ObjectInputStream socketIn;
    public static ObjectOutputStream socketOut;

    public static boolean isConnected(){
        return isConnected;
    }

    public static void connectToServer(){
        if(socket != null) return ;
        try{
            System.out.println("server ip : " + serverAddress);
            socket = new Socket( serverAddress, PORT);
            socketOut = new ObjectOutputStream( socket.getOutputStream() );
            socketIn = new ObjectInputStream( socket.getInputStream() );
            isConnected = true;

        } catch (IOException e){
            e.printStackTrace();
        }
    }


    public static Boolean disconnectFromServer(){
        try{
            socketIn.close();
            socketOut.close();
            socket.close();
            isConnected = false;

            socket = null;
            socketIn = null;
            socketOut = null;

            return true;
        } catch (Exception e){
            e.printStackTrace();
        }
        socket = null;
        socketIn = null;
        socketOut = null;
        return false;
    }

    @SuppressWarnings("unchecked")
    public static Map<String,Object> serve(Map<String,Object> toSend){
        Map<String,Object> received= new HashMap<>();
        try{
            socketOut.writeObject(toSend);
            socketOut.flush();
            socketOut.reset();
            received = (Map<String,Object>) socketIn.readObject();
            return received;

        } catch (ClassNotFoundException | IOException e){
            e.printStackTrace();
        }
        return received;
    }

}
