package com.ludo.ludo;

import com.ludo.DB.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

import java.io.IOException;

public class GameStartController {

    @FXML
    private RadioButton green_rdio_butt;
    @FXML
    private RadioButton red_rdio_butt;
    @FXML
    private RadioButton blue_rdio_butt;
    @FXML
    private RadioButton yellow_rdio_butt;
    @FXML
    private TextField green_nick_name;
    @FXML
    private TextField red_nick_name;
    @FXML
    private TextField blue_nick_name;
    @FXML
    private TextField yellow_nick_name;

    public void onStartButton(ActionEvent clickEvent) throws IOException {


        Boolean has_green = false;
        Boolean has_red = false;
        Boolean has_blue = false;
        Boolean has_yellow = false;
        if (green_rdio_butt.isSelected()){
            has_green = true;
        }
        if (red_rdio_butt.isSelected()){
            has_red = true;
        }
        if (blue_rdio_butt.isSelected()){
            has_blue = true;
        }
        if (yellow_rdio_butt.isSelected()){
            has_yellow = true;
        }
        if (!has_green && !has_red && !has_blue && !has_yellow){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("You must select at least one player!");
            alert.showAndWait();
            return;
        }else {
            Game game = new Game();
            game.store();
            SessionManager sm = new SessionManager();
            Session session = sm.read_object();
            session.game = game;
            session.save();
            if (has_green){
                if (green_nick_name.getText().equals("")){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("You must enter a name for green player!");
                    alert.showAndWait();
                    return;
                }else {
                    Player green_player = new Player();
                    green_player.nickname = green_nick_name.getText();
                    green_player.game = game;
                    green_player.number = 1;
                    green_player.store();
                }
            }
            if (has_red){
                if (red_nick_name.getText().equals("")){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("You must enter a name for red player!");
                    alert.showAndWait();
                    return;
                }else {
                    Player red_player = new Player();
                    red_player.nickname = red_nick_name.getText();
                    red_player.game = game;
                    red_player.number = 2;
                    red_player.store();
                }
            }
            if (has_blue){
                if (blue_nick_name.getText().equals("")){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("You must enter a name for blue player!");
                    alert.showAndWait();
                    return;
                }else {
                    Player blue_player = new Player();
                    blue_player.nickname = blue_nick_name.getText();
                    blue_player.game = game;
                    blue_player.number = 3;
                    blue_player.store();

                }
            }
            if (has_yellow){
                if (yellow_nick_name.getText().equals("")){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("You must enter a name for yellow player!");
                    alert.showAndWait();
                    return;
                }else {
                    Player yellow_player = new Player();
                    yellow_player.nickname = yellow_nick_name.getText();
                    yellow_player.game = game;
                    yellow_player.number = 4;
                    yellow_player.store();
                }
            }

        }

    }
}
