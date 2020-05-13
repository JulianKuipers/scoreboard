package com.juliankuipers.exceptions;

public class PlayerNotInArrayException extends Exception {

    public PlayerNotInArrayException() {
        super("The given id is not found in the array of players!");
    }
}
