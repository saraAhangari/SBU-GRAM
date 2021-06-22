package Client.Controller;

import Client.Model.API;
import Client.Model.Main;
import Client.Model.PageLoader;
import Common.Post;
import Server.Server;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;

public class addPost {
    public TextField post_title;
    public TextArea post_description;
    public VBox vbox;
    public Post currentPost = new Post();
    ArrayList<Post> posts = new ArrayList<>();

    @FXML
    public void initialize(){
    }

    public void add_image(ActionEvent actionEvent) {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        Label label = new Label("no files selected");
        File file = fileChooser.showOpenDialog(stage);
        if (file != null)
            label.setText(file.getAbsolutePath() + "selected");
        assert file != null;
        Image image=new Image(file.toURI().toString());
    }

    public void publish(ActionEvent actionEvent) throws IOException {
        //set the post features
        currentPost.setTitle(post_title.getText());
        currentPost.setDescription(post_description.getText());

        TimeLineController.postArrayList = API.addPost(currentPost);
        Server.Posts.add(currentPost);
        currentPost = new Post();

        //empty fields
        post_title.setText("");
        post_description.setText("");
    }

    public void show_menu(MouseEvent mouseEvent) {
        TranslateTransition tt = new TranslateTransition(Duration.millis(1500), vbox);
        tt.setToX(105);
        tt.playFromStart();
    }

    public void Home(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("timeLine");
    }

    public void Profile(MouseEvent mouseEvent) {
    }

    public void log_out(MouseEvent mouseEvent) {
    }
}
