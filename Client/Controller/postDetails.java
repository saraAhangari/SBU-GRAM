package Client.Controller;

import Client.Model.API;
import Client.Model.Main;
import Client.Model.PageLoader;
import Common.Comment;
import Common.Post;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;

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
    public Button like_button;
    public Button repost_button;
    public AnchorPane anchorPane;
    public Label comment_writer;
    public TextArea theComment;
    public ArrayList<Comment> comments;
    public Post post = Main.getPost();
    public Comment comment = Main.getPost().getComment();


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
        if (!repost_button.getText().equals("reposted")
        && !post.getWriter().equals(Main.getUser().getUsername())) {
            Main.setPost(post);
            Main.post.setReposts(Main.getUser(), Main.getPost());
            Main.getUser().addPost(Main.getPost());
            repost_button.setText("reposted");
            reports_count.setText(String.valueOf(post.getRepost()));
            API.Repost(Main.getPost(), Main.getUser());
        }
    }

    public void add_comment(ActionEvent actionEvent) {
        TranslateTransition tt = new TranslateTransition(Duration.millis(1200), anchorPane);
        tt.setToY(540);
        tt.playFromStart();
        comment_writer.setText(Main.getUser().getUsername());
        String text = theComment.getText();
        theComment.setText(text);
        post.setComment(comment);
    }
    public void send_comment(ActionEvent actionEvent) {
        post.addComment(comment);
        TranslateTransition tt = new TranslateTransition(Duration.millis(1200), anchorPane);
        tt.setToY(-100);
        tt.playFromStart();
    }

    public void show_comments(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("commentPage");
        TranslateTransition ts = new TranslateTransition(Duration.millis(1100), anchorPane);
        ts.setToY(-290);
        ts.playFromStart();
        /*TranslateTransition tt = new TranslateTransition(Duration.millis(1200), commentsListview);
        tt.setToY(-270);
        tt.playFromStart();
        comments = post.getComments();
        commentsListview.setItems(FXCollections.observableArrayList(comments));
        commentsListview.setCellFactory(commentsListview -> new CommentItem());*/
    }

    public void like(ActionEvent actionEvent) {
        if (like_button.getText().equals("like")) {
            Main.post = post;
            Main.post.likePost(Main.getUser());
            like_button.setText("unlike");
            like_count.setText(String.valueOf(post.getLike()));
            API.like(Main.post, Main.getUser());
        }
        else if (like_button.getText().equals("unlike")){
            Main.post = post;
            Main.post.unlikePost(Main.getUser());
            like_button.setText("like");
            like_count.setText(String.valueOf(post.getLike()));
            API.unlike(Main.post, Main.getUser());
        }
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
