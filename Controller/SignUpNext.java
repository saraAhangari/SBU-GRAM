package Controller;

import Model.PageLoader;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class SignUpNext {
    public TextField email;
    public TextField username;
    public PasswordField password;
    public PasswordField confirmation;
    public TextField password_hint;
    public MenuButton securityQuestion;
    public MenuItem Nickname;
    public MenuItem color;
    public MenuItem food;

    public void signUp_button(ActionEvent actionEvent) {
        email.getText();
        username.getText();
        password.getText();
        confirmation.getText();
        securityQuestion.show();
    }

    public void Nickname(ActionEvent actionEvent) {
        Nickname.setVisible(true);
    }

    public void color(ActionEvent actionEvent) {
        color.setVisible(true);
    }

    public void food(ActionEvent actionEvent) {
        food.setVisible(true);
    }

    public void back_button(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("signUp");
    }
}
