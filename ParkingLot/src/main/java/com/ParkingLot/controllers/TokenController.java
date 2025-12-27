package com.ParkingLot.controllers;

import com.ParkingLot.dtos.IssueTokenRequestDto;
import com.ParkingLot.dtos.IssueTokenResponseDTO;
import com.ParkingLot.enums.ResponseStatus;
import com.ParkingLot.models.Token;
import com.ParkingLot.services.TokenService;


public class TokenController {
	
	private TokenService tokenService;
	
	public TokenController(TokenService tokenService){
		this.tokenService = tokenService;
	}
	
	public IssueTokenResponseDTO issueToken(IssueTokenRequestDto issueTokenRequestDTO) {
		IssueTokenResponseDTO issueTokenResponseDTO = new IssueTokenResponseDTO();
		try {
			Token token = tokenService.issueToken(
					issueTokenRequestDTO.getVehicleNumber(),
					issueTokenRequestDTO.getVehicleOwnerName(),
					issueTokenRequestDTO.getGateId(),
					issueTokenRequestDTO.getVehicleType()
				);
			
			issueTokenResponseDTO.setToken(token);
			issueTokenResponseDTO.setResponseStatus(ResponseStatus.SUCCESS);
		} catch (Exception e) {
			issueTokenResponseDTO.setToken(null);
			issueTokenResponseDTO.setFailureMessage(e.getLocalizedMessage());
			issueTokenResponseDTO.setResponseStatus(ResponseStatus.FAILURE);
		}
		return issueTokenResponseDTO;
	}
}
