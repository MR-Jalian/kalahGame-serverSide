package com.backbase.interview.kalahgame.serverside.game.logic;

import com.backbase.interview.kalahgame.serverside.dao.api.IGameDao;
import com.backbase.interview.kalahgame.serverside.dao.entity.KalahGame;
import com.backbase.interview.kalahgame.serverside.dao.entity.Pit;
import com.backbase.interview.kalahgame.serverside.dao.entity.state.GameState;
import com.backbase.interview.kalahgame.serverside.dao.entity.state.PlayerOne;
import com.backbase.interview.kalahgame.serverside.dao.entity.state.PlayerTwo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by M.jalian on 22/03/2022.
 */
@Component
public class KalahGameController {
    
    private static KalahGameController controller;
    
    @Autowired
    private IGameDao gameDao;
    private Queue<KalahGame> gameQueue = new ArrayBlockingQueue<>(5);
    
    private KalahGameController () {
    }
    public static KalahGameController getController() {
        
        if (controller != null) {
            return controller;
        } else {
            synchronized (KalahGameController.class) {
                if (controller == null) {
                    controller = new KalahGameController();
                }
            }
            return controller;
        }
    }
    
    public void setGameDao (IGameDao gameDao) {
        this.gameDao = gameDao;
    }
    
    public long createGame() {
    
        KalahGame kalahGame = gameQueue.poll();
        if (kalahGame == null) {
            kalahGame = new KalahGame(GameState.CREATED);
            kalahGame.setPlayerOne(new PlayerOne());
           long id = gameDao.saveGame(kalahGame);
           gameQueue.add(kalahGame);
           return id;
        } else {
            kalahGame.setPlayerTwo(new PlayerTwo());
            kalahGame.setGameState(GameState.PLAYER_ONE_TURN);
            gameDao.update(kalahGame);
            return kalahGame.getId();
        }
    }
    
    public String makeMove(long gameId, int pitId) {
        
        KalahGame kalahGame = (KalahGame) gameDao.findById(gameId);
        GameState currentGameState = kalahGame.getGameState();
        GameState nextGameState;
        
        switch (currentGameState) {
            
            case CREATED:
                throw new IllegalArgumentException("You should wait another player to join");
            case FINISHED:
                throw new IllegalArgumentException("The game is already finished");
            case PLAYER_ONE_TURN:
                if (!kalahGame.getPlayerOne().getSelectablePits().contains(pitId) ||
                kalahGame.getGameBoard().getPits().get(pitId).getStoneBalance() ==0) {
                    throw new IllegalArgumentException("Its not your turn or select invalid pit");
                }
                nextGameState =
                        kalahGame.getPlayerOne()
                                .makeMove(pitId, kalahGame.getGameBoard().getPits());
                kalahGame.setGameState(nextGameState);
                kalahGame.setGameState(checkWinner(kalahGame));
                return kalahGame.getGameBoard().getStatus();
            case PLAYER_TWO_TURN:
                if (!kalahGame.getPlayerTwo().getSelectablePits().contains(pitId) ||
                            kalahGame.getGameBoard().getPits().get(pitId).getStoneBalance() ==0) {
                    throw new IllegalArgumentException("Its not your turn or select invalid pit");
                }
                nextGameState =
                        kalahGame.getPlayerTwo()
                                .makeMove(pitId, kalahGame.getGameBoard().getPits());
                kalahGame.setGameState(nextGameState);
                kalahGame.setGameState(checkWinner(kalahGame));
                return kalahGame.getGameBoard().getStatus();
            default:
                return kalahGame.getGameBoard().getStatus();
        }
    }
    
    private GameState checkWinner(KalahGame kalahGame) {
        int awardedStones =0;
        GameState finalState = kalahGame.getGameState();
        if (checkEmptySide(kalahGame.getGameBoard().getPits(),
                kalahGame.getPlayerOne().getSelectablePits())) {
            
            for (Integer id : kalahGame.getPlayerTwo().getSelectablePits()) {
                awardedStones += kalahGame.getGameBoard().getPits().get(id).getStoneBalance();
                kalahGame.getGameBoard().getPits().get(id).setStoneBalance(0);
            }
            kalahGame.getGameBoard().getPits().get(KalahGame.PLAYER_ONE_KALAH_ID).addToStoneBalance(awardedStones);
            finalState = GameState.FINISHED;
        } else if (checkEmptySide(kalahGame.getGameBoard().getPits(),
                kalahGame.getPlayerTwo().getSelectablePits())) {
    
            for (Integer id : kalahGame.getPlayerOne().getSelectablePits()) {
                awardedStones += kalahGame.getGameBoard().getPits().get(id).getStoneBalance();
                kalahGame.getGameBoard().getPits().get(id).setStoneBalance(0);
            }
            kalahGame.getGameBoard().getPits().get(KalahGame.PLAYER_TWO_KALAH_ID).addToStoneBalance(awardedStones);
            finalState = GameState.FINISHED;
        }
        if (finalState == GameState.FINISHED) {
            kalahGame.setWinner(
                    kalahGame.getGameBoard().getPits().get(KalahGame.PLAYER_ONE_KALAH_ID).getStoneBalance()
                            > kalahGame.getGameBoard().getPits().get(KalahGame.PLAYER_TWO_KALAH_ID).getStoneBalance()
                            ? kalahGame.getPlayerOne().getClass().getName()
                            :kalahGame.getPlayerTwo().getClass().getName());
        }
        return finalState;
    }
    
    private boolean checkEmptySide(List<Pit> pits, Set<Integer> side) {
        boolean result = true;
        for (Integer id : side) {
            result = result && pits.get(id).isEmpty();
        }
        return result;
    }
}
