package Client.Controller;

import Client.Model.PageLoader;
import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ForgetPass {
    public TextField Answer;
    public TextField question;
    public TextField new_pass;
    public PasswordField confirmation;


    public void first_next(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("ForgetPassNext");
    }

    public void first_back(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("login");
    }

    public void second_next(ActionEvent actionEvent) {
        new_pass.getText();
        confirmation.getText();
    }

    public void second_back(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("ForgetPass");
    }

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
}

