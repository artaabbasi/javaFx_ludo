package com.ludo.DB;

import java.io.Serializable;

public class Session implements Serializable {
    private static final long serialVersionUID = 1234567L;
    public User user;
    public Boolean is_in_game;
    public Game game;

    public void save(){
        SessionManager sm = new SessionManager();
        sm.write_object(this);
    }
}
