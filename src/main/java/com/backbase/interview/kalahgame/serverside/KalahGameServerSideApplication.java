package com.backbase.interview.kalahgame.serverside;

import com.backbase.interview.kalahgame.serverside.dao.api.IGameDao;
import com.backbase.interview.kalahgame.serverside.dao.impl.KalahGameDaoImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Random;

@SpringBootApplication
public class KalahGameServerSideApplication {

	public static void main(String[] args) {
		SpringApplication.run(KalahGameServerSideApplication.class, args);
	}
	
	@Bean(name = "random")
	public Random getRandom(){
		return new Random();
	}
	@Bean()
	public IGameDao gameDao(){
		return new KalahGameDaoImpl();
	}
	
	
}
