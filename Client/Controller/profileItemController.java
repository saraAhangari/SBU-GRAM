package Client.Controller;

import Client.Model.PageLoader;
import Common.User;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class profileItemController {
    public User user;
    public AnchorPane anchorpane;
    public javafx.scene.control.Label name_lastname;
    public javafx.scene.control.Label username;
    public javafx.scene.control.Label birthDate;
    public javafx.scene.control.Label followings;
    public javafx.scene.control.Label followers;
    public ImageView profile_image;

    public profileItemController(User user) throws IOException {
        new PageLoader().load("ProfileItem" , this);
        this.user = user;
    }

    public AnchorPane init() {
        name_lastname.setText(user.getFirstname() + " " + user.getLastName());
        username.setText(user.getUsername());
        birthDate.setText(user.getBirthDate());
        followings.setText(String.valueOf(user.getFollowings().size()));
        followers.setText(String.valueOf(user.getFollowers().size()));
        if(user.getProfilePhoto()!=null){
            profile_image.setImage(new Image(new ByteArrayInputStream(user.getProfilePhoto())));
        }
        return anchorpane;
    }
    public void checkOut_user(ActionEvent actionEvent){

    }
}
