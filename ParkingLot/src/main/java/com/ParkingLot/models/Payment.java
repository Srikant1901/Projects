package com.ParkingLot.models;

import com.ParkingLot.enums.PaymentMode;
import com.ParkingLot.enums.PaymentStatus;

public class Payment extends BaseModel{
	private int amount;
	
	private PaymentMode paymentMode;
	
	private String refId;
	
	private Bill bill;
	
	private PaymentStatus paymentStatus;
	
	
}
