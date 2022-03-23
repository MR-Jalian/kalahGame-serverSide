package com.backbase.interview.kalahgame.serverside.dao.entity.state;

import com.backbase.interview.kalahgame.serverside.dao.entity.KalahGame;
import com.backbase.interview.kalahgame.serverside.dao.entity.Pit;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by M.jalian on 22/03/2022.
 */

public class PlayerTwo extends Player {
    
    private Set<Integer> selectablePits =
            Stream.of(8, 9, 10, 11, 12, 13)
                    .collect(Collectors.toCollection(HashSet::new));
    
    @Override
    public GameState makeMove (int id, List<Pit> pits) {
    
        return super.makeMove(id, pits, selectablePits,
                KalahGame.PLAYER_TWO_KALAH_ID, KalahGame.PLAYER_ONE_KALAH_ID,
                GameState.PLAYER_TWO_TURN, GameState.PLAYER_ONE_TURN);
    }
    
    @Override
    public Set<Integer> getSelectablePits () {
        return selectablePits;
    }
}
