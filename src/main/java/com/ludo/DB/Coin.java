package com.ludo.DB;

import java.io.Serializable;

public class Coin implements Serializable {
    private static final long serialVersionUID = 1234567L;
    public Integer id;
    public Game game;
    public Integer type;
    public Integer position = 0;
    public Integer abs_position = 0;
}
