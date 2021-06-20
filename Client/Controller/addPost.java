package Client.Controller;

import Client.Model.PageLoader;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;

public class addPost {
    public TextField post_title;
    public TextArea post_description;
    public VBox vbox;

    @FXML
    public void initialize(){
    }

    public void add_image(ActionEvent actionEvent) {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        Label label = new Label("no files selected");
        File file = fileChooser.showOpenDialog(stage);
        if (file != null)
            label.setText(file.getAbsolutePath() + "selected");
        assert file != null;
        Image image=new Image(file.toURI().toString());
    }

    public void publish(ActionEvent actionEvent) {
        /*for (int i = 0; i <signUpController.profiles.size() ; i++) {
            if (signUpController.profiles.get(i).getUsername().equals()){
                //add kone post ro be profile yaru
            }
        }
        add kone post ro be time line ke age home ro bezanim time line ro neshun bede*/
    }

    public void show_menu(MouseEvent mouseEvent) {
        TranslateTransition tt = new TranslateTransition(Duration.millis(1500), vbox);
        tt.setToX(105);
        tt.playFromStart();
    }

    public void Home(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("timeLine");
    }

    public void Profile(MouseEvent mouseEvent) {
    }

    public void log_out(MouseEvent mouseEvent) {
    }
}
