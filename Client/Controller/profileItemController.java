package Client.Controller;

import Client.Model.PageLoader;
import Common.User;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.awt.*;
import java.io.IOException;

public class profileItemController {
    public User user;
    public AnchorPane anchorpane;
    public Label name_lastname;
    public Label username;
    public Label birthDate;
    public Label followings;
    public Label followers;
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
        //profile_image.setImage();
        return anchorpane;
    }
}
