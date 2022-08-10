package com.ludo.ludo;

import com.ludo.DB.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Consumer;

public class GameController {
    @FXML
    private Button dice_button;

    @FXML
    private Label blue_nickname;
    @FXML
    private Label coin_1_text;
    @FXML
    private Label blue_coin_1;
    @FXML
    private Label blue_coin_2;
    @FXML
    private Label blue_coin_3;
    @FXML
    private Label blue_coin_4;
    @FXML
    private Label green_coin_1;
    @FXML
    private Label green_coin_2;
    @FXML
    private Label green_coin_3;
    @FXML
    private Label green_coin_4;
    @FXML
    private Label red_coin_1;
    @FXML
    private Label red_coin_2;
    @FXML
    private Label red_coin_3;
    @FXML
    private Label red_coin_4;
    @FXML
    private Label yellow_coin_1;
    @FXML
    private Label yellow_coin_2;
    @FXML
    private Label yellow_coin_3;
    @FXML
    private Label yellow_coin_4;
    @FXML
    private Label coin_2_text;
    @FXML
    private Label coin_3_text;
    @FXML
    private Label coin_4_text;
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
    @FXML
    private Circle turn_crcl1;

    private Session session;
    private DB db;
    private Integer turn =0;
    private Integer dice =0;
    private ArrayList<Player> players;
    private ArrayList<Coin> coins;

