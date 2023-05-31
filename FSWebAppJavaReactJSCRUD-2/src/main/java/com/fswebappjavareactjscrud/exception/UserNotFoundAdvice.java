package com.fswebappjavareactjscrud.exception;

import java.util.Map;
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UserNotFoundAdvice {

	@ResponseBody
	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Map<String,String> exceptionHandler(UserNotFoundException exception){
		Map<String,String>  errorMap=new HashMap<>();
		errorMap.put("errorMessage",exception.getMessage());
		return errorMap;
	}
	
	
//	@ExceptionHandler(UserNotFoundException.class)
// 	@ResponseStatus(HttpStatus.NOT_FOUND)
//	public ResponseEntity<String> handleException(UserNotFoundException ex){
//		return ResponseEntity.body("An error occured: "+ ex.getMessage());
//		
//	}
}
