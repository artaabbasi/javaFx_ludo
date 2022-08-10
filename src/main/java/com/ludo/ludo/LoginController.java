package com.ludo.ludo;

import com.ludo.DB.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.IOException;

public class LoginController {


    @FXML
    private Button loginButton;
    @FXML
    private Button loginSignupButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private TextField loginUsernameField;
    @FXML
    private PasswordField loginPasswordField;

    public void onLoginButton(ActionEvent clickEvent) throws IOException {
        FileManager fm = new FileManager();
        DB db = fm.read_object();
        String username = loginUsernameField.getText();
        String password = loginPasswordField.getText();
        Boolean flag = false;
        for (User user : db.users) {
            if (username.equals(user.username)){
                if (password.equals(user.password)) {
                    loginMessageLabel.setTextFill(Color.web("#5DF50C"));
                    loginMessageLabel.setText("You logged in successfully!");
                    SessionManager sm = new SessionManager();
                    Session session = sm.read_object();
                    session.user = user;
                    session.save();
                    flag = true;
//                    System.out.println("logined");
                    LudoApplication scene = new LudoApplication();
                    scene.onChangeScene("game_start_view.fxml");
                }else {
                    loginMessageLabel.setTextFill(Color.web("#F5130C"));
                    loginMessageLabel.setText("Entered password is wrong!");
                }
            }
        }
        if (!flag){
            loginMessageLabel.setTextFill(Color.web("#F5130C"));
            loginMessageLabel.setText("Entered credentials dose not match our data!");
        }
    }
    public void onSignupButton(ActionEvent clickEvent) throws IOException {
        LudoApplication scene = new LudoApplication();
        scene.onChangeScene("signup_view.fxml");
    }

}