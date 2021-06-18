package Client.Controller;

import Client.Model.PageLoader;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.IOException;


public class ForgetPass {
    public TextField Answer;
    public TextField question;
    public TextField new_pass;
    public TextField email;
    public PasswordField confirmation;
    public Label wrong_input;

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
        String email_field = email.getText();
        String pass_field = new_pass.getText();
        String confirm_field = confirmation.getText();

        if (!signUpController.validPassword(pass_field)){
            //weak security label
        }

        if (confirm_field.equals(pass_field)) {
            wrong_input.setVisible(false);
            for (int i = 0; i < signUpController.profiles.size(); i++) {
                if (signUpController.profiles.get(i).getEmail().equals(email_field)) {
                    signUpController.profiles.get(i).setPassword(pass_field);
                    break;
                }
            }
            new PageLoader().load("login");
        }
        else {
            wrong_input.setVisible(true);
        }
    }

    public void sign_up(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("signUp");
    }
}

