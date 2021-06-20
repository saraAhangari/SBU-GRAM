package Client.Controller;

import Client.Model.PageLoader;
import Common.Post;
import javafx.scene.control.Label;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;


import java.io.IOException;

public class PostItemController {
    Post post;
    public Label usernameLabel;
    public Label titleLabel;
    public ImageView profileImage;
    public AnchorPane anchorPane;

    public PostItemController(Post post) throws IOException {
        new PageLoader().load("PostItem" , this);
        this.post=post;
    }

    public AnchorPane init() {
        usernameLabel.setText(post.getWriter());
        titleLabel.setText(post.getTitle());
        return anchorPane;
    }
}
