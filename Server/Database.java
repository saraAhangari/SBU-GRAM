package Server;

import Common.User;

import java.io.*;
import java.util.concurrent.ConcurrentHashMap;

public class Database {
    public static final String PROFILES_FILE = "C:/Users/baran/Desktop/AP/SBU_gram/src/UserData/Users";
    public static Database database = new Database();

    public static Database getInstance(){
        return database;
    }

    public static synchronized void initializeServer(){
        try {
            FileInputStream fileInputStream = new FileInputStream(Database.PROFILES_FILE);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Server.users = new ConcurrentHashMap<>((ConcurrentHashMap<String, User>)objectInputStream.readObject());
            objectInputStream.close();
            fileInputStream.close();
        }
        catch(EOFException | StreamCorruptedException e){
            Server.users = new ConcurrentHashMap<>();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static synchronized void updateDataBase(){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(PROFILES_FILE);
            ObjectOutputStream objToFile = new ObjectOutputStream(fileOutputStream);
            objToFile.writeObject(Server.users); //writing profiles
            objToFile.close();
            fileOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
