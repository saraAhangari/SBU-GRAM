package Client.Model;

import Common.Comment;
import Common.Post;
import Common.User;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;
/* gitHub source : https://github.com/saraAhangari/SBU-GRAM.git
* i know it's not complete but that's all i could do in less than 10 days :))
*
* */


public class Main extends Application {
    public static User user;
    public static Comment comment;
    public static ArrayList<User> profiles = new ArrayList<>();
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

    public static Post getPost() {
        return post;
    }

    public static void setPost(Post post) {
        Main.post = post;
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        Main.user = user;
    }

    public static User getSearched_user() {
        return searched_user;
    }

    public static void setSearched_user(User searched_user) {
        Main.searched_user = searched_user;
    }
}

