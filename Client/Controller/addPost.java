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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

public class addPost {
    public TextField post_title;
    public TextArea post_description;
    public VBox vbox;
    public Post currentPost = new Post();
    public byte[] photo;
    public ImageView post_image;
    ArrayList<Post> posts = new ArrayList<>();

    public void add_image(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("select profile");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"));
        File file = fileChooser.showOpenDialog(stage);
        Image image = new Image(file.toURI().toString());
        post_image.setImage(image);
        byte[] imageToByteArray;
        imageToByteArray = Files.readAllBytes(file.toPath());
        photo = imageToByteArray;
        post_image.setImage(image);
        if (photo != null)
            post_image.setImage(new Image(new ByteArrayInputStream(photo)));
    }

    public void publish(ActionEvent actionEvent) {
        currentPost.setTitle(post_title.getText());
        currentPost.setDescription(post_description.getText());
        currentPost.setWriter(Main.getUser().getUsername());
        currentPost.setImage(photo);
        Main.getUser().addPost(currentPost);
        API.addPost(Main.getUser() , currentPost);
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
        new PageLoader().load("login");
    }

    public void search(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("Search");
    }
}
