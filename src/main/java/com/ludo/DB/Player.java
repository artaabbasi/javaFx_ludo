package com.ludo.DB;

import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable {
    private static final long serialVersionUID = 1234567L;
    public String nickname;
    public Integer number;
    public Game game;

    public void store(){
        FileManager fm = new FileManager();
        DB db = fm.read_object();
        db.players.add(this);
        fm.write_object(db);
    }
    public void save(){

        FileManager fm = new FileManager();
        DB db = fm.read_object();
        ArrayList<Player> players = new ArrayList<Player>();
        players.add(this);
        db.players.remove(players);
        db.players.add(this);

        fm.write_object(db);
    }
}
