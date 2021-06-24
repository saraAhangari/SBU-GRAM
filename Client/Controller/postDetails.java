package Client.Controller;

import Client.Model.API;
import Client.Model.Main;
import Client.Model.PageLoader;
import Common.Post;
import Server.Server;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.ByteArrayInputStream;
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
    public ListView commentsListview;
    public Post post = Main.post;

    @FXML
    public void initialize(){
        title.setText(post.getTitle());
        writer.setText(post.getWriter());
        description.setText(post.getDescription());
        dateofpost.setText(post.getDateWithTime().toString());
        reports_count.setText(String.valueOf(post.getRepost()));
        like_count.setText(String.valueOf(post.getLike()));
        if (post.getImage() != null)
            post_image.setImage(new Image(new ByteArrayInputStream(post.getImage())));
    }

    public void repost(ActionEvent actionEvent) {
    }

    public void add_comment(ActionEvent actionEvent) {
    }

    public void show_comments(ActionEvent actionEvent) {
        TranslateTransition tt = new TranslateTransition(Duration.millis(1200), commentsListview);
        tt.setToY(-250);
        tt.playFromStart();
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
        new PageLoader().load("add_post");
    }

    public void search(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("Search");
    }

    public void log_out(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("login");
    }
}
