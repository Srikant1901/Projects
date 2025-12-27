package com.ParkingLot;

import com.ParkingLot.controllers.TokenController;
import com.ParkingLot.repositories.GateRepository;
import com.ParkingLot.repositories.ParkingLotRepository;
import com.ParkingLot.repositories.TokenRepository;
import com.ParkingLot.repositories.VehicleRepository;
import com.ParkingLot.services.TokenService;

public class Client {
	
	public static void main (String[] args) {
		ParkingLotRepository parkingLotRepository = new ParkingLotRepository();
		TokenRepository tokenRepository = new TokenRepository();
		VehicleRepository vehicleRepository = new VehicleRepository();
		GateRepository gateRepository = new GateRepository();
		TokenService tokenService = new TokenService(gateRepository, 
														vehicleRepository, 
														parkingLotRepository,
														tokenRepository);
		
		TokenController tokenController = new TokenController(tokenService);
//		tokenController.issueToken();
	}
	
}
