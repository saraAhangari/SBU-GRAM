package Client.Controller;

import Client.Model.API;
import Client.Model.Main;
import Client.Model.PageLoader;
import Common.Comment;
import Common.Post;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

public class commentPage {
    public ListView commentListview;
    public VBox vbox;
    public Vector<Comment> commentVector;
    public Post post = Main.getPost();

    public void search(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("Search");
    }

    public void Profile(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("myProfile");
    }

    public void add_post(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("add_post");
    }

    public void log_out(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("login");
    }

    public void refresh(ActionEvent actionEvent) {
        commentVector = API.getComments(post);
        System.out.println("it idd well"); //واسه چک کردن
        commentListview.setItems(FXCollections.observableArrayList(commentVector));
        commentListview.setCellFactory(commentListview -> new CommentItem());
    }

    public void show_menu(MouseEvent mouseEvent) {
        TranslateTransition tt = new TranslateTransition(Duration.millis(1500), vbox);
        tt.setToX(103);
        tt.playFromStart();
    }
}
