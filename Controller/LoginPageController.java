package Controller;

import Model.PageLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;


public class LoginPageController {

    @FXML
    public TextField username_field;
    public PasswordField password_field;
    public Label wrong_input;
    public TextField password_visible;
    public Button signUp_Button;
    public Button LogIn_Button;
    public Button Forger_Password;

    public void login(ActionEvent actionEvent) throws IOException {
        String username = username_field.getText();
        String password;

        if (password_field.isVisible())
            password = password_field.getText();
        else
            password = password_visible.getText();

        if (username.equalsIgnoreCase("Baran") && password.equalsIgnoreCase("rain")) {
            wrong_input.setVisible(false);
            new PageLoader().load("timeLine");
        } else {
            wrong_input.setVisible(true);
        }
    }

    public void show_password(ActionEvent actionEvent) {
        if (!password_visible.isVisible()) {
            password_visible.setVisible(true);
            password_field.setVisible(false);
            password_visible.setText(password_field.getText());
        }
        else {
            password_visible.setVisible(false);
            password_field.setVisible(true);
            password_field.setText(password_visible.getText());
        }
    }

    public void Sign_up(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("signUp");
    }

    public void Forger_Password(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("ForgetPass");
    }
}
