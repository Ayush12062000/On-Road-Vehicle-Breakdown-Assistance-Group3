package com.VehicleBreakdown.Assistance.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class BlockByAdminException extends Exception{
	public BlockByAdminException() {}
	public BlockByAdminException(String msg)
	{
		super(msg);
	}

}
