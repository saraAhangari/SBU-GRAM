package Client.Controller;

import Client.Model.API;
import Client.Model.Main;
import Client.Model.PageLoader;
import Common.Post;
import Common.User;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class OthersProfiles {
    public ImageView profile_image;
    public ImageView follow_image;
    public Label followers;
    public Label followings;
    public ListView OthersPosts;
    public VBox vbox;
    public Label name_lastname;
    public Label username;
    public Label birthDate;
    public User user = Main.getUser(); //ali
    public User searched_user = Main.searched_user; //baran
    public ArrayList<Post> someoneElsePosts ;

    @FXML
    public void initialize() {
        someoneElsePosts = API.getselfPosts(searched_user.getUsername());
        OthersPosts.setItems(FXCollections.observableArrayList(someoneElsePosts));
        OthersPosts.setCellFactory(OthersPosts -> new PostItem());
        if (searched_user.getProfilePhoto() != null) {
            profile_image.setImage(new Image(new ByteArrayInputStream(searched_user.getProfilePhoto())));
        }
        name_lastname.setText(searched_user.getFirstname() + " " + searched_user.getLastName());
        username.setText(searched_user.getUsername());
        birthDate.setText(searched_user.getBirthDate());
        followers.setText(String.valueOf(searched_user.getFollowers().size()));
        followings.setText(String.valueOf(searched_user.getFollowings().size()));
    }

    public void follow(MouseEvent mouseEvent) {
        if (!user.getFollowings().contains(searched_user)){
            user.getFollowings().add(searched_user);
            searched_user.getFollowers().add(user);
            followers.setText(String.valueOf(searched_user.getFollowers().size()));
            API.addFollower(user , searched_user);
            follow_image.setVisible(false);
        }
        else {
            follow_image.setVisible(false);
            initialize();
        }

    }

    public void log_out(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("login");
    }

    public void add_post(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("add_post");
    }

    public void search(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("Search");
    }

    public void Home(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("timeLine");
    }

    public void back_menu(MouseEvent mouseEvent) {
        TranslateTransition tt = new TranslateTransition(Duration.millis(1500), vbox);
        tt.setToX(-101);
        tt.playFromStart();
    }

    public void show_menu(MouseEvent mouseEvent) {
        TranslateTransition tt = new TranslateTransition(Duration.millis(1500), vbox);
        tt.setToX(103);
        tt.playFromStart();
    }
}
