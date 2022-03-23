package com.backbase.interview.kalahgame.serverside.service;

import com.backbase.interview.kalahgame.serverside.game.logic.KalahGameController;
import com.backbase.interview.kalahgame.serverside.service.api.Response;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

/**
 * Created by M.jalian on 23/03/2022.
 */

@RestController
public class KalahResourse {
    
    @Autowired(required = true)
    private KalahGameController gameController;
    
    @PostMapping("/games")
    public ResponseEntity<MappingJacksonValue> createGame() {
        
        long gameId = gameController.createGame();
        URI uri = ServletUriComponentsBuilder
                          .fromCurrentRequest()
                          .path("/{id}")
                          .buildAndExpand(gameId).toUri();
    
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "uri");
        FilterProvider filters = new SimpleFilterProvider().addFilter("customFilter", filter);
    
    
        Response response = new Response(String.valueOf(gameId), uri.toString());
        MappingJacksonValue mapping = new MappingJacksonValue(response);
        mapping.setFilters(filters);
        return ResponseEntity.created(uri).body(mapping);
    }
    
    @PutMapping("/games/{gameId}/pits/{pitId}")
    public ResponseEntity<Object> makeMove(@PathVariable int gameId,
                                           @PathVariable int pitId) {
        
        String status = gameController.makeMove(gameId, pitId);
        URI uri = ServletUriComponentsBuilder
                          .fromCurrentContextPath().path("/games/{id}")
                          .buildAndExpand(gameId).toUri();
        Response response = new Response(String.valueOf(gameId),uri.toString(),status);
    
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "uri", "status");
        FilterProvider filters = new SimpleFilterProvider().addFilter("customFilter", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(response);
        mapping.setFilters(filters);
        
        return ResponseEntity.ok().body(mapping);
    }
    
    
}
