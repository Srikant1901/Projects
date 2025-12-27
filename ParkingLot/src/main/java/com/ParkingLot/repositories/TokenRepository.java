package com.ParkingLot.repositories;

import java.util.Map;
import java.util.TreeMap;

import com.ParkingLot.models.Token;

public class TokenRepository {
	private Map<Long,Token> tokens = new TreeMap<>();
	private int previousId = 0;
	public Token saveToken(Token token) {
		//save to db;
		previousId +=1;
		tokens.put((long)previousId, token);
		return token;
	}
	

}
