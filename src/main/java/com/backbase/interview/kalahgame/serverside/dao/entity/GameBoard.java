package com.backbase.interview.kalahgame.serverside.dao.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by M.jalian on 21/03/2022.
 */

public class GameBoard {
    
    @Value("${primary.stones.number}")
    private static int primaryStonesNumber = 6;
    private List<Pit> pits = new ArrayList<>();
    @Autowired(required = true)
    private ObjectMapper objectMapper = new JsonMapper();
    private Map<Integer,String> jsonMap ;
    
    public GameBoard () {
        pits.add(null);
        for (int i = 1; i<= 14; i++) {
            pits.add(i,new Pit(i, primaryStonesNumber));
        }
    }
    
    public List<Pit> getPits () {
        return pits;
    }
    public String getStatus() {
        
        
        try {
            
            jsonMap = new HashMap<>();
            for (int i=1; i<pits.size(); i++) {
                jsonMap.put(pits.get(i).getId(), String.valueOf(pits.get(i).getStoneBalance()));
                
            }
            return objectMapper.writeValueAsString(jsonMap);
        } catch (JsonProcessingException e) {
            throw  new RuntimeException(e);
        }
    }
    
    public static void setPrimaryStonesNumber (int primaryStonesNumber) {
        GameBoard.primaryStonesNumber = primaryStonesNumber;
    }
    
    public void setObjectMapper (ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
}
