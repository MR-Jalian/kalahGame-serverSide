package com.backbase.interview.kalahGame.serverSide;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * Created by M.jalian on 24/03/2022.
 */
@Service
public class TestConfig {
    
    @Bean
    TestRestTemplate testRestTemplate(){
        return new TestRestTemplate();
    }
}
