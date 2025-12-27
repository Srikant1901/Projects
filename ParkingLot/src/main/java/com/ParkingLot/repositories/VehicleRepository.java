package com.ParkingLot.repositories;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import com.ParkingLot.models.Vehicle;

public class VehicleRepository {
	private Map<String, Vehicle> vehicleMap = new TreeMap<>();
	
	public Optional<Vehicle> findVehicleByNumber(String vehicleNumber){
		for(Map.Entry<String, Vehicle> v: vehicleMap.entrySet()) {
			String vehicleNo = v.getValue().getVehicleNumber();
			if(vehicleNo.equals(vehicleNumber))
				return Optional.of(v.getValue());
		}
		return Optional.empty();
	}
	
	public Vehicle saveVehicle(Vehicle vehicle) {
		//----
		vehicleMap.put(vehicle.getVehicleNumber(), vehicle);
		return vehicle;
	}
}
