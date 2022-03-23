package com.backbase.interview.kalahgame.serverside.dao.entity;

import com.backbase.interview.kalahgame.serverside.dao.api.IGame;
import com.backbase.interview.kalahgame.serverside.dao.entity.state.GameState;
import com.backbase.interview.kalahgame.serverside.dao.entity.state.IPlayer;
/**
 * Created by M.jalian on 22/03/2022.
 */

public class KalahGame implements IGame {
    
    public static final int PLAYER_ONE_KALAH_ID = 7;
    public static final int PLAYER_TWO_KALAH_ID = 14;
    
    private long id;
    private GameState gameState;
    private GameBoard gameBoard;
    private IPlayer  playerOne;
    private IPlayer  playerTwo;
    private String winner;
    
    public KalahGame (GameState gameState) {
        this.gameState = gameState;
        this.gameBoard = new GameBoard();
    }
    
    public long getId () {
        return id;
    }
    
    public void setId (long id) {
        this.id = id;
    }
    
    public GameState getGameState () {
        return gameState;
    }
    
    public void setGameState (GameState gameState) {
        this.gameState = gameState;
    }
    
    public GameBoard getGameBoard () {
        return gameBoard;
    }
    
    public void setGameBoard (GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }
    
    public IPlayer getPlayerOne () {
        return playerOne;
    }
    
    public void setPlayerOne (IPlayer playerOne) {
        this.playerOne = playerOne;
    }
    
    public IPlayer getPlayerTwo () {
        return playerTwo;
    }
    
    public void setPlayerTwo (IPlayer playerTwo) {
        this.playerTwo = playerTwo;
    }
    
    public String getWinner () {
        return winner;
    }
    
    public void setWinner (String winner) {
        this.winner = winner;
    }
}
