package Controller;

import Model.PageLoader;
import Model.Post;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.nio.file.Paths;

public class PostItemController {
    public AnchorPane root;
    public ImageView profileImage;
    public Label username;
    public Label title;
    Post post;

    //each list item will have its exclusive controller in runtime so we set the controller as we load the fxml
    public PostItemController(Post post) throws IOException {
        new PageLoader().load("postItem", this);
        this.post = post;
    }

    //this anchor pane is returned to be set as the list view item
    public AnchorPane init() {
        username.setText(post.getWriter());
        title.setText(post.getTitle());
        return root;
    }

    //you can show post's detail in new page with this method
    public void detail(ActionEvent actionEvent) {

    }
    /*
    you can also add on mouse click for like and repost image
     */
}
