package com.backbase.interview.kalahgame.serverside.dao.api;

/**
 * Created by M.jalian on 21/03/2022.
 */

public interface IGameDao {

    long saveGame(IGame game);
    IGame findById(long id);
    IGame update(IGame game);
}
