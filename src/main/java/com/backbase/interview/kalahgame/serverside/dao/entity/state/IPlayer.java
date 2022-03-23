package com.backbase.interview.kalahgame.serverside.dao.entity.state;

import com.backbase.interview.kalahgame.serverside.dao.entity.Pit;

import java.util.List;
import java.util.Set;

/**
 * Created by M.jalian on 22/03/2022.
 */

public interface IPlayer {
    
    GameState makeMove(int id, List<Pit> pits);
    Set<Integer> getSelectablePits();
}
