package Controller;

import Model.Post;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class TimeLineController {
    public ListView<Post> postList;
    public TextArea description;
    public TextField title;

    ArrayList<Post> posts = new ArrayList<>();
    Post currentPost = new Post();

    @FXML
    public void initialize() {
        //initialize posts array list to be shown in list view
        for (int i = 0; i < 3; i++) {
            Post p = new Post();
            p.setTitle("post" + i);
            p.setDescription("description" + i);
            p.setWriter("user" + i);
            posts.add(p);
        }

        //show the post array in list view
        postList.setItems(FXCollections.observableArrayList(posts));

        //customize each cell of postList with new graphic object PostItem
        postList.setCellFactory(postList -> new PostItem());
    }

    public void addPost(ActionEvent actionEvent) {
        //set the post features
        currentPost.setTitle(title.getText());
        currentPost.setDescription(description.getText());
        currentPost.setWriter("ali alavi");

        //save the post in arraylist
        posts.add(currentPost);

        //show the arraylist in listview
        postList.setItems(FXCollections.observableArrayList(posts));
        postList.setCellFactory(postList -> new PostItem());

        /*
        if the listview cells are not customized and list view is made of strings
        this code will add new post title to the list view
        postList.getItems().add(currentPost.getTitle());
         */

        currentPost = new Post();

        //empty fields
        title.setText("");
        description.setText("");
    }

    public void clear(ActionEvent actionEvent) {
        title.setText("");
        description.setText("");
    }

    //this function is usable for uncustomized_cell listview of strings
    public void showPost(MouseEvent mouseEvent) {
        Post p = new Post();
        //p.setTitle(postList.getSelectionModel().getSelectedItem());
        for (int i = 0; i < posts.size(); i++) {
            if (posts.get(i).equals(p)) {
                title.setText(posts.get(i).getTitle());
                description.setText(posts.get(i).getDescription());
            }
        }
    }

}
