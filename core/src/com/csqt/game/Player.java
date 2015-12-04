package com.csqt.game;

public class Player extends User {

    private String mName; // string for the player's name

    public Player() { super(); } // empty constructor calls User constructor

    public Player(String name) { // constructor with the players name.
        this();
        mName = name;
    }

    public void placeUnit(Unit.Type unitType, int arrayPosition, float screenPosX, float screenPosY) {


    }
}
