package Client.Controller;

import Common.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;

public class Profile {
    public ImageView profile_image;
    public Label name_lastname;
    public Label username;
    public Label birthDate;
    public Label followers;
    public Label followings;
    public ListView myPosts;

    @FXML
    public void initialize(){

    }
}
