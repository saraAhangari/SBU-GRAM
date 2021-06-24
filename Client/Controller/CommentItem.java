package Client.Controller;

import Common.Comment;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class CommentItem extends ListCell<Comment> {
    @Override
    public void updateItem(Comment comment, boolean empty) {
        super.updateItem(comment, empty);
        if(comment!=null){
            try {
                setGraphic(new CommentItemController(comment).init());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
