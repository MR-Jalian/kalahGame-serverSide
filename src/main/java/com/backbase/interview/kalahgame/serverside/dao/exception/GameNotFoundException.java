package com.backbase.interview.kalahgame.serverside.dao.exception;

/**
 * Created by M.jalian on 22/03/2022.
 */

public class GameNotFoundException extends RuntimeException {
    public GameNotFoundException (String message) {
        super(message);
    }
}
