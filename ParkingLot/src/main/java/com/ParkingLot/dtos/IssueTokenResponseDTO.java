package com.ParkingLot.dtos;

import com.ParkingLot.enums.ResponseStatus;
import com.ParkingLot.models.Token;

public class IssueTokenResponseDTO {
	private Token token;
	
	private ResponseStatus responseStatus;
	
	private String failureMessage;

	public Token getToken() {
		return token;
	}

	public void setToken(Token token) {
		this.token = token;
	}

	public ResponseStatus getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(ResponseStatus responseStatus) {
		this.responseStatus = responseStatus;
	}

	public String getFailureMessage() {
		return failureMessage;
	}

	public void setFailureMessage(String failureMessage) {
		this.failureMessage = failureMessage;
	}
	
	
}
