package Client.Controller;

import Client.Model.PageLoader;
import Common.Post;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;

import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;


import java.io.IOException;
import java.nio.file.Paths;

public class PostItemController {
    Post post;
    public Label usernameLabel;
    public Label titleLabel;
    public Image profileImage;
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
