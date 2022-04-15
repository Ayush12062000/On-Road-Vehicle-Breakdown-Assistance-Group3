package com.VehicleBreakdown.Assistance.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class FeedbackNotFoundException extends Exception{
	public FeedbackNotFoundException() {}
	public FeedbackNotFoundException(String msg)
	{
		super(msg);
	}
}
