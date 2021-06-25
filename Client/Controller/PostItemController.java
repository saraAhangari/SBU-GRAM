package Client.Controller;

import Client.Model.API;
import Client.Model.Main;
import Client.Model.PageLoader;
import Common.Post;
import Common.User;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Paths;

public class PostItemController {
    public Label writer;
    public Label title;
    public ImageView profile_image;
    public ImageView post_image;
    public AnchorPane root;
    public Post post;
    public User user = Main.getUser();

    public PostItemController(Post post) throws IOException {
        new PageLoader().load("PostItem" , this);
        this.post=post;
    }

    public AnchorPane init() {
        writer.setText(post.getWriter());
        title.setText(post.getTitle());
        if (post.getImage() != null)
            post_image.setImage(new Image(new ByteArrayInputStream(post.getImage())));
        else
            post_image.setImage(new Image(Paths.
                    get("C:\\Users\\baran\\Desktop\\AP\\SBU_gram\\Images\\488px-No-Image-Placeholder.svg.png")
                    .toUri().toString()));

        if (user.getProfilePhoto() != null)
            profile_image.setImage(new Image(new ByteArrayInputStream(user.getProfilePhoto())));
        else
            profile_image.setImage(new Image(Paths.
                    get("C:\\Users\\baran\\Desktop\\AP\\SBU_gram\\Images\\488px-No-Image-Placeholder.svg.png")
                    .toUri().toString()));
        return root;
    }

    public void show_details(ActionEvent actionEvent) throws IOException {
        Main.post = post;
        new PageLoader().load("postDetails");
    }
}
