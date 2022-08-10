package com.ludo.DB;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private static final long serialVersionUID = 1234567L;
    public String username;
    public String password;

    public User(String username, String password){
        this.password = password;
        this.username = username;
    }

    public void store(){
        FileManager fm = new FileManager();
        DB db = fm.read_object();
        db.users.add(this);
        fm.write_object(db);
    }
    public void save(){

        FileManager fm = new FileManager();
        DB db = fm.read_object();
        ArrayList<User> users = new ArrayList<User>();
        users.add(this);
        db.users.remove(users);
        db.users.add(this);

        fm.write_object(db);
    }
}