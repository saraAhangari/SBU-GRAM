package Client.Model;

import Common.Post;
import Common.User;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Main extends Application {
    public static User user;
    public static ArrayList<User> profiles = new ArrayList<>();
    public static Map<User , User> timLine = new HashMap<>();
                 //follower , following
    public static User searched_user;
    public static Post post;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Network.connectToServer();
        PageLoader.initStage(primaryStage);
        new PageLoader().load("login");
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() throws Exception {
        Network.disconnectFromServer();
        super.stop();
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        Main.user = user;
    }
}

