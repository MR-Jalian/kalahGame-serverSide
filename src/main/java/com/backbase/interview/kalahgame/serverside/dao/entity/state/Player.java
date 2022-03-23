package com.backbase.interview.kalahgame.serverside.dao.entity.state;

import com.backbase.interview.kalahgame.serverside.dao.entity.Pit;

import java.util.List;
import java.util.Set;

/**
 * Created by M.jalian on 23/03/2022.
 */

public abstract class Player implements IPlayer {
    
    
    protected GameState makeMove(int selectedPit, List<Pit> pits, Set<Integer> selectablePits, int ownKalahId, int oppositeSideKalahId, GameState ownTurn, GameState oppositeSideTurn) {
        
        int stoneBalance = pits.get(selectedPit).getStoneBalance();
        int startIndex = selectedPit ;
        int endIndex = selectedPit + stoneBalance%14;
        int modifyingPitId;
        int lastModifyingPitId = 0;
        
        
        pits.get(selectedPit).setStoneBalance(0);
        for (int i = startIndex;i<endIndex ; i++) {
            modifyingPitId = (i%14)+1;
            if (modifyingPitId == oppositeSideKalahId) {
                endIndex++;
                continue;
            }
            pits.get(modifyingPitId).addToStoneBalance(1);
            lastModifyingPitId = modifyingPitId;
        }
        if (lastModifyingPitId == ownKalahId) {
            return ownTurn;
        } else if (pits.get(lastModifyingPitId).getStoneBalance() == 1 &&
                           selectablePits.contains(lastModifyingPitId)) {
            int oppositeSidePitId = 14 - lastModifyingPitId;
            if (!pits.get(oppositeSidePitId).isEmpty()) {
                int awardedStones = pits.get(lastModifyingPitId).getStoneBalance() +
                                            pits.get(oppositeSidePitId).getStoneBalance();
                pits.get(ownKalahId).addToStoneBalance(awardedStones);
                pits.get(lastModifyingPitId).setStoneBalance(0);
                pits.get(oppositeSidePitId).setStoneBalance(0);
                return oppositeSideTurn;
            } else {
                return oppositeSideTurn;
            }
        } else {
            return oppositeSideTurn;
        }
    }
}
