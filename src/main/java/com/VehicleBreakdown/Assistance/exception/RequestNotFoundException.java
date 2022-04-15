package com.VehicleBreakdown.Assistance.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class RequestNotFoundException extends Exception {
	public RequestNotFoundException() {}
	public RequestNotFoundException(String msg)
	{
		super(msg);
	}
}
