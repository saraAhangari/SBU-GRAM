package Client.Controller;

import Client.Model.API;
import Client.Model.Main;
import Client.Model.PageLoader;
import Common.Post;
import Common.User;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Paths;

public class PostItemController {
    public Label writer;
    public Label title;
    public Label reports_count;
    public Label like_count;
    public Label dateofpost;
    public TextArea description;
    public Button repost_button;
    public Button like_button;
    public Button see_profile;
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
        title.setText(post.getTitle());
        writer.setText(post.getWriter());
        description.setText(post.getDescription());
        dateofpost.setText(post.getDateWithTime().toString());
        reports_count.setText(String.valueOf(post.getRepost()));
        like_count.setText(String.valueOf(post.getLike()));
        if (post.getImage() != null)
            post_image.setImage(new Image(new ByteArrayInputStream(post.getImage())));
        else
            post_image.setImage(new Image(Paths.
                    get("C:\\Users\\baran\\Desktop\\AP\\SBU_gram\\Images\\488px-No-Image-Placeholder.svg.png")
                    .toUri().toString()));

        if (post.getPublisher().getProfilePhoto() != null)
            profile_image.setImage(new Image(new ByteArrayInputStream(post.getPublisher().getProfilePhoto())));
        else
            profile_image.setImage(new Image(Paths.
                    get("C:\\Users\\baran\\Desktop\\AP\\SBU_gram\\Images\\488px-No-Image-Placeholder.svg.png")
                    .toUri().toString()));
        return root;
    }

    public void repost(ActionEvent actionEvent) {
        if (!repost_button.getText().equals("reposted")
                && !post.getWriter().equals(Main.getUser().getUsername())) {
            Main.setPost(post);
            Main.post.setReposts(Main.getUser(), Main.getPost());
            Main.getUser().addPost(Main.getPost());
            repost_button.setText("reposted");
            reports_count.setText(String.valueOf(post.getRepost()));
            API.Repost(Main.getPost() , Main.getUser());
        }
    }

    public void add_comment(ActionEvent actionEvent) throws IOException {
        Main.setPost(post);
        new PageLoader().load("commentPage");
    }

    public void show_comments(ActionEvent actionEvent) throws IOException {
        Main.setPost(post);
        new PageLoader().load("commentPage");
    }

    public void showHisProfile(ActionEvent actionEvent) throws IOException{
        new PageLoader().load("OthersProfiles");
    }

    public void like(ActionEvent actionEvent) {
        if (like_button.getText().equals("like")) {
            Main.setPost(post);
            Main.getPost().likePost(Main.getUser());
            like_button.setText("unlike");
            like_count.setText(String.valueOf(post.getLike()));
            API.like(Main.post, Main.getUser());
        }
        else if (like_button.getText().equals("unlike")){
            Main.setPost(post);
            Main.getPost().unlikePost(Main.getUser());
            like_button.setText("like");
            like_count.setText(String.valueOf(post.getLike()));
            API.unlike(Main.post, Main.getUser());
        }
    }

    public void see_profile(ActionEvent actionEvent) throws IOException {
        if (writer.getText().equals(Main.getUser().getUsername()))
            see_profile.setVisible(false);
        else{
            see_profile.setVisible(true);
            new PageLoader().load("OthersProfiles");
        }

    }
}
