package Client.Model;

import Common.User;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
    public static User user;

    @Override
    public void start(Stage primaryStage) throws Exception{
        PageLoader.initStage(primaryStage);
        new PageLoader().load("login");
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() throws Exception {
        ClientToServer.disconnectFromServer();
        super.stop();
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        Main.user = user;
    }
}

