package com.ParkingLot.repositories;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import com.ParkingLot.models.Gate;

public class GateRepository {
	//hardcoding and mocking db here--->
	private Map<Long,Gate> gates = new TreeMap<>();
	
	public Optional<Gate> findGateById(Long gateId) {
		if(gates.containsKey(gateId)) {
			return Optional.of(gates.get(gateId));
		}
		return Optional.empty();
	}
}
