package com.backbase.interview.kalahgame.serverside.dao.impl;

import com.backbase.interview.kalahgame.serverside.dao.api.IGame;
import com.backbase.interview.kalahgame.serverside.dao.api.IGameDao;
import com.backbase.interview.kalahgame.serverside.dao.entity.KalahGame;
import com.backbase.interview.kalahgame.serverside.dao.exception.GameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by M.jalian on 22/03/2022.
 */
@Component
public class KalahGameDaoImpl implements IGameDao {
    
    private Map<Long, IGame> repository = new HashMap<>();
    @Autowired()
    private Random random;
    
    @Override
    public long saveGame (IGame game) {
        KalahGame kalahGame = (KalahGame) game;
        long id = random.nextInt(Integer.MAX_VALUE);
        kalahGame.setId(id);
        repository.put(id, kalahGame);
        return id;
    }
    
    @Override
    public IGame findById (long id) {
        if (!repository.containsKey(id)) {
            throw new GameNotFoundException("not found game with id :" + id);
        }
        return repository.get(id);
    }
    
    @Override
    public IGame update (IGame game) {
        KalahGame kalahGame = (KalahGame) game;
        repository.put(kalahGame.getId(), kalahGame);
        return kalahGame;
    }
    
    public void setRandom (Random random) {
        this.random = random;
    }
}
