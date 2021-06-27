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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Vector;

public class commentPage {
    public ListView commentListview;
    public VBox vbox;
    public Vector<Comment> commentVector;
    public Post post = Main.getPost();
    public AnchorPane anchorPane;
    public Label comment_writer;
    public TextArea theComment;
    public Comment comment;

    @FXML
    public void initialize(){
        TranslateTransition tt = new TranslateTransition(Duration.millis(1200), anchorPane);
        tt.setToY(550);
        tt.playFromStart();
        comment_writer.setText(Main.getUser().getUsername());
    }

    public void send_comment(ActionEvent actionEvent) {
        comment = new Comment();
        comment.setText(theComment.getText());
        comment.setUser(Main.getUser());
        API.addComment(post , comment);
        TranslateTransition tt = new TranslateTransition(Duration.millis(1200), anchorPane);
        tt.setToY(-100);
        tt.playFromStart();
    }

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
        API.Logout(Main.getUser().getUsername());
        new PageLoader().load("login");
    }

    public void refresh(ActionEvent actionEvent) {
        commentVector = post.getComments();
        commentListview.setItems(FXCollections.observableArrayList(commentVector));
        commentListview.setCellFactory(commentListview -> new CommentItem());
    }

    public void show_menu(MouseEvent mouseEvent) {
        TranslateTransition tt = new TranslateTransition(Duration.millis(1300), vbox);
        tt.setToX(103);
        tt.playFromStart();
    }

    public void back_menu(MouseEvent mouseEvent) {
        TranslateTransition tt = new TranslateTransition(Duration.millis(1300), vbox);
        tt.setToX(-101);
        tt.playFromStart();
    }

    public void Home(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("timeLine");
    }
}
