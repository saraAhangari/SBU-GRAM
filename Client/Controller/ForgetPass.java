package Client.Controller;

import Client.Model.API;
import Client.Model.PageLoader;
import Server.Server;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.IOException;

import static Client.Controller.signUpController.authenticUsername;
import static Client.Controller.signUpController.authenticPassword;

public class ForgetPass {
    public TextField Answer;
    public TextField question;
    public TextField new_pass;
    public TextField username;
    public PasswordField confirmation;
    public Label wrong_input; //password & its confirmation doesn't match
    public Label weak_security; //password & its regex doesn't match
    public Label wrong_answer; //security question and its answer doesn't match
    public Label wrong_user; //user not found


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

    private boolean correctPassword(){
        return new_pass.getText().equals(confirmation.getText());
    }
    private boolean validAnswers() {
        for (int i = 0; i < Server.Profiles.size(); i++) {
            if (Server.Profiles.get(i).getUsername().equals(username.getText())) {
                if (Server.Profiles.get(i).getSecurityQuestion().equals(question.getText())
                        && Server.Profiles.get(i).getSecurityAnswer().equals(Answer.getText())){
                    return true;
                }
            }
        }
        return false;
    }

    public void ChangePass() {
        for (int i = 0; i < Server.Profiles.size(); i++) {
            if (Server.Profiles.get(i).getUsername().equals(username.getText())) {
                Server.Profiles.get(i).setPassword(new_pass.getText());
                break;
            }
        }
    }

    public void Login(ActionEvent actionEvent) throws IOException {
        String username_field = username.getText();
        String pass_field = new_pass.getText();

        if(authenticUsername(username_field)){
            wrong_user.setVisible(true);
            return;
        }
        else
            wrong_user.setVisible(false);

        if (!authenticPassword(pass_field)){
            weak_security.setVisible(true);
            return;
        }
        else
            weak_security.setVisible(false);

        if (!correctPassword()){
            wrong_input.setVisible(true);
            return;
        }
        else
            wrong_input.setVisible(false);

        if (validAnswers()){
            wrong_answer.setVisible(true);
            return;
        }
        else
            wrong_answer.setVisible(false);

        if (API.ForgetPass(username_field , pass_field)){
            ChangePass();
            new PageLoader().load("login");
        }

    }

    public void sign_up(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("signUp");
    }

}

