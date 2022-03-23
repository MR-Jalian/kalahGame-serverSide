package com.backbase.interview.kalahgame.serverside.dao.entity.state;

/**
 * Created by M.jalian on 22/03/2022.
 */
public enum GameState {
    
    CREATED("C"),
    PLAYER_ONE_TURN("P1"),
    PLAYER_TWO_TURN("P2"),
    FINISHED("F");
    
    private String abbreviation;
    
    GameState (String abbreviation) {
        this.abbreviation = abbreviation;
    }
    
    public static GameState getInstance(String abbreviation) {
        
        for (GameState g : GameState.values()) {
            
            if (g.abbreviation.equals(abbreviation)) {
                return g;
            }
        }
        throw new RuntimeException("bad abbreviation for GameState : "+ abbreviation);
    }
    
    
}
