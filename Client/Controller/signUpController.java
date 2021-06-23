package Client.Controller;

import Client.Model.API;
import Client.Model.Main;
import Client.Model.PageLoader;
import Common.User;
import Server.Server;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.*;
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
    public byte[] photo;
    private User user = new User();
    private static final String passwordRegex="^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
    private static final Pattern pattern = Pattern.compile(passwordRegex);

    public static boolean authenticPassword(String password){
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public static boolean authenticUsername(String username){
        return !API.isUserNameExists(username);
    }

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

        if(!authenticPassword(password)){
            weak_security.setVisible(true);
            return;
        }
        else {
            weak_security.setVisible(false);
        }
        if(!authenticUsername(username)){
            repeated_username.setVisible(true);
            return;
        }
        else {
            repeated_username.setVisible(false);
        }

        if(authenticPassword(password) && authenticUsername(username)){
            user.setUsername(username_field.getText());
            user.setFirstname(firstname_field.getText());
            user.setLastName(lastname_field.getText());
            user.setPhoneNumber(PhoneNumber.getText());
            user.setEmail(email.getText());
            user.setPassword(password_field.getText());
            user.setSecurityQuestion(question.getText());
            user.setSecurityAnswer(answer.getText());
            Main.setUser(user);
            API.signUp(user);
            Server.Profiles.add(user);
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

    public void AddImage(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(new Popup());
        FileInputStream fileInputStream = new FileInputStream(file);
        photo = fileInputStream.readAllBytes();
        Image image = new Image(new ByteArrayInputStream(photo));
        user.setPhotoPath(file.toURI().toString());
        profileImage.setImage(image);
        user.setProfilePhoto(fileInputStream.readAllBytes());
    }
}