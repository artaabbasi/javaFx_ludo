package com.ludo.ludo;

import com.ludo.DB.DB;
import com.ludo.DB.FileManager;
import com.ludo.DB.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.IOException;

public class SignUpController {
    @FXML
    private Button signupSignupButton;
    @FXML
    private Label signupMessageLabel;
    @FXML
    private TextField signupUsernameField;
    @FXML
    private PasswordField signupPasswordField;
    @FXML
    private Button backButton;
    public void back() throws IOException {
        LudoApplication scene = new LudoApplication();
        try {
            scene.onChangeScene("login_view.fxml");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void onSignupButton(ActionEvent clickEvent) throws IOException {
        String username = signupUsernameField.getText();
        String password = signupPasswordField.getText();
        FileManager fm = new FileManager();
        DB db = fm.read_object();
        Boolean flag = true;
        for (User user : db.users){
            if (user.username.equals(username)) {
                signupMessageLabel.setTextFill(Color.web("#F5130C"));
                signupMessageLabel.setText("There is an account with this username!");
                signupUsernameField.clear();
                flag = false;
            }

        }
        if (password.length() < 8){
            signupMessageLabel.setTextFill(Color.web("#F5130C"));
            signupMessageLabel.setText("Password most be more than 8 chars!");
            signupPasswordField.clear();
            flag = false;
        }
        if (flag){
            User user = new User(username, password);
            user.store();
            signupMessageLabel.setTextFill(Color.web("#5DF50C"));
            signupMessageLabel.setText("You signed up successfully!");


            LudoApplication scene = new LudoApplication();
            scene.onChangeScene("login_view.fxml");
        }
    }
}