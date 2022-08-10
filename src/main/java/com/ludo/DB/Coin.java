package com.ludo.DB;

import java.io.Serializable;
import java.util.ArrayList;

public class Coin implements Serializable {
    private static final long serialVersionUID = 1234567L;
    public Integer id;
    public Game game;
    public Integer type;
    public Integer position = 0;


    public int get_abs_position(Integer pos){
        if( type.equals(1)){
            Integer abs = pos + 11;
            if (abs > 44){
                abs = abs - 44;
            }
            if (pos > 43){
                abs = 100 + (pos - 43);
            }
            return abs;
        }
        else if( type.equals(4)){
            Integer abs = pos + 22;
            if (abs > 44){
                abs = abs - 44;
            }
            if (this.position > 43){
                abs = 100 + (this.position - 43);
            }
            return abs;
        }
        else if( type.equals(2)){
            Integer abs = pos + 34;
            if (abs > 44){
                abs = abs - 44;
            }
            if (this.position > 43){
                abs = 100 + (pos - 43);
            }
            return abs;
        }
        else {
            Integer abs = pos;
            if (pos > 43){
                abs = 100 + (pos - 43);
            }
            return abs;
        }
    }
    public int abs_position(){
        return get_abs_position(this.position);
    }
    public Boolean can_move(Integer position, ArrayList<Coin> coins) {
        if (position > 47) {
            return false;
        }
        for (Coin coin : coins) {
            if (position > 43) {
                if (coin.type.equals(this.type) && coin.position.equals(position)) {
                    return false;
                }
            } else {
                Integer coins_abs = coin.abs_position();
                if (coins_abs.equals(this.get_abs_position(position))) {
                    return false;
                }
            }
        }
        return true;
    }
    public Boolean can_move_by_dice(Integer dice, ArrayList<Coin> coins) {
        Integer positionnnn = this.position;
        if (positionnnn == 0 ) {
            if (dice.equals(6)){
                positionnnn = 1;
            }
            else {
                return false;
            }
        }else {
            positionnnn = positionnnn + dice;
        }

        if (positionnnn > 47){
            return false;
        }
        for (Coin coin: coins) {
            if (positionnnn > 43) {
                if (coin.type.equals(this.type) && coin.position.equals(positionnnn)) {
                    return false;
                }
            }else{
                Integer coins_abs = coin.abs_position();
                if (coins_abs.equals(this.get_abs_position(positionnnn))) {
                    return false;
                }
            }
        }
        return true;


    }
}
