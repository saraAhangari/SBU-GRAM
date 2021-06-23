package Client.Controller;

import Client.Model.PageLoader;
import Common.Post;
import Server.Server;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;

public class postDetails {

    public Label title;
    public ImageView post_image;
    public Label writer;
    public TextArea description;
    public Label dateofpost;
    public Label reports_count;
    public Label like_count;
    public VBox vbox;
    Post post;

    @FXML
    public void initialize(){
        for (int i = 0; i <Server.Posts.size() ; i++) {
            if (Server.Posts.get(i).writer.equals(post.writer)){
                title.setText(Server.Posts.get(i).title);
                writer.setText(Server.Posts.get(i).writer);
                description.setText(Server.Posts.get(i).description);
                break;
            }
        }
    }

    public void repost(ActionEvent actionEvent) {
    }

    public void add_comment(ActionEvent actionEvent) {
    }

    public void show_comments(ActionEvent actionEvent) {
    }

    public void like(ActionEvent actionEvent) {
    }

    public void show_menu(MouseEvent mouseEvent) {
        TranslateTransition tt = new TranslateTransition(Duration.millis(1500), vbox);
        tt.setToX(102);
        tt.playFromStart();
    }

    public void Home(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("timeLine");
    }

    public void Profile(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("myProfile");
    }

    public void add_post(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("addPost");
    }

    public void search(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("Search");
    }

    public void log_out(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("logout");
    }
}
