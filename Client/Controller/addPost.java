package Client.Controller;

import Client.Model.API;
import Client.Model.Main;
import Client.Model.PageLoader;
import Common.Post;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.util.Duration;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class addPost {
    public TextField post_title;
    public TextArea post_description;
    public VBox vbox;
    public Post currentPost = new Post();
    public byte[] photo;
    ArrayList<Post> posts = new ArrayList<>();

    @FXML
    public void initialize(){
    }

    public void add_image(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(new Popup());
        FileInputStream fileInputStream = new FileInputStream(file);
        photo = fileInputStream.readAllBytes();
        Image image = new Image(new ByteArrayInputStream(photo));
        currentPost.setImage(photo);
    }

    public void publish(ActionEvent actionEvent) {
        currentPost.setTitle(post_title.getText());
        currentPost.setDescription(post_description.getText());
        currentPost.setWriter(Main.getUser().getUsername());
        API.addPost(Main.getUser().getUsername() , currentPost);
    }

    public void show_menu(MouseEvent mouseEvent) {
        TranslateTransition tt = new TranslateTransition(Duration.millis(1500), vbox);
        tt.setToX(105);
        tt.playFromStart();
    }

    public void Home(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("timeLine");
    }

    public void Profile(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("myProfile");
    }

    public void log_out(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("logout");
    }

    public void search(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("Search");
    }
}
