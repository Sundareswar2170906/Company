package com.stockmarket.fse.exceptions;

import java.util.Date;
import java.util.NoSuchElementException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.stockmarket.fse.model.ExceptionModel;

public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionModel exception = new ExceptionModel();
		exception.setDate(new Date());
		exception.setMessage("Change Method to GET");
		return new ResponseEntity<>(exception, HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<ExceptionModel> handleNullPointerException(Exception e) {
		ExceptionModel exception = new ExceptionModel();
		exception.setDate(new Date());
		exception.setMessage("Request Item could not be processed. Make sure the item is present in record.");
	      return new ResponseEntity<ExceptionModel>(exception,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(IllegalTurnoverException.class)
	public ResponseEntity<ExceptionModel> handleIllegalTurnoverException(IllegalTurnoverException e) {
		ExceptionModel exception = new ExceptionModel();
		exception.setDate(new Date());
		exception.setMessage(e.getMessage());
	      return new ResponseEntity<ExceptionModel>(exception,HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<ExceptionModel> handleNoSuchElementException(NoSuchElementException e) {
		ExceptionModel exception = new ExceptionModel();
		exception.setDate(new Date());
		exception.setMessage("Requested Company Not Found");
	      return new ResponseEntity<ExceptionModel>(exception,HttpStatus.BAD_REQUEST);
	}
}


