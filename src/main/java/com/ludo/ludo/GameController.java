package com.ludo.ludo;

import com.ludo.DB.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.util.ArrayList;

public class GameController {
    @FXML
    private Button dice_button;
    @FXML
    private Label blue_nickname;
    @FXML
    private Label dice_lable;
    @FXML
    private Label green_nickname;
    @FXML
    private Label yellow_nickname;
    @FXML
    private Label red_nickname;

    @FXML
    private Circle turn_crcl;

    private Session session;
    private DB db;
    private Integer turn =0;
    private Integer dice =0;
    private ArrayList<Player> players;

    private void setTurn(){
        Boolean flag = false;
        if (turn == 0){
            flag = true;
        }
        while (true) {
            for (Player player : players) {

                if (flag) {
                    turn = player.number;
                    if (turn == 1){
                        turn_crcl.setFill(Color.GREEN);
                    }else if (turn == 2){
                        turn_crcl.setFill(Color.RED);
                    }else if (turn == 3){
                        turn_crcl.setFill(Color.BLUE);
                    }else if (turn == 4){
                        turn_crcl.setFill(Color.YELLOW);
                    }
                    return;
                }
                if (player.number == turn) {
                    flag = true;
                } else {
                    flag = false;
                }
            }
        }
    }
    private void setDice(){
        dice = (int) (Math.random() * 6) + 1;
        dice_lable.setText(dice.toString());
    }
    public void onDiceButton(ActionEvent clickEvent) throws IOException {
        if (dice_button.getText().equals("Start game")){
            players= new ArrayList<Player>();
            FileManager fm = new FileManager();
            db = fm.read_object();
            SessionManager sm = new SessionManager();
            session = sm.read_object();
            dice_button.setText("Dice");
            for (Player player : db.players){

                if (player.game.uuid.equals(session.game.uuid)){
                    players.add(player);
                }
            }
            for (Player player : players){

                if (player.number == 1){
                    green_nickname.setText(player.nickname);
                }
                if (player.number == 2){
                    red_nickname.setText(player.nickname);
                }
                if (player.number == 3){
                    blue_nickname.setText(player.nickname);
                }
                if (player.number == 4){
                    yellow_nickname.setText(player.nickname);
                }
            }

            setTurn();
            return;
        }
        if (dice == 0){
            setDice();
        }else {
            dice_lable.setText("* "+dice.toString()+" *");
        }



    }

}
