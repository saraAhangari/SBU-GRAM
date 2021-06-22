package Client.Controller;

import Client.Model.Main;
import Client.Model.PageLoader;
import Common.Post;
import javafx.scene.control.Label;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;


import java.io.IOException;

public class PostItemController {
    Post post;
    public Label writer;
    public Label title;
    public AnchorPane root;

    public PostItemController(Post post) throws IOException {
        new PageLoader().load("PostItem" , this);
        this.post=post;
    }

    public AnchorPane init() {
        writer.setText(Main.getUser().getUsername());
        title.setText(post.getTitle());
        return root;
    }
}
