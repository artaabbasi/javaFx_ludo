package com.ludo.ludo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class WellcomeController {

    @FXML
    public void onSignInClick() {
        LudoApplication scene = new LudoApplication();
        try {
            scene.onChangeScene("login_view.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void onSignUpClick() {
        LudoApplication scene = new LudoApplication();
        try {
            scene.onChangeScene("signup_view.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}