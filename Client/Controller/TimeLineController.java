package Client.Controller;

import Client.Model.API;
import Client.Model.Main;
import Client.Model.PageLoader;
import Common.Post;
import Common.User;
import Server.Server;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
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
    public static ArrayList<Post> postArrayList;
    public User user;


    @FXML
    public void initialize(){
    }

    public void show_menu(MouseEvent mouseEvent) {
        TranslateTransition tt = new TranslateTransition(Duration.millis(1500), vbox);
        tt.setToX(103);
        tt.playFromStart();
    }

    public void Profile(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("myProfile");
    }

    public void add_post(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("add_post");
    }

    public void log_out(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("login");
    }

    public void refresh(ActionEvent actionEvent) {
        postArrayList = API.getPosts(Main.getUser());
        for (int i = 0; i <Main.profiles.size() ; i++) {
            if (Main.getUser().getFollowings().contains(Main.profiles.get(i))){
                postArrayList.addAll(Main.profiles.get(i).getPosts());
            }
        }
        postListview.setItems(FXCollections.observableArrayList(postArrayList));
        postListview.setCellFactory(postListview -> new PostItem());
    }

    public void search(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("Search");
    }
}