    private void setTurn() throws IOException {
        Boolean flag = false;
        if (turn == 0){
            flag = true;
        }
        ArrayList<Coin> this_type_coins = new ArrayList<Coin>();
        for (Coin c : coins) {
            if (c.type.equals(turn)) {
                this_type_coins.add(c);
            }
        }
        Boolean win = false;
        for (Coin c : this_type_coins) {
            if (c.position > 43) {
                win = true;
            }else {
                win = false;
                break;
            }
        }
        if (win){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Winner");
            alert.setHeaderText("Player " + turn + " won the game!");
            alert.showAndWait();
            LudoApplication scene = new LudoApplication();
            scene.onChangeScene("game_start_view.fxml");
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

                    for (Coin c : this_type_coins){
                        Integer position = c.abs_position();
                        String pos_text = "";
                        if (c.position == 0) {
                            pos_text = " ";
                        }
                        else if (position > 100) {
                            Integer posss = position - 100;
                            pos_text = "#" + posss.toString();
                        }
                        else {
                            pos_text = position.toString();
                        }

                        if (c.id == 1) {
                            coin_1_text.setText(pos_text);
                        }else if (c.id == 2) {
                            coin_2_text.setText(pos_text);
                        }else if (c.id == 3) {
                            coin_3_text.setText(pos_text);
                        }else if (c.id == 4) {
                            coin_4_text.setText(pos_text);
                        }


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
        if (turn == 1){
            turn_crcl1.setFill(Color.GREEN);
        }else if (turn == 2){
            turn_crcl1.setFill(Color.RED);
        }else if (turn == 3){
            turn_crcl1.setFill(Color.BLUE);
        }else if (turn == 4){
            turn_crcl1.setFill(Color.YELLOW);
        }
    }
    public void onDiceButton(ActionEvent clickEvent) throws IOException {
        if (dice_button.getText().equals("Start game")){
            coins = new ArrayList<Coin>();
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
            int[] i_arrey = new int[]{ 1,2,3,4 };

            for (Player player : players){

                if (player.number == 1){
                    green_nickname.setText(player.nickname);
                    for (int i : i_arrey){
                        Coin coin = new Coin();
                        coin.id = i;
                        coin.game = session.game;
                        coin.type = 1;
                        coin.position = 0;
                        coins.add(coin);
                    }
                }
                if (player.number == 2){
                    red_nickname.setText(player.nickname);
                    for (int i : i_arrey){
                        Coin coin = new Coin();
                        coin.id = i;
                        coin.game = session.game;
                        coin.type = 2;
                        coin.position = 0;
                        coins.add(coin);

                    }
                }
                if (player.number == 3){
                    blue_nickname.setText(player.nickname);
                    for (int i : i_arrey){
                        Coin coin = new Coin();
                        coin.id = i;
                        coin.game = session.game;
                        coin.type = 3;
                        coin.position = 0;
                        coins.add(coin);

                    }
                }
                if (player.number == 4){
                    yellow_nickname.setText(player.nickname);
                    for (int i : i_arrey){
                        Coin coin = new Coin();
                        coin.id = i;
                        coin.game = session.game;
                        coin.type = 4;
                        coin.position = 0;
                        coins.add(coin);

                    }
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

    public void onCoinButton(ActionEvent clickEvent) throws IOException {

        Button button = (Button) clickEvent.getTarget();
        String button_text = button.getText();
        if (button_text.equals("Next")){
            ArrayList<Coin> this_type_coins = new ArrayList<Coin>();
            for (Coin c : coins) {
                if (c.type.equals(turn)) {
                    this_type_coins.add(c);
                }
            }
            Boolean win = false;
            for (Coin c : this_type_coins) {
                if (c.can_move_by_dice(dice, coins)) {
                    win = true;
                    break;
                }else {
                    win = false;
                }
            }
            if (win) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText("You can move, MOVE!");
                alert.showAndWait();
                return;
            }
            else {
                dice = 0;
                setTurn();
                return;
            }
        }else {
            String id = button_text.split(" ")[1];
            Integer coin_id = Integer.parseInt(id);
            Coin use_coin = new Coin();
            for (Coin c : coins) {
//                System.out.println(c.id);
////                System.out.println(c.type);
//                System.out.println(coin_id);
////                System.out.println(turn);

                if (c.id.equals(coin_id) && c.type.equals(turn)) {
                    use_coin = c;
                }
            }
            if (use_coin.position == 0) {
                if (dice == 6) {
                    if (use_coin.can_move(1, coins)) {
                        use_coin.position = 1;
                    }else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Error");
                        alert.setHeaderText("You can't move this coin");
                        alert.showAndWait();
                        return;
                    }
                    Integer position = use_coin.abs_position();
                    String pos_text = "";
                    if (use_coin.position == 0) {
                        pos_text = " ";
                    }
                    else if (position > 100) {
                        Integer posss = position - 100;
                        pos_text = "#" + posss.toString();
                    }
                    else {
                        pos_text = position.toString();
                    }


                    if (use_coin.id == 1) {
                        if (use_coin.type == 1) {
                            green_coin_1.setText(pos_text);
                        } else if (use_coin.type == 2) {
                            red_coin_1.setText(pos_text);
                        } else if (use_coin.type == 3) {
                            blue_coin_1.setText(pos_text);
                        } else if (use_coin.type == 4) {
                            yellow_coin_1.setText(pos_text);
                        }
                    } else if (use_coin.id == 2) {
                        if (use_coin.type == 1) {
                            green_coin_2.setText(pos_text);
                        } else if (use_coin.type == 2) {
                            red_coin_2.setText(pos_text);
                        } else if (use_coin.type == 3) {
                            blue_coin_2.setText(pos_text);
                        } else if (use_coin.type == 4) {
                            yellow_coin_2.setText(pos_text);
                        }
                    } else if (use_coin.id == 3) {
                        if (use_coin.type == 1) {
                            green_coin_3.setText(pos_text);
                        } else if (use_coin.type == 2) {
                            red_coin_3.setText(pos_text);
                        } else if (use_coin.type == 3) {
                            blue_coin_3.setText(pos_text);
                        } else if (use_coin.type == 4) {
                            yellow_coin_3.setText(pos_text);
                        }
                    } else if (use_coin.id == 4) {
                        if (use_coin.type == 1) {
                            green_coin_4.setText(pos_text);
                        } else if (use_coin.type == 2) {
                            red_coin_4.setText(pos_text);
                        } else if (use_coin.type == 3) {
                            blue_coin_4.setText(pos_text);
                        } else if (use_coin.type == 4) {
                            yellow_coin_4.setText(pos_text);
                        }
                    }
                    dice = 0;
                    setTurn();
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error");
                    alert.setHeaderText("You can't move this coin with out 6!Good luck");
                    alert.showAndWait();
                    return;

                }
            } else {
                Integer position = use_coin.position + dice;
                if (use_coin.can_move(position, coins)) {
                    use_coin.position = position;
                }else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error");
                    alert.setHeaderText("You can't move this coin");
                    alert.showAndWait();
                    return;
                }
                position = use_coin.abs_position();
                String pos_text = "";
                if (use_coin.position == 0) {
                    pos_text = " ";
                }
                else if (position > 100) {
                    Integer posss = position - 100;
                    pos_text = "#" + posss.toString();
                }
                else {
                    pos_text = position.toString();
                }
                if (use_coin.id == 1) {
                    if (use_coin.type == 1) {
                        green_coin_1.setText(pos_text);
                    } else if (use_coin.type == 2) {
                        red_coin_1.setText(pos_text);
                    } else if (use_coin.type == 3) {
                        blue_coin_1.setText(pos_text);
                    } else if (use_coin.type == 4) {
                        yellow_coin_1.setText(pos_text);
                    }
                } else if (use_coin.id == 2) {
                    if (use_coin.type == 1) {
                        green_coin_2.setText(pos_text);
                    } else if (use_coin.type == 2) {
                        red_coin_2.setText(pos_text);
                    } else if (use_coin.type == 3) {
                        blue_coin_2.setText(pos_text);
                    } else if (use_coin.type == 4) {
                        yellow_coin_2.setText(pos_text);
                    }
                } else if (use_coin.id == 3) {
                    if (use_coin.type == 1) {
                        green_coin_3.setText(pos_text);
                    } else if (use_coin.type == 2) {
                        red_coin_3.setText(pos_text);
                    } else if (use_coin.type == 3) {
                        blue_coin_3.setText(pos_text);
                    } else if (use_coin.type == 4) {
                        yellow_coin_3.setText(pos_text);
                    }
                } else if (use_coin.id == 4) {
                    if (use_coin.type == 1) {
                        green_coin_4.setText(pos_text);
                    } else if (use_coin.type == 2) {
                        red_coin_4.setText(pos_text);
                    } else if (use_coin.type == 3) {
                        blue_coin_4.setText(pos_text);
                    } else if (use_coin.type == 4) {
                        yellow_coin_4.setText(pos_text);
                    }
                }
                dice = 0;
                setTurn();
            }
        }

    }
}
