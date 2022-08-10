package com.ludo.DB;

import java.io.Serializable;
import java.util.ArrayList;

public class DB implements Serializable {
    private static final long serialVersionUID = 1234567L;
    public ArrayList<User> users = new ArrayList<User>();
    public ArrayList<Game> games = new ArrayList<Game>();
    public ArrayList<Player> players = new ArrayList<Player>();
}