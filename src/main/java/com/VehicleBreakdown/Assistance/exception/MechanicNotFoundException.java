package com.VehicleBreakdown.Assistance.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class MechanicNotFoundException extends Exception{
	public MechanicNotFoundException() {}
	
	public MechanicNotFoundException(String msg)
	{
		super(msg);
	}
}
