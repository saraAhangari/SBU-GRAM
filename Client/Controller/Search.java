package Client.Controller;

import Client.Model.API;
import Client.Model.Main;
import Client.Model.PageLoader;
import Common.User;
import Server.Server;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;

public class Search {
    public TextField search;
    public VBox vbox;
    public ListView search_profiles;
    public ArrayList<User> userArrayList = new ArrayList<>();

    public void show_menu(MouseEvent mouseEvent) {
        TranslateTransition tt = new TranslateTransition(Duration.millis(1500), vbox);
        tt.setToX(103);
        tt.playFromStart();
    }

    public void search(MouseEvent mouseEvent) {
        String username2Find = search.getText();
        userArrayList = API.getUser(username2Find);
        if (userArrayList==null){
            Alert alert = new Alert(Alert.AlertType.WARNING , "User doesn't exist !");
            alert.showAndWait();
        }
        search_profiles.setItems(FXCollections.observableArrayList(userArrayList));
        search_profiles.setCellFactory(search_profiles -> new profileItem());
    }

    public void Home(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("timeLine");
    }

    public void Profile(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("myProfile");
    }

    public void add_post(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("add_post");
    }

    public void log_out(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("logout");
    }

}
