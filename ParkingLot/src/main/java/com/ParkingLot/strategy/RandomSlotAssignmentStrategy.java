package com.ParkingLot.strategy;

import java.util.List;

import com.ParkingLot.enums.ParkingSlotStatus;
import com.ParkingLot.enums.VehicleTypes;
import com.ParkingLot.models.Gate;
import com.ParkingLot.models.ParkingFloor;
import com.ParkingLot.models.ParkingLot;
import com.ParkingLot.models.ParkingSlot;
import com.ParkingLot.repositories.ParkingLotRepository;

public class RandomSlotAssignmentStrategy implements SlotAssignmentStrategy{
	
	private ParkingLotRepository parkingLotRepository;
	
	public RandomSlotAssignmentStrategy(ParkingLotRepository parkingLotRepository) {
		this.parkingLotRepository = parkingLotRepository;
	}
	
	@Override
	public ParkingSlot getSlot(Gate gate, VehicleTypes vehicleType) {
		//gate -> parkingLot
		//parkingLot -> floor
		//floor -> parkingSlot
		//slot->vehicleType 
		//then, give any random one then.
		
		ParkingLot p = parkingLotRepository.findParkingLotByGate(gate.getId());
		List<ParkingFloor> floorList = p.getParkingFloors();
		
		for(ParkingFloor pf: floorList) {
			for(ParkingSlot ps : pf.getParkingSlots()) {
				if(ps.getParkingSlotStatus().equals(ParkingSlotStatus.OPEN)  && ps.getVehicleTypes().equals(vehicleType)) {
					return ps;
				}
			}
		}
		return null;
	}

}
