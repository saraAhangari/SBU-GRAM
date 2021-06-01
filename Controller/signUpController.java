package Controller;

import Model.PageLoader;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;

public class signUpController {
    public TextField Firstname;
    public TextField Lastname;
    public DatePicker DateofBirth;
    public TextField PhoneNumber;

    public void Next(ActionEvent actionEvent) throws IOException{
        Firstname.getText();
        Lastname.getText();
        PhoneNumber.getText();
        new PageLoader().load("SignUpNext");
    }

    public void Sign_In(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("loginPage");
    }

    public void AddImage(ActionEvent actionEvent) {
    }
}
