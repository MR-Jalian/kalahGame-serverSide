package com.backbase.interview.kalahgame.serverside.service.api;

import com.fasterxml.jackson.annotation.JsonFilter;

/**
 * Created by M.jalian on 23/03/2022.
 */
@JsonFilter("customFilter")
public class Response {
    
    String id;
    String uri;
    String status;
    
    public Response (String id, String uri, String status) {
        this.id = id;
        this.uri = uri;
        this.status = status;
    }
    
    public Response (String id, String uri) {
        this.id = id;
        this.uri = uri;
    }
    
    public String getId () {
        return id;
    }
    
    public void setId (String id) {
        this.id = id;
    }
    
    public String getUri () {
        return uri;
    }
    
    public void setUri (String uri) {
        this.uri = uri;
    }
    
    public String getStatus () {
        return status;
    }
    
    public void setStatus (String status) {
        this.status = status;
    }
}
