package Client.Controller;

import Common.Post;
import Common.User;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class profileItem extends ListCell<User> {
    @Override
    public void updateItem(User user, boolean empty) {
        super.updateItem(user, empty);
        if(user!=null){
            try {
                setGraphic(new profileItemController(user).init());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}