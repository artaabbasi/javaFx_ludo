package com.ludo.DB;

import java.io.Serializable;
import java.util.ArrayList;

public class Game implements Serializable {
    private static final long serialVersionUID = 1234567L;
    public void store(){
        FileManager fm = new FileManager();
        DB db = fm.read_object();
        db.games.add(this);
        fm.write_object(db);
    }
    public void save(){

        FileManager fm = new FileManager();
        DB db = fm.read_object();
        ArrayList<Game> games = new ArrayList<Game>();
        games.add(this);
        db.games.remove(games);
        db.games.add(this);

        fm.write_object(db);
    }
}
