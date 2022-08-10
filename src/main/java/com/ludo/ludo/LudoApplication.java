package com.ludo.ludo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LudoApplication extends Application {
    public static Stage stg;
    @Override
    public void start(Stage stage) throws IOException {
        stg = stage;

        FXMLLoader fxmlLoader = new FXMLLoader(LudoApplication.class.getResource("welcome-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
    public void onChangeScene(String fxml) throws IOException {
        FXMLLoader fxmlLoad = new FXMLLoader(LudoApplication.class.getResource(fxml));
        Scene scene = new Scene(fxmlLoad.load(), 600, 400);
        stg.setScene(scene);
    }
}