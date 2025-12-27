package com.ParkingLot.models;

import com.ParkingLot.enums.ParkingSlotStatus;
import com.ParkingLot.enums.VehicleTypes;

public class ParkingSlot  extends BaseModel{
	private int slotNumber;
	
	private VehicleTypes vehicleTypes;
	
	private ParkingSlotStatus parkingSlotStatus;
	
	private ParkingFloor parkingFloor;

	public int getSlotNumber() {
		return slotNumber;
	}

	public void setSlotNumber(int slotNumber) {
		this.slotNumber = slotNumber;
	}

	public VehicleTypes getVehicleTypes() {
		return vehicleTypes;
	}

	public void setVehicleTypes(VehicleTypes vehicleTypes) {
		this.vehicleTypes = vehicleTypes;
	}

	public ParkingFloor getParkingFloor() {
		return parkingFloor;
	}

	public void setParkingFloor(ParkingFloor parkingFloor) {
		this.parkingFloor = parkingFloor;
	}

	public ParkingSlotStatus getParkingSlotStatus() {
		return parkingSlotStatus;
	}

	public void setParkingSlotStatus(ParkingSlotStatus parkingSlotStatus) {
		this.parkingSlotStatus = parkingSlotStatus;
	}
	
	
	
	
}
