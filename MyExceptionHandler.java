package com.wb.globalhandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.wb.exception.ListNotPresentException;
import com.wb.exception.OperationHistoryListEmptyexception;
import com.wb.exception.AdmitListEmptyException;
import com.wb.exception.EmptyListException;
import com.wb.exception.ListEmptyException;
import com.wb.exception.ListNotFoundException;

@ControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler
{
	Logger log = LoggerFactory.getLogger(MyExceptionHandler.class);
	@ExceptionHandler(ListNotFoundException.class)
	public ResponseEntity<String> ListNotFoundException()
    {
		log.error("ListNotFoundException::Please check");
		return new ResponseEntity<String>("Empty List",HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(EmptyListException.class)
	public ResponseEntity<String> EmptyListException()
	{
		log.warn("ListNotFoundException::Empty List please check");
		return new  ResponseEntity<String>("List inserted is empty",HttpStatus.BAD_GATEWAY);
	}
	
	@ExceptionHandler(ListNotPresentException.class)
	public ResponseEntity<String> ListNotPresentException()
	{
		return new  ResponseEntity<String>("List does not contain any content",HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ListEmptyException.class)
	public ResponseEntity<String> ListEmptyException()
	{
		return new ResponseEntity<String>("Insert elememts in list",HttpStatus.NOT_IMPLEMENTED);
	}
	
	@ExceptionHandler(AdmitListEmptyException.class)
	public ResponseEntity<String> AdmitListEmptyException()
	{
		return new ResponseEntity<String>("Admited List Empty",HttpStatus.GONE);
	}
	
	@ExceptionHandler(OperationHistoryListEmptyexception.class)
	public ResponseEntity<String> OperationHistoryListEmptyexception()
	{
		return new ResponseEntity<String>("Operation History List Empty",HttpStatus.BAD_REQUEST);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) 
	{
		return new ResponseEntity<Object>("Method Not Supported",HttpStatus.METHOD_NOT_ALLOWED);
	}
}
