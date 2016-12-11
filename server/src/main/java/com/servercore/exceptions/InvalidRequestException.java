package com.servercore.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Invalid Request") 
public class InvalidRequestException extends Exception{
	
	public InvalidRequestException() {
		super("Invalid Request");
	}
	
}
