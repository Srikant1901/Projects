package com.ParkingLot.strategy;

import com.ParkingLot.enums.VehicleTypes;
import com.ParkingLot.models.Gate;
import com.ParkingLot.models.ParkingSlot;

public interface SlotAssignmentStrategy {
	
	public ParkingSlot getSlot(Gate gate,VehicleTypes vehicleType);
}
