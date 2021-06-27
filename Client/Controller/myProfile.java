package Client.Controller;

import Client.Model.API;
import Client.Model.Main;
import Client.Model.PageLoader;
import Common.Post;
import Common.User;
import Server.Server;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class myProfile {
    public ImageView profile_image;
    public Label name_lastname;
    public Label username;
    public Label birthDate;
    public Label followers;
    public Label followings;
    public ListView myPosts;
    public VBox vbox;
    public User user = Main.getUser();
    public ArrayList<Post> selfPosts;

    @FXML
    public void initialize() {
        name_lastname.setText(user.getFirstname() + " " + user.getLastName());
        username.setText(user.getUsername());
        birthDate.setText(user.getBirthDate());
        followers.setText(String.valueOf(user.getFollowers().size()));
        followings.setText(String.valueOf(user.getFollowings().size()));
        selfPosts = API.getPosts(user);
        myPosts.setItems(FXCollections.observableArrayList(selfPosts));
        myPosts.setCellFactory(myPosts -> new PostItem());
        if(user.getProfilePhoto()!=null){
            profile_image.setImage(new Image(new ByteArrayInputStream(user.getProfilePhoto())));
        }
    }

    public void show_menu(MouseEvent mouseEvent) {
        TranslateTransition tt = new TranslateTransition(Duration.millis(1300), vbox);
        tt.setToX(170);
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

    public void add_post(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("add_post");
    }

    public void log_out(MouseEvent mouseEvent) throws IOException {
        API.Logout(Main.getUser().getUsername());
        new PageLoader().load("login");

    }

    public void search(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("Search");
    }

    public void edit_profile(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("edit_myProfile");
    }

    public void delete_account(MouseEvent mouseEvent) throws IOException {
        API.deleteAccount(Main.getUser() , Main.getUser().getUsername());
        initialize();
        new PageLoader().load("login");
    }
}
