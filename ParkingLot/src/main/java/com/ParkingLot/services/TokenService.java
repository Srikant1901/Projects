package com.ParkingLot.services;

import java.util.Date;
import java.util.Optional;

import com.ParkingLot.enums.ParkingSlotStatus;
import com.ParkingLot.enums.SlotAssignmentStrategyType;
import com.ParkingLot.enums.VehicleTypes;
import com.ParkingLot.models.Gate;
import com.ParkingLot.models.ParkingLot;
import com.ParkingLot.models.ParkingSlot;
import com.ParkingLot.models.Token;
import com.ParkingLot.models.Vehicle;
import com.ParkingLot.repositories.GateRepository;
import com.ParkingLot.repositories.ParkingLotRepository;
import com.ParkingLot.repositories.TokenRepository;
import com.ParkingLot.repositories.VehicleRepository;
import com.ParkingLot.strategy.SlotAssignmentStrategyFactory;

public class TokenService {
	
	private GateRepository gateRepository;
	private VehicleRepository vehicleRepository;
	private ParkingLotRepository parkingLotRepository;
	private TokenRepository tokenRepository;
	
	public TokenService(GateRepository gateRepository, 
			VehicleRepository vehicleRepository, 
			ParkingLotRepository parkingLotRepository,
			TokenRepository tokenRepository){
		this.gateRepository = gateRepository;
		this.vehicleRepository = vehicleRepository;
		this.parkingLotRepository = parkingLotRepository;
		this.tokenRepository = tokenRepository;
	}
	
	public Token issueToken(
			String vehicleNumber,
			String vehicleOwnerName,
			Long gateId,
			VehicleTypes vehicleType) {
		
		//1.create token object
		Token token = new Token();
		token.setEntryTime(new Date());
		Optional<Gate> gate = gateRepository.findGateById(gateId);
		if(gate.isEmpty()) {
			throw new RuntimeException();
		}
		token.setGeneratedAt(gate.get());
		token.setGeneratedBy(gate.get().getCurrentOperator());
		Vehicle savedVehicle;
		Optional<Vehicle> vehicleOptional = vehicleRepository.findVehicleByNumber(vehicleNumber);
		if(vehicleOptional.isEmpty()) {
			Vehicle vehicle = new Vehicle();
			vehicle.setOwnerName(vehicleOwnerName);
			vehicle.setVehicleNumber(vehicleNumber);
			vehicle.setVehicleType(vehicleType);
			savedVehicle = vehicleRepository.saveVehicle(vehicle);
		}else {
			savedVehicle = vehicleOptional.get();
		}
		token.setVehicle(savedVehicle);
		
		
		//2.assign a spot
		ParkingLot parkingLot = parkingLotRepository.findParkingLotByGate(gateId.intValue());
		SlotAssignmentStrategyType slotAssignmentStrategyType = parkingLot.getSlotAssignmentStrategyType();
		
		ParkingSlot parkingSlot = SlotAssignmentStrategyFactory.getSlotAssignmentStrategyFactoryByType(slotAssignmentStrategyType).getSlot(gate.get(), vehicleType);
		token.setParkingSlot(parkingSlot);
		parkingSlot.setParkingSlotStatus(ParkingSlotStatus.FILLED);
		Token savedToken = tokenRepository.saveToken(token);
		savedToken.setTokenNumber("TOKEN - " + savedToken.getId());
		
		//3.return token
		return savedToken;
		
	}
}

//2.complete client and make the proper call by first ingesting some data
//3.complete random slot strategy
//4.give an attempt to generateBill
