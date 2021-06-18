package Client.Controller;

import Common.Post;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class PostItem extends ListCell<Post> {
    @Override
    public void updateItem(Post post, boolean b) {
        super.updateItem(post, b);
        if(post!=null){
            try {
                setGraphic(new PostItemController(post).init());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}
