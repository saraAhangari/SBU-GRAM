package Client.Controller;

import Client.Model.Main;
import Client.Model.PageLoader;
import Common.User;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class profileItemController {
    public User user;
    public AnchorPane anchorpane;
    public Label searched_name;
    public Label searched_username;
    public Label searched_date;
    public Label searched_followings;
    public Label searched_followers;
    public ImageView searched_profileImage = new ImageView();

    public profileItemController(User user) throws IOException {
        new PageLoader().load("ProfileItem" , this);
        this.user = user;
    }

    public AnchorPane init() {
        searched_name.setText(user.getFirstname() + " " + user.getLastName());
        searched_username.setText(user.getUsername());
        searched_date.setText(user.getBirthDate());
        searched_followings.setText(String.valueOf(user.getFollowings().size()));
        searched_followers.setText(String.valueOf(user.getFollowers().size()));
        if(user.getProfilePhoto()!=null){
            searched_profileImage.setImage(new Image(new ByteArrayInputStream(user.getProfilePhoto())));
        }
        return anchorpane;
    }
    public void checkOut_user(ActionEvent actionEvent) throws IOException {
        Main.searched_user = user;
        new PageLoader().load("OthersProfiles");
    }
}
