package com.ParkingLot.repositories;

import java.util.Map;
import java.util.TreeMap;

import com.ParkingLot.models.Gate;
import com.ParkingLot.models.ParkingLot;

public class ParkingLotRepository {
	//mocking db--->
	private Map<Long,ParkingLot> parkingLots = new TreeMap<>();
	
	public ParkingLot findParkingLotByGate(int gateId){
		
		for(Map.Entry<Long,ParkingLot> p : parkingLots.entrySet()) {
			for(Gate g : p.getValue().getGates()) {
				if(g.getId() == gateId)
					return p.getValue();
			}
		}
		return null;
	}
}
