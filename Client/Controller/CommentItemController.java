package Client.Controller;

import Client.Model.PageLoader;
import Common.Comment;
import javafx.scene.layout.AnchorPane;

import java.awt.*;
import java.io.IOException;

public class CommentItemController {
    public Comment comment;
    public Label comment_writer;
    public TextArea theComment;
    public AnchorPane anchorPane;

    public CommentItemController(Comment comment) throws IOException {
        new PageLoader().load("CommentItem" , this);
        this.comment = comment;
    }

    public AnchorPane init() {
        comment_writer.setText(comment.getUser().getUsername());
        theComment.setText(comment.getText());
        return anchorPane;
    }
}
