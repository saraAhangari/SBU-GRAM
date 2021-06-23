package Client.Controller;

import Client.Model.Main;
import Client.Model.PageLoader;
import Common.Post;
import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class CommentItemController {
    public Post post;

    public CommentItemController(Post post) throws IOException {
        new PageLoader().load("CommentItem" , this);
        this.post=post;
    }

}
