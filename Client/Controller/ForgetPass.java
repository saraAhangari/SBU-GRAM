package Client.Controller;

import Client.API;
import Client.Model.PageLoader;
import Common.User;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.util.regex.Matcher;

import static Client.Controller.signUpController.validPassword;


public class ForgetPass {
    public TextField Answer;
    public TextField question;
    public TextField new_pass;
    public TextField username;
    public PasswordField confirmation;
    public Label wrong_input;
    public Label weak_security;
    public Label wrong_answer;
    public Label wrong_user;


    public void nickname(ActionEvent actionEvent) {
        question.setText("Your Nickname");
        Answer.getText();
    }

    public void color(ActionEvent actionEvent) {
        question.setText("Your favourite color");
        Answer.getText();
    }

    public void food(ActionEvent actionEvent) {
        question.setText("Your favourite food");
        Answer.getText();
    }

    public void login(ActionEvent actionEvent) throws IOException {
        String username_field = username.getText();
        String question_field = question.getText();
        String answer_field = Answer.getText();
        String pass_field = new_pass.getText();
        String confirm_field = confirmation.getText();

        if(!validUsername(username_field)){
            wrong_user.setVisible(true);
            return;
        }
        else{
            wrong_user.setVisible(false);
        }
        if(!validAnswer(username_field , question_field,answer_field)){
            wrong_answer.setVisible(true);
            return;
        }
        else {
            wrong_answer.setVisible(false);
        }
        if(validAnswer(username_field , question_field,answer_field) && validUsername(username_field)){
            wrong_answer.setVisible(false);
            wrong_user.setVisible(false);
        }
        if(!validPassword(pass_field)){
            weak_security.setVisible(true);
        }
        else {
            if (!pass_field.equals(confirm_field)){
                wrong_input.setVisible(true);
                return;
            }
            weak_security.setVisible(false);
            wrong_input.setVisible(false);
            ChangePass(username_field, pass_field);
            new PageLoader().load("login");
        }
    }

    private boolean validUsername(String username) {
        return !API.isUserNameExists(username);
    }

    public void sign_up(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("signUp");
    }

    public boolean validAnswer(String email , String question , String answer){
        return API.ForgetPass(email , question , answer);
    }
    public void ChangePass(String username , String newPass){
        API.changePassword(username, newPass);
    }

}

