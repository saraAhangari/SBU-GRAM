package Client.Controller;

import Client.Model.API;
import Client.Model.Main;
import Client.Model.PageLoader;
import Common.Post;
import Common.User;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class edit_myProfile {
    public ImageView profile_image;
    public Label followers;
    public Label followings;
    public ListView myPosts;
    public VBox vbox;
    public TextField username;
    public TextField Firstname;
    public TextField Lastname;
    public byte[] photo;
    public User user;
    public ArrayList<Post> postArrayList;
    public DatePicker DateBirth;

    @FXML
    public void initialize() {
        user = Main.getUser();
        username.setText(user.getUsername());
    }
    public void Home(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("timeLine");
    }

    public void search(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("Search");
    }

    public void add_post(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("add_post");
    }

    public void log_out(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("login");
    }

    public void save_edit(MouseEvent mouseEvent) throws IOException {
        if (API.updateInfo(user)){
            Main.setUser(user);
            user.setFirstname(Firstname.getText());
            user.setLastName(Lastname.getText());
            user.setBirthDate(DateBirth.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            postArrayList = API.getPosts(user.getUsername());
            user.setPosts(postArrayList);
            Alert alert = new Alert(Alert.AlertType.INFORMATION , "Edited successfully !");
            alert.showAndWait();
            new PageLoader().load("myProfile");
        }
    }

    public void unchangeable_username(MouseEvent mouseEvent){
        Alert alert = new Alert(Alert.AlertType.WARNING , "You can't change this one !");
        alert.showAndWait();
    }

    public void change_picture(ActionEvent actionEvent) throws IOException {
        Stage stage=new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("select profile");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PNG" , "*.png") ,
                new FileChooser.ExtensionFilter("JPG" , "*.jpg"));
        File file = fileChooser.showOpenDialog(stage);
        Image image=new Image(file.toURI().toString());
        profile_image.setImage(image);
        byte[] imageToByteArray;
        imageToByteArray= Files.readAllBytes(file.toPath());
        photo = imageToByteArray;
        profile_image.setImage(new Image(new ByteArrayInputStream(photo)));
        user.setProfilePhoto(photo);
    }

    public void show_menu(MouseEvent mouseEvent) {
        TranslateTransition tt = new TranslateTransition(Duration.millis(1500), vbox);
        tt.setToX(103);
        tt.playFromStart();
    }
}
