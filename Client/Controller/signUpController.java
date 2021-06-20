package Client.Controller;

import Client.Model.Main;
import Client.Model.PageLoader;
import Common.User;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import Client.*;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class signUpController {
    public TextField firstname_field;
    public TextField lastname_field;
    public DatePicker DateBirth;
    public TextField PhoneNumber;
    public TextField email;
    public TextField username_field;
    public PasswordField password_field;
    public PasswordField confirmation;
    public TextField answer;
    public TextField question;
    public ImageView profileImage;
    public Label wrong_input;
    public Label repeated_username;
    public Label weak_security;
    public String profilePath;
    public User user;
    public static LinkedList<User> profiles = new LinkedList<>();
    private static final String passwordRegex="^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
    private static final Pattern pattern = Pattern.compile(passwordRegex);



    public void signUp_button(ActionEvent actionEvent) throws IOException {
        String password = password_field.getText();
        String pass_confirm = confirmation.getText();
        String username = username_field.getText();

        if (!password.equals(pass_confirm)){
            wrong_input.setVisible(true);
            return;
        }
        else {
            wrong_input.setVisible(false);
        }

        if(!validPassword(password)){
            weak_security.setVisible(true);
            return;
        }
        else {
            weak_security.setVisible(false);
        }
        if(!validUsername(username)){
            repeated_username.setVisible(true);
            return;
        }
        else {
            repeated_username.setVisible(false);
        }

        if(validPassword(password) && validUsername(username)){
            user = new User(username_field.getText());

            user.setFirstname(firstname_field.getText());
            user.setLastName(lastname_field.getText());
            user.setPhoneNumber(PhoneNumber.getText());
            user.setEmail(email.getText());
            user.setProfileImage(profilePath);
            user.setPassword(password_field.getText());
            user.setSecurityQuestion(question.getText());
            user.setSecurityAnswer(answer.getText());
            Main.setUser(user);
            API.signUp(user);
            profiles.add(user);
            new PageLoader().load("login");
        }
    }


    public void Nickname(ActionEvent actionEvent) {
        question.setText("Your Nickname");
        answer.getText();
    }

    public void color(ActionEvent actionEvent) {
        question.setText("Your favourite color");
        answer.getText();
    }

    public void food(ActionEvent actionEvent) {
        question.setText("Your favourite food");
        answer.getText();
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
        profilePath = file.toURI().toString();
        profileImage.setImage(image);
    }

    public static boolean validPassword(String password){
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public static boolean validUsername(String username){
        return !API.isUserNameExists(username);
    }
}

