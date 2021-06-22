package Client.Controller;

import Client.Model.Main;
import Client.Model.PageLoader;
import Common.Post;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;


import java.io.IOException;

public class PostItemController {
    public Label writer;
    public Label title;
    public AnchorPane root;
    public Post post;

    public PostItemController(Post post) throws IOException {
        new PageLoader().load("PostItem" , this);
        this.post=post;
    }

    public AnchorPane init() {
        writer.setText(Main.getUser().getUsername());
        title.setText(post.getTitle());
        return root;
    }

    @FXML
    public void show_details(){

    }
}
