package Client.Controller;

import Client.Model.PageLoader;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import Client.*;
import java.io.File;
import java.io.IOException;

public class signUpController {
    public TextField Firstname;
    public TextField Lastname;
    public DatePicker DateofBirth;
    public TextField PhoneNumber;
    public TextField email;
    public TextField username;
    public PasswordField password;
    public PasswordField confirmation;
    public TextField password_hint;
    public TextField question;
    public ImageView profileImage;
    public Label wrong_input;
    public Label repeated_username;

    public void Next(ActionEvent actionEvent) throws IOException{
        Firstname.getText();
        Lastname.getText();
        PhoneNumber.getText();
        new PageLoader().load("SignUpNext");
    }

    public void Nickname(ActionEvent actionEvent) {
        question.setText("Your Nickname");
        password_hint.getText();
    }

    public void color(ActionEvent actionEvent) {
        question.setText("Your favourite color");
        password_hint.getText();
    }

    public void food(ActionEvent actionEvent) {
        question.setText("Your favourite food");
        password_hint.getText();
    }

    public void Sign_In(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("login");
    }

    public void AddImage(ActionEvent actionEvent) {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        Label label = new Label("no files selected");
        File file = fileChooser.showOpenDialog(stage);
        if (file != null)
            label.setText(file.getAbsolutePath() + "selected");
        assert file != null;
        Image image=new Image(file.toURI().toString());
        profileImage.setImage(image);
    }

    //Page 2
    public void signUp_button(ActionEvent actionEvent) throws IOException {
        String email_address = email.getText();
        String usernameText = username.getText();
        String passwordText = password.getText();
        String confirmationText = confirmation.getText();

        if (!API.signUp(usernameText , passwordText)) {
            repeated_username.setVisible(true);
            /*email_address = email.getText();
            usernameText = username.getText();
            passwordText = password.getText();
            confirmationText = confirmation.getText();*/
        }
        if (!passwordText.equals(confirmationText)){
            wrong_input.setVisible(true);
            /*password.getText();
            confirmation.getText();*/
        }
        new PageLoader().load("login");
    }

    public void back_button(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("signUp");
    }
}

