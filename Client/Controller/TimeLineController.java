package Client.Controller;

import Client.Model.PageLoader;
import Common.Post;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.util.Duration;


import java.io.IOException;
import java.util.ArrayList;

public class TimeLineController {
    public ListView postListview;
    public VBox vbox;
    ArrayList<Post> posts = new ArrayList<>();
    Post currentPost = new Post();

    @FXML
    public void initialize() {
    }


    public void show_menu(MouseEvent mouseEvent) {
        TranslateTransition tt = new TranslateTransition(Duration.millis(1500), vbox);
        tt.setToX(103);
        tt.playFromStart();
    }

    public void Profile(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("Profile");
    }

    public void add_post(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("add_post");
    }

    public void log_out(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("log_out");
    }

    public void refresh(ActionEvent actionEvent) {
    }
}
