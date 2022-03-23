package com.backbase.interview.kalahgame.serverside.dao.entity;

/**
 * Created by M.jalian on 21/03/2022.
 */

public class Pit {
    
    private int id;
    private int stoneBalance;
    
    public Pit (int id, int stoneBalance) {
        this.id = id;
        this.stoneBalance = stoneBalance;
    }
    
    public int getId () {
        return id;
    }
    
    public void setId (int id) {
        this.id = id;
    }
    
    public int getStoneBalance () {
        return stoneBalance;
    }
    
    public void addToStoneBalance (int stoneBalance) {
        this.stoneBalance = this.stoneBalance + stoneBalance;
    }
    public void setStoneBalance (int stoneBalance) {
        this.stoneBalance = stoneBalance;
    }
    public boolean isEmpty() {
        return stoneBalance == 0;
    }
    
    @Override
    public String toString () {
        return id + ":" + stoneBalance;
    }
}
