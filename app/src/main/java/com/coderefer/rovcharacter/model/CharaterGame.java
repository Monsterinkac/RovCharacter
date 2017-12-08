package com.coderefer.rovcharacter.model;

/**
 * Created by User on 7/12/2560.
 */

public class CharaterGame {
    public final int id;
    public final String positionx;
    public final String name;
    public final String picture;

    public CharaterGame(String name, String positionx,String picture , int id) {
        this.name = name;
        this.positionx = positionx;
        this.picture = picture;
        this.id = id;
    }

    @Override
    public String toString(){

        return name;
    }
}
