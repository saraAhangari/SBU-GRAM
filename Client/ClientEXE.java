package Client;

import Common.User;

public class ClientEXE {
    public static User user;

    public static void main(String[] args) {
        ClientNetworker.connectToServer();
        System.out.println(API.isUserNameExists("baran"));
        // this methods should be called from controller methods
    }
}
