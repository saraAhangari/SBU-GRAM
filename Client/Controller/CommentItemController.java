package Client.Controller;

import Client.Model.API;
import Client.Model.Main;
import Client.Model.PageLoader;
import Common.Comment;
import Common.Post;
import javafx.scene.layout.AnchorPane;

import java.awt.*;
import java.io.IOException;

public class CommentItemController {
    public Comment comment;
    public Label comment_writer;
    public TextArea theComment;
    public AnchorPane anchorPane;
    public Post post = Main.getPost();

    public CommentItemController(Comment comment) throws IOException {
        new PageLoader().load("CommentItem" , this);
        this.comment = comment;
    }

    public AnchorPane init() {
        for (int i = 0; i <post.getComments().size() ; i++) {
            if (post.getComments().get(i).equals(comment)){
                comment_writer.setText(comment.getUser().getUsername());
                theComment.setText(post.getComments().get(i).getText());
                API.addComment(post , comment);
                break;
            }
        }

        return anchorPane;
    }

}
