package com.ParkingLot.strategy;

import com.ParkingLot.enums.SlotAssignmentStrategyType;

public class SlotAssignmentStrategyFactory {
	public static SlotAssignmentStrategy getSlotAssignmentStrategyFactoryByType(SlotAssignmentStrategyType slotAssignmentStrategyType) {
		//based on selected type , we need to give the object of that strategy type class returned ----> Factory Design
		return new RandomSlotAssignmentStrategy(); //for now random one harcodedly sent
	}
}
